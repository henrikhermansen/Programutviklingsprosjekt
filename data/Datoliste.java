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
	 * En liste av typen ArrayList for � lagre alle datoer som er registrert for et bestemt sted.
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
	 * Sorterer Dato-objektene i listen liste i kronologisk rekkef�lge.
	 */
	public void sorter()
	{
		Collections.sort(liste,new Datosammenligner());
	}
	
	/**
	 * Finner og returnerer et Dato-objekt basert p� angitt �r, m�ned og dag.
	 * @param �r	�ret i datoen det skal s�kes etter.
	 * @param m�ned	m�neden i datoen det skal s�kes etter.
	 * @param dag	dagen i datoen det skal s�kes etter.
	 * @return		et Dato-objekt basert p� s�kekriteriene.
	 */
	public Dato finnDato(int �r,int m�ned,int dag)
	{
		GregorianCalendar gc=new GregorianCalendar(�r,m�ned,dag);
		return finnDato(gc);
	}
	
	/**
	 * Finner og returnerer et Dato-objekt ved � sammenligne med et allerede opprettet Dato-objekt.
	 * @param gc	et GregorianCalendar-objekt.
	 * @return		et Dato-objekt basert p� s�kekriteriet.
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
	 * Finner og returnerer et Datoliste-objekt som inneholder registrerte datoer i det �ret det s�kes etter.
	 * @param �r	det �rstallet man vil hente datoer ut fra.
	 * @return		et Datoliste-objekt.
	 */
	public Datoliste finnDatoer(int �r)
	{
		Datoliste datoliste=new Datoliste();
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
			if(iterator.next().getDato().get(Calendar.YEAR)==�r)
				datoliste.settInn(iterator.next());
		return datoliste;
	}
	
	/**
	 * Finner og returnerer et Datoliste-objekt som inneholder registrete datoer i den m�neden og det �ret det s�kes etter.
	 * @param �r	det �rstallet man vil hente datoer ut fra.
	 * @param m�ned	den m�neden man vil hente datoer ut fra.
	 * @return		et Datoliste-objekt.
	 */
	public Datoliste finnDatoer(int �r, int m�ned)
	{
		Datoliste datoliste=new Datoliste();
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
			if(iterator.next().getDato().get(Calendar.YEAR)==�r && iterator.next().getDato().get(Calendar.MONTH)==m�ned)
				datoliste.settInn(iterator.next());
		return datoliste;
	}
}
