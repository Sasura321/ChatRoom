package com.github.chatroom.client.single;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SingleThreadClient {
    public static void main(String[] args) {
        try {
            // 0. 通过命令行获取参数
            int port = 1234;
            if(args.length > 0) {
                try {
                    port = Integer.parseInt(args[0]);

                } catch (NumberFormatException e) {
                    System.out.println("端口参数不正确，采用默认端口" + port);
                }
            }
            String host = "127.0.0.1";
            if (args.length > 1) {
                host = args[1];
                // host 格式化校验
            }

            // 1. 创建客户端，连接到服务器
            Socket clientSocket = new Socket(host,port);

            // 2. 发送数据，接收数据
            // 2.1 发送数据
            OutputStream clientOutput = clientSocket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(clientOutput);
            writer.write("您好，我是客户端\n");
            writer.flush();

            // 2.2 接收数据
            InputStream clientInput = clientSocket.getInputStream();
            Scanner scanner = new Scanner(clientInput);
            String serverData = scanner.nextLine();
            System.out.println("来自服务器的数据：" + serverData);

            // 3. 客户端关闭
            clientOutput.close();
            clientInput.close();
            clientSocket.close();
            System.out.println("客户端关闭");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
