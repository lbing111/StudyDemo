package com.bing.study1.threadpool.source;

import sun.misc.Unsafe;

import java.util.concurrent.*;

public class LbingFuturnTask implements Runnable, Future {

    private volatile int state;
    private static final int NEW          = 0;
    private static final int COMPLETING   = 1;
    private static final int NORMAL       = 2;
    private static final int EXCEPTIONAL  = 3;
    private static final int CANCELLED    = 4;
    private static final int INTERRUPTING = 5;
    private static final int INTERRUPTED  = 6;

    private static final Unsafe UNSAFE;
    private static final Long stateOffset;

    static {
        try {
            UNSAFE = Unsafe.getUnsafe();
            Class<?> k = LbingFuturnTask.class;
            stateOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    Callable callable;

    public LbingFuturnTask(Callable call) {
        this.callable = call;
    }


    @Override
    public void run() {
        try {
            this.callable.call();
        } catch (Exception e) {

        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
