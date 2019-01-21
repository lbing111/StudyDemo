package com.bing.study1.lock.source;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class BingReentrantLockSample {
    LinkedBlockingQueue<Thread> waiter = new LinkedBlockingQueue();
    volatile AtomicReference<Thread> owner = new AtomicReference();

    volatile AtomicInteger count = new AtomicInteger(0);

    public void lock(){
        boolean inQueue = true;
        Thread thread = Thread.currentThread();

        while (!tryLock()){
            if(inQueue){
                waiter.add(thread);
                inQueue = false;
            }else{
                LockSupport.park();
            }
        }
        waiter.remove(thread);
    }

    public void unlock(){
        tryUnlock();
    }

    public boolean tryLock(){
        return owner.compareAndSet(null, Thread.currentThread());
    }

    public void tryUnlock(){
        if(owner.compareAndSet(Thread.currentThread(),null)){
            Iterator<Thread> iterator = waiter.iterator();

            while (iterator.hasNext()){
                Thread thread = iterator.next();
                LockSupport.unpark(thread);
            }
        }
    }

    public int getHoldCount(){
        return 1;
    }
}
