package data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author	Gruppe 3
 * @version	1
 * @since	1.6
 */
public class Dato implements Serializable
{
	private static final long serialVersionUID = 8069404848049956509L;
	/**
	 * minTemp	Minimumstemperatur for denne dagen.
	 * maxTemp	Maksimumstemperatur for denne dagen.
	 * nedb�r	Nedb�rsmengde for denne dagen.
	 */
	private int minTemp,maxTemp,nedb�r;
	/**
	 * Objekt av klassen GregorianCalendar for � lagre hvilken dag, m�ned og �r dette er.
	 */
	private GregorianCalendar dato;
	
	/**
	 * Setter dag, m�ned og �r.
	 * @param dag	dato i m�neden.
	 * @param m�ned	m�ned i �ret.
	 * @param �r	�rstall.
	 */
	public Dato(int dag, int m�ned, int �r)
	{
		this.dato=new GregorianCalendar(�r,m�ned,dag);
	}
	
	/**
	 * Setter dag, m�ned og �r ved � gi et ferdig GregorianCalendar-objekt.
	 * @param dato	et ferdig GregorianCalendar-objekt.
	 */
	public Dato(GregorianCalendar dato)
	{
		this.dato=dato;
	}
	
	/**
	 * Setter laveste temperatur denne dagen.
	 * @param minTemp	minimumstemperatur denne dagen.
	 */
	public void setMinTemp(int minTemp)
	{
		this.minTemp=minTemp;
	}
	
	/**
	 * Setter h�yeste temperatur denne dagen.
	 * @param maxTemp	maksimumstemperatur denne dagen.
	 */
	public void setMaxTemp(int maxTemp)
	{
		this.maxTemp=maxTemp;
	}
	
	
	/**
	 * Setter nedb�rsmendge denne dagen.
	 * @param nedb�r	nedb�rsmengde denne dagen.
	 */
	public void setNedb�r(int nedb�r)
	{
		this.nedb�r=nedb�r;
	}
	
	/**
	 * Get-metode for dato.
	 * @return	datoen i form av et GregorianCalendar-objekt.
	 */
	public GregorianCalendar getDato()
	{
		return this.dato;
	}
	
	/**
	 * Get-metode for dato som String.
	 * @return	datoen i form av en String.
	 */
	public String getDatoString()
	{
		int dag=this.dato.get(Calendar.DATE);
		int m�ned=this.dato.get(Calendar.MONTH);
		int �r=this.dato.get(Calendar.YEAR);
		return dag+"-"+m�ned+"-"+�r;
	}
	
	/**
	 * Get-metode for laveste temperatur denne dagen.
	 * @return	minimumstemperatur denne dagen.
	 */
	public int getMinTemp()
	{
		return this.minTemp;
	}
	
	/**
	 * Get-metode for h�yeste temperatur denne dagen.
	 * @return	maksimumstemperatur denne dagen.
	 */
	public int getMaxTemp()
	{
		return this.maxTemp;
	}
	
	/**
	 * Get-metode for gjennomsnittstemperatur denne dagen.
	 * @return	gjennomsnittstemperatur denne dagen.
	 */
	public int getAvgTemp()
	{
		return (this.minTemp+this.maxTemp)/2;
	}
	
	/**
	 * Get-metoden for nedb�rsmengde denne dagen.
	 * @return	nedb�rsmengden denne dagen.
	 */
	public int getNedb�r()
	{
		return this.nedb�r;
	}
	
	/**
	 * toString-metode som gir informasjon om dagen i form av en String.
	 * @return	Dato, minimumstemperatur, maksimumstemperatur og nedb�rsmengde som en String.
	 */
	public String toString()
	{
		return "Dato: "+this.getDatoString()+"\n"+
				"Minimumstemperatur: "+getMinTemp()+"\n"+
				"Maksimumstemperatur: "+getMaxTemp()+"\n"+
				"Nedb�r: "+getNedb�r();
	}
}
