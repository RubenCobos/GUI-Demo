/*************************************************************************************
 * 
 * 	A currency converter that converts between dollars, euros, and pounds.
 * 
 *************************************************************************************/

package finance;

import javax.swing.JFrame;

/** The main application class. */

public class CurrencyConverter
{
	/** Application entry point.
        * @param args */
	
	public static void main(String[] args)
	{
		// Create GUI
		
		JFrame appWindow = new FinanceConvFrame();
		
		appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appWindow.setTitle("Currency Converter");
		appWindow.setVisible(true);
	}
}
