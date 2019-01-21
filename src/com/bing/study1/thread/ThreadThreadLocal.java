package com.bing.study1.thread;

public class ThreadThreadLocal {

    public static void main(String[] args) throws InterruptedException {

        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("main value");
        Thread thread = new Thread(() -> {
            System.out.println("第一次读取ThreadLocal： "+local.get());

            local.set("thread value");
            System.out.println("第二次读取ThreadLocal： "+local.get());
        });

        thread.start();
        thread.join();

        Thread.sleep(3000);
        System.out.println("第三次读取ThreadLocal： "+local.get());
    }
}
