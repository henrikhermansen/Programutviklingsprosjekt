/**
 * Inneholder klassen StedDataPanel.
 * @author Lars Smeby
 * @since 04.05.2011
 * @version	1 13.05.2011
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import logic.FinnData;

import data.Stedliste;

/**
 *	Klassen er en subklasse av MetroPanel, og representerer gui og lyttere
 *	for panelet der data for sted blir hentet ut.
 */
public class StedDataPanel extends MetroPanel
{
	private JPanel datotype;
	private ButtonGroup datotypegruppe;
	protected JRadioButton rdag, rmåned, rår;
	private JButton hentData;
	
	/**
	 * Konstruktør, oppretter og tegner panelet.
	 * Skrevet av: Lars Smeby
	 * @param sl	Stedlisten til hovedvinduet
	 */
	public StedDataPanel(Stedliste sl)
	{
		super(sl);
		
		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		lår.addActionListener(handlingslytter);
		lmåned.addActionListener(handlingslytter);
		hentDager(0);
		
		datotype = new JPanel(new GridLayout(0,3));
		datotypegruppe = new ButtonGroup();
		rdag = new JRadioButton("Dag", true);
		rmåned = new JRadioButton("Måned", false);
		rår = new JRadioButton("År", false);
		rdag.addActionListener(handlingslytter);
		rmåned.addActionListener(handlingslytter);
		rår.addActionListener(handlingslytter);
		datotypegruppe.add(rdag);
		datotypegruppe.add(rmåned);
		datotypegruppe.add(rår);
		datotype.add(rdag);
		datotype.add(rmåned);
		datotype.add(rår);
		
		hentData = new JButton("Hent registrerte data");
		hentData.addActionListener(handlingslytter);
		
		grid.add(new JLabel("Velg fylke"));
		grid.add(fylke);
		grid.add(new JLabel("Velg sted"));
		grid.add(sted);
		grid.add(new JLabel("Velg type søk"));
		grid.add(datotype);
		grid.add(new JLabel("Velg dato"));
		grid.add(dato);
		grid.add(new JLabel(""));
		grid.add(hentData);
	}
	
	/**
	 * Genererer en tabell og tegner den ut på panelet
	 * Skrevet av: Lars Smeby
	 * @param data	Arrayen som inneholder dataene som skal skrives til tabellen
	 */
	public void genererTabell(Object[][] data)
	{
		super.genererTabell(data);
		tabell.removeColumn(tabell.getColumnModel().getColumn(0));
		panel.validate();
		panel.repaint();
	}
	
	/**
	 * Lytteklasse som lytter på knapper og felt
	 * Skrevet av: Lars Smeby
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == hentData)
			{
				if(rdag.isSelected())
				{
					Object[][] data = FinnData.finnDataSted(sl, panel, fylke, sted, ldag, lmåned, lår);
					if(data != null)
						genererTabell(data);
				}
				if(rmåned.isSelected())
				{
					Object[][] data = FinnData.finnDataSted(sl, panel, fylke, sted, lmåned, lår);
					if(data != null)
						genererTabell(data);
				}
				if(rår.isSelected())
				{
					Object[][] data = FinnData.finnDataSted(sl, panel, fylke, sted, lår);
					if(data != null)
						genererTabell(data);
				}
			}
			if(e.getSource() == fylke)
			{
				hentSteder(fylke.getSelectedIndex());
			}
			if(e.getSource() == lår || e.getSource() == lmåned)
			{
				hentDager(ldag.getSelectedIndex());
			}
			if(rdag.isSelected())
			{
				ldag.setEnabled(true);
				lmåned.setEnabled(true);
			}
			if(rmåned.isSelected())
			{
				ldag.setEnabled(false);
				lmåned.setEnabled(true);
			}
			if(rår.isSelected())
			{
				ldag.setEnabled(false);
				lmåned.setEnabled(false);
			} // end of if(...)
		} // end of actionPerformed(...)
	} // end of class HandlingsLytter
} // end of class StedDataPanel