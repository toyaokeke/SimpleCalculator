package server.model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerModel {
    private ServerSocket serverSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;
    private Socket socket;
    private ExecutorService pool;

    public ServerModel(){
        try {
            serverSocket = new ServerSocket(999);
            System.out.println("Server started.");
            pool = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptConnection(){
        try{
            socket = serverSocket.accept();
            System.out.println("A client has connected.");
            openStreams();
        } catch (IOException e) {
            e.printStackTrace();
            closeStreams();
        }
    }

    public void startCalculator(){
        ServerCalculator calculator = new ServerCalculator(socketIn, socketOut);
        pool.execute(calculator);
    }

    private void openStreams(){
        try{
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
            System.err.println("Unable to open streams...");
            e.printStackTrace();
        }
    }

    public void closeStreams(){
        try {
            socketIn.close();
            socketOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdownServer(){
        try{
            System.out.println("Shutting down server...");
            socket.close();
            serverSocket.close();
            pool.shutdown();
            System.out.println("Server successfully shut down.");
        } catch (IOException e) {
            System.err.println("Unable to close streams...");
            e.printStackTrace();
        }
    }
}
