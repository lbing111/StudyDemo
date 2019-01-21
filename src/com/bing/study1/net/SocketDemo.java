package com.bing.study1.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8800);
        System.out.println("����������������Ϣ�� "+socket.toString());
        while (true) {
            Socket request = socket.accept();
            System.out.println("�˿ں�Ϊ�� "+request.toString());

            InputStream in = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String msg;
            while ((msg = reader.readLine()) != null){
                System.out.println("�ͻ�����Ϣ�� "+msg);
            }
        }
    }
}
