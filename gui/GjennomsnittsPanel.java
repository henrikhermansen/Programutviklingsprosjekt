/**
 * 
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import logic.FinnData;
import data.Stedliste;

/**
 * @author Bård Skeie
 *
 */
public class GjennomsnittsPanel extends MetroPanel 
{
	private JPanel datotype, gjdato;
	private ButtonGroup datotypegruppe;
	private JButton hentData;
	protected JRadioButton rmåned, rår;

	/**
	 * @param sl
	 */
	public GjennomsnittsPanel(Stedliste sl) 
	{
		super(sl);
		
		HandlingsLytter handlingslytter = new HandlingsLytter();
		fylke.addActionListener(handlingslytter);
		hentSteder(fylke.getSelectedIndex());
		
		datotype = new JPanel(new GridLayout(0,2));
		datotypegruppe = new ButtonGroup();
		rmåned = new JRadioButton("Måned", false);
		rår = new JRadioButton("År", false);
		rmåned.addActionListener(handlingslytter);
		rår.addActionListener(handlingslytter);
		datotypegruppe.add(rmåned);
		datotypegruppe.add(rår);
		datotype.add(rmåned);
		datotype.add(rår);
		
		hentData = new JButton("Hent Gjennomsnittsverdier");
		hentData.addActionListener(handlingslytter);
		
		gjdato = new JPanel(new GridLayout(0,2));
		gjdato.add(lmåned);
		gjdato.add(lår);
		
		grid.add(new JLabel("Velg fylke"));
		grid.add(fylke);
		grid.add(new JLabel("Velg sted"));
		grid.add(sted);
		grid.add(new JLabel("Velg type søk"));
		grid.add(datotype);
		grid.add(new JLabel("Velg dato"));
		grid.add(gjdato);
		grid.add(new JLabel(""));
		grid.add(hentData);
	}
	
	public void genererTabell(Object[][] data)
	{
		super.genererTabell(data);
		tabell.removeColumn(tabell.getColumnModel().getColumn(0));
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
			if(e.getSource() == hentData)
			{
				if(rmåned.isSelected())
				{
					Object[][] data = FinnData.finnDataSted(sl, panel, fylke, sted, lmåned, lår);
					if(data != null)
						genererTabell(data);
				}
				if(rår.isSelected())
				{
					Object[][] data = FinnData.finnDataSted(sl, panel, fylke, sted, lår);
					if(data != null)
						genererTabell(data);
					return;
				}
			}
				

		}
	}

}
