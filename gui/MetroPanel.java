/**
 * Inneholder klassen MetroPanel.
 * @author Lars Smeby
 * @since 04.05.2011
 * @version	1 18.05.2011
 */
package gui;

import java.awt.*;
import java.util.GregorianCalendar;
import javax.swing.*;

import logic.SkrivMelding;

import data.Sted;
import data.Stedliste;

/**
 * Klasse som fungerer som superklasse til alle andre paneler.
 * Knapper, lister, felt og metoder som g�r mye igjen ligger her.
 */
public abstract class MetroPanel
{
	protected JPanel panel, grid, dato;
	protected Stedliste sl;
	protected JComboBox fylke, sted, ldag, lm�ned, l�r;
	protected JTable tabell;
	public static final int F�RSTE�R = 1900;
	public static final String[] M�NEDER = {"Januar","Februar","Mars","April","Mai","Juni","Juli","August","September","Oktober","November","Desember"};
	
	/**
	 * Konstrukt�r som tegner opp panelet
	 * Skrevet av: Lars Smeby
	 * @param sl	Stedlisten til hovedvinduet
	 */
	public MetroPanel(Stedliste sl)
	{
		this.sl = sl;
		
		panel = new JPanel(new BorderLayout());
		grid = new JPanel(new GridLayout(0,2,3,3));
		
		int dette�r = new GregorianCalendar().get(GregorianCalendar.YEAR);
		String[] �rliste = new String[dette�r-(F�RSTE�R-1)];
		for(int i = 0; i < �rliste.length; i++)
			�rliste[i] = Integer.toString(dette�r--);
		
		fylke = new JComboBox(Sted.FYLKESLISTE);
		sted = new JComboBox();
		ldag = new JComboBox();
		lm�ned = new JComboBox(M�NEDER);
		l�r = new JComboBox(�rliste);
		
		dato = new JPanel(new GridLayout(0,3));
		dato.add(ldag);
		dato.add(lm�ned);
		dato.add(l�r);

		panel.add(grid, BorderLayout.PAGE_START);
		panel.add(new JLabel(""), BorderLayout.CENTER); //Plassholder for tabellen
		panel.setBorder(BorderFactory.createEmptyBorder(25, 35, 10, 35));
	}
	
	/**
	 * Returnerer det panelet som skal brukes i hovedvinduet.
	 * Skrevet av: Lars Smeby
	 * @return et objekt av typen JPanel.
	 */
	public JPanel getPanel()
	{
		return panel;
	}
	
	/**
	 * Oppdaterer stedlisten i gui etter hvilket fylke som er valgt
	 * Skrevet av: Lars Smeby
	 * @param fylke	Fylkesnummeret det skal vises steder for
	 */
	protected void hentSteder(int fylke)
	{
		sted.setModel(new DefaultComboBoxModel(sl.toString(fylke)));
		sted.validate();
		sted.repaint();
	}
	
	/**
	 * Oppdaterer daglisten i gui slik at det vises korrekte datoer i hver m�ned i �ret, ogs� ved skudd�r.
	 * Skrevet av: Lars Smeby
	 * @param valgtDag	Den dagen som allerede er valgt, s� den beholdes som aktiv selv om listen oppdateres
	 */
	protected void hentDager(int valgtDag)
	{
		int �r;
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			SkrivMelding.skriv("Ukjent programfeil (L003)/E", panel);
			return;
		}
		int m�ned = lm�ned.getSelectedIndex();
		
		GregorianCalendar kal = new GregorianCalendar(�r, m�ned, 1);
		int antallDager = kal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		
		String[] dager = new String[antallDager];
		for(int i = 0; i < dager.length; i++)
			dager[i] = Integer.toString(i+1);
		
		ldag.setModel(new DefaultComboBoxModel(dager));
		ldag.validate();
		ldag.repaint();
		try
		{
			ldag.setSelectedIndex(valgtDag);
		}
		catch(IllegalArgumentException iae)
		{
			ldag.setSelectedIndex(0);
		}
	}
	
	/**
	 * Brukes til � kunne aksessere fylkeslisten utenfor denne klassen og dens subklasser
	 * Skrevet av: Lars Smeby
	 * @return	Indexen til det fylke som er valgt i fylkeslisten
	 */
	public int getFylke()
	{
		return fylke.getSelectedIndex();
	}
	
	/**
	 * Genererer en tabell og tegner den ut p� panelet. Denne m� overrides og validate()/repaint() i subklassene
	 * Skrevet av: Lars Smeby
	 * @param data	Arrayen som inneholder dataene som skal skrives ut i tabellen
	 */
	public void genererTabell(Object[][] data)
	{
		DataTabell modell = new DataTabell(data);
		tabell = new JTable(modell);
		tabell.getTableHeader().setReorderingAllowed(false);
		tabell.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tabell.setRowSelectionAllowed(true);
		tabell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JPanel tabellPanel = new JPanel(new GridLayout(1, 0));
		tabellPanel.add(new JScrollPane(tabell));
		tabellPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		panel.remove(1);
		panel.add(tabellPanel, BorderLayout.CENTER);
	} // end of genererTabell(...)
} // end of class MetroPanel