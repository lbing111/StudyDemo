package com.bing.study1.thread;

import java.util.concurrent.locks.LockSupport;

public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("===�߳�"+Thread.currentThread().getName()+": ��ʼִ��===");

                // sleep(),wait(),park(),join()

//                try {
//                    LockSupport.park();
//                }catch (Exception e){
//                        δ�����쳣����
//                    System.out.println("�߳�"+Thread.currentThread().getName()+": �����쳣-"+e.getMessage());
//                }


//                try {
//                    synchronized (ThreadInterrupt.class) {
//                        ThreadInterrupt.class.wait();
//                    }
//                } catch (InterruptedException e) {
////                    �߳�Thread-0: �����쳣-null
//                    System.out.println("�߳�"+Thread.currentThread().getName()+": �����쳣-"+e.getMessage());
//                }

//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
////                    �߳�Thread-0: �����쳣-sleep interrupted
//                    System.out.println("�߳�"+Thread.currentThread().getName()+": �����쳣-"+e.getMessage());
//                }

//                ʹ���߳�interrupt״̬��Ϊ�߳��ж�����
//                while (!Thread.interrupted()){
//
//                }

//                while (!Thread.currentThread().isInterrupted()){
//                }
                System.out.println("---�߳�ִ�����---");
            }
        });
        thread0.start();
        Thread.sleep(3000);
        thread0.interrupt();
    }

}
