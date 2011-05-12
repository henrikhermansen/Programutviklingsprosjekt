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
 * @author B�rd Skeie
 *
 */
public class GjennomsnittsPanel extends MetroPanel 
{
	private JPanel datotype, gjdato;
	private ButtonGroup datotypegruppe;
	private JButton hentData;
	protected JRadioButton rm�ned, r�r;

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
		rm�ned = new JRadioButton("M�ned", false);
		r�r = new JRadioButton("�r", false);
		rm�ned.addActionListener(handlingslytter);
		r�r.addActionListener(handlingslytter);
		datotypegruppe.add(rm�ned);
		datotypegruppe.add(r�r);
		datotype.add(rm�ned);
		datotype.add(r�r);
		
		hentData = new JButton("Hent Gjennomsnittsverdier");
		hentData.addActionListener(handlingslytter);
		
		gjdato = new JPanel(new GridLayout(0,2));
		gjdato.add(lm�ned);
		gjdato.add(l�r);
		
		grid.add(new JLabel("Velg fylke"));
		grid.add(fylke);
		grid.add(new JLabel("Velg sted"));
		grid.add(sted);
		grid.add(new JLabel("Velg type s�k"));
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
			if(e.getSource() == hentData)
			{
				if(rm�ned.isSelected())
				{
					Object[][] data = FinnData.finnDataSted(sl, panel, fylke, sted, lm�ned, l�r);
					if(data != null)
						genererTabell(data);
				}
				if(r�r.isSelected())
				{
					Object[][] data = FinnData.finnDataSted(sl, panel, fylke, sted, l�r);
					if(data != null)
						genererTabell(data);
					return;
				}
			}
				

		}
	}

}
