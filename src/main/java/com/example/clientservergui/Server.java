package com.example.clientservergui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Thread serverConnectionThread = new Thread(new ServerConnectionThread());
        serverConnectionThread.start();
    }
}
