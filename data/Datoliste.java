package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;

import logic.Registrering;

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
	 * @author Henrik Hermansen
	 * @param dato	et ferdig objekt av klassen Dato.
	 */
	public void settInn(Dato dato)
	{
		liste.add(dato);
		this.sorter();
	}
	
	/**
	 * Sorterer Dato-objektene i listen liste i kronologisk rekkef�lge.
	 * @author Henrik Hermansen
	 */
	public void sorter()
	{
		Collections.sort(liste,new Datosammenligner());
	}
	
	/**
	 * Returnerer antall elementer i listen
	 * @author Lars Smeby
	 * @return	int antall elementer
	 */
	public int size()
	{
		return liste.size();
	}
	
	/**
	 * Returnerer iteratoren til listen
	 * @author Lars Smeby
	 * @return	Iterator<Sted> til listen
	 */
	public Iterator<Dato> iterator()
	{
		return liste.iterator();
	}
	
	/**
	 * Finner og returnerer et Dato-objekt basert p� angitt �r, m�ned og dag.
	 * @author Henrik Hermansen
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
	 * @author Henrik Hermansen
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
	 * @author Henrik Hermansen
	 * @param �r	det �rstallet man vil hente datoer ut fra.
	 * @return		et Datoliste-objekt.
	 */
	public Datoliste finnDatoer(int �r)
	{
		Datoliste datoliste=new Datoliste();
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getDato().get(Calendar.YEAR)==�r)
				datoliste.settInn(neste);
		}
		return datoliste;
	}
	
	/**
	 * Finner og returnerer et Datoliste-objekt som inneholder registrete datoer i den m�neden og det �ret det s�kes etter.
	 * @author Henrik Hermansen
	 * @param �r	det �rstallet man vil hente datoer ut fra.
	 * @param m�ned	den m�neden man vil hente datoer ut fra.
	 * @return		et Datoliste-objekt.
	 */
	public Datoliste finnDatoer(int �r, int m�ned)
	{
		Datoliste datoliste=new Datoliste();
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getDato().get(Calendar.YEAR)==�r && neste.getDato().get(Calendar.MONTH)==m�ned)
				datoliste.settInn(neste);
		}
		return datoliste;
	}
	
	/**
	 * Kaller videre p� calcAvgTemp med passende parameter
	 * @author Henrik Hermansen
	 * @return en double-verdi fra calcAvgTemp
	 */
	public double getAvgTemp()
	{
		return calcAvgTemp("avg");
	}
	/**
	 * Kaller videre p� calcAvgTemp med passende parameter
	 * @author Henrik Hermansen
	 * @return en double-verdi fra calcAvgTemp
	 */
	public double getAvgMaxTemp()
	{
		return calcAvgTemp("max");
	}
	/**
	 * Kaller videre p� calcAvgTemp med passende parameter
	 * @author Henrik Hermansen
	 * @return en double-verdi fra calcAvgTemp
	 */
	public double getAvgMinTemp()
	{
		return calcAvgTemp("min");
	}
	/**
	 * Returnerer gjennomsnittet av gjennomsnitts-, minimums- eller maksimumstemperaturene for datoene i datolisten
	 * @author Henrik Hermansen
	 * @return en double-verdi med gjennomsnittet av temperaturer
	 */
	private double calcAvgTemp(String type)
	{
		int antDatoer=0;
		double totalTemp=0.0;
		double temp=Registrering.MAXMAXTEMP+1;
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(type.equals("avg"))
				temp=neste.getAvgTemp();
			if(type.equals("max"))
				temp=neste.getMaxTemp();
			if(type.equals("min"))
				temp=neste.getMinTemp();
			if(temp>=Registrering.MAXMINTEMP && temp<=Registrering.MAXMAXTEMP)
			{
				totalTemp+=temp;
				antDatoer++;
			}
			temp=Registrering.MAXMAXTEMP+1;
		}
		if(antDatoer==0)
			return Registrering.MAXMAXTEMP+1;
		return (double) totalTemp/antDatoer;
	}
	
	/**
	 * Finner det dato-objektet i datolisten som har den laveste minimumstemperaturen
	 * @author Henrik Hermansen
	 * @return en double-verdi med laveste mintemp
	 */
	public double getMinTemp()
	{
		double minTemp=Registrering.MAXMAXTEMP+1;
		Dato svar=null;
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getMinTemp()<minTemp)
			{
				svar=neste;
				minTemp=svar.getMinTemp();
			}
		}
		return svar.getMinTemp();
	}
	
	/**
	 * Finner det dato-objektet i datolisten som har den h�yeste maksimumstemperaturen
	 * @author Henrik Hermansen
	 * @return en double-verdi med h�yeste maxtemp
	 */
	public double getMaxTemp()
	{
		double maxTemp=Registrering.MAXMINTEMP-1;
		Dato svar=null;
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getMaxTemp()>maxTemp)
			{
				svar=neste;
				maxTemp=svar.getMaxTemp();
			}
		}
		return svar.getMaxTemp();
	}
	
	/**
	 * Returnerer gjennomsnittet av nedb�r for datoene i datolisten
	 * @author Henrik Hermansen
	 * @return en double-verdi med gjennomsnittet av nedb�r
	 */
	public double getAvgNedb�r()
	{
		int antDatoer=0;
		double totalNedb�r=0.0;
		double nedb�r;
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			nedb�r=neste.getNedb�r();
			if(nedb�r>=0 && nedb�r<=Registrering.MAXNEDB�R)
			{
				totalNedb�r+=nedb�r;
				antDatoer++;
			}
			nedb�r=-1;
		}
		if(antDatoer==0)
			return -1;
		return (double) totalNedb�r/antDatoer;
	}
	
	/**
	 * Finner det dato-objektet i datolisten som har den laveste nedb�rsmengden
	 * @author Henrik Hermansen
	 * @return en double-verdi med laveste nedb�rsmengde
	 */
	public double getMinNedb�r()
	{
		double minNedb�r=Registrering.MAXNEDB�R+1;
		Dato svar=null;
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getNedb�r()<minNedb�r)
			{
				svar=neste;
				minNedb�r=svar.getNedb�r();
			}
		}
		return svar.getNedb�r();
	}
	
	/**
	 * Finner det dato-objektet i datolisten som har den h�yeste maksimumstemperaturen
	 * @author Henrik Hermansen
	 * @return en double-verdi med h�yeste nedb�rsmengde
	 */
	public double getMaxNedb�r()
	{
		double maxNedb�r=-1;
		Dato svar=null;
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getNedb�r()>maxNedb�r)
			{
				svar=neste;
				maxNedb�r=svar.getNedb�r();
			}
		}
		return svar.getNedb�r();
	}
}
