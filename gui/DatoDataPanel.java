package gui;

import java.awt.event.*;
import javax.swing.*;

import logic.FinnData;

import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class DatoDataPanel extends MetroPanel
{
	private JButton hentData;
	
	/**
	 * Konstruktør som setter opp layoutet
	 * @author Lars Smeby
	 * @param sl	Mottar stedlisten til hovedvinduet
	 */
	public DatoDataPanel(Stedliste sl)
	{
		super(sl);
		
		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		lår.addActionListener(handlingslytter);
		lmåned.addActionListener(handlingslytter);
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
	 * Genererer en tabell og tegner den ut på panelet
	 * @author Lars Smeby
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
	 * Handlingslytter til værdata for dato.
	 * @author Bård Skeie
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == hentData)
			{
				Object[][] data = FinnData.FinnDatoVaer(sl, fylke, lår, lmåned, ldag, panel);
				if(data != null)
					genererTabell(data);
			}
			if(e.getSource() == lår || e.getSource() == lmåned)
				hentDager(ldag.getSelectedIndex());
		}
	}
}
