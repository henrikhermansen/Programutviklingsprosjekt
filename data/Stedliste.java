package data;

import java.io.Serializable;
import java.util.*;

/**
 * @author	Gruppe 3
 * @version	1
 * @since	1.6
 */
public class Stedliste implements Serializable
{
	private static final long serialVersionUID = 4783661286758461364L;
	/**
	 * Arrayliste som inneholder stedene
	 */
	private ArrayList<Sted> liste = new ArrayList<Sted>();
	
	/**
	 * @author Lars Smeby
	 * Setter inn et sted-objekt bakerst i listen
	 * @param s	Sted-objekt
	 */
	public void settInn(Sted s)
	{
		liste.add(s);
		sorter();		
	}
	
	/**
	 * @author Lars Smeby
	 * Sorterer listen etter stedsnavn
	 */
	public void sorter()
	{
		Collections.sort(liste, new Stedsammenligner(true));
	}
	
	/**
	 * @author Lars Smeby
	 * Returnerer antall elementer i listen
	 * @return	int antall elementer
	 */
	public int size()
	{
		return liste.size();
	}
	
	/**
	 * @author Lars Smeby
	 * Returnerer iteratoren til listen
	 * @return	Iterator<Sted> til listen
	 */
	public Iterator<Sted> iterator()
	{
		return liste.iterator();
	}
	
	/**
	 * @author Lars Smeby
	 * Finner et sted-objekt hvis det finnes i listen
	 * IKKE I BRUK
	 * @param navn	Navn på sted
	 * @return	Et sted-objekt hvis det finnes, ellers null
	 */
//	public Sted finnSted(String navn)
//	{
//		int index = Collections.binarySearch(liste, new Sted(navn,0), new Stedsammenligner(false));
//		if(index<0)
//			return null;
//		return liste.get(index);
//	}
	
	/**
	 * @author Lars Smeby
	 * Finner et sted-objekt hvis det finnes i listen
	 * @param navn	Navn på sted
	 * @param fylke	Fylket stedet ligger i (SSBs fylkesnummer)
	 * @return	Et sted-objekt hvis det finnes, ellers null
	 */
	public Sted finnSted(String navn, int fylke)
	{
		int index = Collections.binarySearch(liste, new Sted(navn, fylke), new Stedsammenligner(true));
		if(index<0)
			return null;
		return liste.get(index);
	}
	
	/**
	 * @author Henrik Hermansen
	 * Returnerer en Stedliste med stedene i et gitt fylke
	 * @param fylke	int fylkesnummeret
	 * @return	Stedliste med Stedobjektene i et gitt fylke
	 */
	public Stedliste finnSted(int fylke)
	{
		Stedliste stedliste = new Stedliste();
		Iterator<Sted> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Sted neste = iterator.next();
			if(neste.getFylke()==fylke)
				stedliste.settInn(neste);
		}
		return stedliste;
	}
	
	/**
	 * @author Lars Smeby
	 * @param fylke	Fylkesnummer
	 * @return	String-array av alle stedsnavnene i et gitt fylke
	 */
	public String[] toString(int fylke)
	{
		Stedliste templiste = finnSted(fylke);
		Iterator<Sted> iterator = templiste.iterator();
		String[] steder = new String[templiste.size()];
		int løkketeller = 0;
		
		while(iterator.hasNext())
			steder[løkketeller++] = iterator.next().getNavn();

		return steder;
	}
	
	/**
	 * Sletter et sted og alle dets data fra listen (Alle værdata går også tapt)
	 * @param s	Sted-objekt
	 */
	public void slettSted(Sted s)
	{
		liste.remove(s);
		sorter();
	}
}
