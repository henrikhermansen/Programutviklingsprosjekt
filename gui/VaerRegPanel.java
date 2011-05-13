package gui;

import java.awt.event.*;
import javax.swing.*;

import logic.Registrering;
import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class VaerRegPanel extends MetroPanel
{
	private JTextField minTemp, maxTemp, nedb�r;
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
		 * En JComboBox med liste over alle fylkene. handlingslytter legges p� som ActionListener.
		 */
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		l�r.addActionListener(handlingslytter);
		lm�ned.addActionListener(handlingslytter);
		hentDager(0);
		/**
		 * Oppretter tekstfelt for v�rdataene.
		 */
		minTemp=new JTextField(10);
		maxTemp=new JTextField(10);
		nedb�r=new JTextField(10);
		
		/**
		 * Alle tekstfeltene settes false inntil et fylke og sted er valgt.
		 */
//		minTemp.setEditable(false);
//		maxTemp.setEditable(false);
//		nedb�r.setEditable(false);
		
		/**
		 * Alle tekstfeltene f�r skrivefeltlytter som KeyListener.
		 */
		minTemp.addKeyListener(skrivefeltlytter);
		maxTemp.addKeyListener(skrivefeltlytter);
		nedb�r.addKeyListener(skrivefeltlytter);
		
		/**
		 * Oppretter knappen for � utf�re en registrering av v�rdata.
		 */
		registrer=new JButton("Registrer v�rdata");
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
		grid.add(new JLabel("Nedb�rsmengde i mm"));
		grid.add(nedb�r);
		grid.add(new JLabel("Velg dato"));
		grid.add(dato);
		grid.add(new JLabel(""));
		grid.add(registrer);
	}
	
	/**
	 * @author Henrik Hermansen, B�rd Skeie
	 * Privat lytterklasse av typen ActionListener.
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==fylke)
				hentSteder(fylke.getSelectedIndex());
			if(e.getSource()==l�r || e.getSource()== lm�ned)
				hentDager(ldag.getSelectedIndex());
			if(e.getSource()==registrer)
			{
				String resultat = Registrering.registrerData(minTemp, maxTemp, nedb�r, sl, sted, fylke, l�r, lm�ned, ldag, panel);
				if(resultat.equals("Data ble satt inn i tabellen")) //En svak test med tanke p� endringer
				{
					fylke.setSelectedIndex(0);
					sted.setSelectedIndex(0);
					minTemp.setText("");
					maxTemp.setText("");
					nedb�r.setText("");
					ldag.setSelectedIndex(0);
					lm�ned.setSelectedIndex(0);
					l�r.setSelectedIndex(0);
				}
				JOptionPane.showMessageDialog(panel, resultat, "Registrering av v�rdata", JOptionPane.INFORMATION_MESSAGE);
			}
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
