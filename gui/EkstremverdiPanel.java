package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import logic.FinnEkstremData;

import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class EkstremverdiPanel extends MetroPanel
{
	private JPanel stedtype, datotype, spesAvgEnkelt, spesType;
	private ButtonGroup stedtypegruppe, datotypegruppe, spesAvgEnkeltGruppe, spesTypeGruppe;
	protected JRadioButton stedLandet, stedFylke, stedSted, rdag, rmåned, rår, rEnkelverdi, rAvgverdi, rNedbør, rMintemp, rMaxtemp;
	private JButton hentData;
	
	/**
	 * Konstruktør som setter opp panelet for søking etter ekstremverdier
	 * @author Henrik Hermansen
	 * @param sl hoved-stedlisten i programmet
	 */
	public EkstremverdiPanel(Stedliste sl) {
		super(sl);

		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		lår.addActionListener(handlingslytter);
		lmåned.addActionListener(handlingslytter);
		hentDager(0);
		
		stedtype = new JPanel(new GridLayout(0,3));
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
		
		spesAvgEnkelt = new JPanel(new GridLayout(0,2));
		spesAvgEnkeltGruppe = new ButtonGroup();
		rEnkelverdi = new JRadioButton("Enkeltverdi", true);
		rAvgverdi = new JRadioButton("Gjennomsnitt", false);
		rEnkelverdi.addActionListener(handlingslytter);
		rAvgverdi.addActionListener(handlingslytter);
		spesAvgEnkeltGruppe.add(rEnkelverdi);
		spesAvgEnkeltGruppe.add(rAvgverdi);
		spesAvgEnkelt.add(rEnkelverdi);
		spesAvgEnkelt.add(rAvgverdi);
		
		spesType = new JPanel(new GridLayout(0,3));
		spesTypeGruppe = new ButtonGroup();
		rNedbør = new JRadioButton("Nedbør", true);
		rMintemp = new JRadioButton("Mintemp", false);
		rMaxtemp = new JRadioButton("Maxtemp", false);
		rNedbør.addActionListener(handlingslytter);
		rMintemp.addActionListener(handlingslytter);
		rMaxtemp.addActionListener(handlingslytter);
		spesTypeGruppe.add(rNedbør);
		spesTypeGruppe.add(rMintemp);
		spesTypeGruppe.add(rMaxtemp);
		spesType.add(rNedbør);
		spesType.add(rMintemp);
		spesType.add(rMaxtemp);
		
		hentData = new JButton("Hent registrerte data");
		hentData.addActionListener(handlingslytter);
		
		grid.add(new JLabel("Velg søkeområde"));
		grid.add(stedtype);
		grid.add(new JLabel("Velg fylke"));
		grid.add(fylke);
		grid.add(new JLabel("Velg sted"));
		grid.add(sted);
		grid.add(new JLabel("Velg type søk"));
		grid.add(datotype);
		grid.add(new JLabel("Velg dato"));
		grid.add(dato);
		grid.add(new JLabel("Spesifiser søket"));
		grid.add(spesAvgEnkelt);
		grid.add(new JLabel(""));
		grid.add(spesType);
		grid.add(new JLabel(""));
		grid.add(hentData);
		
		fylke.setEnabled(false);
		sted.setEnabled(false);
	}
	
	/**
	 * @author Lars Smeby
	 * @param data	Arrayen som inneholder dataene som skal skrives til tabellen
	 */
	public void genererTabell(Object[][] data,JRadioButton rNedbør,JRadioButton rMintemp,JRadioButton rMaxtemp)
	{
		super.genererTabell(data);
		if(rNedbør.isSelected())
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
			tabell.removeColumn(tabell.getColumnModel().getColumn(1));
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
				Object[][] data = FinnEkstremData.finnData(sl, panel, stedLandet, stedFylke, stedSted, fylke, sted, rdag, rmåned, rår, ldag, lmåned, lår, rEnkelverdi, rAvgverdi, rNedbør, rMintemp, rMaxtemp);
				if(data != null)
					genererTabell(data,rNedbør,rMintemp,rMaxtemp);
			}
			if(e.getSource() == fylke)
			{
				hentSteder(fylke.getSelectedIndex());
			}
			if(e.getSource() == lår || e.getSource() == lmåned)
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
					rmåned.setSelected(true);
				rdag.setEnabled(false);
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
			}
			if(rEnkelverdi.isSelected())
			{
				if(rmåned.isSelected() || rår.isSelected())
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
				if(!rmåned.isSelected() && !rår.isSelected())
					rmåned.setSelected(true);
				if(stedSted.isSelected())
					stedFylke.setSelected(true);
				stedSted.setEnabled(false);
			}
		}
	}
}
