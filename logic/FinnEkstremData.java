package logic;

import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data.Dato;
import data.Datoliste;
import data.Sted;
import data.Stedliste;

public class FinnEkstremData
{
	// =========================== NIV� 1 =========================== NIV� 1 =========================== NIV� 1 =========================== NIV� 1 ===========================
	/**
	 * Hovedmetode som sender parametere videre til korrekt undermetode
	 * @author Henrik Hermansen
	 * @param sl
	 * @param panel
	 * @param stedLandet
	 * @param stedFylke
	 * @param stedSted
	 * @param fylke
	 * @param sted
	 * @param rdag
	 * @param rm�ned
	 * @param r�r
	 * @param ldag
	 * @param lm�ned
	 * @param l�r
	 * @param rEnkelverdi
	 * @param rAvgverdi
	 * @param rNedb�r
	 * @param rMintemp
	 * @param rMaxtemp
	 * @return
	 */
	public static Object[][] finnData(Stedliste sl, JPanel panel, JRadioButton stedLandet, JRadioButton stedFylke, JRadioButton stedSted, JComboBox fylke, JComboBox sted, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, JComboBox ldag, JComboBox lm�ned, JComboBox l�r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
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
			return finnDataForSteder(sl,panel,f,rdag,rm�ned,r�r,dag,m�ned,�r,rEnkelverdi,rAvgverdi,rNedb�r,rMintemp,rMaxtemp,stedLandet,stedFylke);
		if(stedSted.isSelected())
			return finnDataForSted(panel,st,rdag,rm�ned,r�r,dag,m�ned,�r,rEnkelverdi,rAvgverdi,rNedb�r,rMintemp,rMaxtemp);
		return null;
	}

	// =========================== NIV� 2 =========================== NIV� 2 =========================== NIV� 2 =========================== NIV� 2 ===========================
	/**
	 * Undermetoder som sender parametere videre til korrekt undermetode
	 * @author Henrik Hermansen
	 * @param sl
	 * @param panel
	 * @param fylke
	 * @param sted
	 * @param rdag
	 * @param rm�ned
	 * @param r�r
	 * @param ldag
	 * @param lm�ned
	 * @param l�r
	 * @param rEnkelverdi
	 * @param rAvgverdi
	 * @param rNedb�r
	 * @param rMintemp
	 * @param rMaxtemp
	 * @return
	 */
	private static Object[][] finnDataForSted(JPanel panel, Sted sted, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
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
		if(rNedb�r.isSelected())
			datoliste=datoliste.getMaxNedb�r();
		if(rMintemp.isSelected())
			datoliste=datoliste.getMinTemp();
		if(rMaxtemp.isSelected())
			datoliste=datoliste.getMaxTemp();
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForSted(datoliste,rNedb�r,rMintemp,rMaxtemp,sted);
		return null;
	}

	private static Object[][] finnDataForSteder(Stedliste sl, JPanel panel, int fylke, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp, JRadioButton stedLandet, JRadioButton stedFylke)
	{
		Stedliste stedliste=stedLandet.isSelected() ? sl : sl.finnSted(fylke);
		Stedliste aktuelleSteder=new Stedliste();
		if(stedliste.size()==0)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette omr�det i denne tidsperioden/I", panel);
			return null;
		}
		Iterator<Sted> iterator=stedliste.iterator();
		double verdi,maxNedb�r,minTemp,maxTemp;
		Dato tempDato=null;
		int antVerdier=0;
		verdi=-100;
		if(rNedb�r.isSelected())
		{
			maxNedb�r=-1;
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
						if(verdi==maxNedb�r)
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
					}
				}
				else
				{
					verdi=rm�ned.isSelected() ? sted.getDatoliste().finnDatoer(�r,m�ned).getAvgNedb�r() : sted.getDatoliste().finnDatoer(�r).getAvgNedb�r();
					if(verdi==maxNedb�r)
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
				}
			}
		}
		if(rMintemp.isSelected())
		{
			minTemp=Registrering.MAXMAXTEMP+1;
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
						if(verdi==minTemp)
						{
							if(rdag.isSelected())			antVerdier+=1;
							else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMinTemp().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMinTemp().size();
							aktuelleSteder.settInn(sted);
						}
						if(verdi<minTemp)
						{
							antVerdier=0;
							if(rdag.isSelected())			antVerdier+=1;
							else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMinTemp().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMinTemp().size();
							minTemp=verdi;
							aktuelleSteder=new Stedliste();
							aktuelleSteder.settInn(sted);
						}
					}
				}
				else
				{
					verdi=rm�ned.isSelected() ? sted.getDatoliste().finnDatoer(�r,m�ned).getAvgMinTemp() : sted.getDatoliste().finnDatoer(�r).getAvgMinTemp();
					if(verdi==minTemp)
					{
						antVerdier+=1;
						aktuelleSteder.settInn(sted);
					}
					if(verdi<minTemp)
					{
						antVerdier=1;
						minTemp=verdi;
						aktuelleSteder=new Stedliste();
						aktuelleSteder.settInn(sted);
					}
				}
			}
		}
		if(rMaxtemp.isSelected())
		{
			maxTemp=Registrering.MAXMINTEMP-1;
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
						if(verdi==maxTemp)
						{
							if(rdag.isSelected())			antVerdier+=1;
							else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxTemp().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMaxTemp().size();
							aktuelleSteder.settInn(sted);
						}
						if(verdi>maxTemp)
						{
							antVerdier=0;
							if(rdag.isSelected())			antVerdier+=1;
							else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxTemp().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMaxTemp().size();
							maxTemp=verdi;
							aktuelleSteder=new Stedliste();
							aktuelleSteder.settInn(sted);
						}
					}
				}
				else
				{
					verdi=rm�ned.isSelected() ? sted.getDatoliste().finnDatoer(�r,m�ned).getAvgMaxTemp() : sted.getDatoliste().finnDatoer(�r).getAvgMaxTemp();
					if(verdi==maxTemp)
					{
						antVerdier+=1;
						aktuelleSteder.settInn(sted);
					}
					if(verdi>maxTemp)
					{
						antVerdier=1;
						maxTemp=verdi;
						aktuelleSteder=new Stedliste();
						aktuelleSteder.settInn(sted);
					}
				}
			}
		}
		if(verdi==-100 || (verdi==-1 && rNedb�r.isSelected()) || verdi<Registrering.MAXMINTEMP || verdi>Registrering.MAXMAXTEMP)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette omr�det i denne tidsperioden8/I", panel);
			return null;
		}
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForSteder(aktuelleSteder,panel,rdag,rm�ned,r�r,dag,m�ned,�r,rNedb�r,rMintemp,rMaxtemp,antVerdier);
		if(rAvgverdi.isSelected())
			return finnAvgverdiForSteder(aktuelleSteder, panel, rm�ned, r�r, m�ned, �r, rNedb�r, rMintemp, rMaxtemp, antVerdier);
		return null;
	}
	
	// =========================== NIV� 3 =========================== NIV� 3 =========================== NIV� 3 =========================== NIV� 3 ===========================
	/**
	 * Undermetoder bla bla
	 * @author Henrik Hermansen
	 * @param sl
	 * @param panel
	 * @param rdag
	 * @param rm�ned
	 * @param r�r
	 * @param ldag
	 * @param lm�ned
	 * @param l�r
	 * @param rNedb�r
	 * @param rMintemp
	 * @param rMaxtemp
	 * @return
	 */

	private static Object[][] finnEnkelverdiForSted(Datoliste datoliste, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp, Sted sted)
	{
		Object[][] returarray = new Object[datoliste.size()][5];
		int i=0;
		Iterator<Dato> iterator=datoliste.iterator();
		while(iterator.hasNext())
		{
			Dato dato=iterator.next();
			returarray[i][0] = sted.getNavn();
			returarray[i][1] = dato.getDato().getTime();
			returarray[i][2] = rNedb�r.isSelected() ? dato.getNedb�r() : null;
			returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
			returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
			i++;
		}
		
		return returarray;
	}

	private static Object[][] finnEnkelverdiForSteder(Stedliste sl, JPanel panel, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp, int antVerdier)
	{
		Object[][] returarray = new Object[antVerdier][5];
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
				returarray[i][2] = rNedb�r.isSelected() ? sted.getDatoliste().finnDato(�r,m�ned,dag).getNedb�r() : null;
				returarray[i][3] = rMintemp.isSelected() ? sted.getDatoliste().finnDato(�r,m�ned,dag).getMinTemp() : null;
				returarray[i][4] = rMaxtemp.isSelected() ? sted.getDatoliste().finnDato(�r,m�ned,dag).getMaxTemp() : null;
				i++;
			}
			else
			{
				if(rm�ned.isSelected())
				{
					if(rNedb�r.isSelected())		datoiterator=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxNedb�r().iterator();
					else if(rMintemp.isSelected())	datoiterator=sted.getDatoliste().finnDatoer(�r,m�ned).getMinTemp().iterator();
					else							datoiterator=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxTemp().iterator();
				}
				else
				{
					if(rNedb�r.isSelected())		datoiterator=sted.getDatoliste().finnDatoer(�r).getMaxNedb�r().iterator();
					else if(rMintemp.isSelected())	datoiterator=sted.getDatoliste().finnDatoer(�r).getMinTemp().iterator();
					else							datoiterator=sted.getDatoliste().finnDatoer(�r).getMaxTemp().iterator();
				}
				while(datoiterator.hasNext())
				{
					Dato dato=datoiterator.next();
					returarray[i][0] = sted.getNavn();
					returarray[i][1] = dato.getDato().getTime();
					returarray[i][2] = rNedb�r.isSelected() ? dato.getNedb�r() : null;
					returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
					returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
					i++;
				}
			}
		}
		return returarray;
	}
	
	private static Object[][] finnAvgverdiForSteder(Stedliste sl, JPanel panel, JRadioButton rm�ned, JRadioButton r�r, int m�ned, int �r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp, int antVerdier)
	{
		Object[][] returarray = new Object[antVerdier][5];
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
			returarray[i][2] = rNedb�r.isSelected() ? avgNedb�r : null;
			returarray[i][3] = rMintemp.isSelected() ? avgMinTemp : null;
			returarray[i][4] = rMaxtemp.isSelected() ? avgMaxTemp : null;
			i++;
		}
		return returarray;
	}
}
