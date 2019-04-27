package com.github.chatroom.client.multi;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteDataToServerThread extends Thread {
    private final Socket client;

    public WriteDataToServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            OutputStream clientOutput = client.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(clientOutput);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("请输入消息：");
                String message = scanner.nextLine();

                // 给服务器发数据
                writer.write(message + "\n");
                writer.flush();

                if(message.equals("bye")) {
                    // 表示客户端要关闭
                    client.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
