package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;


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
	
	// Søke/finnemetoder
	public Dato finnDato(int år,int måned,int dag)
	{
		GregorianCalendar gc=new GregorianCalendar(år,måned,dag);
		return finnDato(gc);
	}
	
	public Dato finnDato(GregorianCalendar gc)
	{
		Dato dato=new Dato(gc);
		int index=Collections.binarySearch(liste, dato, new Datosammenligner());
		if(index<0)
			return null;
		return liste.get(index);
	}
	
	public Dato[] finnDatoer(int år)
	{
	    String personer = "";
	    Iterator<Dato> iterator = liste.iterator();
	    while ( iterator.hasNext() )
	    {
	      personer += iterator.next().toString() + "\n";
	    }
	    return personer;
	}
	
	public Dato[] finnDatoer(int år, int måned)
	{
		
	}
}
