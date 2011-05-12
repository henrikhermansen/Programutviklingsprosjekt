/**
 * 
 */
package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data.Stedliste;

/**
 * @author Bård Skeie
 *
 */
public class GjennomsnittsPanel extends MetroPanel 
{
	private JPanel gjdato;
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
						
		hentData = new JButton("Hent Gjennomsnittsverdier");
		hentData.addActionListener(handlingslytter);
		
		gjdato = new JPanel(new GridLayout(0,1));
		gjdato.add(lår);
		
		grid.add(new JLabel("Velg fylke"));
		grid.add(fylke);
		grid.add(new JLabel("Velg sted"));
		grid.add(sted);
		
		grid.add(new JLabel("Velg år"));
		grid.add(gjdato);
		grid.add(new JLabel(""));
		grid.add(hentData);
	}
	
	public void genererTabell(Object[][] data)
	{
		super.genererTabell(data);
		tabell.removeColumn(tabell.getColumnModel().getColumn(1));
		tabell.getColumnModel().getColumn(0).setHeaderValue("Tidsperiode");
		tabell.getColumnModel().getColumn(2).setHeaderValue("Total nedbør");
		tabell.getColumnModel().getColumn(3).setHeaderValue("Gjennomsnittsnedbør");
		tabell.getColumnModel().getColumn(4).setHeaderValue("Gjennomsnittstemperatur");
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
			if(e.getSource() == hentData)
			{
				
			}
				

		}
	}

}
