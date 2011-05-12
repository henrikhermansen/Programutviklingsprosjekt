package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
	private JPanel fylkepanel, stedpanel, månedpanel, årpanel;
	private ButtonGroup omfangsgruppe, datogruppe;
	private JRadioButton rland, rfylke, rsted, rmåned, rår;

	/**
	 * @author Lars Smeby
	 * @param sl	Referanse til Stedlisten med all lagret data
	 */
	public UtviklingPanel(Stedliste sl)
	{
		super(sl);
		
		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		
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
		
		datogruppe = new ButtonGroup();
		rmåned = new JRadioButton("", false);
		rår = new JRadioButton("", true);
		rmåned.addActionListener(handlingslytter);
		rår.addActionListener(handlingslytter);
		datogruppe.add(rmåned);
		datogruppe.add(rår);
		
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
		grid.add(årpanel);
		grid.add(new JLabel(""));
		grid.add(månedpanel);
		grid.add(new JLabel(""));
		grid.add(hentData);
	}
	
	public void genererGrafikk(double[][] data)
	{
		Utviklingsgrafikk grafikkpanel = new Utviklingsgrafikk(data);
		panel.remove(1);
		panel.add(grafikkpanel, BorderLayout.CENTER);
		panel.validate();
		panel.repaint();
	}
	
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
				double[][] data = Utvikling.dataTilGrafikk(sl, panel, rland,rfylke,rsted,rår,rmåned,fylke,sted,lår,lmåned);
				if(data != null)
					genererGrafikk(data);
			}
		}
	}
}
