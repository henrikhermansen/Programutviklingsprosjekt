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
	 * nedbør	Nedbørsmengde for denne dagen.
	 */
	private int minTemp,maxTemp,nedbør;
	/**
	 * Objekt av klassen GregorianCalendar for å lagre hvilken dag, måned og år dette er.
	 */
	private GregorianCalendar dato;
	
	/**
	 * Setter dag, måned og år.
	 * @param dag	dato i måneden.
	 * @param måned	måned i året.
	 * @param år	årstall.
	 */
	public Dato(int dag, int måned, int år)
	{
		this.dato=new GregorianCalendar(år,måned,dag);
	}
	
	/**
	 * Setter dag, måned og år ved å gi et ferdig GregorianCalendar-objekt.
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
	 * Setter høyeste temperatur denne dagen.
	 * @param maxTemp	maksimumstemperatur denne dagen.
	 */
	public void setMaxTemp(int maxTemp)
	{
		this.maxTemp=maxTemp;
	}
	
	
	/**
	 * Setter nedbørsmendge denne dagen.
	 * @param nedbør	nedbørsmengde denne dagen.
	 */
	public void setNedbør(int nedbør)
	{
		this.nedbør=nedbør;
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
		int måned=this.dato.get(Calendar.MONTH);
		int år=this.dato.get(Calendar.YEAR);
		return dag+"-"+måned+"-"+år;
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
	 * Get-metode for høyeste temperatur denne dagen.
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
	 * Get-metoden for nedbørsmengde denne dagen.
	 * @return	nedbørsmengden denne dagen.
	 */
	public int getNedbør()
	{
		return this.nedbør;
	}
	
	/**
	 * toString-metode som gir informasjon om dagen i form av en String.
	 * @return	Dato, minimumstemperatur, maksimumstemperatur og nedbørsmengde som en String.
	 */
	public String toString()
	{
		return "Dato: "+this.getDatoString()+"\n"+
				"Minimumstemperatur: "+getMinTemp()+"\n"+
				"Maksimumstemperatur: "+getMaxTemp()+"\n"+
				"Nedbør: "+getNedbør();
	}
}
