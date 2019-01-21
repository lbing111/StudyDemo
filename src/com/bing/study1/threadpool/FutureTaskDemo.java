package com.bing.study1.threadpool;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> call = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("��ʼ���㡣����");
                Thread.sleep(3000);
                return 111;
            }
        };

        FutureTask<Integer> task = new FutureTask<>(call);

        Thread thread = new Thread(task);
        thread.start();

        System.out.println("�ȴ��С�����");
        Integer res = task.get();

        System.out.println("������Ϊ��"+res);
    }
}
