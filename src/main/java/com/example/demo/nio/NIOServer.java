package com.example.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: NIO单线程方案，支持并发
 * 应用示例： Redis 单线程、高并发，底层epoll模式
 * @author: stwen_gan
 * @date: 2020/05/13
 **/
public class NIOServer {

    // 保存客户端数据，可设为局部变量
//    static ByteBuffer[] bytes = new ByteBuffer[1024];
    static ByteBuffer byteBuffer = ByteBuffer.allocate(512);//申请堆外内存

    // 保存已连接的客户端 SocketChannel
    static List<SocketChannel> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        try {
            while (true) {

                // BIO ServerSocket阻塞，等待连接
//                ServerSocket server = new ServerSocket(8080);
//                Socket socket = server.accept();

                // NIO
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                //1、设为非阻塞
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.bind(new InetSocketAddress(8080));

                while(true){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    if(socketChannel==null){
                        Thread.sleep(1000);
                        System.out.println("没有新连接...");
                    }else{
                        System.out.println("有新连接...");
                        //2、设为非阻塞
                        socketChannel.configureBlocking(false);
                        list.add(socketChannel);
                    }

                    /**
                     * 遍历list，读取客户端数据
                     * 注：每次while，不管有没有新连接，都要遍历读取一遍,
                     *    因为，假如client1连接上了，但隔很久也不会有其他新连接进来，然后client1发送数据，进不了else
                     *    linux操作系统--主动感知有数据的socket
                     */
                    for(SocketChannel channel:list){
                        int read = channel.read(byteBuffer);
                        if(read>0){
                            byteBuffer.flip();
                            byte[] bs = new byte[read];
                            byteBuffer.get(bs);
                            System.out.println(new String(bs));
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
