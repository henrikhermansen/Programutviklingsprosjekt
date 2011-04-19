package gui;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		
		minTemp.setEditable(false);
		maxTemp.setEditable(false);
		nedbør.setEditable(false);
		
		FeltLytter feltlytter=new FeltLytter();
		minTemp.addKeyListener(feltlytter);
		maxTemp.addKeyListener(feltlytter);
		nedbør.addKeyListener(feltlytter);
		
		registrer
	}
	
	private class FeltLytter implements KeyListener
	{
		public void keyPressed(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
		}public void keyReleased(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
		}
		public void keyTyped(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
		}
		
	}
}
