package com.company.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.company.JavaFish.PropertiesPackage;


public class MultiThreadServer {

    static ExecutorService executeIt = Executors.newFixedThreadPool(2);


    public static void main(String[] args) {
        clientCount = 0;
        propertiesPackage = new PropertiesPackage();
        propertiesPackage.getProperties(5, 2, 40, 60, 10, 20);
        try (ServerSocket server = new ServerSocket(3345);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Server socket created, command console reader for listen to server commands");
            while (!server.isClosed()) {
                Socket client = server.accept();
                MonoThreadClientHandler clientThread = new MonoThreadClientHandler(client);
                clientThread.start();
                System.out.println("Connection accepted." + client.getInetAddress());
                clientCount++;
            }
            executeIt.shutdown();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static PropertiesPackage propertiesPackage;
    public static Integer clientCount;
}
