package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class Datoliste implements Serializable
{
	private static final long serialVersionUID = 5128304930051989367L;
	
	/**
	 * En liste av typen ArrayList for å lagre alle datoer som er registrert for et bestemt sted.
	 */
	private ArrayList<Dato> liste=new ArrayList<Dato>();
	
	/**
	 * Setter Dato-objektet inn i listen liste.
	 * @param dato	et ferdig objekt av klassen Dato.
	 */
	public void settInn(Dato dato)
	{
		liste.add(dato);
		this.sorter();
	}
	
	/**
	 * Sorterer Dato-objektene i listen liste i kronologisk rekkefølge.
	 */
	public void sorter()
	{
		Collections.sort(liste,new Datosammenligner());
	}
	
	/**
	 * Finner og returnerer et Dato-objekt basert på angitt år, måned og dag.
	 * @param år	året i datoen det skal søkes etter.
	 * @param måned	måneden i datoen det skal søkes etter.
	 * @param dag	dagen i datoen det skal søkes etter.
	 * @return		et Dato-objekt basert på søkekriteriene.
	 */
	public Dato finnDato(int år,int måned,int dag)
	{
		GregorianCalendar gc=new GregorianCalendar(år,måned,dag);
		return finnDato(gc);
	}
	
	/**
	 * Finner og returnerer et Dato-objekt ved å sammenligne med et allerede opprettet Dato-objekt.
	 * @param gc	et GregorianCalendar-objekt.
	 * @return		et Dato-objekt basert på søkekriteriet.
	 */
	public Dato finnDato(GregorianCalendar gc)
	{
		Dato dato=new Dato(gc);
		int index=Collections.binarySearch(liste, dato, new Datosammenligner());
		if(index<0)
			return null;
		return liste.get(index);
	}
	
	/**
	 * Finner og returnerer et Datoliste-objekt som inneholder registrerte datoer i det året det søkes etter.
	 * @param år	det årstallet man vil hente datoer ut fra.
	 * @return		et Datoliste-objekt.
	 */
	public Datoliste finnDatoer(int år)
	{
		Datoliste datoliste=new Datoliste();
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
			if(iterator.next().getDato().get(Calendar.YEAR)==år)
				datoliste.settInn(iterator.next());
		return datoliste;
	}
	
	/**
	 * Finner og returnerer et Datoliste-objekt som inneholder registrete datoer i den måneden og det året det søkes etter.
	 * @param år	det årstallet man vil hente datoer ut fra.
	 * @param måned	den måneden man vil hente datoer ut fra.
	 * @return		et Datoliste-objekt.
	 */
	public Datoliste finnDatoer(int år, int måned)
	{
		Datoliste datoliste=new Datoliste();
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
			if(iterator.next().getDato().get(Calendar.YEAR)==år && iterator.next().getDato().get(Calendar.MONTH)==måned)
				datoliste.settInn(iterator.next());
		return datoliste;
	}
}
