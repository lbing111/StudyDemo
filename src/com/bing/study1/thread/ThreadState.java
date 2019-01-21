package com.bing.study1.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 2. Thread-0�̵߳�ǰ״̬Ϊ��RUNNABLE
                System.out.println(Thread.currentThread().getName()+"�̵߳�ǰ״̬Ϊ��"+Thread.currentThread().getState());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (ThreadState.class){
                    try {
                        ThreadState.class.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // 1. Thread-0�̵߳�ǰ״̬Ϊ��NEW
        System.out.println(thread.getName()+"�̵߳�ǰ״̬Ϊ��"+thread.getState());
        thread.start();
        Thread.sleep(1000);
        // 3. Thread-0�̵߳�ǰ״̬Ϊ��TIMED_WAITING
        System.out.println(thread.getName()+"�̵߳�ǰ״̬Ϊ��"+thread.getState());
        synchronized (ThreadState.class){
            Thread.sleep(3000);
            // 4. Thread-0�̵߳�ǰ״̬Ϊ��BLOCKED
            System.out.println(thread.getName()+"�̵߳�ǰ״̬Ϊ��"+thread.getState());
        }
        Thread.sleep(1000);
        // 5. Thread-0�̵߳�ǰ״̬Ϊ��WAITING
        System.out.println(thread.getName()+"�̵߳�ǰ״̬Ϊ��"+thread.getState());
        synchronized (ThreadState.class){
            ThreadState.class.notify();
        }
        thread.join();
        // 6. Thread-0�̵߳�ǰ״̬Ϊ��TERMINATED
        System.out.println(thread.getName()+"�̵߳�ǰ״̬Ϊ��"+thread.getState());
    }
}
