package ru.nk.training.DataStructures.BlockingQueue;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<E> implements BlockingQueue<E> {
    private final E[] array;
    private int start;
    private int size;
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();

    public ArrayBlockingQueue(Class<E> cls, int capacity) {
        @SuppressWarnings("unchecked")
        final E[] array = (E[]) Array.newInstance(cls, capacity);
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
        if (e == null) {
            throw new NullPointerException();
        }
        if (size < capacity) {
            lock.lock();
            try {
                if (size < capacity) {
                    int i = (start + size) % capacity;
                    if (array[i] != null) {
                        throw new IllegalStateException();
                    }
                    array[i] = e;
                    size++;
                    return true;
                } else {
                    return false;
                }
            } finally {
                lock.unlock();
            }
        } else {
            return false;
        }
    }

    @Override
    public void waitingAdd(E e) throws InterruptedException {
        while (!tryAdd(e)) {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            Thread.yield();
        }
    }

    @Override
    public boolean waitingAdd(E e, long timeout, TimeUnit unit) throws InterruptedException {
        final long timeEnd = System.currentTimeMillis() + unit.toMillis(timeout);

        while (timeEnd >= System.currentTimeMillis()) {
            if (tryAdd(e)) {
                return true;
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            Thread.yield();
        }
        return false;
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
        if (size > 0) {
            lock.lock();
            try {
                if (size > 0) {
                    E e = array[start];
                    array[start] = null;
                    start = (start + 1) % capacity;
                    size--;
                    return e;
                } else {
                    return null;
                }
            } finally {
                lock.unlock();
            }
        } else {
            return null;
        }
    }

    @Override
    public E waitingRemove() throws InterruptedException {
        E e;
        while ((e = tryRemove()) == null) {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            Thread.yield();
        }
        return e;
    }

    @Override
    public E waitingRemove(long timeout, TimeUnit unit) throws InterruptedException {
        final long timeEnd = System.currentTimeMillis() + unit.toMillis(timeout);

        while (timeEnd >= System.currentTimeMillis()) {
            E e = tryRemove();
            if (e != null) {
                return e;
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            Thread.yield();
        }
        return null;
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
}
