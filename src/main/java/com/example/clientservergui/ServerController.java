package com.example.clientservergui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class ServerController {
    static ObjectOutputStream lastClientSocket;
    public Label serverLabel;
    public TextField typedMessage;
    public Button startButton;
    public ListView oldMessages;

    public void initialize() {

    }

    public void startButtonPressed() {
        Thread serverConnectionThread = new Thread(new ServerConnectionThread(oldMessages));
        serverConnectionThread.start();
        startButton.setDisable(true);
        serverLabel.setText("Server started");
    }

    public void newMessageTyped() throws IOException {
        String newMessage = typedMessage.getText();
        if (lastClientSocket != null) {
            lastClientSocket.writeObject(1);
            lastClientSocket.writeObject(newMessage);
        }
        typedMessage.clear();
    }
}