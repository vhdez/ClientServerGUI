package com.example.clientservergui;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        System.out.println("Client is running.");
        try {
            Socket socketClientSide = new Socket("10.37.149.156", 5001);
            ObjectOutputStream dataWriter = new ObjectOutputStream(socketClientSide.getOutputStream());
            dataWriter.writeObject(5);
            dataWriter.writeObject("Who;'s");
            dataWriter.writeObject("Hi 2!!!!!");
            dataWriter.writeObject("Hi 3!!!!!");
            dataWriter.writeObject("Hi 4!!!!!");
            dataWriter.writeObject("Hi 5!!!!!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Darn... the client crashed.");
        }

    }
}
