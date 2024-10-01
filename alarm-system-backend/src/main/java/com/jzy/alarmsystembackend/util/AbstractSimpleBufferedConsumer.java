package com.jzy.alarmsystembackend.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Slf4j
public abstract class AbstractSimpleBufferedConsumer<T> implements IBufferedConsumer<T> {

    private Queue<T> _queue;
    private int _maxSize;

    Lock lock = new ReentrantLock();
    Consumer<Throwable> applendErrorHandle;

    BiConsumer<T, Throwable> flushErrorHandle;

    private static AbstractSimpleBufferedConsumer abstractSimpleBufferedConsumer;

    public static AbstractSimpleBufferedConsumer getAbstractSimpleBufferedConsumer() {
        return abstractSimpleBufferedConsumer;
    }

    public AbstractSimpleBufferedConsumer(Queue<T> _queue, int _maxSize) {
        this._queue = _queue;
        this._maxSize = _maxSize;
        abstractSimpleBufferedConsumer = this;
    }

    public AbstractSimpleBufferedConsumer(int _maxSize) {
        this._maxSize = _maxSize;
        this._queue = new LinkedList<>();
        abstractSimpleBufferedConsumer = this;
    }

    public Consumer<Throwable> getApplendErrorHandle() {
        return applendErrorHandle;
    }

    public AbstractSimpleBufferedConsumer<T> setApplendErrorHandle(Consumer<Throwable> applendErrorHandle) {
        this.applendErrorHandle = applendErrorHandle;
        return this;
    }

    public BiConsumer<T, Throwable> getFlushErrorHandle() {
        return flushErrorHandle;
    }

    public AbstractSimpleBufferedConsumer<T> setFlushErrorHandle(BiConsumer<T, Throwable> flushErrorHandle) {
        this.flushErrorHandle = flushErrorHandle;
        return this;
    }

    @Override
    public void add(T t) {
        lock.lock();
        try {
            if (getBufferedCount() < getBufferSize()) {
                _queue.add(t);
            } else {
                AbstractSimpleBufferedConsumer.getAbstractSimpleBufferedConsumer().flush();
            }
        } catch (Exception e) {
            if (applendErrorHandle != null) {
                applendErrorHandle.accept(e);
            }
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    @Override
    public void flush() {
        lock.lock();
        try {
            while (!_queue.isEmpty()) {
                T t = _queue.poll();
                try {
                    accept(t);
                } catch (Exception e) {
                    if (flushErrorHandle != null) {
                        flushErrorHandle.accept(t, e);
                    }
                }
            }
        } finally {
            lock.unlock();
        }

    }

    @Override
    public long getBufferSize() {
        return _maxSize;
    }

    @Override
    public long getBufferedCount() {
        return _queue.size();
    }

}
