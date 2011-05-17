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
	private JPanel fylkepanel, stedpanel, m�nedpanel, mange�rpanel, �rpanel;
	private ButtonGroup omfangsgruppe, datogruppe;
	private JRadioButton rland, rfylke, rsted, rm�ned, rmange�r, r�r;

	/**
	 * Konstrukt�r, tegner ut panelet
	 * @author Lars Smeby
	 * @param sl	Referanse til Stedlisten med all lagret data
	 */
	public UtviklingPanel(Stedliste sl)
	{
		super(sl);
		
		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		l�r.addActionListener(handlingslytter);
		
		fylke.setEnabled(false);
		sted.setEnabled(false);
		lm�ned.setEnabled(false);
		
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
		
		int fra�r = 0;
		try
		{
			fra�r = Integer.parseInt((String)l�r.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			SkrivMelding.skriv("Ukjent programfeil (L009)/E", panel);
		}
		if(fra�r <= F�RSTE�R+9)
			fra�r = F�RSTE�R;
		else
			fra�r -= 9;
		
		datogruppe = new ButtonGroup();
		rm�ned = new JRadioButton("", false);
		rmange�r = new JRadioButton("Fra "+fra�r+" til", true);
		r�r = new JRadioButton("", false);
		rm�ned.addActionListener(handlingslytter);
		r�r.addActionListener(handlingslytter);
		rmange�r.addActionListener(handlingslytter);
		datogruppe.add(rm�ned);
		datogruppe.add(r�r);
		datogruppe.add(rmange�r);
		
		fylkepanel = new JPanel(new BorderLayout());
		fylkepanel.add(rfylke, BorderLayout.LINE_START);
		fylkepanel.add(fylke, BorderLayout.CENTER);
		
		stedpanel = new JPanel(new BorderLayout());
		stedpanel.add(rsted, BorderLayout.LINE_START);
		stedpanel.add(sted, BorderLayout.CENTER);
		
		�rpanel = new JPanel(new BorderLayout());
		�rpanel.add(r�r, BorderLayout.LINE_START);
		�rpanel.add(l�r, BorderLayout.CENTER);
		
		m�nedpanel = new JPanel(new BorderLayout());
		m�nedpanel.add(rm�ned, BorderLayout.LINE_START);
		m�nedpanel.add(lm�ned, BorderLayout.CENTER);
		
		hentData = new JButton("Generer oversikt");
		hentData.addActionListener(handlingslytter);

		grid.add(new JLabel("Hent statistikk fra"));
		grid.add(rland);
		grid.add(new JLabel(""));
		grid.add(fylkepanel);
		grid.add(new JLabel(""));
		grid.add(stedpanel);
		grid.add(new JLabel("Velg tidsperiode"));
		grid.add(rmange�r);
		grid.add(new JLabel(""));
		grid.add(�rpanel);
		grid.add(new JLabel(""));
		grid.add(m�nedpanel);
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
	 * Lytter p� knapper og felt
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
			if(e.getSource() == l�r)
			{
				int fra�r = 0;
				try
				{
					fra�r = Integer.parseInt((String)l�r.getSelectedItem());
				}
				catch(NumberFormatException nfe)
				{
					SkrivMelding.skriv("Ukjent programfeil (L010)/E", panel);
				}
				if(fra�r <= F�RSTE�R+9)
					fra�r = F�RSTE�R;
				else
					fra�r -= 9;
				rmange�r.setText("Fra "+fra�r+" til");
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
			if(rmange�r.isSelected())
			{
				lm�ned.setEnabled(false);
			}
			if(r�r.isSelected())
			{
				lm�ned.setEnabled(false);
			}
			if(rm�ned.isSelected())
			{
				lm�ned.setEnabled(true);
			}
			if(e.getSource() == hentData)
			{
				double[][] data = Utvikling.dataTilGrafikk(sl,panel,rland,rfylke,rsted,r�r,rm�ned,rmange�r,fylke,sted,l�r,lm�ned);
				if(data != null)
					genererGrafikk(data);
			}
		}
	}
}
