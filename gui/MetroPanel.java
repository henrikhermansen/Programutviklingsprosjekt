package gui;

import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import data.Sted;
import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public abstract class MetroPanel
{
	protected JPanel panel, grid, dato;
	protected Stedliste sl;
	protected JComboBox fylke, sted, ldag, lmåned, lår;
	private final int førsteår = 1900;
	
	/**
	 * @author Lars Smeby
	 * @param sl	Stedlisten til hovedvinduet
	 */
	public MetroPanel(Stedliste sl)
	{
		this.sl = sl;
		
		panel = new JPanel(new BorderLayout());
		grid = new JPanel(new GridLayout(0,2,3,3));
		
		String[] dager = new String[31];
		for(int i = 0; i < dager.length; i++)
			dager[i] = Integer.toString(i+1);
		
		String[] måneder = {"Januar","Februar","Mars","April","Mai","Juni","Juli","August","September","Oktober","November","Desember"};
		
		int detteår = new GregorianCalendar().get(Calendar.YEAR);
		String[] årliste = new String[detteår-(førsteår-1)];
		for(int i = 0; i < årliste.length; i++)
			årliste[i] = Integer.toString(detteår--);
		
		fylke = new JComboBox(Sted.fylkesliste);
		sted = new JComboBox();
		ldag = new JComboBox(dager);
		lmåned = new JComboBox(måneder);
		lår = new JComboBox(årliste);
		
		dato = new JPanel(new GridLayout(0,3));
		dato.add(ldag);
		dato.add(lmåned);
		dato.add(lår);

		panel.add(grid, BorderLayout.PAGE_START);
		panel.setBorder(BorderFactory.createEmptyBorder(25, 35, 10, 35));
	}
	
	/**
	 * @author Lars Smeby
	 * Returnerer det panelet som skal brukes i hovedvinduet.
	 * @return et objekt av typen JPanel.
	 */
	public JPanel getPanel()
	{
		return panel;
	}
	
	/**
	 * @author Lars Smeby
	 * @param fylke	Fylkesnummeret det skal vises steder for
	 */
	protected void hentSteder(int fylke)
	{
		sted.setModel(new DefaultComboBoxModel(sl.toString(fylke)));
		sted.validate();
		sted.repaint();
	}
	
	/**
	 * @author Lars Smeby
	 * @return	Indexen til det fylke som er valgt i fylkeslisten
	 */
	public int getFylke()
	{
		return fylke.getSelectedIndex();
	}
}
