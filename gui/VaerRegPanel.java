package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Sted;

public class VaerRegPanel
{
	private static final long serialVersionUID = 5190542424951093587L;
	private JPanel panel,panel2,panel3;
	private JComboBox fylke,sted;
	private JTextField minTemp, maxTemp, nedbør, dato; // TODO fikse dato!
	private JButton registrer;
	private Metrovindu mv;	// Trenger vi denne?
	
	public VaerRegPanel(Metrovindu mv)
	{
		this.mv=mv;
		
		/**
		 * panel	GridLayout. Skal inneholde knapper og felt.
		 * panel2	BorderLayout. Skal inneholde panel for å kunne plassere dette på PAGE_START.
		 * panel3	KANSKJE FJERNE!?
		 */
		panel=new JPanel(new GridLayout(0,2,3,3));
		
		panel2=new JPanel(new BorderLayout());
		panel2.add(panel,BorderLayout.PAGE_START);
		panel2.setBorder(BorderFactory.createEmptyBorder(25, 35, 10, 35));
		
		panel3=new JPanel(new BorderLayout());
		panel3.add(panel2,BorderLayout.PAGE_START);
		panel3.setBorder(BorderFactory.createRaisedBevelBorder());
		
		/**
		 * handlingslytter	En ActionListener for JComboBox'ene og JButton.
		 * skrivefeltlytter	En KeyListener for skrivefeltene.
		 */
		HandlingsLytter handlingslytter=new HandlingsLytter();
		SkrivefeltLytter skrivefeltlytter=new SkrivefeltLytter();
		
		/**
		 * En JComboBox med liste over alle fylkene. handlingslytter legges på som ActionListener.
		 */
		fylke=new JComboBox(Sted.fylkesliste);
		fylke.addActionListener(handlingslytter);
		
		/**
		 * En JComboBox med liste over stedene i fylket. handlingslytter legges på som ActionListener. Den settes false inntil et fylke er valgt.
		 */
		sted=new JComboBox();
		sted.addActionListener(handlingslytter);
		sted.setEnabled(false);
		
		/**
		 * Oppretter tekstfelt for værdataene.
		 */
		minTemp=new JTextField(10);
		maxTemp=new JTextField(10);
		nedbør=new JTextField(10);
		
		/**
		 * Alle tekstfeltene settes false inntil et fylke og sted er valgt.
		 */
		minTemp.setEditable(false);
		maxTemp.setEditable(false);
		nedbør.setEditable(false);
		
		/**
		 * Alle tekstfeltene får skrivefeltlytter som KeyListener.
		 */
		minTemp.addKeyListener(skrivefeltlytter);
		maxTemp.addKeyListener(skrivefeltlytter);
		nedbør.addKeyListener(skrivefeltlytter);
		
		/**
		 * Oppretter knappen for å utføre en registrering av værdata.
		 */
		registrer=new JButton("Registrer værdata");
		registrer.setEnabled(false);
		registrer.addActionListener(handlingslytter);
		
		/**
		 * Legger inn alt av innhold i panelet.
		 */
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
	
	/**
	 * Returnerer det panelet som skal brukes i hovedvinduet.
	 * @return et objekt av typen JPanel.
	 */
	public JPanel getPanel()
	{
		return panel2;
	}
	
	/**
	 * Privat lytterklasse av typen ActionListener.
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==fylke)
				return;
			if(e.getSource()==registrer)
				return;
		}
	}
	
	/**
	 * Privat lytterklasse av typen KeyListener.
	 */
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
