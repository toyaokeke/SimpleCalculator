package client.controller;

import client.model.*;
import client.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StringController {
	
	private StringView theView;
	private StringModel theModel;
	
	public StringController(StringView v, StringModel m)
	{
		theView = v;
		theModel = m;

		theView.addStringListener(new StringListener());
	}

	class StringListener implements ActionListener
	{
		@Override
		public void actionPerformed (ActionEvent arg0) {
			if (theView.getFirstString().equals(null) || theView.getSecString().equals(null) ||
					theView.getFirstString().equals("") || theView.getSecString().equals("")) {
				theView.displayErrorMessage("Please enter 2 Strings");
			} else {
				theModel.sendStrings(theView.getFirstString(), theView.getSecString());
				theModel.sendOperation();
				try {
					theView.setResult(theModel.getMyString());
				} catch (Exception ex) {
					theView.displayErrorMessage("Error!");
				}
			}
		}
	}

	public static void main(String[] args) {
		StringView theView = new StringView();

		StringModel theModel = new StringModel();

		StringController theController = new StringController(theView, theModel);

	}
}
