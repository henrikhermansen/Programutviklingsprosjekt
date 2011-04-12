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
	public static final int ØSTFOLD = 1,
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
							MØRE_OG_ROMSDAL = 15,
							SØR_TRØNDELAG = 16,
							NORD_TRØNDELAG = 17,
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
	 * Et datoliste-objekt som refererer til værdataene registrert om stedet
	 */
	private Datoliste datoliste;
	
	/**
	 * Konstruktør
	 * @param n	Navn på sted
	 * @param f	Fylkesnummer på fylket som stedet ligger i
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