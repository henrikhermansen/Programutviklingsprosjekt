/**
 * Inneholder klassen EkstremverdiPanel.
 * @author Henrik Hermansen
 * @since 12.05.2011
 * @updated 18.05.2011
 * @version	1
 */
package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import logic.FinnEkstremData;

import data.Stedliste;

/**
 *	Klassen er en subklasse av MetroPanel, og representerer gui og lyttere
 *	til panelet for henting av ekstremdata.
 */
public class EkstremverdiPanel extends MetroPanel
{
	private JPanel stedtype, datotype, spesAvgEnkelt, spesType1, spesType2;
	private ButtonGroup stedtypegruppe, datotypegruppe, spesAvgEnkeltGruppe, spesTypeGruppe;
	protected JRadioButton stedLandet, stedFylke, stedSted, rdag, rm�ned, r�r, rEnkelverdi, rAvgverdi, rMinNedb�r, rNedb�r, rMintemp, rMaxtemp;
	private JButton hentData;
	
	/**
	 * Konstrukt�r som setter opp panelet for s�king etter ekstremverdier
	 * @author Henrik Hermansen
	 * @param sl hoved-stedlisten i programmet
	 */
	public EkstremverdiPanel(Stedliste sl)
	{
		super(sl);

		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		l�r.addActionListener(handlingslytter);
		lm�ned.addActionListener(handlingslytter);
		hentDager(0);
		
		stedtype = new JPanel(new FlowLayout(FlowLayout.LEFT));
		stedtypegruppe = new ButtonGroup();
		stedLandet = new JRadioButton("Hele landet", true);
		stedFylke = new JRadioButton("Et fylke", false);
		stedSted = new JRadioButton("Et sted", false);
		stedLandet.addActionListener(handlingslytter);
		stedFylke.addActionListener(handlingslytter);
		stedSted.addActionListener(handlingslytter);
		stedtypegruppe.add(stedLandet);
		stedtypegruppe.add(stedFylke);
		stedtypegruppe.add(stedSted);
		stedtype.add(stedLandet);
		stedtype.add(stedFylke);
		stedtype.add(stedSted);
		
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
		
		spesAvgEnkelt = new JPanel(new FlowLayout(FlowLayout.LEFT));
		spesAvgEnkeltGruppe = new ButtonGroup();
		rEnkelverdi = new JRadioButton("Enkeltverdi", true);
		rAvgverdi = new JRadioButton("Gjennomsnitt", false);
		rEnkelverdi.addActionListener(handlingslytter);
		rAvgverdi.addActionListener(handlingslytter);
		spesAvgEnkeltGruppe.add(rEnkelverdi);
		spesAvgEnkeltGruppe.add(rAvgverdi);
		spesAvgEnkelt.add(rEnkelverdi);
		spesAvgEnkelt.add(rAvgverdi);
		
		spesType1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		spesType2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		spesTypeGruppe = new ButtonGroup();
		rMinNedb�r = new JRadioButton("Minst nedb�r", true);
		rNedb�r = new JRadioButton("Mest nedb�r", false);
		rMintemp = new JRadioButton("Kaldest", false);
		rMaxtemp = new JRadioButton("Varmest", false);
		rMinNedb�r.addActionListener(handlingslytter);
		rNedb�r.addActionListener(handlingslytter);
		rMintemp.addActionListener(handlingslytter);
		rMaxtemp.addActionListener(handlingslytter);
		spesTypeGruppe.add(rMinNedb�r);
		spesTypeGruppe.add(rNedb�r);
		spesTypeGruppe.add(rMintemp);
		spesTypeGruppe.add(rMaxtemp);
		spesType1.add(rMinNedb�r);
		spesType1.add(rNedb�r);
		spesType2.add(rMintemp);
		spesType2.add(rMaxtemp);
		
		hentData = new JButton("Hent registrerte data");
		hentData.addActionListener(handlingslytter);
		
		grid.add(new JLabel("Velg s�keomr�de"));
		grid.add(stedtype);
		grid.add(new JLabel("Velg fylke"));
		grid.add(fylke);
		grid.add(new JLabel("Velg sted"));
		grid.add(sted);
		grid.add(new JLabel("Velg type s�k"));
		grid.add(datotype);
		grid.add(new JLabel("Velg dato"));
		grid.add(dato);
		grid.add(new JLabel("Spesifiser s�ket"));
		grid.add(spesAvgEnkelt);
		grid.add(spesType1);
		grid.add(spesType2);
		grid.add(new JLabel(""));
		grid.add(hentData);
		
		fylke.setEnabled(false);
		sted.setEnabled(false);
	}
	
	/**
	 * @author Henrik Hermansen
	 * @param data	Arrayen som inneholder dataene som skal skrives til tabellen
	 */
	public void genererTabell(Object[][] data,JRadioButton rMinNedb�r, JRadioButton rNedb�r,JRadioButton rMintemp,JRadioButton rMaxtemp)
	{
		super.genererTabell(data);
		if(rNedb�r.isSelected() || rMinNedb�r.isSelected())
		{
			tabell.removeColumn(tabell.getColumnModel().getColumn(3));
			tabell.removeColumn(tabell.getColumnModel().getColumn(3));
		}
		if(rMintemp.isSelected())
		{
			tabell.removeColumn(tabell.getColumnModel().getColumn(2));
			tabell.removeColumn(tabell.getColumnModel().getColumn(3));
		}
		if(rMaxtemp.isSelected())
		{
			tabell.removeColumn(tabell.getColumnModel().getColumn(2));
			tabell.removeColumn(tabell.getColumnModel().getColumn(2));
		}
		if(rAvgverdi.isSelected())
		{
			tabell.removeColumn(tabell.getColumnModel().getColumn(1));
			tabell.moveColumn(2, 0);
		}
		else
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
			if(e.getSource() == hentData)
			{
				Object[][] data = FinnEkstremData.finnData(sl, panel, stedLandet, stedFylke, stedSted, fylke, sted, rdag, rm�ned, r�r, ldag, lm�ned, l�r, rEnkelverdi, rAvgverdi, rMinNedb�r, rNedb�r, rMintemp, rMaxtemp);
				if(data != null)
					genererTabell(data,rMinNedb�r,rNedb�r,rMintemp,rMaxtemp);
			}
			if(e.getSource() == fylke)
			{
				hentSteder(fylke.getSelectedIndex());
			}
			if(e.getSource() == l�r || e.getSource() == lm�ned)
			{
				hentDager(ldag.getSelectedIndex());
			}
			if(stedLandet.isSelected())
			{
				fylke.setEnabled(false);
				sted.setEnabled(false);
				rAvgverdi.setEnabled(true);
			}
			if(stedFylke.isSelected())
			{
				fylke.setEnabled(true);
				sted.setEnabled(false);
				rAvgverdi.setEnabled(true);
			}
			if(stedSted.isSelected())
			{
				fylke.setEnabled(true);
				sted.setEnabled(true);
				if(rAvgverdi.isSelected())
					rEnkelverdi.setSelected(true);
				rAvgverdi.setEnabled(false);
				if(rdag.isSelected())
					rm�ned.setSelected(true);
				rdag.setEnabled(false);
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
			}
			if(rEnkelverdi.isSelected())
			{
				if(rm�ned.isSelected() || r�r.isSelected())
					ldag.setEnabled(false);
				else
					ldag.setEnabled(true);
				if(!stedSted.isSelected())
					rdag.setEnabled(true);
				stedSted.setEnabled(true);
			}
			if(rAvgverdi.isSelected())
			{
				ldag.setEnabled(false);
				rdag.setEnabled(false);
				if(!rm�ned.isSelected() && !r�r.isSelected())
					rm�ned.setSelected(true);
				if(stedSted.isSelected())
					stedFylke.setSelected(true);
				stedSted.setEnabled(false);
			} // end of if(...)
		} // end of actionPerformed(...)
	} // end of class HandlingsLytter
} // end of class EkstremverdiPanel