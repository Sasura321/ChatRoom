package com.github.chatroom.client.multi;

import java.io.IOException;
import java.net.Socket;

public class MultiThreadClient {
    public static void main(String[] args) {
        try {
            // 通过命令行获取参数
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

            final Socket socket = new Socket(host,port);

            // 1. 往服务器发送数据
            new WriteDataToServerThread(socket).start();

            // 2. 从服务器读取数据
            new ReadDataFromServerThread(socket).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
