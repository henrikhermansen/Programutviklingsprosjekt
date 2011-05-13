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
	 * En liste av typen ArrayList for å lagre alle datoer som er registrert for et bestemt sted.
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
	 * Sorterer Dato-objektene i listen liste i kronologisk rekkefølge.
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
	 * Finner og returnerer et Dato-objekt basert på angitt år, måned og dag.
	 * @author Henrik Hermansen
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
	 * @author Henrik Hermansen
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
	 * @author Henrik Hermansen
	 * @param år	det årstallet man vil hente datoer ut fra.
	 * @return		et Datoliste-objekt.
	 */
	public Datoliste finnDatoer(int år)
	{
		Datoliste datoliste=new Datoliste();
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getDato().get(Calendar.YEAR)==år)
				datoliste.settInn(neste);
		}
		return datoliste;
	}
	
	/**
	 * Finner og returnerer et Datoliste-objekt som inneholder registrete datoer i den måneden og det året det søkes etter.
	 * @author Henrik Hermansen
	 * @param år	det årstallet man vil hente datoer ut fra.
	 * @param måned	den måneden man vil hente datoer ut fra.
	 * @return		et Datoliste-objekt.
	 */
	public Datoliste finnDatoer(int år, int måned)
	{
		Datoliste datoliste=new Datoliste();
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getDato().get(Calendar.YEAR)==år && neste.getDato().get(Calendar.MONTH)==måned)
				datoliste.settInn(neste);
		}
		return datoliste;
	}
	
	/**
	 * Kaller videre på calcAvgTemp med passende parameter
	 * @author Henrik Hermansen
	 * @return en double-verdi fra calcAvgTemp
	 */
	public double getAvgTemp()
	{
		return calcAvgTemp("avg");
	}
	/**
	 * Kaller videre på calcAvgTemp med passende parameter
	 * @author Henrik Hermansen
	 * @return en double-verdi fra calcAvgTemp
	 */
	public double getAvgMaxTemp()
	{
		return calcAvgTemp("max");
	}
	/**
	 * Kaller videre på calcAvgTemp med passende parameter
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
	 * Finner det dato-objektet i datolisten som har den høyeste maksimumstemperaturen
	 * @author Henrik Hermansen
	 * @return en double-verdi med høyeste maxtemp
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
	 * Returnerer gjennomsnittet av nedbør for datoene i datolisten
	 * @author Henrik Hermansen
	 * @return en double-verdi med gjennomsnittet av nedbør
	 */
	public double getAvgNedbør()
	{
		int antDatoer=0;
		double totalNedbør=0.0;
		double nedbør;
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			nedbør=neste.getNedbør();
			if(nedbør>=0 && nedbør<=Registrering.MAXNEDBØR)
			{
				totalNedbør+=nedbør;
				antDatoer++;
			}
			nedbør=-1;
		}
		if(antDatoer==0)
			return -1;
		return (double) totalNedbør/antDatoer;
	}
	
	/**
	 * Finner det dato-objektet i datolisten som har den laveste nedbørsmengden
	 * @author Henrik Hermansen
	 * @return en double-verdi med laveste nedbørsmengde
	 */
	public double getMinNedbør()
	{
		double minNedbør=Registrering.MAXNEDBØR+1;
		Dato svar=null;
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getNedbør()<minNedbør)
			{
				svar=neste;
				minNedbør=svar.getNedbør();
			}
		}
		return svar.getNedbør();
	}
	
	/**
	 * Finner det dato-objektet i datolisten som har den høyeste maksimumstemperaturen
	 * @author Henrik Hermansen
	 * @return en double-verdi med høyeste nedbørsmengde
	 */
	public double getMaxNedbør()
	{
		double maxNedbør=-1;
		Dato svar=null;
		Iterator<Dato> iterator = liste.iterator();
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getNedbør()>maxNedbør)
			{
				svar=neste;
				maxNedbør=svar.getNedbør();
			}
		}
		return svar.getNedbør();
	}
}
