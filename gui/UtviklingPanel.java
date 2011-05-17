package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import logic.SkrivMelding;
import logic.Utvikling;

import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class UtviklingPanel extends MetroPanel
{
	private JButton hentData;
	private JPanel fylkepanel, stedpanel, månedpanel, mangeårpanel, årpanel;
	private ButtonGroup omfangsgruppe, datogruppe;
	private JRadioButton rland, rfylke, rsted, rmåned, rmangeår, rår;

	/**
	 * Konstruktør, tegner ut panelet
	 * @author Lars Smeby
	 * @param sl	Referanse til Stedlisten med all lagret data
	 */
	public UtviklingPanel(Stedliste sl)
	{
		super(sl);
		
		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		lår.addActionListener(handlingslytter);
		
		fylke.setEnabled(false);
		sted.setEnabled(false);
		lmåned.setEnabled(false);
		
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
		
		int fraår = 0;
		try
		{
			fraår = Integer.parseInt((String)lår.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			SkrivMelding.skriv("Ukjent programfeil (L009)/E", panel);
		}
		if(fraår <= FØRSTEÅR+9)
			fraår = FØRSTEÅR;
		else
			fraår -= 9;
		
		datogruppe = new ButtonGroup();
		rmåned = new JRadioButton("", false);
		rmangeår = new JRadioButton("Fra "+fraår+" til", true);
		rår = new JRadioButton("", false);
		rmåned.addActionListener(handlingslytter);
		rår.addActionListener(handlingslytter);
		rmangeår.addActionListener(handlingslytter);
		datogruppe.add(rmåned);
		datogruppe.add(rår);
		datogruppe.add(rmangeår);
		
		fylkepanel = new JPanel(new BorderLayout());
		fylkepanel.add(rfylke, BorderLayout.LINE_START);
		fylkepanel.add(fylke, BorderLayout.CENTER);
		
		stedpanel = new JPanel(new BorderLayout());
		stedpanel.add(rsted, BorderLayout.LINE_START);
		stedpanel.add(sted, BorderLayout.CENTER);
		
		årpanel = new JPanel(new BorderLayout());
		årpanel.add(rår, BorderLayout.LINE_START);
		årpanel.add(lår, BorderLayout.CENTER);
		
		månedpanel = new JPanel(new BorderLayout());
		månedpanel.add(rmåned, BorderLayout.LINE_START);
		månedpanel.add(lmåned, BorderLayout.CENTER);
		
		hentData = new JButton("Generer oversikt");
		hentData.addActionListener(handlingslytter);

		grid.add(new JLabel("Hent statistikk fra"));
		grid.add(rland);
		grid.add(new JLabel(""));
		grid.add(fylkepanel);
		grid.add(new JLabel(""));
		grid.add(stedpanel);
		grid.add(new JLabel("Velg tidsperiode"));
		grid.add(rmangeår);
		grid.add(new JLabel(""));
		grid.add(årpanel);
		grid.add(new JLabel(""));
		grid.add(månedpanel);
		grid.add(new JLabel(""));
		grid.add(hentData);
	}
	
	/**
	 * Oppretter et grafikkpanel og sender med data, og tegner det ut i panelet
	 * @author Lars Smeby
	 * @param data	Dataen som skal tegnes ut som grafikk
	 */
	public void genererGrafikk(double[][] data)
	{
		Utviklingsgrafikk grafikkpanel = new Utviklingsgrafikk(data);
		panel.remove(1);
		panel.add(grafikkpanel, BorderLayout.CENTER);
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
			if(e.getSource() == lår)
			{
				int fraår = 0;
				try
				{
					fraår = Integer.parseInt((String)lår.getSelectedItem());
				}
				catch(NumberFormatException nfe)
				{
					SkrivMelding.skriv("Ukjent programfeil (L010)/E", panel);
				}
				if(fraår <= FØRSTEÅR+9)
					fraår = FØRSTEÅR;
				else
					fraår -= 9;
				rmangeår.setText("Fra "+fraår+" til");
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
			if(rmangeår.isSelected())
			{
				lmåned.setEnabled(false);
			}
			if(rår.isSelected())
			{
				lmåned.setEnabled(false);
			}
			if(rmåned.isSelected())
			{
				lmåned.setEnabled(true);
			}
			if(e.getSource() == hentData)
			{
				double[][] data = Utvikling.dataTilGrafikk(sl,panel,rland,rfylke,rsted,rår,rmåned,rmangeår,fylke,sted,lår,lmåned);
				if(data != null)
					genererGrafikk(data);
			}
		}
	}
}
