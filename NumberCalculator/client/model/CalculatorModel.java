package client.model;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class CalculatorModel {
	private Socket socket;
	private BufferedReader socketIn;
	private PrintWriter socketOut;

	public CalculatorModel(){
		try {
			socket = new Socket("localhost", 999);
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null,"Unable to connect to host. Please close your calculator window.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Unable to open streams. Please close your calculator window.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public void sendNumbers(float a, float b){
		socketOut.println(a + "\t" + b);
	}

	public String getResult(){
		String result = "";
		try {
			result = socketIn.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Connection to the server has been lost. Please close your calculator window.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	public void sendOperation(){
		String[] options = {"ADD", "MULTIPLY", "DIVIDE"};
		String operation = "";
		try{
			operation = (String) JOptionPane.showInputDialog(null, "Select an Operation:", "Operations", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		} catch (NullPointerException e){
			operation = "ADD";
		} finally {
			socketOut.println(operation);
		}
	}

}
