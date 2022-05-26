package com.company.Server;

import com.company.JavaFish.PropertiesPackage;

import java.io.*;
import java.net.Socket;

public class MonoThreadClientHandler extends Thread {

    private Socket clientDialog;

    public MonoThreadClientHandler(Socket client) {
        clientDialog = client;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(clientDialog.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientDialog.getOutputStream());
            oos.reset();
            String clientName = ois.readUTF();
            System.out.println("Connected " + clientName);
            label:
            while (!clientDialog.isClosed()  && !clientDialog.isOutputShutdown() && !clientDialog.isOutputShutdown()) {
                    System.out.println("Server reading from channel " + getId());
                    String entry = ois.readUTF();
                    System.out.println("READ from clientDialog message - " + entry);

                    switch (entry) {
                        case "get":
                            String name = ois.readUTF();
                            synchronized (MultiThreadServer.clientsMap) {
                                MultiThreadServer.clientsMap.forEach((login, propertiesPackage) -> {
                                    if (login.equals(name)) {
                                        try {
                                            oos.writeObject(propertiesPackage);
                                            oos.reset();
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                });
                            }
                            System.out.println("Sent properties");
                            break;
                        case "set":
                            synchronized (MultiThreadServer.clientsMap) {
                                PropertiesPackage tmp = (PropertiesPackage) ois.readObject();
                                MultiThreadServer.clientsMap.put(clientName, tmp);
                            }
                            System.out.println("Got properties");
                            break;
                        case "getClientCount":
                            oos.writeUTF(Integer.toString(MultiThreadServer.clientCount));
                            oos.reset();
                            break;
                        case "getClientList":
                            oos.writeUTF(Integer.toString(MultiThreadServer.clientCount));
                            oos.reset();
                            synchronized (MultiThreadServer.clientsMap) {
                                MultiThreadServer.clientsMap.forEach((login, tmp) -> {
                                    try {
                                        oos.writeUTF(login);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            }
                            oos.reset();
                            break;
                        case "quit":
                            break label;
                    }
                Thread.sleep(100);
            }
            MultiThreadServer.clientCount--;
            System.out.println("Client disconnected " + clientDialog.getInetAddress() + " " + getId());

            ois.close();
            oos.close();

            clientDialog.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
