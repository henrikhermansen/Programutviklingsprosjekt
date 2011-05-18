/**
 * Inneholder klassen StedSlettPanel.
 * @author Bård Skeie
 * @since 15.05.2011
 * @updated 18.05.2011
 * @version	1
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import logic.Registrering;
import logic.SkrivMelding;
import data.Stedliste;

/**
 *	Klassen er en subklasse av MetroPanel, og representerer gui og lyttere
 *	for panelet der man sletter steder.
 */
public class StedSlettPanel extends MetroPanel 
{
	private JButton sletteSted;
	
	/**
	 * Konstruktør. Oppretter felt og knapp.
	 * @author Bård Skeie
	 * @param sl Referanse til stedliste
	 */
	public StedSlettPanel(Stedliste sl)
	{
		super(sl);
		
		/**
		 * handlingslytter	En ActionListener for JComboBox'ene og JButton.
		 */
		HandlingsLytter handlingslytter=new HandlingsLytter();
		
		/**
		 * En JComboBox med liste over alle fylkene. handlingslytter legges på som ActionListener.
		 */
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
				
		/**
		 * Oppretter knappen for å utføre en registrering av værdata.
		 */
		sletteSted=new JButton("Slett sted");
		sletteSted.addActionListener(handlingslytter);
		
		/**
		 * Legger inn alt av innhold i panelet.
		 */
		grid.add(new JLabel("Velg fylke"));
		grid.add(fylke);
		grid.add(new JLabel("Velg sted"));
		grid.add(sted);
		grid.add(new JLabel(""));
		grid.add(sletteSted);
	}
	
	/**
	 * Privat lytterklasse av typen ActionListener.
	 * @author Bård Skeie
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==fylke)
				hentSteder(fylke.getSelectedIndex());
			
			if(e.getSource()==sletteSted)
			{
				String resultat = Registrering.slettSted(fylke, sted, sl, panel);
				if(resultat == null)
					return;
				SkrivMelding.skriv(resultat, panel);
				hentSteder(fylke.getSelectedIndex());
			} // end of if(...)
		} // end of actionPerformed(...)
	} // end of class HandlingsLytter
} // end of class StedSlettPanel