package gui;

import data.*;
import javax.swing.*;
import java.awt.event.*;

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
	private JComboBox fylke;
	JButton regSted;
	Stedliste sl;
	
	/**
	 * 
	 * @param mv	for å kunne referere til hovedvinduet hvis det trengs
	 * 
	 * Konstruktør. Oppretter felt og knapper, og setter inn i et gridlayout.
	 * Setter gridlayoutet inn i et borderlayout.
	 */
	public StedRegPanel()
	{
		super();
		sl = new Stedliste(); //Må motta stedliste fra hovedvindu
		
		navn = new JTextField(20);
		fylke = new JComboBox(Sted.fylkesliste);
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
	 * Foretar inputvalidering og gir tilbakemelding.
	 */
	private class RegStedLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String n = navn.getText();
			if (n == null || n.length() < 2)
			{
				JOptionPane.showMessageDialog(panel, "Skriv inn et stedsnavn", "Ufullstendig informasjon", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (sl.finnSted(n, fylke.getSelectedIndex()) != null)
			{
				JOptionPane.showMessageDialog(panel, "Dette stedet eksisterer allerede i dette fylket", "Eksisterende stedsnavn", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			sl.settInn(new Sted(n, fylke.getSelectedIndex()));
			JOptionPane.showMessageDialog(panel, n+" ble registrert i "+fylke.getSelectedItem().toString(), "Sted registrert", JOptionPane.INFORMATION_MESSAGE);
			navn.setText("");
			fylke.setSelectedIndex(0);
		}
	}
}
