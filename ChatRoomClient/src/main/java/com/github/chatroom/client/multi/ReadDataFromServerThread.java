package com.github.chatroom.client.multi;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class ReadDataFromServerThread extends Thread {
    private final Socket client;

    public ReadDataFromServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        Scanner readFromServer = null;
        try {
            readFromServer = new Scanner(client.getInputStream());
            readFromServer.useDelimiter("\n");

            while (true) {
                if (readFromServer.hasNextLine()) {
                    String message = readFromServer.nextLine();
                    System.out.println("来自服务器的消息：" + message);
                }
                if (client.isClosed()) {
                    System.out.println("服务器已经关闭");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            readFromServer.close();
        }
    }
}
