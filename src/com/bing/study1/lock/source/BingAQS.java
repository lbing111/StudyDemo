package com.bing.study1.lock.source;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

// 抽象队列同步器
// state， owner， waiters
public class BingAQS {
    // acquire、 acquireShared ： 定义了资源争用的逻辑，如果没拿到，则等待。
    // tryAcquire、 tryAcquireShared ： 实际执行占用资源的操作，如何判定一个由使用者具体去实现。
    // release、 releaseShared ： 定义释放资源的逻辑，释放之后，通知后续节点进行争抢。
    // tryRelease、 tryReleaseShared： 实际执行资源释放的操作，具体的AQS使用者去实现。

    LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue();
    public volatile AtomicReference<Thread> owner = new AtomicReference();
    private volatile int state = 0;

    public void acquire () {
        boolean inQueue = true;
        Thread thread = Thread.currentThread();

        while (!tryAcquire()){
            if(inQueue){
                waiters.add(thread);
                inQueue = false;
            }else{
                LockSupport.park();
            }
        }
        waiters.remove(thread);
    }

    public void release (){
        if(tryRelease()){
            Iterator<Thread> iterator = waiters.iterator();

            while (iterator.hasNext()){
                Thread thread = iterator.next();
                LockSupport.unpark(thread);
            }
        }
    }

    public boolean tryAcquire(){
        throw new UnsupportedOperationException();
    }

    public boolean tryRelease(){
        throw new UnsupportedOperationException();
    }

    public boolean isCurrent(){
        return Thread.currentThread() == owner.get();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
