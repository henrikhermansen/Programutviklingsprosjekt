package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import logic.Gjennomsnitt;

import data.Stedliste;

/**
 * @author Bård Skeie
 *
 */
public class GjennomsnittsPanel extends MetroPanel 
{
	private JPanel fylkepanel, stedpanel;
	private JButton hentData;
	private JRadioButton rland, rfylke, rsted;
	private ButtonGroup omfangsgruppe;
	

	/**
	 * @param sl
	 */
	public GjennomsnittsPanel(Stedliste sl) 
	{
		super(sl);
		
		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		
		fylke.setEnabled(false);
		sted.setEnabled(false);
		
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
		
		fylkepanel = new JPanel(new BorderLayout());
		fylkepanel.add(rfylke, BorderLayout.LINE_START);
		fylkepanel.add(fylke, BorderLayout.CENTER);
		
		stedpanel = new JPanel(new BorderLayout());
		stedpanel.add(rsted, BorderLayout.LINE_START);
		stedpanel.add(sted, BorderLayout.CENTER);
						
		hentData = new JButton("Hent gjennomsnittsverdier");
		hentData.addActionListener(handlingslytter);
		
		grid.add(new JLabel("Hent statistikk fra"));
		grid.add(rland);
		grid.add(new JLabel(""));
		grid.add(fylkepanel);
		grid.add(new JLabel(""));
		grid.add(stedpanel);
		grid.add(new JLabel("Velg år"));
		grid.add(lår);
		grid.add(new JLabel(""));
		grid.add(hentData);
	}
	
	public void genererTabell(Object[][] data)
	{
		super.genererTabell(data);
		tabell.removeColumn(tabell.getColumnModel().getColumn(1));
		tabell.getColumnModel().getColumn(0).setHeaderValue("Tidsperiode");
		tabell.getColumnModel().getColumn(1).setHeaderValue("Total nedbør");
		tabell.getColumnModel().getColumn(2).setHeaderValue("Gjennomsnittsnedbør");
		tabell.getColumnModel().getColumn(3).setHeaderValue("Gjennomsnittstemperatur");
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
			if(e.getSource() == hentData && rland.isSelected())
			{
				
			}
			if(e.getSource() == hentData && rfylke.isSelected())
			{
				
			}
			if(e.getSource() == hentData && rsted.isSelected())
			{
				if(sted.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(panel, "Sted er ikke valgt", "Feil", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Object[][] data = Gjennomsnitt.finnGjennomsnittSted(sl, lår, panel, fylke, sted);
				if(data != null)
					genererTabell(data);
			}
		}
	}
}