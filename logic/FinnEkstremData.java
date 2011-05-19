/**
 * Inneholder klassen FinnEkstremData.
 * @author Henrik Hermansen
 * @since 12.05.2011
 * @version	1 18.05.2011
 */
package logic;

import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data.Dato;
import data.Datoliste;
import data.Sted;
import data.Stedliste;

/**
 *	Klassen inneholder statiske metoder for � hente ut ekstremdata basert p� mottatte parametere.
 *	Metoden finnData(...) kalles opp, som igjen kaller opp korrekt undermetode.
 */
public class FinnEkstremData
{
	// =========================== NIV� 1 =========================== NIV� 1 =========================== NIV� 1 =========================== NIV� 1 ===========================
	/**
	 * Hovedmetode som behandler data og sender parametere videre til korrekt undermetode.
	 * Her blir dag, m�ned og �r behandlet og gjort om til int, det blir opprettet Sted-objekt basert p� sted-comboboxen og fylke blir lagret i int basert p� fylke-comboboxen
	 * Skrevet av: Henrik Hermansen
	 * @param sl			hoved-stedlisten i programmet
	 * @param panel			panelet som kaller opp metoden
	 * @param stedLandet	radioknappen som sier om det sp�rres etter data for hele landet
	 * @param stedFylke		radioknappen som sier om det sp�rres etter data fra et bestemt fylke
	 * @param stedSted		radioknappen som sier om det sp�rres etter data fra et bestemt sted
	 * @param fylke			comboboxen med valgt fylke
	 * @param sted			comboboxen med valgt sted
	 * @param rdag			radioknappen om sier om det sp�rres etter data fra en bestemt dag
	 * @param rm�ned		radioknappen om sier om det sp�rres etter data fra en bestemt m�ned
	 * @param r�r			radioknappen om sier om det sp�rres etter data fra et bestemt �r
	 * @param ldag			comboboxen med valgt dag
	 * @param lm�ned		comboboxen med valgt m�ned
	 * @param l�r			comboboxen med valgt �r
	 * @param rEnkelverdi	radioknappen som sier om det sp�rres etter en enkeltverdi
	 * @param rAvgverdi		radioknappen som sier om det sp�rres etter en gjennomsnittsverdi
	 * @param rMinNedb�r	radioknappen som sier om det sp�rres etter data om minimumsnedb�r
	 * @param rNedb�r		radioknappen som sier om det sp�rres etter data om maksimumsnedb�r
	 * @param rMintemp		radioknappen som sier om det sp�rres etter data om minimumstemperaturer
	 * @param rMaxtemp		radioknappen som sier om det sp�rres etter data om maksimumstemperaturer
	 * @return det som returneres av "undermetodene"
	 */
	public static Object[][] finnData(Stedliste sl, JPanel panel, JRadioButton stedLandet, JRadioButton stedFylke, JRadioButton stedSted, JComboBox fylke, JComboBox sted, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, JComboBox ldag, JComboBox lm�ned, JComboBox l�r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rMinNedb�r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		int f = fylke.getSelectedIndex();
		String s = (String)sted.getSelectedItem();
		int m�ned = lm�ned.getSelectedIndex();
		int �r;
		int dag;
		if(stedSted.isSelected() && s == null)
		{
			SkrivMelding.skriv("Ingen steder valgt/W", panel);
			return null;
		}
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
			dag = Integer.parseInt((String)ldag.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			SkrivMelding.skriv("Ukjent programfeil (H-FED001)/E", panel);
			return null;
		}
		
		Sted st=null;
		if(s!=null)
			st = sl.finnSted(s, f);
		if(stedSted.isSelected() && st == null)
		{
			SkrivMelding.skriv("Ukjent programfeil (H-FED002)/E", panel);
			return null;
		}
		if(stedLandet.isSelected() || stedFylke.isSelected())
			return finnDataForSteder(sl,panel,f,rdag,rm�ned,r�r,dag,m�ned,�r,rEnkelverdi,rAvgverdi,rMinNedb�r,rNedb�r,rMintemp,rMaxtemp,stedLandet,stedFylke);
		if(stedSted.isSelected())
			return finnDataForSted(panel,st,rdag,rm�ned,r�r,dag,m�ned,�r,rEnkelverdi,rAvgverdi,rMinNedb�r,rNedb�r,rMintemp,rMaxtemp);
		return null;
	}

	// =========================== NIV� 2 =========================== NIV� 2 =========================== NIV� 2 =========================== NIV� 2 ===========================
	/**
	 * Undermetode som behandler data og sender parametere videre til korrekt undermetode.
	 * I denne metoden blir det generert en datoliste med de datoene som passer til s�ket.
	 * Skrevet av: Henrik Hermansen
	 * @param panel			panelet som kaller opp metoden
	 * @param fylke			int-representasjon av valgt fylke
	 * @param sted			Sted-objekt av valgt sted
	 * @param rdag			radioknappen om sier om det sp�rres etter data fra en bestemt dag
	 * @param rm�ned		radioknappen om sier om det sp�rres etter data fra en bestemt m�ned
	 * @param r�r			radioknappen om sier om det sp�rres etter data fra et bestemt �r
	 * @param ldag			comboboxen med valgt dag
	 * @param lm�ned		comboboxen med valgt m�ned
	 * @param l�r			comboboxen med valgt �r
	 * @param rEnkelverdi	radioknappen som sier om det sp�rres etter en enkeltverdi
	 * @param rAvgverdi		radioknappen som sier om det sp�rres etter en gjennomsnittsverdi
	 * @param rMinNedb�r	radioknappen som sier om det sp�rres etter data om minimumsnedb�r
	 * @param rNedb�r		radioknappen som sier om det sp�rres etter data om maksimumsnedb�r
	 * @param rMintemp		radioknappen som sier om det sp�rres etter data om minimumstemperaturer
	 * @param rMaxtemp		radioknappen som sier om det sp�rres etter data om maksimumstemperaturer
	 * @return det som returneres av "undermetoden"
	 */
	private static Object[][] finnDataForSted(JPanel panel, Sted sted, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rMinNedb�r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		Datoliste datoliste=new Datoliste();
		if(rm�ned.isSelected())
		{
			datoliste = sted.getDatoliste().finnDatoer(�r,m�ned);
		}
		if(r�r.isSelected())
		{
			datoliste = sted.getDatoliste().finnDatoer(�r);
		}
		
		int lengde = datoliste.size();
		if(lengde == 0)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
			return null;
		}
		if(rMinNedb�r.isSelected())
			datoliste=datoliste.getMinNedb�r();
		if(rNedb�r.isSelected())
			datoliste=datoliste.getMaxNedb�r();
		if(rMintemp.isSelected())
			datoliste=datoliste.getMinTemp();
		if(rMaxtemp.isSelected())
			datoliste=datoliste.getMaxTemp();
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForSted(datoliste,rMinNedb�r,rNedb�r,rMintemp,rMaxtemp,sted);
		return null;
	}

	/**
	 * Undermetode som behandler data og sender parametere videre til korrekt undermetode.
	 * I denne metoden blir alle steder i s�ket og alle stedene sine datoer som inng�r i s�ket gjennomg�tt for � finne ekstremverdien.
	 * Deretter blir det generert en stedliste med steder som har en eller flere datoer som har samme verdi som ekstremverdien.
	 * Skrevet av: Henrik Hermansen
	 * @param sl			hoved-stedlisten i programmet
	 * @param panel			panelet som kaller opp metoden
	 * @param fylke			int-representasjon av valgt fylke
	 * @param rdag			radioknappen om sier om det sp�rres etter data fra en bestemt dag
	 * @param rm�ned		radioknappen om sier om det sp�rres etter data fra en bestemt m�ned
	 * @param r�r			radioknappen om sier om det sp�rres etter data fra et bestemt �r
	 * @param dag			int-representasjon av valgt dag
	 * @param m�ned			int-representasjon av valgt m�ned
	 * @param �r			int-representasjon av valgt �r
	 * @param rEnkelverdi	radioknappen som sier om det sp�rres etter en enkeltverdi
	 * @param rAvgverdi		radioknappen som sier om det sp�rres etter en gjennomsnittsverdi
	 * @param rMinNedb�r	radioknappen som sier om det sp�rres etter data om minimumsnedb�r
	 * @param rNedb�r		radioknappen som sier om det sp�rres etter data om maksimumsnedb�r
	 * @param rMintemp		radioknappen som sier om det sp�rres etter data om minimumstemperaturer
	 * @param rMaxtemp		radioknappen som sier om det sp�rres etter data om maksimumstemperaturer
	 * @param stedLandet	radioknappen som sier om det sp�rres etter data for hele landet
	 * @param stedFylke		radioknappen som sier om det sp�rres etter data fra et bestemt fylke
	 * @return det som returneres av "undermetoden"
	 */
	private static Object[][] finnDataForSteder(Stedliste sl, JPanel panel, int fylke, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rMinNedb�r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp, JRadioButton stedLandet, JRadioButton stedFylke)
	{
		Stedliste stedliste=stedLandet.isSelected() ? sl : sl.finnSted(fylke);
		Stedliste aktuelleSteder=new Stedliste();
		if(stedliste.size()==0)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette omr�det i denne tidsperioden/I", panel);
			return null;
		}
		Iterator<Sted> iterator=stedliste.iterator();
		double verdi,minNedb�r,maxNedb�r,minTemp,maxTemp;
		Dato tempDato=null;
		int antVerdier=0;
		verdi=-100;
		minNedb�r=Registrering.MAXNEDB�R+1;
		maxNedb�r=-1;
		minTemp=Registrering.MAXMAXTEMP+1;
		maxTemp=Registrering.MAXMINTEMP-1;
		if(rMinNedb�r.isSelected())
		{
			while(iterator.hasNext())
			{
				Sted sted=iterator.next();
				if(rEnkelverdi.isSelected())
				{
					if(rdag.isSelected())			tempDato=sted.getDatoliste().finnDato(�r,m�ned,dag);
					else if(rm�ned.isSelected())	tempDato=sted.getDatoliste().finnDatoer(�r,m�ned).getMinNedb�r().f�rste();
					else							tempDato=sted.getDatoliste().finnDatoer(�r).getMinNedb�r().f�rste();
					if(tempDato!=null)
					{
						verdi=tempDato.getNedb�r();
						if(verdi >= 0 && verdi==minNedb�r && verdi<=Registrering.MAXNEDB�R)
						{
							if(rdag.isSelected())			antVerdier+=1;
							else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMinNedb�r().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMinNedb�r().size();
							aktuelleSteder.settInn(sted);
						}
						if(verdi >= 0 && verdi<minNedb�r && verdi<=Registrering.MAXNEDB�R)
						{
							antVerdier=0;
							if(rdag.isSelected())			antVerdier+=1;
							else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMinNedb�r().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMinNedb�r().size();
							minNedb�r=verdi;
							aktuelleSteder=new Stedliste();
							aktuelleSteder.settInn(sted);
						}
					} // end of if(tempDato!=null)
				} // end of if(rEnkelverdi.isSelected())
				else
				{
					verdi=rm�ned.isSelected() ? sted.getDatoliste().finnDatoer(�r,m�ned).getAvgNedb�r() : sted.getDatoliste().finnDatoer(�r).getAvgNedb�r();
					if(verdi>=0 && verdi==minNedb�r && verdi<=Registrering.MAXNEDB�R)
					{
						antVerdier+=1;
						aktuelleSteder.settInn(sted);
					}
					if(verdi>=0 && verdi<minNedb�r && verdi<=Registrering.MAXNEDB�R)
					{
						antVerdier=1;
						minNedb�r=verdi;
						aktuelleSteder=new Stedliste();
						aktuelleSteder.settInn(sted);
					}
				} // end of else
			} // end of while(...)
		} // end of if(rMinNedb�r.isSelected())
		if(rNedb�r.isSelected())
		{
			while(iterator.hasNext())
			{
				Sted sted=iterator.next();
				if(rEnkelverdi.isSelected())
				{
					if(rdag.isSelected())			tempDato=sted.getDatoliste().finnDato(�r,m�ned,dag);
					else if(rm�ned.isSelected())	tempDato=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxNedb�r().f�rste();
					else							tempDato=sted.getDatoliste().finnDatoer(�r).getMaxNedb�r().f�rste();
					if(tempDato!=null)
					{
						verdi=tempDato.getNedb�r();
						if(verdi >= 0 && verdi==maxNedb�r)
						{
							if(rdag.isSelected())			antVerdier+=1;
							else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxNedb�r().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMaxNedb�r().size();
							aktuelleSteder.settInn(sted);
						}
						if(verdi>maxNedb�r)
						{
							antVerdier=0;
							if(rdag.isSelected())			antVerdier+=1;
							else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxNedb�r().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMaxNedb�r().size();
							maxNedb�r=verdi;
							aktuelleSteder=new Stedliste();
							aktuelleSteder.settInn(sted);
						}
					} // end of if(tempDato!=null)
				} // end of if(rEnkelverdi.isSelected())
				else
				{
					verdi=rm�ned.isSelected() ? sted.getDatoliste().finnDatoer(�r,m�ned).getAvgNedb�r() : sted.getDatoliste().finnDatoer(�r).getAvgNedb�r();
					if(verdi>=0 && verdi==maxNedb�r)
					{
						antVerdier+=1;
						aktuelleSteder.settInn(sted);
					}
					if(verdi>maxNedb�r)
					{
						antVerdier=1;
						maxNedb�r=verdi;
						aktuelleSteder=new Stedliste();
						aktuelleSteder.settInn(sted);
					}
				} // end of else
			} // end of while(...)
		} // end of if(rNedb�r.isSelected())
		if(rMintemp.isSelected())
		{
			while(iterator.hasNext())
			{
				Sted sted=iterator.next();
				if(rEnkelverdi.isSelected())
				{
					if(rdag.isSelected())			tempDato=sted.getDatoliste().finnDato(�r,m�ned,dag);
					else if(rm�ned.isSelected())	tempDato=sted.getDatoliste().finnDatoer(�r,m�ned).getMinTemp().f�rste();
					else							tempDato=sted.getDatoliste().finnDatoer(�r).getMinTemp().f�rste();
					if(tempDato!=null)
					{
						verdi=tempDato.getMinTemp();
						if(verdi <= Registrering.MAXMAXTEMP && verdi==minTemp)
						{
							if(rdag.isSelected())			antVerdier+=1;
							else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMinTemp().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMinTemp().size();
							aktuelleSteder.settInn(sted);
						}
						if(verdi <= Registrering.MAXMAXTEMP && verdi<minTemp)
						{
							antVerdier=0;
							if(rdag.isSelected())			antVerdier+=1;
							else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMinTemp().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMinTemp().size();
							minTemp=verdi;
							aktuelleSteder=new Stedliste();
							aktuelleSteder.settInn(sted);
						}
					} // end of if(tempDato!=null)
				} // end of if(rEnkelverdi.isSelected())
				else
				{
					verdi=rm�ned.isSelected() ? sted.getDatoliste().finnDatoer(�r,m�ned).getAvgMinTemp() : sted.getDatoliste().finnDatoer(�r).getAvgMinTemp();
					if(verdi <= Registrering.MAXMAXTEMP && verdi==minTemp)
					{
						antVerdier+=1;
						aktuelleSteder.settInn(sted);
					}
					if(verdi <= Registrering.MAXMAXTEMP && verdi<minTemp)
					{
						antVerdier=1;
						minTemp=verdi;
						aktuelleSteder=new Stedliste();
						aktuelleSteder.settInn(sted);
					}
				} // end of else
			} // end of while(...)
		} // end of if(rMintemp.isSelected())
		if(rMaxtemp.isSelected())
		{
			while(iterator.hasNext())
			{
				Sted sted=iterator.next();
				if(rEnkelverdi.isSelected())
				{
					if(rdag.isSelected())			tempDato=sted.getDatoliste().finnDato(�r,m�ned,dag);
					else if(rm�ned.isSelected())	tempDato=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxTemp().f�rste();
					else							tempDato=sted.getDatoliste().finnDatoer(�r).getMaxTemp().f�rste();
					if(tempDato!=null)
					{
						verdi=tempDato.getMaxTemp();
						if(verdi <= Registrering.MAXMAXTEMP && verdi==maxTemp)
						{
							if(rdag.isSelected())			antVerdier+=1;
							else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxTemp().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMaxTemp().size();
							aktuelleSteder.settInn(sted);
						}
						if(verdi <= Registrering.MAXMAXTEMP && verdi>maxTemp)
						{
							antVerdier=0;
							if(rdag.isSelected())			antVerdier+=1;
							else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxTemp().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMaxTemp().size();
							maxTemp=verdi;
							aktuelleSteder=new Stedliste();
							aktuelleSteder.settInn(sted);
						}
					} // end of if(tempDato!=null)
				} // end of if(rEnkelverdi.isSelected())
				else
				{
					verdi=rm�ned.isSelected() ? sted.getDatoliste().finnDatoer(�r,m�ned).getAvgMaxTemp() : sted.getDatoliste().finnDatoer(�r).getAvgMaxTemp();
					if(verdi <= Registrering.MAXMAXTEMP && verdi==maxTemp)
					{
						antVerdier+=1;
						aktuelleSteder.settInn(sted);
					}
					if(verdi <= Registrering.MAXMAXTEMP && verdi>maxTemp)
					{
						antVerdier=1;
						maxTemp=verdi;
						aktuelleSteder=new Stedliste();
						aktuelleSteder.settInn(sted);
					}
				} // end of else
			} // end of while(...)
		} // end of if(rMaxtemp.isSelected())
		if((rMinNedb�r.isSelected() && minNedb�r > Registrering.MAXNEDB�R) || (rNedb�r.isSelected() && maxNedb�r == -1) || (rMintemp.isSelected() && minTemp>Registrering.MAXMAXTEMP) || (rMaxtemp.isSelected() && maxTemp<Registrering.MAXMINTEMP))
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette omr�det i denne tidsperioden/I", panel);
			return null;
		}
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForSteder(aktuelleSteder,rdag,rm�ned,r�r,dag,m�ned,�r,rMinNedb�r,rNedb�r,rMintemp,rMaxtemp,antVerdier);
		if(rAvgverdi.isSelected())
			return finnAvgverdiForSteder(aktuelleSteder, rm�ned, r�r, m�ned, �r, rMinNedb�r, rNedb�r, rMintemp, rMaxtemp, antVerdier);
		return null;
	} // end of finnDataForSteder(...)
	
	// =========================== NIV� 3 =========================== NIV� 3 =========================== NIV� 3 =========================== NIV� 3 ===========================

	/**
	 * Metoden behandler dataene som kommer inn og setter dem inn i et to-dimensjonalt array som s� returneres
	 * Skrevet av: Henrik Hermansen
	 * @param datoliste	datoliste som inneholder de datoer som har den ekstremverdien det s�kes etter
	 * @param rMinNedb�r	radioknappen som sier om det sp�rres etter data om minimumsnedb�r
	 * @param rNedb�r		radioknappen som sier om det sp�rres etter data om maksimumsnedb�r
	 * @param rMintemp	radioknappen som sier om det sp�rres etter data om minimumstemperaturer
	 * @param rMaxtemp	radioknappen som sier om det sp�rres etter data om maksimumstemperaturer
	 * @param sted		Sted-objekt for det stedet det s�kes etter
	 * @return et to-dimensjonalt array med de dataene det ble s�kt etter
	 */
	private static Object[][] finnEnkelverdiForSted(Datoliste datoliste, JRadioButton rMinNedb�r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp, Sted sted)
	{
		Object[][] returarray = new Object[datoliste.size()][6];
		int i=0;
		Iterator<Dato> iterator=datoliste.iterator();
		while(iterator.hasNext())
		{
			Dato dato=iterator.next();
			returarray[i][0] = sted.getNavn();
			returarray[i][1] = dato.getDato().getTime();
			returarray[i][2] = (rNedb�r.isSelected() || rMinNedb�r.isSelected()) ? dato.getNedb�r() : null;
			returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
			returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
			returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
			i++;
		}
		
		return returarray;
	}

	/**
	 * Metoden behandler dataene som kommer inn og setter dem i et to-dimensjonalt array som s� returneres
	 * Skrevet av: Henrik Hermansen
	 * @param sl			stedliste med de stedene som har en eller flere datoer med den ekstremverdien det s�kes etter
	 * @param rdag			radioknappen om sier om det sp�rres etter data fra en bestemt dag
	 * @param rm�ned		radioknappen om sier om det sp�rres etter data fra en bestemt m�ned
	 * @param r�r			radioknappen om sier om det sp�rres etter data fra et bestemt �r
	 * @param dag			int-representasjon av valgt dag
	 * @param m�ned			int-representasjon av valgt m�ned
	 * @param �r			int-representasjon av valgt �r
	 * @param rMinNedb�r	radioknappen som sier om det sp�rres etter data om minimumsnedb�r
	 * @param rNedb�r		radioknappen som sier om det sp�rres etter data om maksimumsnedb�r
	 * @param rMintemp		radioknappen som sier om det sp�rres etter data om minimumstemperaturer
	 * @param rMaxtemp		radioknappen som sier om det sp�rres etter data om maksimumstemperaturer
	 * @param antVerdier	int-verdi med st�rrelsen p� den f�rste dimensjonen i retur-arrayet
	 * @return et to-dimensjonalt array med de dataene det ble s�kt etter
	 */
	private static Object[][] finnEnkelverdiForSteder(Stedliste sl, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rMinNedb�r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp, int antVerdier)
	{
		Object[][] returarray = new Object[antVerdier][6];
		int i=0;
		Iterator<Sted> iterator=sl.iterator();
		Iterator<Dato> datoiterator;
		while(iterator.hasNext())
		{
			Sted sted=iterator.next();
			if(rdag.isSelected())
			{
				returarray[i][0] = sted.getNavn();
				returarray[i][1] = sted.getDatoliste().finnDato(�r,m�ned,dag).getDato().getTime();
				returarray[i][2] = (rNedb�r.isSelected() || rMinNedb�r.isSelected()) ? sted.getDatoliste().finnDato(�r,m�ned,dag).getNedb�r() : null;
				returarray[i][3] = rMintemp.isSelected() ? sted.getDatoliste().finnDato(�r,m�ned,dag).getMinTemp() : null;
				returarray[i][4] = rMaxtemp.isSelected() ? sted.getDatoliste().finnDato(�r,m�ned,dag).getMaxTemp() : null;
				returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
				i++;
			}
			else
			{
				if(rm�ned.isSelected())
				{
					if(rMinNedb�r.isSelected())		datoiterator=sted.getDatoliste().finnDatoer(�r,m�ned).getMinNedb�r().iterator();
					else if(rNedb�r.isSelected())	datoiterator=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxNedb�r().iterator();
					else if(rMintemp.isSelected())	datoiterator=sted.getDatoliste().finnDatoer(�r,m�ned).getMinTemp().iterator();
					else							datoiterator=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxTemp().iterator();
				}
				else
				{
					if(rMinNedb�r.isSelected())		datoiterator=sted.getDatoliste().finnDatoer(�r).getMinNedb�r().iterator();
					else if(rNedb�r.isSelected())	datoiterator=sted.getDatoliste().finnDatoer(�r).getMaxNedb�r().iterator();
					else if(rMintemp.isSelected())	datoiterator=sted.getDatoliste().finnDatoer(�r).getMinTemp().iterator();
					else							datoiterator=sted.getDatoliste().finnDatoer(�r).getMaxTemp().iterator();
				}
				while(datoiterator.hasNext())
				{
					Dato dato=datoiterator.next();
					returarray[i][0] = sted.getNavn();
					returarray[i][1] = dato.getDato().getTime();
					returarray[i][2] = (rNedb�r.isSelected() || rMinNedb�r.isSelected()) ? dato.getNedb�r() : null;
					returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
					returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
					returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
					i++;
				}
			} // end of else
		} // end of while(iterator.hasNext())
		return returarray;
	} // end of finnEnkelverdiForSteder(...)
	
	/**
	 * Metoden behandler dataene som kommer inn og setter dem i et to-dimensjonalt array som s� returneres
	 * Skrevet av: Henrik Hermansen
	 * @param sl			stedliste med de stedene som har en eller flere datoer med den ekstremverdien det s�kes etter
	 * @param rm�ned		radioknappen om sier om det sp�rres etter data fra en bestemt m�ned
	 * @param r�r			radioknappen om sier om det sp�rres etter data fra et bestemt �r
	 * @param m�ned			int-representasjon av valgt m�ned
	 * @param �r			int-representasjon av valgt �r
	 * @param rMinNedb�r	radioknappen som sier om det sp�rres etter data om minimumsnedb�r
	 * @param rNedb�r		radioknappen som sier om det sp�rres etter data om maksimumsnedb�r
	 * @param rMintemp		radioknappen som sier om det sp�rres etter data om minimumstemperaturer
	 * @param rMaxtemp		radioknappen som sier om det sp�rres etter data om maksimumstemperaturer
	 * @param antVerdier	int-verdi med st�rrelsen p� den f�rste dimensjonen i retur-arrayet
	 * @return et to-dimensjonalt array med de dataene det ble s�kt etter
	 */
	private static Object[][] finnAvgverdiForSteder(Stedliste sl, JRadioButton rm�ned, JRadioButton r�r, int m�ned, int �r, JRadioButton rMinNedb�r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp, int antVerdier)
	{
		Object[][] returarray = new Object[antVerdier][6];
		int i=0;
		Iterator<Sted> iterator=sl.iterator();
		double avgNedb�r, avgMinTemp, avgMaxTemp;
		while(iterator.hasNext())
		{
			Sted sted=iterator.next();
			if(rm�ned.isSelected())
			{
				avgNedb�r=sted.getDatoliste().finnDatoer(�r,m�ned).getAvgNedb�r();
				avgMinTemp=sted.getDatoliste().finnDatoer(�r,m�ned).getAvgMinTemp();
				avgMaxTemp=sted.getDatoliste().finnDatoer(�r,m�ned).getAvgMaxTemp();
			}
			else
			{
				avgNedb�r=sted.getDatoliste().finnDatoer(�r).getAvgNedb�r();
				avgMinTemp=sted.getDatoliste().finnDatoer(�r).getAvgMinTemp();
				avgMaxTemp=sted.getDatoliste().finnDatoer(�r).getAvgMaxTemp();
			}
			returarray[i][0] = sted.getNavn();
			returarray[i][1] = null;
			returarray[i][2] = (rNedb�r.isSelected() || rMinNedb�r.isSelected()) ? avgNedb�r : null;
			returarray[i][3] = rMintemp.isSelected() ? avgMinTemp : null;
			returarray[i][4] = rMaxtemp.isSelected() ? avgMaxTemp : null;
			returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
			i++;
		}
		return returarray;
	} // end of finnAvgverdiForSteder(...)
} // end of class FinnEkstremData