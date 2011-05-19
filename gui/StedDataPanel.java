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
	protected JRadioButton rdag, rm�ned, r�r;
	private JButton hentData;
	
	/**
	 * Konstrukt�r, oppretter og tegner panelet.
	 * Skrevet av: Lars Smeby
	 * @param sl	Stedlisten til hovedvinduet
	 */
	public StedDataPanel(Stedliste sl)
	{
		super(sl);
		
		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		l�r.addActionListener(handlingslytter);
		lm�ned.addActionListener(handlingslytter);
		hentDager(0);
		
		datotype = new JPanel(new GridLayout(0,3));
		datotypegruppe = new ButtonGroup();
		rdag = new JRadioButton("Dag", true);
		rm�ned = new JRadioButton("M�ned", false);
		r�r = new JRadioButton("�r", false);
		rdag.addActionListener(handlingslytter);
		rm�ned.addActionListener(handlingslytter);
		r�r.addActionListener(handlingslytter);
		datotypegruppe.add(rdag);
		datotypegruppe.add(rm�ned);
		datotypegruppe.add(r�r);
		datotype.add(rdag);
		datotype.add(rm�ned);
		datotype.add(r�r);
		
		hentData = new JButton("Hent registrerte data");
		hentData.addActionListener(handlingslytter);
		
		grid.add(new JLabel("Velg fylke"));
		grid.add(fylke);
		grid.add(new JLabel("Velg sted"));
		grid.add(sted);
		grid.add(new JLabel("Velg type s�k"));
		grid.add(datotype);
		grid.add(new JLabel("Velg dato"));
		grid.add(dato);
		grid.add(new JLabel(""));
		grid.add(hentData);
	}
	
	/**
	 * Genererer en tabell og tegner den ut p� panelet
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
	 * Lytteklasse som lytter p� knapper og felt
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
					Object[][] data = FinnData.finnDataSted(sl, panel, fylke, sted, ldag, lm�ned, l�r);
					if(data != null)
						genererTabell(data);
				}
				if(rm�ned.isSelected())
				{
					Object[][] data = FinnData.finnDataSted(sl, panel, fylke, sted, lm�ned, l�r);
					if(data != null)
						genererTabell(data);
				}
				if(r�r.isSelected())
				{
					Object[][] data = FinnData.finnDataSted(sl, panel, fylke, sted, l�r);
					if(data != null)
						genererTabell(data);
				}
			}
			if(e.getSource() == fylke)
			{
				hentSteder(fylke.getSelectedIndex());
			}
			if(e.getSource() == l�r || e.getSource() == lm�ned)
			{
				hentDager(ldag.getSelectedIndex());
			}
			if(rdag.isSelected())
			{
				ldag.setEnabled(true);
				lm�ned.setEnabled(true);
			}
			if(rm�ned.isSelected())
			{
				ldag.setEnabled(false);
				lm�ned.setEnabled(true);
			}
			if(r�r.isSelected())
			{
				ldag.setEnabled(false);
				lm�ned.setEnabled(false);
			} // end of if(...)
		} // end of actionPerformed(...)
	} // end of class HandlingsLytter
} // end of class StedDataPanel