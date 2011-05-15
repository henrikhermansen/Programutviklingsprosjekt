/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import logic.Registrering;

import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
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
		sletteSted=new JButton("Slett Sted");
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
				String stedString = (String) sted.getSelectedItem();
				if(stedString == null)
				{
					JOptionPane.showMessageDialog(panel, "Ingen steder valgt", "Slett sted", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String resultat = Registrering.slettSted(fylke, stedString, sl, panel);
				if(resultat == "")
					return;
				JOptionPane.showMessageDialog(panel, resultat, "Sted slettet", JOptionPane.INFORMATION_MESSAGE);
				hentSteder(fylke.getSelectedIndex());
			}
		}
	}
}


