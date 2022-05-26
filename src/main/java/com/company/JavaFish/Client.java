package com.company.JavaFish;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread {
    public Client(String _login, String _ip, int _port) throws IOException {
        instance = this;
        login = _login;
        ip = _ip;
        port = _port;
    }

    public void run() {
        try (Socket socket = new Socket(ip, port)) {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.reset();
            ois = new ObjectInputStream(socket.getInputStream());
            if (oos != null) {
                System.out.println("Client connected to socket. ");
                oos.writeUTF(login);
                oos.reset();
                readyFlag = true;
                while (!socket.isClosed() && !closeFlag) {
                    Thread.sleep(100); //todo timerTask
                }
                System.out.println("Client disconnected " + closeFlag);
                ois.close();
            } else {
                closeFlag = true;
            }
        } catch (ConnectException e){
            closeFlag = true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        closeFlag = true;
    }

    public void sendProperties(PropertiesPackage propertiesPackage) throws IOException {
        if (!closeFlag) {
            oos.writeUTF("set");
            oos.reset();
            oos.writeObject(propertiesPackage);
            oos.reset();
        }
    }

    public void getProperties(PropertiesPackage propertiesPackage, String name) throws IOException, ClassNotFoundException {
        if (!closeFlag) {
            oos.writeUTF("get");
            oos.writeUTF(name);
            oos.reset();
            propertiesPackage.print();
            PropertiesPackage tmp = (PropertiesPackage) ois.readObject();
            propertiesPackage.copyProperties(tmp);
        }
    }

    public void quit() throws IOException {
        if (!closeFlag) {
            oos.writeUTF("quit");
            oos.close();
            closeFlag = true;
            readyFlag = false;
            System.out.println("Closing connections & channels on clentSide - DONE.");
        }
    }

    public void getClientCount() throws IOException {
        if (!closeFlag) {
            System.out.println("getClientCount");
            oos.writeUTF("getClientCount");
            oos.reset();
            clientCount = Integer.parseInt(ois.readUTF());
        }
    }

    public void getClientList() throws IOException {
        if (!closeFlag) {
            System.out.println("getClientList");
            oos.writeUTF("getClientList");
            oos.reset();
            clientCount = Integer.parseInt(ois.readUTF());
            clientsList = new ArrayList<>();
            for (int i = 0; i < clientCount; i++)
                clientsList.add(ois.readUTF());
        }
    }

    public static Client getInstance() {
        Client localInstance = instance;
        if (localInstance == null) {
            synchronized (Client.class) {
                localInstance = instance;
            }
        }
        return localInstance;
    }

    public boolean readyFlag = false;
    private final String login;
    private final String ip;
    private final int port;
    private static volatile Client instance;
    public boolean closeFlag = false;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    public ArrayList<String> clientsList;
    public int clientCount;
}
