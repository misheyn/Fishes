package com.company.Server;

import com.company.JavaFish.PropertiesPackage;

import java.io.*;
import java.net.Socket;

public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;

    public MonoThreadClientHandler(Socket client) {
        MonoThreadClientHandler.clientDialog = client;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(clientDialog.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientDialog.getOutputStream());
            oos.reset();
            synchronized (MultiThreadServer.clientCount) {
                oos.writeUTF(Integer.toString(MultiThreadServer.clientCount));
                oos.reset();
            }
            while (!clientDialog.isClosed()) {
                System.out.println("Server reading from channel");
                String entry = ois.readUTF();
                System.out.println("READ from clientDialog message - " + entry);

                if (entry.equalsIgnoreCase("get")) {
                    System.out.println("Send properties");
                    System.out.println("Sent " + MultiThreadServer.propertiesPackage.N1 + " " + MultiThreadServer.propertiesPackage.N2 + " " + MultiThreadServer.propertiesPackage.P1 + " " + MultiThreadServer.propertiesPackage.P2);
                    oos.writeObject(MultiThreadServer.propertiesPackage);
                    oos.reset();
                } else if (entry.equalsIgnoreCase("set")) {
                    System.out.println("Get properties");
                    MultiThreadServer.propertiesPackage.print();
                    PropertiesPackage tmp = (PropertiesPackage) ois.readObject();
                    MultiThreadServer.propertiesPackage.copyProperties(tmp);
                    System.out.println("Got: " + tmp.N1 + " " + tmp.N2 + " " + tmp.P1 + " " + tmp.P2);
                } else if (entry.equalsIgnoreCase("getClientCount")) {
                    oos.writeUTF(Integer.toString(MultiThreadServer.clientCount));
                    oos.reset();
                } else if (entry.equalsIgnoreCase("quit")) {
                    break;
                }
            }
            MultiThreadServer.clientCount--;
            System.out.println("Client disconnected");

            ois.close();
            oos.close();

            clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
