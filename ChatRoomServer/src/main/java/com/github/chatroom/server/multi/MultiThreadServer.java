package com.github.chatroom.server.multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程聊天室服务端程序
 * Author: zsm
 * Created: 2019/4/25
 */
public class MultiThreadServer {
    public static void main(String[] args) {

        // 通过命令行获取服务器端口
        int port = 1234;
        if(args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);

            } catch (NumberFormatException e) {
                System.out.println("端口参数不正确，采用默认端口" + port);
            }
        }

        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            // 配置端口号
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("等待客户端连接...");

            // 循环等待用户连接
            while (true) {
                Socket client = serverSocket.accept();
                executorService.submit(new ExecuteClient(client));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
