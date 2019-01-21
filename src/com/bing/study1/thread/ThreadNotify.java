package com.bing.study1.thread;

import java.util.concurrent.locks.LockSupport;

public class ThreadNotify {

    public static void main(String[] args) throws InterruptedException {
        ThreadNotify notify = new ThreadNotify();

        Thread parker = notify.parkUnpark();
        Thread waiter = notify.waitNotify();

        parker.start();
        waiter.start();

        LockSupport.unpark(parker);
        System.out.println("park �߳�֪ͨ");

        Thread.sleep(2000);
        synchronized (ThreadNotify.class){
            ThreadNotify.class.notify();
            System.out.println("wait �߳�֪ͨ");
        }
    }

    public Thread waitNotify () {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("wait �߳�ִ�п�ʼ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (ThreadNotify.class){
                    try {
                        ThreadNotify.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("wait �߳�ִ�н���");
            }
        });
    }

    public Thread parkUnpark () {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("park �߳�ִ�п�ʼ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                LockSupport.park();
                System.out.println("park �߳�ִ�н���");
            }
        });
    }

}
