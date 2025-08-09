package finance;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing. JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;


/*********************************************************************
  A custom frame for converting between dollars, euros, and pounds.
*********************************************************************/

public class FinanceConvFrame extends JFrame
{
	private static final long serialVersionUID = 4277745341297384779L;
	
	// Menu Bar
	
	private final JMenuBar mainMenu;
	private final  JMenu fileMenu;
	private final  JMenu fontMenu;
	private final  JMenuItem fileItem1;
	private final  JMenuItem fontItem1;
	private final  JMenuItem fontItem2;
	private final  JMenuItem fontItem3;
	private final  JMenuItem fontItem4;
	private final  JMenuItem fontItem5;
	
	// Size and Style Panel
	
	private final  JRadioButton fontSmall;
	private final  JRadioButton fontMedium;
	private final  JRadioButton fontLarge;
	private final  JCheckBox fontBold;
	private final  JCheckBox fontItalic;
	private final  JPanel fontSizeContainer;
	private final  JPanel fontStyleContainer;
	private final  JPanel fontOptionsContainer;
	
	// Conversion Panel
	
	private final  JLabel inputLabel;
	private final  JTextField inputField;
	private final  JPanel inputContainer;
	private final  JLabel unitsSeperator;
	private final  JComboBox<String> unitsFrom;
	private final  JComboBox<String> unitsTo;
	private final  JButton convert;
	private final  JPanel unitsContainer;
	private final  JPanel conversionContainer;
	
	// Display Area
	
	private final  JLabel displayResults;
	private final  JPanel resultsContainer;
	
	// Font
	
	private String selectedFont = "Serif";
	private int selectedSize = 24;
	private int selectedStyle = Font.PLAIN;
	
	/** Constructor. */
	
	FinanceConvFrame()
	{
		// Set Frame Size
		
		this.setSize(1000, 400);
		
		// Font Style Options Action Listener
		
		StyleListener options = new StyleListener();
		
		// Create Menu Bar
		
		mainMenu = new JMenuBar();
		fileMenu = new JMenu("File");
		fontMenu = new JMenu("Font");
		
		fileItem1 = new JMenuItem("Exit");
		fileItem1.addActionListener(new ExitListener());
		fontItem1 = new JMenuItem("Dialog");
		fontItem1.addActionListener(new FontListener("Dialog"));
		fontItem2 = new JMenuItem("DialogInput");
		fontItem2.addActionListener(new FontListener("DialogInput"));
		fontItem3 = new JMenuItem("Monospaced");
		fontItem3.addActionListener(new FontListener("Monospaced"));
		fontItem4 = new JMenuItem("Serif");
		fontItem4.addActionListener(new FontListener("Serif"));
		fontItem5 = new JMenuItem("SansSerif");
		fontItem5.addActionListener(new FontListener("SansSerif"));
		
		fileMenu.add(fileItem1);
		fontMenu.add(fontItem1);
		fontMenu.add(fontItem2);
		fontMenu.add(fontItem3);
		fontMenu.add(fontItem4);
		fontMenu.add(fontItem5);
		
		mainMenu.add(fileMenu);
		mainMenu.add(fontMenu);
		
		this.setJMenuBar(mainMenu);
		
		// Create Font Size Panel
		
		fontSmall = new JRadioButton("Small");
		fontSmall.setSelected(true);
		fontSmall.setBackground(new Color(255, 215, 0));
		fontSmall.addActionListener(options);
		fontMedium = new JRadioButton("Medium");
		fontMedium.setBackground(new Color(255, 215, 0));
		fontMedium.addActionListener(options);
		fontLarge = new JRadioButton("Large");
		fontLarge.setBackground(new Color(255, 215, 0));
		fontLarge.addActionListener(options);
		
		ButtonGroup fontSize = new ButtonGroup();
		
		fontSize.add(fontSmall);
		fontSize.add(fontMedium);
		fontSize.add(fontLarge);
		
		fontSizeContainer = new JPanel();
		fontSizeContainer.add(fontSmall);
		fontSizeContainer.add(fontMedium);
		fontSizeContainer.add(fontLarge);
		fontSizeContainer.setBackground(new Color(192, 192, 192));
		fontSizeContainer.setBorder(new TitledBorder(new EtchedBorder(), "Size"));
		
		// Create Font Style Panel

		fontBold = new JCheckBox("Bold");
		fontBold.setBackground(new Color(255, 215, 0));
		fontBold.addActionListener(options);
		fontItalic = new JCheckBox("Italic");
		fontItalic.setBackground(new Color(255, 215, 0));
		fontItalic.addActionListener(options);
		
		fontStyleContainer = new JPanel();
		fontStyleContainer.add(fontBold);
		fontStyleContainer.add(fontItalic);
		fontStyleContainer.setBackground(new Color(192, 192, 192));
		fontStyleContainer.setBorder(new TitledBorder(new EtchedBorder(), "Style"));
		
		// Create Font Options Grid
		
		fontOptionsContainer = new JPanel();
		fontOptionsContainer.setLayout(new GridLayout(2, 1));
		fontOptionsContainer.add(fontSizeContainer);
		fontOptionsContainer.add(fontStyleContainer);
		
		this.add(fontOptionsContainer, BorderLayout.NORTH);
		
		// Create Input Panel
		
		inputLabel = new JLabel("Amount:");
		inputField = new JTextField(30);
		inputField.setText("0.00");
		
		inputContainer = new JPanel();
		inputContainer.add(inputLabel);
		inputContainer.add(inputField);
		inputContainer.setBackground(new Color(255, 215, 0));
		inputContainer.setBorder(new EtchedBorder());
		
		// Create Conversion Panel
		
		unitsFrom = new JComboBox<>();
		unitsFrom.addItem("USD");
		unitsFrom.addItem("EUR");
		unitsFrom.addItem("GBP");
		unitsFrom.setSelectedIndex(0);
		unitsFrom.setEditable(false);
		
		unitsSeperator = new JLabel(" - ");
		
		unitsTo = new JComboBox<>();
		unitsTo.addItem("USD");
		unitsTo.addItem("EUR");
		unitsTo.addItem("GBP");
		unitsTo.setSelectedIndex(0);
		unitsTo.setEditable(false);
		
		convert = new JButton("Convert");
		convert.addActionListener(new ConvListener());
		
		unitsContainer = new JPanel();
		unitsContainer.add(unitsFrom);
		unitsContainer.add(unitsSeperator);
		unitsContainer.add(unitsTo);
		unitsContainer.add(convert);
		unitsContainer.setBackground(new Color(255, 215, 0));
		unitsContainer.setBorder(new EtchedBorder());
		
		// Create Conversion Area Panel
		
		conversionContainer = new JPanel();
		conversionContainer.setLayout(new BorderLayout());
		conversionContainer.add(inputContainer, BorderLayout.NORTH);
		conversionContainer.add(unitsContainer, BorderLayout.SOUTH);
		
		this.add(conversionContainer, BorderLayout.SOUTH);
		
		// Create Display Area
		
		Font displayFont = new Font(selectedFont, selectedStyle, selectedSize);
		
		displayResults = new JLabel("\u0024" + "0.00 = " + "\u0024" + "0.00", SwingConstants.CENTER);
		displayResults.setFont(displayFont);
		
		resultsContainer = new JPanel();
		resultsContainer.setLayout(new BorderLayout());
		resultsContainer.add(displayResults, BorderLayout.CENTER);
		resultsContainer.setBackground(new Color(245, 222, 179));
		resultsContainer.setBorder(new EtchedBorder());
		
		this.add(resultsContainer, BorderLayout.CENTER);
	}
	
	/** A menu item listener to change the font */
	
	private class FontListener implements ActionListener
	{
		private final  String faceName;
		
		/** Constructor stores the font name. */
		
		FontListener(String name)
		{
			faceName = name;
		}
		
		/** Changes the font of the display label. */
		
                @Override
		public void actionPerformed(ActionEvent event)
		{
			selectedFont = faceName;
			
			Font displayFont = new Font(selectedFont, selectedStyle, selectedSize);
			
			displayResults.setFont(displayFont);
			displayResults.repaint();
		}
	}
	
	/** A radio button and checkbox listener to change the size and style of the font. */
	
	private class StyleListener implements ActionListener
	{
		/** Changes the size and style of the font. */
		
                @Override
		public void actionPerformed(ActionEvent event)
		{
			// Set Size
			
			if (fontSmall.isSelected())
			{
				selectedSize = 24;
			}
			else if (fontMedium.isSelected())
			{
				selectedSize = 36;
			}
			else if (fontLarge.isSelected())
			{
				selectedSize = 48;
			}
			
			// Set Style
			
			if (fontBold.isSelected() && fontItalic.isSelected())
			{
				selectedStyle = Font.BOLD | Font.ITALIC;
			}
			else if (fontBold.isSelected())
			{
				selectedStyle = Font.BOLD;
			}
			else if (fontItalic.isSelected())
			{
				selectedStyle = Font.ITALIC;
			}
			else
			{
				selectedStyle = Font.PLAIN;
			}
			
			// Set Font
			
			Font displayFont = new Font(selectedFont, selectedStyle, selectedSize);
			
			displayResults.setFont(displayFont);
			displayResults.repaint();
		}
	}
	
	/** The conversion button listener. */
	
	private class ConvListener implements ActionListener
	{
		private double value1;
		private double value2;
		private String displayString;
		
		/** Constructor. */
		
		ConvListener()
		{
			value1 = 0.0;
			value2 = 0.0;
			displayString = "";
		}
		
		/** Converts the input to the desired units. */
		
                @Override
		public void actionPerformed(ActionEvent event)
		{
			// Parse Input
			
			try
			{
				value1 = Double.parseDouble(inputField.getText());
			}
			catch(NumberFormatException exception)
			{
				displayResults.setText(exception.toString());
				displayResults.repaint();
				return;
			}
			
			// Extract Conversion Units
			
			String units1 = (String) unitsFrom.getSelectedItem();
			String units2 = (String) unitsTo.getSelectedItem();
			
			// Perform Conversion
			
			if (units1.equals("USD") && units2.equals("USD"))
			{
				value2 = value1;
				displayString = String.format("\u0024%,.2f = \u0024%,.2f", value1, value2);
			}
			else if (units1.equals("USD") && units2.equals("EUR"))
			{
				value2 = value1 * (1.0 / 1.42);
				displayString = String.format("\u0024%,.2f = \u20AC%,.2f", value1, value2);
			}
			else if (units1.equals("USD") && units2.equals("GBP"))
			{
				value2 = value1 * (1.0 / 1.64);
				displayString = String.format("\u0024%,.2f = \u00A3%,.2f", value1, value2);
			}
			else if (units1.equals("EUR") && units2.equals("USD"))
			{
				value2 = value1 * 1.42;
				displayString = String.format("\u20AC%,.2f = \u0024%,.2f", value1, value2);
			}
			else if (units1.equals("EUR") && units2.equals("EUR"))
			{
				value2 = value1;
				displayString = String.format("\u20AC%,.2f = \u20AC%,.2f", value1, value2);
			}
			else if (units1.equals("EUR") && units2.equals("GBP"))
			{
				value2 = value1 * (1.0 / 1.13);
				displayString = String.format("\u20AC%,.2f = \u00A3%,.2f", value1, value2);
			}
			else if (units1.equals("GBP") && units2.equals("USD"))
			{
				value2 = value1 * 1.64;
				displayString = String.format("\u00A3%,.2f = \u0024%,.2f", value1, value2);
			}
			else if (units1.equals("GBP") && units2.equals("EUR"))
			{
				value2 = value1 * 1.13;
				displayString = String.format("\u00A3%,.2f = \u20AC%,.2f", value1, value2);
			}
			else if (units1.equals("GBP") && units2.equals("GBP"))
			{
				value2 = value1;
				displayString = String.format("\u00A3%,.2f = \u00A3%,.2f", value1, value2);
			}
			
			// Update Label
			
			displayResults.setText(displayString);
			displayResults.repaint();
		}
	}
	
	/** A menu item listener that exits the application. */
	
	private class ExitListener implements ActionListener
	{
		/** Exits the application. */
		
                @Override
		public void actionPerformed(ActionEvent event)
		{
			System.exit(0);
		}
	}
}
