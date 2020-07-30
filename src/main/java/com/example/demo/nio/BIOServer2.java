package com.example.demo.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description: BIO多线程方案，支持并发
 * 缺点：线程资源有限，假如1000万用户，部分800w用户只连接，不发送数据，岂不是一直占用着线程资源
 * 改进方案--》NIO （单线程、IO多路复用）
 * @author: stwen_gan
 * @date: 2020/05/13
 **/
public class BIOServer2 {

    static byte[] bytes = new byte[1024];

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(8080);
            while (true) {
                System.out.println("等待新连接...");
                // 1、阻塞（主线程），等待连接
                Socket socket = server.accept();

                System.out.println("等待客户端发送数据...");
                //2、创建子线程，接收用户数据
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // todo 阻塞，等待客户端发送数据
                            int read = socket.getInputStream().read(bytes);
                            System.out.println("data:"+new String(bytes));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                );
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
