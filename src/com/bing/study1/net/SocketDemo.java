package com.bing.study1.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8800);
        System.out.println("服务启动，监听信息： "+socket.toString());
        while (true) {
            Socket request = socket.accept();
            System.out.println("端口号为： "+request.toString());

            InputStream in = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String msg;
            while ((msg = reader.readLine()) != null){
                System.out.println("客户端信息： "+msg);
            }
        }
    }
}
