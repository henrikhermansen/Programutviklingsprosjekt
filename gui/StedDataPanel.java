package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class StedDataPanel extends MetroPanel
{
	private JPanel datotype;
	private ButtonGroup datotypegruppe;
	protected JRadioButton rdag, rm�ned, r�r;
	private JButton hentData;
	
	public StedDataPanel(Stedliste sl)
	{
		super(sl);
		
		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		sted.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		
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
	
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == hentData)
			{
				
			}
			if(e.getSource() == fylke)
			{
				hentSteder(fylke.getSelectedIndex());
			}
			if(e.getSource() == sted)
			{
				
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
		}
	}
}