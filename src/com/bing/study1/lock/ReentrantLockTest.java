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
            System.out.println(Thread.currentThread().getName() + ": ��ʼȡ���� 001");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": ��ȡ���� 001, ������" + lock.getHoldCount());
            Thread.sleep(300);
            innerLock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+": �ͷ����� 001");
        }
    }

    public void innerLock(){
        try {
            System.out.println(Thread.currentThread().getName()+": ��ʼȡ���� 002");
            lock.lock();
            Thread.sleep(300);
            for(int i = 0; i<10000; i++){
                count++;
            }
            System.out.println(Thread.currentThread().getName()+": ��ȡ���� 002, ������"+lock.getHoldCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+": �ͷ����� 002");
            System.out.println(Thread.currentThread().getName()+"------count:"+count);
        }
    }
}
