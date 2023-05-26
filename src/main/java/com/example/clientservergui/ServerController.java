package com.example.clientservergui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ServerController {
    public Label serverLabel;
    public TextField typedMessage;
    public Button startButton;
    public ListView oldMessages;

    public void initialize() {

    }

    public void startButtonPressed() {
        Thread serverConnectionThread = new Thread(new ServerConnectionThread(oldMessages));
        serverConnectionThread.start();
    }

    public void newMessageTyped() {

}
}