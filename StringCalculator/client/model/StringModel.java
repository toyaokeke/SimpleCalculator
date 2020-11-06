package client.model;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class StringModel {
	private Socket socket;
	private BufferedReader socketIn;
	private PrintWriter socketOut;

	public StringModel(){
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

	public void sendStrings(String a, String b){
		socketOut.println(a + "\t" + b);
	}

	public String getMyString(){
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
		String[] options = {"CONCATENATE", "UPPERCASE", "LOWERCASE"};
		String operation = "";
		try{
			operation = (String) JOptionPane.showInputDialog(null, "Select an Operation:", "Operations", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		} catch (NullPointerException e){
			operation = "CONCATENATE";
		} finally {
			socketOut.println(operation);
		}
	}

}
