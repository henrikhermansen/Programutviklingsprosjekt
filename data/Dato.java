package data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import logic.Registrering;

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
	 * Disse blir satt til ugyldige verdier for � testes p� senere om de er satt eller ikke.
	 */
	private double minTemp = Registrering.MAXMAXTEMP + 1; 
	private double maxTemp = Registrering.MAXMAXTEMP + 1;
	private double nedb�r = -1;
	/**
	 * Objekt av klassen GregorianCalendar for � lagre hvilken dag, m�ned og �r dette er.
	 */
	private GregorianCalendar dato;
	
	/**
	 * Setter dag, m�ned og �r.
	 * @author Henrik Hermansen
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
	 * @author Henrik Hermansen
	 * @param dato	et ferdig GregorianCalendar-objekt.
	 */
	public Dato(GregorianCalendar dato)
	{
		this.dato=dato;
	}
	
	/**
	 * Setter laveste temperatur denne dagen.
	 * @author Henrik Hermansen
	 * @param minTemp	minimumstemperatur denne dagen.
	 */
	public void setMinTemp(double minTemp)
	{
		this.minTemp=minTemp;
	}
	
	/**
	 * Setter h�yeste temperatur denne dagen.
	 * @author Henrik Hermansen
	 * @param maxTemp	maksimumstemperatur denne dagen.
	 */
	public void setMaxTemp(double maxTemp)
	{
		this.maxTemp=maxTemp;
	}
	
	
	/**
	 * Setter nedb�rsmendge denne dagen.
	 * @author Henrik Hermansen
	 * @param nedb�r	nedb�rsmengde denne dagen.
	 */
	public void setNedb�r(double nedb�r)
	{
		this.nedb�r=nedb�r;
	}
	
	/**
	 * Get-metode for dato.
	 * @author Henrik Hermansen
	 * @return	datoen i form av et GregorianCalendar-objekt.
	 */
	public GregorianCalendar getDato()
	{
		return this.dato;
	}
	
	/**
	 * Get-metode for dato som String.
	 * @author Henrik Hermansen
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
	 * @author Henrik Hermansen
	 * @return	minimumstemperatur denne dagen.
	 */
	public double getMinTemp()
	{
		return this.minTemp;
	}
	
	/**
	 * Get-metode for h�yeste temperatur denne dagen.
	 * @author Henrik Hermansen
	 * @return	maksimumstemperatur denne dagen.
	 */
	public double getMaxTemp()
	{
		return this.maxTemp;
	}
	
	/**
	 * Get-metode for gjennomsnittstemperatur denne dagen.
	 * @author Henrik Hermansen
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
	 * Get-metoden for nedb�rsmengde denne dagen.
	 * @author Henrik Hermansen
	 * @return	nedb�rsmengden denne dagen.
	 */
	public double getNedb�r()
	{
		return this.nedb�r;
	}
	
	/**
	 * toString-metode som gir informasjon om dagen i form av en String.
	 * @author Henrik Hermansen
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
