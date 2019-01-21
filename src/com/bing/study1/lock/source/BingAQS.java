package com.bing.study1.lock.source;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

// �������ͬ����
// state�� owner�� waiters
public class BingAQS {
    // acquire�� acquireShared �� ��������Դ���õ��߼������û�õ�����ȴ���
    // tryAcquire�� tryAcquireShared �� ʵ��ִ��ռ����Դ�Ĳ���������ж�һ����ʹ���߾���ȥʵ�֡�
    // release�� releaseShared �� �����ͷ���Դ���߼����ͷ�֮��֪ͨ�����ڵ����������
    // tryRelease�� tryReleaseShared�� ʵ��ִ����Դ�ͷŵĲ����������AQSʹ����ȥʵ�֡�

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
