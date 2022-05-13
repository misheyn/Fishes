package com.company.JavaFish;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    public void run() {
        try (Socket socket = new Socket("localhost", 3345)) {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.reset();
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Client connected to socket. ");
            Habitat.getInstance().clientCount = Integer.parseInt(ois.readUTF());
            while (!socket.isClosed() && !closeFlag) {
                Thread.sleep(100);
            }
            if (!socket.isClosed()) socket.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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

    public void getProperties(PropertiesPackage propertiesPackage) throws IOException, ClassNotFoundException, InterruptedException {
        if (!closeFlag) {
            oos.writeUTF("get");
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
            System.out.println("Closing connections & channels on clentSide - DONE.");
        }
    }

    public void getClientCount() throws IOException {
        if (!closeFlag) {
            oos.writeUTF("getClientCount");
            oos.reset();
            Habitat.getInstance().clientCount = Integer.parseInt(ois.readUTF());
        }
    }

    private boolean closeFlag = false;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
}
