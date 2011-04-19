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
	 * Setter inn et sted-objekt bakerst i listen
	 * @param s	Sted-objekt
	 */
	public void settInn(Sted s)
	{
		liste.add(s);
		sorter();		
	}
	
	/**
	 * Sorterer listen etter stedsnavn
	 */
	public void sorter()
	{
		Collections.sort(liste, new Stedsammenligner(true));
	}
	
	/**
	 * Finner et sted-objekt hvis det finnes i listen
	 * @param navn	Navn på sted
	 * @return	Et sted-objekt hvis det finnes, ellers null
	 */
	public Sted finnSted(String navn)
	{
		int index = Collections.binarySearch(liste, new Sted(navn,0), new Stedsammenligner(false));
		if(index<0)
			return null;
		return liste.get(index);
	}
	
	/**
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
	
	public Stedliste finnSted(int fylke)
	{
		Stedliste stedliste=new Stedliste();
		Iterator<Sted> iterator = liste.iterator();
		while(iterator.hasNext())
			if(iterator.next().getFylke()==fylke)
				stedliste.settInn(iterator.next());
		return stedliste;
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
