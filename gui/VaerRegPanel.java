/**
 * Inneholder klassen VaerRegPanel.
 * @author Henrik Hermansen
 * @author B�rd Skeie
 * @since 28.04.2011
 * @updated 15.05.2011
 * @version	1
 */
package gui;

import java.awt.event.*;
import javax.swing.*;

import logic.Registrering;
import logic.SkrivMelding;
import data.Stedliste;

/**
 *	Klassen er en subklasse av MetroPanel, og representerer gui og lyttere
 *	for panelet hvor man registrerer v�rdata.
 */
public class VaerRegPanel extends MetroPanel
{
	private JTextField minTemp, maxTemp, nedb�r;
	private JButton registrer;
	
	/**
	 * Konstrukt�r, oppretter og tegner panelet
	 * @author Henrik Hermansen
	 * @param sl	Referanse til stedlisten
	 */
	public VaerRegPanel(Stedliste sl)
	{
		super(sl);
		
		HandlingsLytter handlingslytter=new HandlingsLytter();
		
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		l�r.addActionListener(handlingslytter);
		lm�ned.addActionListener(handlingslytter);
		hentDager(0);
		
		minTemp=new JTextField(10);
		maxTemp=new JTextField(10);
		nedb�r=new JTextField(10);
		
		registrer=new JButton("Registrer v�rdata");
		registrer.addActionListener(handlingslytter);
		
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
	 * Privat lytterklasse av typen ActionListener.
	 * @author Henrik Hermansen
	 * @author B�rd Skeie
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
				if(resultat.equals("Data ble satt inn i tabellen/I")) //En svak test med tanke p� endringer
				{
					fylke.setSelectedIndex(0);
					try
					{
					sted.setSelectedIndex(0);
					}
					catch(IllegalArgumentException iae)
					{
						//Forekommer hvis det ikke er registrert noen steder i Akershus
						hentSteder(fylke.getSelectedIndex());
					}
					minTemp.setText("");
					maxTemp.setText("");
					nedb�r.setText("");
					ldag.setSelectedIndex(0);
					lm�ned.setSelectedIndex(0);
					l�r.setSelectedIndex(0);
				}
				SkrivMelding.skriv(resultat, panel);
			} // end of if(...)
		} // end of actionPerformed(...)
	} // end of class HandlingsLytter
} // end of class VaerRegPanel