package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Sted;

@SuppressWarnings("serial")
public class VaerRegPanel extends JPanel
{
	private JPanel panel;
	private JComboBox fylke,sted;
	private JTextField minTemp, maxTemp, nedbør, dato;
	private JButton registrer;
	private GridLayout layout;
	
	public VaerRegPanel()
	{
		panel=new JPanel();
		panel.setLayout(layout);
		
		fylke=new JComboBox(Sted.fylkesliste);
		
		minTemp=new JTextField(10);
		maxTemp=new JTextField(10);
		nedbør=new JTextField(10);
	}
}
