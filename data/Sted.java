/**
 * Inneholder klassen Sted.
 * @author Lars Smeby
 * @since 28.04.2011
 * @version	1 16.05.2011
 */
package data;

import java.io.Serializable;

/**
 *	Klassen representerer et sted, og inneholder navn, fylke og en Datoliste for stedet.
 *	Klassen inneholder ogs� statiske int- og String-variabler til fylkene, som brukes
 *	i resten av programmet.
 */
public class Sted implements Serializable
{
	private static final long serialVersionUID = 5741295195692438029L;
	/**
	 * Fylkene er gitt int-konstanter
	 */
	public static final int AKERSHUS = 0,
							AUST_AGDER = 1,
							BUSKERUD = 2,
							FINNMARK = 3,
							HEDMARK = 4,
							HORDALAND = 5,
							M�RE_OG_ROMSDAL = 6,
							NORD_TR�NDELAG = 7,
							NORDLAND = 8,
							OPPLAND = 9,
							OSLO = 10,
							ROGALAND = 11,
							SOGN_OG_FJORDANE = 12,
							S�R_TR�NDELAG = 13,
							TELEMARK = 14,
							TROMS = 15,
							VEST_AGDER = 16,
							VESTFOLD = 17,
							�STFOLD = 18;							
							
	/**
	 * Fylkene i en String[]-konstant
	 */
	public static final String[] FYLKESLISTE={"Akershus","Aust-agder","Buskerud","Finnmark","Hedmark","Hordaland","M�re og romsdal",
											"Nord-tr�ndelag","Nordland","Oppland","Oslo","Rogaland","Sogn og fjordane",
											"S�r-tr�ndelag","Telemark","Troms","Vest-agder","Vestfold","�stfold"};
	/**
	 * Stedsnavn
	 */
	private String navn;
	/**
	 * Fylkesnummer
	 */
	private int fylke;
	/**
	 * Et datoliste-objekt som refererer til v�rdataene registrert om stedet
	 */
	private Datoliste datoliste;
	
	/**
	 * Konstrukt�r
	 * Skrevet av: Lars Smeby
	 * @param n	Navn p� sted
	 * @param f	Fylkesnummer p� fylket som stedet ligger i
	 */
	public Sted(String n, int f)
	{
		navn = n;
		fylke = f;
		datoliste = new Datoliste();
	}
	
	/**
	 * Returnerer stedsnavnet
	 * Skrevet av: Lars Smeby
	 * @return	stedsnavn
	 */
	public String getNavn()
	{
		return navn;
	}
	
	/**
	 * Returnerer fylkesnummeret
	 * Skrevet av: Lars Smeby
	 * @return	fylkesnummer
	 */
	public int getFylke()
	{
		return fylke;
	}
	
	/**
	 * Returnerer datoliste-objektet
	 * Skrevet av: Lars Smeby
	 * @return	datoliste
	 */
	public Datoliste getDatoliste()
	{
		return datoliste;
	}
	
	/**
	 * Endrer navnet p� stedet
	 * Skrevet av: Lars Smeby
	 * @param n	Navn
	 */
	public void setNavn(String n)
	{
		navn = n;
	}
	
	/**
	 * Endrer fylkesnummeret
	 * Skrevet av: Lars Smeby
	 * @param f	fylkesnummer
	 */
	public void setFylke(int f)
	{
		fylke = f;
	}
} // end of class Sted