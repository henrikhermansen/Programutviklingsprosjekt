package gui;

import data.*;
import javax.swing.*;
import java.awt.event.*;

import logic.Registrering;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class StedRegPanel extends MetroPanel
{
	/**
	 * navn		inputfelt for stedsnavn
	 * fylke	listeboks med fylkene
	 * regSted	knapp for å registrere
	 * sl		refererer til klassen Stedsliste og brukes til aksessere Stedslistes metoder
	 */
	private JTextField navn;
	JButton regSted;
	
	/**
	 * @author Lars Smeby
	 * @param sl	Referanse til stedlisten
	 * 
	 * Konstruktør. Oppretter felt og knapper.
	 */
	public StedRegPanel(Stedliste sl)
	{
		super(sl);
		
		navn = new JTextField(20);
		regSted = new JButton("Registrer sted");
		
		RegStedLytter regStedLytter = new RegStedLytter();
		regSted.addActionListener(regStedLytter);
		navn.addActionListener(regStedLytter);

		grid.add(new JLabel("Stedsnavn:"));
		grid.add(navn);
		grid.add(new JLabel("Fylke:"));
		grid.add(fylke);
		grid.add(new JLabel(""));
		grid.add(regSted);
	}
	
	/**
	 * @author Lars Smeby
	 * Indre klasse som lytter på knappen og tekstfelt.
	 * Foretar inputvalidering og gir tilbakemelding.
	 */
	private class RegStedLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(panel, 
							Registrering.registrerSted(navn, fylke, sl), 
							"Registrering av sted", 
							JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
