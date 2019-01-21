package com.bing.study1.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8800);

        OutputStream out = socket.getOutputStream();
        System.out.println("«Î ‰»Î°£°£°£");
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();
        out.write(msg.getBytes());
    }
}
