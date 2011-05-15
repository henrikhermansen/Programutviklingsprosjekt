package gui;

import data.*;
import javax.swing.*;
import java.awt.event.*;

import logic.Registrering;
import logic.SkrivMelding;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class StedRegPanel extends MetroPanel
{
	private JTextField navn;
	private JButton regSted;
	
	/**
	 * Konstruktør. Oppretter felt og knapper.
	 * @author Lars Smeby
	 * @param sl	Referanse til stedlisten
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
	 * Indre klasse som lytter på knappen og tekstfelt.
	 * @author Lars Smeby
	 */
	private class RegStedLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String melding = Registrering.registrerSted(navn, fylke, sl);
			SkrivMelding.skriv(melding, panel);
			navn.setText("");
			fylke.setSelectedIndex(0);
		}
	}
}
