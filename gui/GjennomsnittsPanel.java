/**
 * Inneholder klassen GjennomsnittsPanel.
 * @author Lars Smeby
 * @since 12.05.2011
 * @updated 18.05.2011
 * @version	1
 */
package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import logic.Gjennomsnitt;

import data.Stedliste;

/**
 *	Klassen er en subklasse av MetroPanel, og representerer gui og lyttere
 *	for panelet hvor man henter ut gjennomsnittsverdier.
 */
public class GjennomsnittsPanel extends MetroPanel 
{
	private JPanel fylkepanel, stedpanel;
	private JButton hentData;
	private JRadioButton rland, rfylke, rsted;
	private ButtonGroup omfangsgruppe;
	

	/**
	 * Konstruktør som setter layoutet på panelet
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 */
	public GjennomsnittsPanel(Stedliste sl) 
	{
		super(sl);
		
		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		
		fylke.setEnabled(false);
		sted.setEnabled(false);
		
		omfangsgruppe = new ButtonGroup();
		rland = new JRadioButton("Hele landet", true);
		rfylke = new JRadioButton("", false);
		rsted = new JRadioButton("", false);
		rland.addActionListener(handlingslytter);
		rfylke.addActionListener(handlingslytter);
		rsted.addActionListener(handlingslytter);
		omfangsgruppe.add(rland);
		omfangsgruppe.add(rfylke);
		omfangsgruppe.add(rsted);
		
		fylkepanel = new JPanel(new BorderLayout());
		fylkepanel.add(rfylke, BorderLayout.LINE_START);
		fylkepanel.add(fylke, BorderLayout.CENTER);
		
		stedpanel = new JPanel(new BorderLayout());
		stedpanel.add(rsted, BorderLayout.LINE_START);
		stedpanel.add(sted, BorderLayout.CENTER);
						
		hentData = new JButton("Hent gjennomsnittsverdier");
		hentData.addActionListener(handlingslytter);
		
		grid.add(new JLabel("Hent gjennomsnittsdata fra"));
		grid.add(rland);
		grid.add(new JLabel(""));
		grid.add(fylkepanel);
		grid.add(new JLabel(""));
		grid.add(stedpanel);
		grid.add(new JLabel("Velg år"));
		grid.add(lår);
		grid.add(new JLabel(""));
		grid.add(hentData);
	}
	
	/**
	 * Genererer en tabell og tegner den ut på panelet
	 * @author Lars Smeby
	 * @param data	Dataen som tabellen skal inneholde
	 */
	public void genererTabell(Object[][] data)
	{
		super.genererTabell(data);
		tabell.removeColumn(tabell.getColumnModel().getColumn(1));
		tabell.getColumnModel().getColumn(0).setHeaderValue("Tidsperiode");
		tabell.getColumnModel().getColumn(1).setHeaderValue("Total nedbør");
		tabell.getColumnModel().getColumn(2).setHeaderValue("Gjennomsnittsnedbør");
		tabell.getColumnModel().getColumn(3).setHeaderValue("Gjennomsnittstemperatur");
		panel.validate();
		panel.repaint();
	}
	
	/**
	 * Lytter på knapper og felt
	 * @author Lars Smeby
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{	
			if(e.getSource() == fylke)
			{
				hentSteder(fylke.getSelectedIndex());
			}
			if(rland.isSelected())
			{
				fylke.setEnabled(false);
				sted.setEnabled(false);
			}
			if(rfylke.isSelected())
			{
				fylke.setEnabled(true);
				sted.setEnabled(false);
			}
			if(rsted.isSelected())
			{
				fylke.setEnabled(true);
				sted.setEnabled(true);
			}
			if(e.getSource() == hentData)
			{
				Object[][] data = Gjennomsnitt.finnGjennomsnitt(sl, lår, panel, fylke, sted, rland, rfylke, rsted);
				if(data != null)
					genererTabell(data);
			} // end of if(...)
		} // end of actionPerformed(...)
	} // end of class HandlingsLytter
} // end of class GjennomsnittsPanel