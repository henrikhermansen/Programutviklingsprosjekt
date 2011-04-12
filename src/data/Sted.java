package data;

import java.io.Serializable;

/**
 * @author Gruppe 3
 *
 */

public class Sted implements Serializable
{
	private static final long serialVersionUID = 5741295195692438029L;
	/**
	 * Fylkene er gitt int-konstanter etter SSBs fylkesnummer
	 */
	public static final int �STFOLD = 1,
							AKERSHUS = 2,
							OSLO = 3,
							HEDMARK = 4,
							OPPLAND = 5,
							BUSKERUD = 6,
							VESTFOLD = 7,
							TELEMARK = 8,
							AUST_AGDER = 9,
							VEST_AGDER = 10,
							ROGALAND = 11,
							HORDALAND = 12,
							SOGN_OG_FJORDANE = 14,
							M�RE_OG_ROMSDAL = 15,
							S�R_TR�NDELAG = 16,
							NORD_TR�NDELAG = 17,
							NORDLAND = 18,
							TROMS = 19,
							FINNMARK = 20;
	/**
	 * Stedsnavn
	 */
	private String navn;
	/**
	 * Fylkesnummer (SSB)
	 */
	private int fylke;
	/**
	 * Et datoliste-objekt som refererer til v�rdataene registrert om stedet
	 */
	private Datoliste datoliste;
	
	/**
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
	 * Returnerer stedsnavnet
	 * @return	stedsnavn
	 */
	public String getNavn()
	{
		return navn;
	}
	
	/**
	 * Returnerer fylkesnummeret (SSB)
	 * @return	fylkesnummer
	 */
	public int getFylke()
	{
		return fylke;
	}
	
	public Datoliste getDatoliste()
	{
		return datoliste;
	}
	
	public void setNavn(String n)
	{
		navn = n;
	}
	
	public void setFylke(int f)
	{
		fylke = f;
	}
}