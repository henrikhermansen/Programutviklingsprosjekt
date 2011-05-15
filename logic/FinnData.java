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
	 * Metode for å få tak i all data for et gitt sted en gitt dag.
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metodekallet gjøres fra
	 * @param fylke	JComboBox fylkesliste
	 * @param sted	JComboBox stedliste
	 * @param ldag	JComboBox dagliste
	 * @param lmåned	JComboBox månedsliste
	 * @param lår	JComboBox årliste
	 * @return	Object[][] array med en dato og igjen data for et gitt sted en gitt dag
	 */
	public static Object[][] finnDataSted(Stedliste sl, JPanel panel, JComboBox fylke, JComboBox sted, JComboBox ldag, JComboBox lmåned, JComboBox lår)
	{
		int f = fylke.getSelectedIndex();
		String s = (String)sted.getSelectedItem();
		int dag;
		int måned = lmåned.getSelectedIndex();
		int år;
		if(s == null)
		{
			SkrivMelding.skriv("Ingen steder valgt/W", panel);
			return null;
		}
		try
		{
			dag = Integer.parseInt((String)ldag.getSelectedItem());
			år = Integer.parseInt((String)lår.getSelectedItem());
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
		
		Dato dato = st.getDatoliste().finnDato(år, måned, dag);

		if(dato == null)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
			return null;
		}
		
		Object[][] returarray = new Object[1][5];

		returarray[0][0] = null;
		returarray[0][1] = dato.getDato().getTime();
		returarray[0][2] = dato.getNedbør() >= 0 ? dato.getNedbør() : null;
		returarray[0][3] = dato.getMinTemp() <= Registrering.MAXMAXTEMP ? dato.getMinTemp() : null;
		returarray[0][4] = dato.getMaxTemp() <= Registrering.MAXMAXTEMP ? dato.getMaxTemp() : null;
		
		return returarray;
	}
	
	/**
	 * Metode for å få tak i all data for et gitt sted en gitt måned.
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metodekallet gjøres fra
	 * @param fylke	JComboBox fylkesliste
	 * @param sted	JComboBox stedliste
	 * @param lmåned	JComboBox månedsliste
	 * @param lår	JComboBox årliste
	 * @return	Object[][] array med datoer og igjen data for et gitt sted en gitt måned
	 */
	public static Object[][] finnDataSted(Stedliste sl, JPanel panel, JComboBox fylke, JComboBox sted, JComboBox lmåned, JComboBox lår)
	{
		int f = fylke.getSelectedIndex();
		String s = (String)sted.getSelectedItem();
		int måned = lmåned.getSelectedIndex();
		int år;
		if(s == null)
		{
			SkrivMelding.skriv("Ingen steder valgt/W", panel);
			return null;
		}
		try
		{
			år = Integer.parseInt((String)lår.getSelectedItem());
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
		
		Datoliste månedliste = st.getDatoliste().finnDatoer(år, måned);
		
		int lengde = månedliste.size();
		if(lengde == 0)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
			return null;
		}
		
		Object[][] returarray = new Object[lengde][5];
		
		Iterator<Dato> iterator = månedliste.iterator();
		
		for(int i = 0; i < lengde; i++)
		{
			Dato neste = iterator.next();
			returarray[i][0] = null;
			returarray[i][1] = neste.getDato().getTime();
			returarray[i][2] = neste.getNedbør() >= 0 ? neste.getNedbør() : null;
			returarray[i][3] = neste.getMinTemp() <= Registrering.MAXMAXTEMP ? neste.getMinTemp() : null;
			returarray[i][4] = neste.getMaxTemp() <= Registrering.MAXMAXTEMP ? neste.getMaxTemp() : null;
		}
		
		return returarray;
	}
	
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
			SkrivMelding.skriv("Ingen steder valgt/W", panel);
			return null;
		}
		try
		{
			år = Integer.parseInt((String)lår.getSelectedItem());
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
		
		Datoliste årliste = st.getDatoliste().finnDatoer(år);
		
		int lengde = årliste.size();
		if(lengde == 0)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
			return null;
		}
		
		Object[][] returarray = new Object[lengde][5];
		
		Iterator<Dato> iterator = årliste.iterator();
		
		for(int i = 0; i < lengde; i++)
		{
			Dato neste = iterator.next();
			returarray[i][0] = null;
			returarray[i][1] = neste.getDato().getTime();
			returarray[i][2] = neste.getNedbør() >= 0 ? neste.getNedbør() : null;
			returarray[i][3] = neste.getMinTemp() <= Registrering.MAXMAXTEMP ? neste.getMinTemp() : null;
			returarray[i][4] = neste.getMaxTemp() <= Registrering.MAXMAXTEMP ? neste.getMaxTemp() : null;
		}
		
		return returarray;
	}
	
	/**
	 * Metode for å finne værdata for et gitt sted på en gitt dato.
	 * @author Bård Skeie
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
			SkrivMelding.skriv("Fylket har ingen registrerte steder/I", panel);
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
			SkrivMelding.skriv("Ukjent programfeil (B004)/E", panel);
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
			returarray[i][2] = neste.getDatoliste().finnDato(år, måned, dag).getNedbør() >= 0
								? neste.getDatoliste().finnDato(år, måned, dag).getNedbør() : null;
			returarray[i][3] = neste.getDatoliste().finnDato(år, måned, dag).getMinTemp() <= Registrering.MAXMAXTEMP
								? neste.getDatoliste().finnDato(år, måned, dag).getMinTemp() : null;
			returarray[i][4] = neste.getDatoliste().finnDato(år, måned, dag).getMaxTemp() <= Registrering.MAXMAXTEMP
								? neste.getDatoliste().finnDato(år, måned, dag).getMaxTemp() : null;
		}

		return returarray;
	}
}

