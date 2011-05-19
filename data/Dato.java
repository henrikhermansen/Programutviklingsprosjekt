/**
 * Inneholder klassen Dato.
 * @author Henrik Hermansen
 * @since 28.04.2011
 * @version	1 13.05.2011
 */
package data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import logic.Registrering;

/**
 * Klassen representerer en dato, og inneholder værdata for denne.
 */
public class Dato implements Serializable
{
	private static final long serialVersionUID = 8069404848049956509L;
	/**
	 * minTemp	Minimumstemperatur for denne dagen.
	 * maxTemp	Maksimumstemperatur for denne dagen.
	 * nedbør	Nedbørsmengde for denne dagen.
	 * Disse blir satt til ugyldige verdier for å testes på senere om de er satt eller ikke.
	 */
	private double minTemp = Registrering.MAXMAXTEMP + 1; 
	private double maxTemp = Registrering.MAXMAXTEMP + 1;
	private double nedbør = -1;
	/**
	 * Objekt av klassen GregorianCalendar for å lagre hvilken dag, måned og år dette er.
	 */
	private GregorianCalendar dato;
	
	/**
	 * Setter dag, måned og år.
	 * Skrevet av: Henrik Hermansen
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
	 * Skrevet av: Henrik Hermansen
	 * @param dato	et ferdig GregorianCalendar-objekt.
	 */
	public Dato(GregorianCalendar dato)
	{
		this.dato=dato;
	}
	
	/**
	 * Setter laveste temperatur denne dagen.
	 * Skrevet av: Henrik Hermansen
	 * @param minTemp	minimumstemperatur denne dagen.
	 */
	public void setMinTemp(double minTemp)
	{
		this.minTemp=minTemp;
	}
	
	/**
	 * Setter høyeste temperatur denne dagen.
	 * Skrevet av: Henrik Hermansen
	 * @param maxTemp	maksimumstemperatur denne dagen.
	 */
	public void setMaxTemp(double maxTemp)
	{
		this.maxTemp=maxTemp;
	}
	
	
	/**
	 * Setter nedbørsmendge denne dagen.
	 * Skrevet av: Henrik Hermansen
	 * @param nedbør	nedbørsmengde denne dagen.
	 */
	public void setNedbør(double nedbør)
	{
		this.nedbør=nedbør;
	}
	
	/**
	 * Get-metode for dato.
	 * Skrevet av: Henrik Hermansen
	 * @return	datoen i form av et GregorianCalendar-objekt.
	 */
	public GregorianCalendar getDato()
	{
		return this.dato;
	}
	
	/**
	 * Get-metode for dato som String.
	 * Skrevet av: Henrik Hermansen
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
	 * Skrevet av: Henrik Hermansen
	 * @return	minimumstemperatur denne dagen.
	 */
	public double getMinTemp()
	{
		return this.minTemp;
	}
	
	/**
	 * Get-metode for høyeste temperatur denne dagen.
	 * Skrevet av: Henrik Hermansen
	 * @return	maksimumstemperatur denne dagen.
	 */
	public double getMaxTemp()
	{
		return this.maxTemp;
	}
	
	/**
	 * Get-metode for gjennomsnittstemperatur denne dagen.
	 * Skrevet av: Henrik Hermansen
	 * @return	gjennomsnittstemperatur denne dagen.
	 */
	public double getAvgTemp()
	{
		if(this.minTemp <= Registrering.MAXMAXTEMP && this.maxTemp <= Registrering.MAXMAXTEMP)
			return (this.minTemp+this.maxTemp)/2;
		if(this.minTemp <= Registrering.MAXMAXTEMP && this.maxTemp > Registrering.MAXMAXTEMP)
			return this.minTemp;
		if(this.minTemp > Registrering.MAXMAXTEMP && this.maxTemp <= Registrering.MAXMAXTEMP)
			return this.maxTemp;
		return this.minTemp;
	}
	
	/**
	 * Get-metoden for nedbørsmengde denne dagen.
	 * Skrevet av: Henrik Hermansen
	 * @return	nedbørsmengden denne dagen.
	 */
	public double getNedbør()
	{
		return this.nedbør;
	}
	
	/**
	 * toString-metode som gir informasjon om dagen i form av en String.
	 * Skrevet av: Henrik Hermansen
	 * @return	Dato, minimumstemperatur, maksimumstemperatur og nedbørsmengde som en String.
	 */
	public String toString()
	{
		return "Dato: "+this.getDatoString()+"\n"+
				"Minimumstemperatur: "+getMinTemp()+"\n"+
				"Maksimumstemperatur: "+getMaxTemp()+"\n"+
				"Nedbør: "+getNedbør();
	}
} // end of class Dato