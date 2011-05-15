package logic;

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
	 * Metode for � f� tak i all data for et gitt sted en gitt dag.
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metodekallet gj�res fra
	 * @param fylke	JComboBox fylkesliste
	 * @param sted	JComboBox stedliste
	 * @param ldag	JComboBox dagliste
	 * @param lm�ned	JComboBox m�nedsliste
	 * @param l�r	JComboBox �rliste
	 * @return	Object[][] array med en dato og igjen data for et gitt sted en gitt dag
	 */
	public static Object[][] finnDataSted(Stedliste sl, JPanel panel, JComboBox fylke, JComboBox sted, JComboBox ldag, JComboBox lm�ned, JComboBox l�r)
	{
		int f = fylke.getSelectedIndex();
		String s = (String)sted.getSelectedItem();
		int dag;
		int m�ned = lm�ned.getSelectedIndex();
		int �r;
		if(s == null)
		{
			SkrivMelding.skriv("Ingen steder valgt/W", panel);
			return null;
		}
		try
		{
			dag = Integer.parseInt((String)ldag.getSelectedItem());
			�r = Integer.parseInt((String)l�r.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			SkrivMelding.skriv("Ukjent programfeil (L001)/E", panel);
			return null;
		}
		
		Sted st = sl.finnSted(s, f);
		if(st == null)
		{
			SkrivMelding.skriv("Ukjent programfeil (L002)/E", panel);
			return null;
		}
		
		Dato dato = st.getDatoliste().finnDato(�r, m�ned, dag);

		if(dato == null)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
			return null;
		}
		
		Object[][] returarray = new Object[1][5];

		returarray[0][0] = null;
		returarray[0][1] = dato.getDato().getTime();
		returarray[0][2] = dato.getNedb�r() >= 0 ? dato.getNedb�r() : null;
		returarray[0][3] = dato.getMinTemp() <= Registrering.MAXMAXTEMP ? dato.getMinTemp() : null;
		returarray[0][4] = dato.getMaxTemp() <= Registrering.MAXMAXTEMP ? dato.getMaxTemp() : null;
		
		return returarray;
	}
	
	/**
	 * Metode for � f� tak i all data for et gitt sted en gitt m�ned.
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metodekallet gj�res fra
	 * @param fylke	JComboBox fylkesliste
	 * @param sted	JComboBox stedliste
	 * @param lm�ned	JComboBox m�nedsliste
	 * @param l�r	JComboBox �rliste
	 * @return	Object[][] array med datoer og igjen data for et gitt sted en gitt m�ned
	 */
	public static Object[][] finnDataSted(Stedliste sl, JPanel panel, JComboBox fylke, JComboBox sted, JComboBox lm�ned, JComboBox l�r)
	{
		int f = fylke.getSelectedIndex();
		String s = (String)sted.getSelectedItem();
		int m�ned = lm�ned.getSelectedIndex();
		int �r;
		if(s == null)
		{
			SkrivMelding.skriv("Ingen steder valgt/W", panel);
			return null;
		}
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			SkrivMelding.skriv("Ukjent programfeil (L003)/E", panel);
			return null;
		}
		
		Sted st = sl.finnSted(s, f);
		if(st == null)
		{
			SkrivMelding.skriv("Ukjent programfeil (L004)/E", panel);
			return null;
		}
		
		Datoliste m�nedliste = st.getDatoliste().finnDatoer(�r, m�ned);
		
		int lengde = m�nedliste.size();
		if(lengde == 0)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
			return null;
		}
		
		Object[][] returarray = new Object[lengde][5];
		
		Iterator<Dato> iterator = m�nedliste.iterator();
		
		for(int i = 0; i < lengde; i++)
		{
			Dato neste = iterator.next();
			returarray[i][0] = null;
			returarray[i][1] = neste.getDato().getTime();
			returarray[i][2] = neste.getNedb�r() >= 0 ? neste.getNedb�r() : null;
			returarray[i][3] = neste.getMinTemp() <= Registrering.MAXMAXTEMP ? neste.getMinTemp() : null;
			returarray[i][4] = neste.getMaxTemp() <= Registrering.MAXMAXTEMP ? neste.getMaxTemp() : null;
		}
		
		return returarray;
	}
	
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
			SkrivMelding.skriv("Ingen steder valgt/W", panel);
			return null;
		}
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			SkrivMelding.skriv("Ukjent programfeil (L005)/E", panel);
			return null;
		}
		
		Sted st = sl.finnSted(s, f);
		if(st == null)
		{
			SkrivMelding.skriv("Ukjent programfeil (L006)/E", panel);
			return null;
		}
		
		Datoliste �rliste = st.getDatoliste().finnDatoer(�r);
		
		int lengde = �rliste.size();
		if(lengde == 0)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
			return null;
		}
		
		Object[][] returarray = new Object[lengde][5];
		
		Iterator<Dato> iterator = �rliste.iterator();
		
		for(int i = 0; i < lengde; i++)
		{
			Dato neste = iterator.next();
			returarray[i][0] = null;
			returarray[i][1] = neste.getDato().getTime();
			returarray[i][2] = neste.getNedb�r() >= 0 ? neste.getNedb�r() : null;
			returarray[i][3] = neste.getMinTemp() <= Registrering.MAXMAXTEMP ? neste.getMinTemp() : null;
			returarray[i][4] = neste.getMaxTemp() <= Registrering.MAXMAXTEMP ? neste.getMaxTemp() : null;
		}
		
		return returarray;
	}
	
	/**
	 * Metode for � finne v�rdata for et gitt sted p� en gitt dato.
	 * @author B�rd Skeie
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
			SkrivMelding.skriv("Fylket har ingen registrerte steder/I", panel);
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
			SkrivMelding.skriv("Ukjent programfeil (B004)/E", panel);
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
			SkrivMelding.skriv("Fylket har ingen data/I", panel);
			return null;
		}
		
		iterator = stedDatoListe.iterator();
		
		Object[][] returarray = new Object[lengde][5];
		
		for(int i = 0; i < lengde; i++)
		{
			Sted neste = iterator.next();
			returarray[i][0] = neste.getNavn();
			returarray[i][1] = null;
			returarray[i][2] = neste.getDatoliste().finnDato(�r, m�ned, dag).getNedb�r() >= 0
								? neste.getDatoliste().finnDato(�r, m�ned, dag).getNedb�r() : null;
			returarray[i][3] = neste.getDatoliste().finnDato(�r, m�ned, dag).getMinTemp() <= Registrering.MAXMAXTEMP
								? neste.getDatoliste().finnDato(�r, m�ned, dag).getMinTemp() : null;
			returarray[i][4] = neste.getDatoliste().finnDato(�r, m�ned, dag).getMaxTemp() <= Registrering.MAXMAXTEMP
								? neste.getDatoliste().finnDato(�r, m�ned, dag).getMaxTemp() : null;
		}

		return returarray;
	}
}

