package data;

import java.io.Serializable;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
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
	public static final String[] fylkesliste={"Akershus","Aust-agder","Buskerud","Finnmark","Hedmark","Hordaland","M�re og romsdal",
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
	 * @author Lars Smeby
	 * Konstrukt�r
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
	 * @author Lars Smeby
	 * Returnerer stedsnavnet
	 * @return	stedsnavn
	 */
	public String getNavn()
	{
		return navn;
	}
	
	/**
	 * @author Lars Smeby
	 * Returnerer fylkesnummeret (SSB)
	 * @return	fylkesnummer
	 */
	public int getFylke()
	{
		return fylke;
	}
	
	/**
	 * @author Lars Smeby
	 * Returnerer datoliste-objektet
	 * @return	datoliste
	 */
	public Datoliste getDatoliste()
	{
		return datoliste;
	}
	
	/**
	 * @author Lars Smeby
	 * Endrer navnet p� stedet
	 * @param n	Navn
	 */
	public void setNavn(String n)
	{
		navn = n;
	}
	
	/**
	 * @author Lars Smeby
	 * Endrer fylkesnummeret
	 * @param f	fylkesnummer (SSB)
	 */
	public void setFylke(int f)
	{
		fylke = f;
	}
}