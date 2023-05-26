package com.example.clientservergui;

import javafx.application.Platform;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.io.ObjectInputStream;

public class NetworkReadingThread implements Runnable {
    ObjectInputStream dataReader;
    ListView oldMessages;

    public NetworkReadingThread(ObjectInputStream dataReader) {
        this.dataReader = dataReader;
    }

    public NetworkReadingThread(ObjectInputStream dataReader, ListView oldMessages) {
        this.dataReader = dataReader;
        this.oldMessages = oldMessages;
    }

    @Override
    public void run() {
        try {
        while (!Thread.interrupted()) {
            int dataCount = -1;
            try {
                dataCount = (Integer) dataReader.readObject();
            } catch (IOException ex) {
                Thread.currentThread().yield();
            }
            for (int i = 0; i < dataCount; i = i + 1) {
                Object data = null;
                while (data == null) {
                    try {
                        data = dataReader.readObject();
                    } catch (IOException ex) {
                        Thread.currentThread().yield();
                    }
                }
                if (oldMessages == null) {
                    System.out.println(data);
                } else {
                    final Object displayData = data;
                    Platform.runLater(new Runnable() {
                        @Override public void run() {
                            oldMessages.getItems().add(displayData);
                        }
                    });

                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Darn... the server crashed!");
    }

}
}
