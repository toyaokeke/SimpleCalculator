package server.controller;

import server.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerController {
    private ServerModel serverModel;
    private BufferedReader stdIn;

    public ServerController(ServerModel sm){
        serverModel = sm;
        stdIn = new BufferedReader(new InputStreamReader(System.in));
    }

    private void runTheServer(){
        long startTime = System.currentTimeMillis();
        while(true){
            serverModel.acceptConnection();
            serverModel.startCalculator();
            if (System.currentTimeMillis()-startTime > 300000) {
                askForShutdown();
                startTime = System.currentTimeMillis();
            }
        }
    }

    private void askForShutdown() {
        System.out.println("Would you like to shut down the server? (YES/NO)");
        String answer = "NO";
        try {
            answer = stdIn.readLine();
            if (answer.toUpperCase().contains("YES")) {
                serverModel.closeStreams();
                serverModel.shutdownServer();
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerModel model = new ServerModel();
        ServerController controller = new ServerController(model);
        controller.runTheServer();
    }
}
