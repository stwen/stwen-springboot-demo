package com.example.demo.nio;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @description: Socket客户端
 * @author: stwen_gan
 * @date: 2020/05/13
 **/
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket =new Socket("127.0.0.1",8080);
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("请输入：");
            String next = scanner.next();
            socket.getOutputStream().write(next.getBytes());
        }
    }
}
