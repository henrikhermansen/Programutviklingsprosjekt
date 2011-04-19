package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
	
	public VaerRegPanel()
	{
		panel=new JPanel();
		panel.setLayout(new GridLayout(6,2,3,3));
		
		HandlingsLytter handlingslytter=new HandlingsLytter();
		SkrivefeltLytter skrivefeltlytter=new SkrivefeltLytter();
		
		fylke=new JComboBox(Sted.fylkesliste);
		fylke.addActionListener(handlingslytter);
		
		sted=new JComboBox();
		sted.setEnabled(false);
		
		minTemp=new JTextField(10);
		maxTemp=new JTextField(10);
		nedbør=new JTextField(10);
		
		minTemp.setEditable(false);
		maxTemp.setEditable(false);
		nedbør.setEditable(false);
		
		minTemp.addKeyListener(skrivefeltlytter);
		maxTemp.addKeyListener(skrivefeltlytter);
		nedbør.addKeyListener(skrivefeltlytter);
		
		registrer=new JButton("Registrer værdata");
		registrer.setEnabled(false);
		registrer.addActionListener(handlingslytter);
		
		panel.add(new JLabel("Velg fylke"));
		panel.add(fylke);
		panel.add(new JLabel("Velg sted"));
		panel.add(sted);
		panel.add(new JLabel("Minimumstemperatur"));
		panel.add(minTemp);
		panel.add(new JLabel("Maksimumstemperatur"));
		panel.add(maxTemp);
		panel.add(new JLabel("Nedbørsmengde i mm"));
		panel.add(nedbør);
		panel.add(new JLabel(""));
		panel.add(registrer);
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
	
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==fylke)
				return;
			if(e.getSource()==registrer)
				return;
		}
	}
	
	private class SkrivefeltLytter implements KeyListener
	{
		public void keyPressed(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
		}
		public void keyReleased(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
		}
		public void keyTyped(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
		}
	}
}
