package logic;

import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.*;

import data.*;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class FinnData
{
	
	/**
	 * Metode for å få tak i all data for et gitt sted et gitt år.
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metodekallet gjøres fra
	 * @param fylke	JComboBox fylkesliste
	 * @param sted	JComboBox stedliste
	 * @param lår	JComboBox årliste
	 * @return	Object[][] array med datoer og igjen data for et gitt sted et gitt år
	 */
	public static Object[][] finnDataSted(Stedliste sl, JPanel panel, JComboBox fylke, JComboBox sted, JComboBox lår)
	{
		int f = fylke.getSelectedIndex();
		String s = (String)sted.getSelectedItem();
		int år;
		if(s == null)
		{
			JOptionPane.showMessageDialog(panel, "Ingen steder valgt", "Fant ikke sted", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		try
		{
			år = Integer.parseInt((String)lår.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (L001)", "Feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		Sted st = sl.finnSted(s, f);
		if(st == null)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (L002)", "Feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		Datoliste årliste = st.getDatoliste().finnDatoer(år);
		
		int lengde = årliste.size();
		if(lengde == 0)
		{
			JOptionPane.showMessageDialog(panel, "Det eksisterer ikke data for dette stedet i denne tidsperioden", "Fant ikke data", JOptionPane.INFORMATION_MESSAGE);
		}
		
		Object[][] returarray = new Object[lengde][5];
		
		Iterator<Dato> iterator = årliste.iterator();
		
		for(int i = 0; i < lengde; i++)
		{
			Dato neste = iterator.next();
			returarray[i][0] = "";
			returarray[i][1] = neste.getDato().getTime();
			returarray[i][2] = new Double(neste.getNedbør());
			returarray[i][3] = new Double(neste.getMinTemp());
			returarray[9][4] = new Double(neste.getMaxTemp());
		}
		
		return returarray;
	}
	
	/**
	 * @author Bård Skeie
	 * Klasse for å finne værdata for et gitt sted på en gitt dato.
	 * @param navn
	 * @param fylke
	 * @param JComboBox
	 * @return
	 */
	public static Object[][] FinnDatoVaer(Stedliste stedliste, JComboBox fylke, JComboBox lår, JComboBox lmåned, JComboBox ldag, JPanel panel)
	{
		int f = fylke.getSelectedIndex();
		Stedliste sted = stedliste.finnSted(f);
		if(sted == null)
		{
			JOptionPane.showMessageDialog(panel, "Fylket har ingen registrerte steder", "Ingen registreringer", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		Iterator<Sted> iterator = sted.iterator();
		
		int år;
		int måned = lmåned.getSelectedIndex();
		int dag;
		
		try
		{
			år = Integer.parseInt((String)lår.getSelectedItem());
			dag = Integer.parseInt((String)ldag.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (B004)", "System feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		Stedliste stedDatoListe = new Stedliste();
		while(iterator.hasNext())
		{
			Sted neste = iterator.next();
			Datoliste dato = neste.getDatoliste();
			if(dato.finnDato(år, måned, dag) != null)
				stedDatoListe.settInn(neste);
		}
		int lengde = stedDatoListe.size();
		if(lengde == 0)
		{
			JOptionPane.showMessageDialog(panel, "Fylket har ingen data", "Ingen registreringer", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		iterator = stedDatoListe.iterator();
		
		Object[][] returarray = new Object[lengde][5];
		
		for(int i = 0; i < lengde; i++)
		{
			Sted neste = iterator.next();
			returarray[i][0] = neste.getNavn();
			returarray[i][1] = new GregorianCalendar(år, måned, dag).getTime();
			returarray[i][2] = new Double(neste.getDatoliste().finnDato(år, måned, dag).getNedbør());
			returarray[i][3] = new Double(neste.getDatoliste().finnDato(år, måned, dag).getMinTemp());
			returarray[i][4] = new Double(neste.getDatoliste().finnDato(år, måned, dag).getMaxTemp());
		}

		return returarray;
	}
}

