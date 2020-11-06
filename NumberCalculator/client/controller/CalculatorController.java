package client.controller;

import client.model.CalculatorModel;
import client.view.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
	
	private CalculatorView theView;
	private CalculatorModel theModel;
	
	public CalculatorController(CalculatorView v, CalculatorModel m)
	{
		theView = v;
		theModel = m;
		
		theView.addCalcListener(new CalculateListener());
	}

	class CalculateListener implements ActionListener
	{
		@Override
		public void actionPerformed (ActionEvent arg0)
		{
		    if (theView.getFirstNumber() == null || theView.getSecNumber() == null){
		    	return;
			} else {
				theModel.sendNumbers(theView.getFirstNumber(), theView.getSecNumber());
				theModel.sendOperation();
				try {
					theView.setSolution(theModel.getResult());
				} catch (NumberFormatException ex)
				{
					theView.displayErrorMessage("You Need to Enter 2 Integers");
				}
			}

		}
	}

	public static void main(String[] args) {
		CalculatorView theView = new CalculatorView();

		CalculatorModel theModel = new CalculatorModel ();

		CalculatorController theController = new CalculatorController (theView, theModel);

	}
}
