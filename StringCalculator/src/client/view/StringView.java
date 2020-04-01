package client.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class StringView extends JFrame{


	private JTextField firstString = new JTextField(10);
	private JLabel addLabel = new JLabel ("and");

	private JTextField secondString = new JTextField(10);
	private JButton concatButton = new JButton ("Operate");

	private JTextField result = new JTextField(10);
    
	public StringView(){
		super("Toya's String Concatenator");
		JPanel calcPanel = new JPanel ();
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		calcPanel.add(firstString);
		calcPanel.add(addLabel);
		calcPanel.add(secondString);
		calcPanel.add(concatButton);
		calcPanel.add(result);

		this.add(calcPanel);
		setVisible(true);
	}

	public String getFirstString ()
	{
		return firstString.getText();
	}
	public String getSecString ()
	{
		return secondString.getText();
	}
	public void setResult (String res)
	{
		result.setText(res);
	}
	public void addStringListener (ActionListener listenForConcatButton)
	{
		concatButton.addActionListener(listenForConcatButton);
	}
	public void displayErrorMessage (String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage,"Error", JOptionPane.ERROR_MESSAGE);
	}
}
