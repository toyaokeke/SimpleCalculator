package server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerCalculator implements Runnable {
    private BufferedReader socketIn;
    private PrintWriter socketOut;

    public ServerCalculator(BufferedReader socketIn, PrintWriter socketOut) {
        this.socketIn = socketIn;
        this.socketOut = socketOut;
    }

    private float add(float a, float b) {
        return a + b;
    }

    private float multiply(float a, float b) {
        return a * b;
    }

    private float divide(float a, float b) {
        try {
            return a / b;
        } catch (ArithmeticException e){
            return 0;
        }
    }

    @Override
    public void run() {
        boolean checking = true;
        String operation = null;
        Float solution = null;
        while (checking) {
            try {
                String[] numbers = socketIn.readLine().split("\t");
                float a = Float.parseFloat(numbers[0]);
                float b = Float.parseFloat(numbers[1]);

                operation = socketIn.readLine();
                switch (operation) {
                    case "ADD":
                        solution = add(a, b);
                        socketOut.println(solution);
                        break;
                    case "MULTIPLY":
                        solution = multiply(a, b);
                        socketOut.println(solution);
                        break;
                    case "DIVIDE":
                        solution = divide(a, b);
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
