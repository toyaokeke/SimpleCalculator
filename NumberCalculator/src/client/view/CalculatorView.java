package client.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame{
	
	
	private JTextField firstNumber = new JTextField(10);
	private JLabel addLabel = new JLabel (",");
	
	private JTextField secondNumber = new JTextField(10);
	private JButton calculateButton = new JButton ("Calculate");
	
	private JTextField solution = new JTextField(10);
    
	public CalculatorView(){
		super("Toya's Calculator");
		JPanel calcPanel = new JPanel ();
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		calcPanel.add(firstNumber);
		calcPanel.add(addLabel);
		calcPanel.add(secondNumber);
		calcPanel.add(calculateButton);
		calcPanel.add(solution);
		
		this.add(calcPanel);
		setVisible(true);
	}

	public Float getFirstNumber ()
	{
		Float first = null;
		try{
			first = Float.parseFloat(firstNumber.getText());
		} catch (NumberFormatException e){
			displayErrorMessage("Please enter a valid integer as your first number");
		} finally {
			return first;
		}
	}
	public Float getSecNumber ()
	{
		Float second = null;
		try{
			second = Float.parseFloat(secondNumber.getText());
		} catch (NumberFormatException e){
			displayErrorMessage("Please enter a valid integer as your second number");
		} finally {
			return second;
		}
	}
	public void setSolution (String sol)
	{
		solution.setText(sol);
	}
	public void addCalcListener (ActionListener listenForCalcButton)
	{
		calculateButton.addActionListener(listenForCalcButton);
	}
	public void displayErrorMessage (String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}

}
