package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
		rmåned = new JRadioButton("", true);
		rår = new JRadioButton("", false);
		
		fylkepanel = new JPanel(new BorderLayout());
		fylkepanel.add(rfylke, BorderLayout.LINE_START);
		fylkepanel.add(fylke, BorderLayout.CENTER);
		
		stedpanel = new JPanel(new BorderLayout());
		stedpanel.add(rsted, BorderLayout.LINE_START);
		stedpanel.add(sted, BorderLayout.CENTER);
		
		hentData = new JButton("Generer oversikt");
		hentData.addActionListener(handlingslytter);

		grid.add(new JLabel("Hent statistikk fra"));
		grid.add(rland);
		grid.add(new JLabel(""));
		grid.add(fylkepanel);
		grid.add(new JLabel(""));
		grid.add(stedpanel);
		grid.add(new JLabel(""));
		grid.add(hentData);
	}
	
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
}
