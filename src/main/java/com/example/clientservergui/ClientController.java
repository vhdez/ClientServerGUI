package com.example.clientservergui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientController {
    public Label clientLabel;
    public TextField typedMessage;
    public ListView oldMessages;
    ObjectOutputStream dataWriter;

    public void initialize() {
        try {
            Socket socketClientSide = new Socket("127.0.0.1", 5001);
            clientLabel.setText("Connected to server.");
            dataWriter = new ObjectOutputStream(socketClientSide.getOutputStream());
            ObjectInputStream dataReader = new ObjectInputStream(socketClientSide.getInputStream());
            NetworkReadingThread readerThread = new NetworkReadingThread(dataReader, oldMessages);
            Thread otherThread = new Thread(readerThread);
            otherThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            clientLabel.setText("Failed to connect to server.");
        }

    }

    public void newMessageTyped() {
        try {
            String message = typedMessage.getText();
            typedMessage.clear();
            dataWriter.writeObject(2);
            dataWriter.writeObject("From: Mr. Hernandez's client");
            dataWriter.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
            clientLabel.setText("Failed to write message to server.");
        }

    }
}
