package com.example.clientservergui;

import javafx.scene.control.ListView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnectionThread implements Runnable {
    ListView oldMessages;

    public ServerConnectionThread() {

    }
    public ServerConnectionThread(ListView oldMessages) {
        this.oldMessages = oldMessages;
    }

    public void run() {
        try {
            ServerSocket connectionSocket = new ServerSocket(5001);
            System.out.println("Server is running...");
            while (!Thread.interrupted()) {
                Socket socketServerSide = connectionSocket.accept();
                System.out.println("Server has new client...");
                ObjectInputStream dataReader = new ObjectInputStream(socketServerSide.getInputStream());
                ObjectOutputStream dataWriter = new ObjectOutputStream(socketServerSide.getOutputStream());
                ServerController.allWriters.add(dataWriter);
                Thread perClientReadingThread = new Thread(new NetworkReadingThread(dataReader, oldMessages));
                perClientReadingThread.start();
            }
        } catch (Exception ex) {
            System.out.println("ServerConnectionThread crashed: " + ex);
        }
    }
}
