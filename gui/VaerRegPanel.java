package gui;

import java.awt.event.*;
import javax.swing.*;
import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class VaerRegPanel extends MetroPanel
{
	private JTextField minTemp, maxTemp, nedbør;
	private JButton registrer;
	
	/**
	 * @author Henrik Hermansen
	 * @param sl	Referanse til stedlisten
	 */
	public VaerRegPanel(Stedliste sl)
	{
		super(sl);
		
		/**
		 * handlingslytter	En ActionListener for JComboBox'ene og JButton.
		 * skrivefeltlytter	En KeyListener for skrivefeltene.
		 */
		HandlingsLytter handlingslytter=new HandlingsLytter();
		SkrivefeltLytter skrivefeltlytter=new SkrivefeltLytter();
		
		/**
		 * En JComboBox med liste over alle fylkene. handlingslytter legges på som ActionListener.
		 */
		fylke.addActionListener(handlingslytter);
		sted.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		/**
		 * Oppretter tekstfelt for værdataene.
		 */
		minTemp=new JTextField(10);
		maxTemp=new JTextField(10);
		nedbør=new JTextField(10);
		
		/**
		 * Alle tekstfeltene settes false inntil et fylke og sted er valgt.
		 */
//		minTemp.setEditable(false);
//		maxTemp.setEditable(false);
//		nedbør.setEditable(false);
		
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
//		registrer.setEnabled(false);
		registrer.addActionListener(handlingslytter);
		
		/**
		 * Legger inn alt av innhold i panelet.
		 */
		grid.add(new JLabel("Velg fylke"));
		grid.add(fylke);
		grid.add(new JLabel("Velg sted"));
		grid.add(sted);
		grid.add(new JLabel("Minimumstemperatur"));
		grid.add(minTemp);
		grid.add(new JLabel("Maksimumstemperatur"));
		grid.add(maxTemp);
		grid.add(new JLabel("Nedbørsmengde i mm"));
		grid.add(nedbør);
		grid.add(new JLabel("Velg dato"));
		grid.add(dato);
		grid.add(new JLabel(""));
		grid.add(registrer);
	}
	
	/**
	 * @author Henrik Hermansen
	 * Privat lytterklasse av typen ActionListener.
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==fylke)
				hentSteder(fylke.getSelectedIndex());
			if(e.getSource()==registrer)
				return;
		}
	}
	
	/**
	 * @author Henrik Hermansen
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
