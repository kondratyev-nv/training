package ru.nk.training.DataStructures.BlockingQueue;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedLinkedBlockingQueue<E> implements BlockingQueue<E> {
    private final LinkedList<E> list = new LinkedList<>();
    private final int maxSize;
    private final ReentrantLock lock = new ReentrantLock();

    public BoundedLinkedBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public BoundedLinkedBlockingQueue() {
        this(Integer.MAX_VALUE);
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
        if (list.size() < maxSize) {
            lock.lock();
            try {
                if (list.size() < maxSize) {
                    list.add(e);
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
        if (!list.isEmpty()) {
            lock.lock();
            try {
                if (!list.isEmpty()) {
                    return list.removeFirst();
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
            return list.size();
        } finally {
            lock.unlock();
        }
    }
}
