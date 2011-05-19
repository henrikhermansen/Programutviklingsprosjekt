/**
 * Inneholder klassen EkstremverdiPanel.
 * @author Henrik Hermansen
 * @since 12.05.2011
 * @version	1 18.05.2011
 */
package gui;

import java.awt.BorderLayout;
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
	private JPanel fylkepanel, stedpanel, datotype, spesAvgEnkelt, spesType1, spesType2;
	private ButtonGroup stedtypegruppe, datotypegruppe, spesAvgEnkeltGruppe, spesTypeGruppe;
	protected JRadioButton stedLandet, stedFylke, stedSted, rdag, rmåned, rår, rEnkelverdi, rAvgverdi, rMinNedbør, rNedbør, rMintemp, rMaxtemp;
	private JButton hentData;
	
	/**
	 * Konstruktør som setter opp panelet for søking etter ekstremverdier
	 * Skrevet av: Henrik Hermansen
	 * @param sl hoved-stedlisten i programmet
	 */
	public EkstremverdiPanel(Stedliste sl)
	{
		super(sl);

		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		lår.addActionListener(handlingslytter);
		lmåned.addActionListener(handlingslytter);
		hentDager(0);
		
		fylkepanel = new JPanel(new BorderLayout());
		stedtypegruppe = new ButtonGroup();
		stedLandet = new JRadioButton("Hele landet", true);
		stedFylke = new JRadioButton("", false);
		stedSted = new JRadioButton("", false);
		stedLandet.addActionListener(handlingslytter);
		stedFylke.addActionListener(handlingslytter);
		stedSted.addActionListener(handlingslytter);
		stedtypegruppe.add(stedLandet);
		stedtypegruppe.add(stedFylke);
		stedtypegruppe.add(stedSted);
		fylkepanel = new JPanel(new BorderLayout());
		fylkepanel.add(stedFylke,BorderLayout.LINE_START);
		fylkepanel.add(fylke,BorderLayout.CENTER);
		stedpanel = new JPanel(new BorderLayout());
		stedpanel.add(stedSted,BorderLayout.LINE_START);
		stedpanel.add(sted,BorderLayout.CENTER);
		
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
		
		spesAvgEnkelt = new JPanel(new GridLayout(1, 2));
		spesAvgEnkeltGruppe = new ButtonGroup();
		rEnkelverdi = new JRadioButton("Enkeltverdi", true);
		rAvgverdi = new JRadioButton("Gjennomsnitt", false);
		rEnkelverdi.addActionListener(handlingslytter);
		rAvgverdi.addActionListener(handlingslytter);
		spesAvgEnkeltGruppe.add(rEnkelverdi);
		spesAvgEnkeltGruppe.add(rAvgverdi);
		spesAvgEnkelt.add(rEnkelverdi);
		spesAvgEnkelt.add(rAvgverdi);
		
		spesType1 = new JPanel(new GridLayout(1, 2));
		spesType2 = new JPanel(new GridLayout(1, 2));
		spesTypeGruppe = new ButtonGroup();
		rMinNedbør = new JRadioButton("Minst nedbør", true);
		rNedbør = new JRadioButton("Mest nedbør", false);
		rMintemp = new JRadioButton("Kaldest", false);
		rMaxtemp = new JRadioButton("Varmest", false);
		rMinNedbør.addActionListener(handlingslytter);
		rNedbør.addActionListener(handlingslytter);
		rMintemp.addActionListener(handlingslytter);
		rMaxtemp.addActionListener(handlingslytter);
		spesTypeGruppe.add(rMinNedbør);
		spesTypeGruppe.add(rNedbør);
		spesTypeGruppe.add(rMintemp);
		spesTypeGruppe.add(rMaxtemp);
		spesType1.add(rMinNedbør);
		spesType1.add(rNedbør);
		spesType2.add(rMintemp);
		spesType2.add(rMaxtemp);
		
		hentData = new JButton("Hent registrerte data");
		hentData.addActionListener(handlingslytter);
		
		grid.add(new JLabel("Hent ekstremdata fra"));
		grid.add(stedLandet);
		grid.add(new JLabel(""));
		grid.add(fylkepanel);
		grid.add(new JLabel(""));
		grid.add(stedpanel);
		grid.add(new JLabel("Velg tidsperiode"));
		grid.add(datotype);
		grid.add(new JLabel("Velg dato"));
		grid.add(dato);
		grid.add(new JLabel("Spesifiser søket"));
		grid.add(spesAvgEnkelt);
		grid.add(spesType1);
		grid.add(spesType2);
		grid.add(new JLabel(""));
		grid.add(hentData);
		
		fylke.setEnabled(false);
		sted.setEnabled(false);
	}
	
	/**
	 * Metoden kaller opp MetroPanel sin genererTabell()-metoden for å sette dataene inn i tabellen.
	 * Metoden tilpasse også tabellen til de dataene som skal presenteres.
	 * Skrevet av: Henrik Hermansen
	 * @param data	Arrayen som inneholder dataene som skal skrives til tabellen
	 */
	public void genererTabell(Object[][] data,JRadioButton rMinNedbør, JRadioButton rNedbør,JRadioButton rMintemp,JRadioButton rMaxtemp)
	{
		super.genererTabell(data);
		if(rNedbør.isSelected() || rMinNedbør.isSelected())
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
	 * Skrevet av: Henrik Hermansen
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == hentData)
			{
				Object[][] data = FinnEkstremData.finnData(sl, panel, stedLandet, stedFylke, stedSted, fylke, sted, rdag, rmåned, rår, ldag, lmåned, lår, rEnkelverdi, rAvgverdi, rMinNedbør, rNedbør, rMintemp, rMaxtemp);
				if(data != null)
					genererTabell(data,rMinNedbør,rNedbør,rMintemp,rMaxtemp);
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
			} // end of if(...)
		} // end of actionPerformed(...)
	} // end of class HandlingsLytter
} // end of class EkstremverdiPanel