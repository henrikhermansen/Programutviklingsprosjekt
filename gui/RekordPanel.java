/**
 * Inneholder klassen RekordPanel.
 * @author Henrik Hermansen
 * @since 18.05.2011
 * @version	1 18.05.2011
 */
package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import logic.FinnRekordData;

import data.Stedliste;

/**
 *	Klassen er en subklasse av MetroPanel, og representerer gui og lyttere
 *	for panelet hvor utskrift av rekorder foregår.
 */
public class RekordPanel extends MetroPanel
{
	private JPanel tekst,type;
	private ButtonGroup typeGruppe;
	protected JRadioButton rNedbør, rMintemp, rMaxtemp;
	
	/**
	 * Konstruktør som setter opp panelet for søking etter rekordverdier
	 * Skrevet av: Henrik Hermansen
	 * @param sl hoved-stedlisten i programmet
	 */
	public RekordPanel(Stedliste sl)
	{
		super(sl);

		HandlingsLytter handlingslytter = new HandlingsLytter();
		
		type = new JPanel(new GridLayout(3, 1));
		typeGruppe = new ButtonGroup();
		rNedbør = new JRadioButton("Nedbørsrekorder", false);
		rMintemp = new JRadioButton("Kulderekorder", false);
		rMaxtemp = new JRadioButton("Varmerekorder", false);
		rNedbør.addActionListener(handlingslytter);
		rMintemp.addActionListener(handlingslytter);
		rMaxtemp.addActionListener(handlingslytter);
		typeGruppe.add(rNedbør);
		typeGruppe.add(rMintemp);
		typeGruppe.add(rMaxtemp);
		type.add(rNedbør);
		type.add(rMintemp);
		type.add(rMaxtemp);
		
		tekst = new JPanel(new BorderLayout());
		tekst.add(new JLabel("Velg type rekord"),BorderLayout.PAGE_START);
		grid.add(tekst);
		grid.add(type);
	}
	
	/**
	 * Skrevet av: Henrik Hermansen
	 * @param data	Arrayen som inneholder dataene som skal skrives til tabellen
	 */
	public void genererTabell(Object[][] data,JRadioButton rNedbør,JRadioButton rMintemp,JRadioButton rMaxtemp)
	{
		super.genererTabell(data);
		if(rNedbør.isSelected())
		{
			tabell.removeColumn(tabell.getColumnModel().getColumn(3));
			tabell.removeColumn(tabell.getColumnModel().getColumn(3));
			tabell.getColumnModel().getColumn(2).setHeaderValue("Nedbørsrekorder");
		}
		if(rMintemp.isSelected())
		{
			tabell.removeColumn(tabell.getColumnModel().getColumn(2));
			tabell.removeColumn(tabell.getColumnModel().getColumn(3));
			tabell.getColumnModel().getColumn(2).setHeaderValue("Kulderekorder");
		}
		if(rMaxtemp.isSelected())
		{
			tabell.removeColumn(tabell.getColumnModel().getColumn(2));
			tabell.removeColumn(tabell.getColumnModel().getColumn(2));
			tabell.getColumnModel().getColumn(2).setHeaderValue("Varmerekorder");
		}
		tabell.moveColumn(3, 0);
		tabell.moveColumn(4, 0);
		tabell.setAutoCreateRowSorter(true);
		panel.validate();
		panel.repaint();
	}
	
	/**
	 * Privat lytterklasse for elementene i panelet.
	 * Skrevet av: Henrik Hermansen
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == rNedbør || e.getSource() == rMintemp || e.getSource() == rMaxtemp)
			{
				Object[][] data = FinnRekordData.finnData(sl, panel, rNedbør, rMintemp, rMaxtemp);
				if(data != null)
					genererTabell(data,rNedbør,rMintemp,rMaxtemp);
			} // end of if(...)
		} // end of actionPerformed(...)
	} // end of class HanslingsLytter
} // end of class RekordPanel