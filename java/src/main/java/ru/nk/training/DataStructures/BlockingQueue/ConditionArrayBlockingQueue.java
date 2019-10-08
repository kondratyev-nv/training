package ru.nk.training.DataStructures.BlockingQueue;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionArrayBlockingQueue<E> implements BlockingQueue<E> {
    private final E[] array;
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private int start;
    private int size;

    public ConditionArrayBlockingQueue(Class<E> cls, int capacity) {
        @SuppressWarnings("unchecked") final E[] array = (E[]) Array.newInstance(cls, capacity);
        this.array = array;
        this.capacity = capacity;
        this.start = 0;
        this.size = 0;
    }

    @Override
    public void add(E e) {
        if (!tryAdd(e)) {
            throw new IllegalStateException();
        }
    }

    @Override
    public boolean tryAdd(E e) {
        ensureNotNull(e);
        if (size >= capacity) {
            return false;
        }
        lock.lock();
        try {
            if (size >= capacity) {
                return false;
            }
            addInternal(e);
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void waitingAdd(E e) throws InterruptedException {
        ensureNotNull(e);
        lock.lockInterruptibly();
        try {
            while (true) {
                if (size < capacity) {
                    addInternal(e);
                    return;
                }
                notFull.await();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean waitingAdd(E e, long timeout, TimeUnit unit) throws InterruptedException {
        ensureNotNull(e);
        final long timeEnd = System.currentTimeMillis() + unit.toMillis(timeout);
        lock.lockInterruptibly();
        try {
            while (timeEnd >= System.currentTimeMillis()) {
                if (size < capacity) {
                    addInternal(e);
                    return true;
                }
                notFull.await(timeEnd - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E remove() {
        E e = tryRemove();
        if (e == null) {
            throw new NoSuchElementException();
        }
        return e;
    }

    @Override
    public E tryRemove() {
        if (size <= 0) {
            return null;
        }
        lock.lock();
        try {
            if (size <= 0) {
                return null;
            }
            return removeInternal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E waitingRemove() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (true) {
                if (size > 0) {
                    return removeInternal();
                }
                notEmpty.await();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E waitingRemove(long timeout, TimeUnit unit) throws InterruptedException {
        final long timeEnd = System.currentTimeMillis() + unit.toMillis(timeout);
        lock.lockInterruptibly();
        try {
            while (timeEnd >= System.currentTimeMillis()) {
                if (size > 0) {
                    return removeInternal();
                }
                notEmpty.await(timeEnd - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }

    private E removeInternal() {
        E e = array[start];
        array[start] = null;
        start = (start + 1) % capacity;
        size--;
        notFull.signal();
        return e;
    }

    private void ensureNotNull(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
    }

    private void addInternal(E e) {
        int i = (start + size) % capacity;
        if (array[i] != null) {
            throw new IllegalStateException();
        }
        array[i] = e;
        size++;
        notEmpty.signal();
    }
}
