package com.example.demo.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description: BIO单线程方案，不支持并发
 * 鲁班学院-子路老师 https://www.bilibili.com/video/BV1Cx411R72P
 * @author: stwen_gan
 * @date: 2020/05/11
 **/
public class BIOServer1 {

    static byte[] bytes = new byte[1024];
    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(8080);
            while(true){
                System.out.println("等待新连接...");

                // 1、阻塞，放弃CPU,等待连接
                Socket socket = server.accept();

                System.out.println("有新连接成功！");
                System.out.println("等待客户端发送数据...");

                /**
                 * 2、阻塞,等待客户端发送数据
                 * 注：此read会一直阻塞，直到客户端发送数据，则服务端没法继续接收新的连接
                 * 【改进】：
                 *   方案一、考虑将此BIO改造为多线程方案，但是线程资源有限，假如部分用户只连接，不发送数据，岂不是一直占用着线程资源
                 *   方案二、将accept与read设为非阻塞，并每次循坏遍历已连接的客户端是否有发送数据-->NIO原理
                 *       注：使用一个共享变量List保存已连接的客户端，疑问：List可能很大，遍历耗费内存资源，如何解决？
                 *       -->linux操作系统底层解决，帮助我们检测客户端是否有发送数据，活性用户。。
                 */
                int read = socket.getInputStream().read(bytes);

                System.out.println("接收数据成功!");

                String content = new String(bytes);
                System.out.println("data:"+content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
