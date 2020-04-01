package server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerString implements Runnable {
    private BufferedReader socketIn;
    private PrintWriter socketOut;

    public ServerString(BufferedReader socketIn, PrintWriter socketOut) {
        this.socketIn = socketIn;
        this.socketOut = socketOut;
    }

    private String concat(String a, String b) {
        return a + b;
    }

    private String upperCase(String a, String b) {
        return (a + b).toUpperCase();
    }

    private String lowerCase(String a, String b) {
            return (a + b).toLowerCase();
    }

    @Override
    public void run() {
        boolean checking = true;
        String operation = null;
        String solution = null;
        while (checking) {
            try {
                String[] words = socketIn.readLine().split("\t");
                String a = words[0];
                String b = words[1];

                operation = socketIn.readLine();
                switch (operation) {
                    case "CONCATENATE":
                        solution = concat(a, b);
                        socketOut.println(solution);
                        break;
                    case "UPPERCASE":
                        solution = upperCase(a, b);
                        socketOut.println(solution);
                        break;
                    case "LOWERCASE":
                        solution = lowerCase(a, b);
                        socketOut.println(solution);
                        break;
                    default:
                        checking = false;
                }
            } catch (IOException e) {
                System.err.println("A client has disconnected.");
                checking = false;
            } catch (NullPointerException e) {
                closeStreams();
                checking = false;
            } catch (ArrayIndexOutOfBoundsException e){

            }
        }
    }

    private void closeStreams(){
        try{
            socketIn.close();
            socketOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
