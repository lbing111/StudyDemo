package com.bing.study1.aqs;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        System.out.println(latch);
        Thread thread = new Thread(()->{
            latch.countDown();
            System.out.println(Thread.currentThread().getName()+"可以执行了...");
            System.out.println(latch);
        });

        Thread thread1 = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            System.out.println(Thread.currentThread().getName()+"可以执行了...");
            System.out.println(latch);
        });
        thread.start();
        thread1.start();

        thread1.interrupt();
        thread1.isInterrupted();
        latch.await();
        System.out.println(latch);
    }
}
