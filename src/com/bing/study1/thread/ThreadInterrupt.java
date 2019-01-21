package com.bing.study1.thread;

import java.util.concurrent.locks.LockSupport;

public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("===线程"+Thread.currentThread().getName()+": 开始执行===");

                // sleep(),wait(),park(),join()

//                try {
//                    LockSupport.park();
//                }catch (Exception e){
//                        未进入异常捕获
//                    System.out.println("线程"+Thread.currentThread().getName()+": 捕获异常-"+e.getMessage());
//                }


//                try {
//                    synchronized (ThreadInterrupt.class) {
//                        ThreadInterrupt.class.wait();
//                    }
//                } catch (InterruptedException e) {
////                    线程Thread-0: 捕获异常-null
//                    System.out.println("线程"+Thread.currentThread().getName()+": 捕获异常-"+e.getMessage());
//                }

//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
////                    线程Thread-0: 捕获异常-sleep interrupted
//                    System.out.println("线程"+Thread.currentThread().getName()+": 捕获异常-"+e.getMessage());
//                }

//                使用线程interrupt状态做为线程中断条件
//                while (!Thread.interrupted()){
//
//                }

//                while (!Thread.currentThread().isInterrupted()){
//                }
                System.out.println("---线程执行完成---");
            }
        });
        thread0.start();
        Thread.sleep(3000);
        thread0.interrupt();
    }

}
