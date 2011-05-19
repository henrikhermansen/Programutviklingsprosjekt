/**
 * Inneholder klassen DatoDataPanel.
 * @author Lars Smeby
 * @author B�rd Skeie
 * @since 04.05.2011
 * @version	1 13.05.2011
 */
package gui;

import java.awt.event.*;
import javax.swing.*;

import logic.FinnData;

import data.Stedliste;

/**
 *	Klassen er en subklasse til MetroPanel, og representerer gui og lyttere
 *	til panelet for henting av data for en bestemt dato.
 */
public class DatoDataPanel extends MetroPanel
{
	private JButton hentData;
	
	/**
	 * Konstrukt�r som setter opp layoutet
	 * Skrevet av: Lars Smeby
	 * @param sl	Mottar stedlisten til hovedvinduet
	 */
	public DatoDataPanel(Stedliste sl)
	{
		super(sl);
		
		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		l�r.addActionListener(handlingslytter);
		lm�ned.addActionListener(handlingslytter);
		hentDager(0);
		
		hentData = new JButton("Hent registrerte data");
		hentData.addActionListener(handlingslytter);
		
		grid.add(new JLabel("Velg fylke"));
		grid.add(fylke);
		grid.add(new JLabel("Velg dato"));
		grid.add(dato);
		grid.add(new JLabel(""));
		grid.add(hentData);
	}
	
	/**
	 * Genererer en tabell og tegner den ut p� panelet
	 * Skrevet av: Lars Smeby
	 * @param data	Arrayen som inneholder dataene som skal skrives til tabellen
	 */
	public void genererTabell(Object[][] data)
	{
		super.genererTabell(data);
		tabell.removeColumn(tabell.getColumnModel().getColumn(1));
		panel.validate();
		panel.repaint();
	}
	
	/**
	 * Handlingslytter til v�rdata for dato.
	 * Skrevet av: B�rd Skeie
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == hentData)
			{
				Object[][] data = FinnData.FinnDatoVaer(sl, fylke, l�r, lm�ned, ldag, panel);
				if(data != null)
					genererTabell(data);
			}
			if(e.getSource() == l�r || e.getSource() == lm�ned)
				hentDager(ldag.getSelectedIndex());
		} // end of actionPerformed(...)
	} // end of class Handlingslytter
} // end of class DatoDataPanel