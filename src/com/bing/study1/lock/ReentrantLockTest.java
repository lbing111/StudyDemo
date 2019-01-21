package com.bing.study1.lock;

import com.bing.study1.lock.source.BingAQS;
import com.bing.study1.lock.source.BingReentrantLock;
import com.bing.study1.lock.source.BingReentrantLockSample;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    BingReentrantLock lock = new BingReentrantLock();
    ReentrantLock trantLock = new ReentrantLock();
    volatile int count = 0;

    public static void main(String[] args) {

        ReentrantLockTest test = new ReentrantLockTest();
        new Thread(()->{
            test.outLock();
        }).start();

        new Thread(()->{
            test.outLock();
        }).start();

        new Thread(()->{
            test.outLock();
        }).start();

        test.outLock();
    }

    public void outLock(){
        try {
            System.out.println(Thread.currentThread().getName() + ": 开始取锁锁 001");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": 获取到锁 001, 共有锁" + lock.getHoldCount());
            Thread.sleep(300);
            innerLock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+": 释放了锁 001");
        }
    }

    public void innerLock(){
        try {
            System.out.println(Thread.currentThread().getName()+": 开始取锁锁 002");
            lock.lock();
            Thread.sleep(300);
            for(int i = 0; i<10000; i++){
                count++;
            }
            System.out.println(Thread.currentThread().getName()+": 获取到锁 002, 共有锁"+lock.getHoldCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+": 释放了锁 002");
            System.out.println(Thread.currentThread().getName()+"------count:"+count);
        }
    }
}
