package com.bing.study1.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketDemo {
    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8800);
        System.out.println("������Ϣ��"+socket.toString());
        while (true) {
            Socket request = socket.accept();
            System.out.println("�ͻ�����Ϣ "+request.toString());

            threadPool.execute(()->{
                InputStream in = null;
                try {
                    in = request.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    OutputStream out = request.getOutputStream();
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
                    out.write("Content-Length: 12\r\n\n".getBytes());
                    out.write("Hello Chrome".getBytes());
                    out.flush();

                    String msg;
                    while ((msg = reader.readLine()) != null){
                        System.out.println("������Ϣ�� "+msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
