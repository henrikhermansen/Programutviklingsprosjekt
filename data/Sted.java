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
	public static final int ØSTFOLD = 0,
							AKERSHUS = 1,
							OSLO = 2,
							HEDMARK = 3,
							OPPLAND = 4,
							BUSKERUD = 5,
							VESTFOLD = 6,
							TELEMARK = 7,
							AUST_AGDER = 8,
							VEST_AGDER = 9,
							ROGALAND = 10,
							HORDALAND = 11,
							SOGN_OG_FJORDANE = 12,
							MØRE_OG_ROMSDAL = 13,
							SØR_TRØNDELAG = 14,
							NORD_TRØNDELAG = 15,
							NORDLAND = 16,
							TROMS = 17,
							FINNMARK = 18;
	/**
	 * Fylkene i en String[]-konstant
	 */
	public static final String[] fylkesliste={"Østfold","Akershus","Oslo","Hedmark","Oppland","Buskerud","Vestfold","Telemark","Aust-agder",
											"Vest-agder","Rogaland","Hordaland","Sogn og fjordane","Møre og romsdal","Sør-trøndelag",
											"Nord-trøndelag","Nordland","Troms","Finnmark"};
	/**
	 * Stedsnavn
	 */
	private String navn;
	/**
	 * Fylkesnummer
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
	
	/**
	 * Returnerer datoliste-objektet
	 * @return	datoliste
	 */
	public Datoliste getDatoliste()
	{
		return datoliste;
	}
	
	/**
	 * Endrer navnet på stedet
	 * @param n	Navn
	 */
	public void setNavn(String n)
	{
		navn = n;
	}
	
	/**
	 * Endrer fylkesnummeret
	 * @param f	fylkesnummer (SSB)
	 */
	public void setFylke(int f)
	{
		fylke = f;
	}
}