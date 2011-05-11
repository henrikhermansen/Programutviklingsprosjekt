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
	 * Metode for � f� tak i all data for et gitt sted et gitt �r.
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metodekallet gj�res fra
	 * @param fylke	JComboBox fylkesliste
	 * @param sted	JComboBox stedliste
	 * @param l�r	JComboBox �rliste
	 * @return	Object[][] array med datoer og igjen data for et gitt sted et gitt �r
	 */
	public static Object[][] finnDataSted(Stedliste sl, JPanel panel, JComboBox fylke, JComboBox sted, JComboBox l�r)
	{
		int f = fylke.getSelectedIndex();
		String s = (String)sted.getSelectedItem();
		int �r;
		if(s == null)
		{
			JOptionPane.showMessageDialog(panel, "Ingen steder valgt", "Fant ikke sted", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
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
		
		Datoliste �rliste = st.getDatoliste().finnDatoer(�r);
		
		int lengde = �rliste.size();
		if(lengde == 0)
		{
			JOptionPane.showMessageDialog(panel, "Det eksisterer ikke data for dette stedet i denne tidsperioden", "Fant ikke data", JOptionPane.INFORMATION_MESSAGE);
		}
		
		Object[][] returarray = new Object[lengde][5];
		
		Iterator<Dato> iterator = �rliste.iterator();
		
		for(int i = 0; i < lengde; i++)
		{
			Dato neste = iterator.next();
			returarray[i][0] = "";
			returarray[i][1] = neste.getDato().getTime();
			returarray[i][2] = new Double(neste.getNedb�r());
			returarray[i][3] = new Double(neste.getMinTemp());
			returarray[9][4] = new Double(neste.getMaxTemp());
		}
		
		return returarray;
	}
	
	/**
	 * @author B�rd Skeie
	 * Klasse for � finne v�rdata for et gitt sted p� en gitt dato.
	 * @param navn
	 * @param fylke
	 * @param JComboBox
	 * @return
	 */
	public static Object[][] FinnDatoVaer(Stedliste stedliste, JComboBox fylke, JComboBox l�r, JComboBox lm�ned, JComboBox ldag, JPanel panel)
	{
		int f = fylke.getSelectedIndex();
		Stedliste sted = stedliste.finnSted(f);
		if(sted == null)
		{
			JOptionPane.showMessageDialog(panel, "Fylket har ingen registrerte steder", "Ingen registreringer", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		Iterator<Sted> iterator = sted.iterator();
		
		int �r;
		int m�ned = lm�ned.getSelectedIndex();
		int dag;
		
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
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
			if(dato.finnDato(�r, m�ned, dag) != null)
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
			returarray[i][1] = new GregorianCalendar(�r, m�ned, dag).getTime();
			returarray[i][2] = new Double(neste.getDatoliste().finnDato(�r, m�ned, dag).getNedb�r());
			returarray[i][3] = new Double(neste.getDatoliste().finnDato(�r, m�ned, dag).getMinTemp());
			returarray[i][4] = new Double(neste.getDatoliste().finnDato(�r, m�ned, dag).getMaxTemp());
		}

		return returarray;
	}
}

