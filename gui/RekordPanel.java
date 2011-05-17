package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import logic.FinnRekordData;

import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class RekordPanel extends MetroPanel
{
	private JPanel type;
	private ButtonGroup typeGruppe;
	protected JRadioButton rNedb�r, rMintemp, rMaxtemp;
	
	/**
	 * Konstrukt�r som setter opp panelet for s�king etter rekordverdier
	 * @author Henrik Hermansen
	 * @param sl hoved-stedlisten i programmet
	 */
	public RekordPanel(Stedliste sl) {
		super(sl);

		HandlingsLytter handlingslytter = new HandlingsLytter();
		
		type = new JPanel(new GridLayout(3, 1));
		typeGruppe = new ButtonGroup();
		rNedb�r = new JRadioButton("Nedb�rsrekorder", false);
		rMintemp = new JRadioButton("Kulderekorder", false);
		rMaxtemp = new JRadioButton("Varmerekorder", false);
		rNedb�r.addActionListener(handlingslytter);
		rMintemp.addActionListener(handlingslytter);
		rMaxtemp.addActionListener(handlingslytter);
		typeGruppe.add(rNedb�r);
		typeGruppe.add(rMintemp);
		typeGruppe.add(rMaxtemp);
		type.add(rNedb�r);
		type.add(rMintemp);
		type.add(rMaxtemp);
		
		grid.add(new JLabel("Velg type rekord"));
		grid.add(type);
	}
	
	/**
	 * @author Henrik Hermansen
	 * @param data	Arrayen som inneholder dataene som skal skrives til tabellen
	 */
	public void genererTabell(Object[][] data,JRadioButton rNedb�r,JRadioButton rMintemp,JRadioButton rMaxtemp)
	{
		super.genererTabell(data);
		if(rNedb�r.isSelected())
		{
			tabell.removeColumn(tabell.getColumnModel().getColumn(3));
			tabell.removeColumn(tabell.getColumnModel().getColumn(3));
			tabell.getColumnModel().getColumn(2).setHeaderValue("Nedb�rsrekorder");
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
		tabell.setAutoCreateRowSorter(true);
		panel.validate();
		panel.repaint();
	}
	
	/**
	 * Privat lytterklasse for elementene i panelet.
	 * @author Henrik Hermansen
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == rNedb�r || e.getSource() == rMintemp || e.getSource() == rMaxtemp)
			{
				Object[][] data = FinnRekordData.finnData(sl, panel, rNedb�r, rMintemp, rMaxtemp);
				if(data != null)
					genererTabell(data,rNedb�r,rMintemp,rMaxtemp);
			}
		}
	}
}
