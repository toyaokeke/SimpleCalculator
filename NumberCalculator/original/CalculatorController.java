package original;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
	
	private CalculatorView theView;
	private CalculatorModel theModel;
	
	public CalculatorController (CalculatorView v, CalculatorModel m)
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
			int first, sec = 0;
			try
			{
				first = theView.getFirstNumber();
				sec = theView.getSecNumber();
				
				theModel.addTwoNumbers(first, sec);
				
				theView.setSolution(theModel.getValue());
			}
			catch (NumberFormatException ex)
			{
				theView.displayErrorMessage("You Need to Enter 2 Integers");
			}
		}
	}
	

}
