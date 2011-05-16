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
	// =========================== NIVÅ 1 =========================== NIVÅ 1 =========================== NIVÅ 1 =========================== NIVÅ 1 ===========================
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
	 * @param rmåned
	 * @param rår
	 * @param ldag
	 * @param lmåned
	 * @param lår
	 * @param rEnkelverdi
	 * @param rAvgverdi
	 * @param rNedbør
	 * @param rMintemp
	 * @param rMaxtemp
	 * @return
	 */
	public static Object[][] finnData(Stedliste sl, JPanel panel, JRadioButton stedLandet, JRadioButton stedFylke, JRadioButton stedSted, JComboBox fylke, JComboBox sted, JRadioButton rdag, JRadioButton rmåned, JRadioButton rår, JComboBox ldag, JComboBox lmåned, JComboBox lår, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		int f = fylke.getSelectedIndex();
		String s = (String)sted.getSelectedItem();
		int måned = lmåned.getSelectedIndex();
		int år;
		int dag;
		if(stedSted.isSelected() && s == null)
		{
			SkrivMelding.skriv("Ingen steder valgt/W", panel);
			return null;
		}
		try
		{
			år = Integer.parseInt((String)lår.getSelectedItem());
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
			return finnDataForSteder(sl,panel,f,rdag,rmåned,rår,dag,måned,år,rEnkelverdi,rAvgverdi,rNedbør,rMintemp,rMaxtemp,stedLandet,stedFylke);
		if(stedSted.isSelected())
			return finnDataForSted(panel,st,rdag,rmåned,rår,dag,måned,år,rEnkelverdi,rAvgverdi,rNedbør,rMintemp,rMaxtemp);
		return null;
	}

	// =========================== NIVÅ 2 =========================== NIVÅ 2 =========================== NIVÅ 2 =========================== NIVÅ 2 ===========================
	/**
	 * Undermetoder som sender parametere videre til korrekt undermetode
	 * @author Henrik Hermansen
	 * @param sl
	 * @param panel
	 * @param fylke
	 * @param sted
	 * @param rdag
	 * @param rmåned
	 * @param rår
	 * @param ldag
	 * @param lmåned
	 * @param lår
	 * @param rEnkelverdi
	 * @param rAvgverdi
	 * @param rNedbør
	 * @param rMintemp
	 * @param rMaxtemp
	 * @return
	 */
	private static Object[][] finnDataForSted(JPanel panel, Sted sted, JRadioButton rdag, JRadioButton rmåned, JRadioButton rår, int dag, int måned, int år, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		Datoliste datoliste=new Datoliste();
		if(rmåned.isSelected())
		{
			datoliste = sted.getDatoliste().finnDatoer(år,måned);
		}
		if(rår.isSelected())
		{
			datoliste = sted.getDatoliste().finnDatoer(år);
		}
		
		int lengde = datoliste.size();
		if(lengde == 0)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
			return null;
		}
		if(rNedbør.isSelected())
			datoliste=datoliste.getMaxNedbør();
		if(rMintemp.isSelected())
			datoliste=datoliste.getMinTemp();
		if(rMaxtemp.isSelected())
			datoliste=datoliste.getMaxTemp();
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForSted(datoliste,rNedbør,rMintemp,rMaxtemp,sted);
		return null;
	}

	private static Object[][] finnDataForSteder(Stedliste sl, JPanel panel, int fylke, JRadioButton rdag, JRadioButton rmåned, JRadioButton rår, int dag, int måned, int år, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp, JRadioButton stedLandet, JRadioButton stedFylke)
	{
		Stedliste stedliste=stedLandet.isSelected() ? sl : sl.finnSted(fylke);
		Stedliste aktuelleSteder=new Stedliste();
		if(stedliste.size()==0)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette området i denne tidsperioden/I", panel);
			return null;
		}
		Iterator<Sted> iterator=stedliste.iterator();
		double verdi,maxNedbør,minTemp,maxTemp;
		Dato tempDato=null;
		int antVerdier=0;
		verdi=-100;
		if(rNedbør.isSelected())
		{
			maxNedbør=-1;
			while(iterator.hasNext())
			{
				Sted sted=iterator.next();
				if(rEnkelverdi.isSelected())
				{
					if(rdag.isSelected())			tempDato=sted.getDatoliste().finnDato(år,måned,dag);
					else if(rmåned.isSelected())	tempDato=sted.getDatoliste().finnDatoer(år,måned).getMaxNedbør().første();
					else							tempDato=sted.getDatoliste().finnDatoer(år).getMaxNedbør().første();
					if(tempDato!=null)
					{
						verdi=tempDato.getNedbør();
						if(verdi==maxNedbør)
						{
							if(rdag.isSelected())			antVerdier+=1;
							else if(rmåned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(år,måned).getMaxNedbør().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(år).getMaxNedbør().size();
							aktuelleSteder.settInn(sted);
						}
						if(verdi>maxNedbør)
						{
							antVerdier=0;
							if(rdag.isSelected())			antVerdier+=1;
							else if(rmåned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(år,måned).getMaxNedbør().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(år).getMaxNedbør().size();
							maxNedbør=verdi;
							aktuelleSteder=new Stedliste();
							aktuelleSteder.settInn(sted);
						}
					}
				}
				else
				{
					verdi=rmåned.isSelected() ? sted.getDatoliste().finnDatoer(år,måned).getAvgNedbør() : sted.getDatoliste().finnDatoer(år).getAvgNedbør();
					if(verdi==maxNedbør)
					{
						antVerdier+=1;
						aktuelleSteder.settInn(sted);
					}
					if(verdi>maxNedbør)
					{
						antVerdier=1;
						maxNedbør=verdi;
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
					if(rdag.isSelected())			tempDato=sted.getDatoliste().finnDato(år,måned,dag);
					else if(rmåned.isSelected())	tempDato=sted.getDatoliste().finnDatoer(år,måned).getMinTemp().første();
					else							tempDato=sted.getDatoliste().finnDatoer(år).getMinTemp().første();
					if(tempDato!=null)
					{
						verdi=tempDato.getMinTemp();
						if(verdi==minTemp)
						{
							if(rdag.isSelected())			antVerdier+=1;
							else if(rmåned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(år,måned).getMinTemp().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(år).getMinTemp().size();
							aktuelleSteder.settInn(sted);
						}
						if(verdi<minTemp)
						{
							antVerdier=0;
							if(rdag.isSelected())			antVerdier+=1;
							else if(rmåned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(år,måned).getMinTemp().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(år).getMinTemp().size();
							minTemp=verdi;
							aktuelleSteder=new Stedliste();
							aktuelleSteder.settInn(sted);
						}
					}
				}
				else
				{
					verdi=rmåned.isSelected() ? sted.getDatoliste().finnDatoer(år,måned).getAvgMinTemp() : sted.getDatoliste().finnDatoer(år).getAvgMinTemp();
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
					if(rdag.isSelected())			tempDato=sted.getDatoliste().finnDato(år,måned,dag);
					else if(rmåned.isSelected())	tempDato=sted.getDatoliste().finnDatoer(år,måned).getMaxTemp().første();
					else							tempDato=sted.getDatoliste().finnDatoer(år).getMaxTemp().første();
					if(tempDato!=null)
					{
						verdi=tempDato.getMaxTemp();
						if(verdi==maxTemp)
						{
							if(rdag.isSelected())			antVerdier+=1;
							else if(rmåned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(år,måned).getMaxTemp().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(år).getMaxTemp().size();
							aktuelleSteder.settInn(sted);
						}
						if(verdi>maxTemp)
						{
							antVerdier=0;
							if(rdag.isSelected())			antVerdier+=1;
							else if(rmåned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(år,måned).getMaxTemp().size();
							else							antVerdier+=sted.getDatoliste().finnDatoer(år).getMaxTemp().size();
							maxTemp=verdi;
							aktuelleSteder=new Stedliste();
							aktuelleSteder.settInn(sted);
						}
					}
				}
				else
				{
					verdi=rmåned.isSelected() ? sted.getDatoliste().finnDatoer(år,måned).getAvgMaxTemp() : sted.getDatoliste().finnDatoer(år).getAvgMaxTemp();
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
		if(verdi==-100 || (verdi==-1 && rNedbør.isSelected()) || verdi<Registrering.MAXMINTEMP || verdi>Registrering.MAXMAXTEMP)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette området i denne tidsperioden8/I", panel);
			return null;
		}
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForSteder(aktuelleSteder,panel,rdag,rmåned,rår,dag,måned,år,rNedbør,rMintemp,rMaxtemp,antVerdier);
		if(rAvgverdi.isSelected())
			return finnAvgverdiForSteder(aktuelleSteder, panel, rmåned, rår, måned, år, rNedbør, rMintemp, rMaxtemp, antVerdier);
		return null;
	}
	
	// =========================== NIVÅ 3 =========================== NIVÅ 3 =========================== NIVÅ 3 =========================== NIVÅ 3 ===========================
	/**
	 * Undermetoder bla bla
	 * @author Henrik Hermansen
	 * @param sl
	 * @param panel
	 * @param rdag
	 * @param rmåned
	 * @param rår
	 * @param ldag
	 * @param lmåned
	 * @param lår
	 * @param rNedbør
	 * @param rMintemp
	 * @param rMaxtemp
	 * @return
	 */

	private static Object[][] finnEnkelverdiForSted(Datoliste datoliste, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp, Sted sted)
	{
		Object[][] returarray = new Object[datoliste.size()][5];
		int i=0;
		Iterator<Dato> iterator=datoliste.iterator();
		while(iterator.hasNext())
		{
			Dato dato=iterator.next();
			returarray[i][0] = sted.getNavn();
			returarray[i][1] = dato.getDato().getTime();
			returarray[i][2] = rNedbør.isSelected() ? dato.getNedbør() : null;
			returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
			returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
			i++;
		}
		
		return returarray;
	}

	private static Object[][] finnEnkelverdiForSteder(Stedliste sl, JPanel panel, JRadioButton rdag, JRadioButton rmåned, JRadioButton rår, int dag, int måned, int år, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp, int antVerdier)
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
				returarray[i][1] = sted.getDatoliste().finnDato(år,måned,dag).getDato().getTime();
				returarray[i][2] = rNedbør.isSelected() ? sted.getDatoliste().finnDato(år,måned,dag).getNedbør() : null;
				returarray[i][3] = rMintemp.isSelected() ? sted.getDatoliste().finnDato(år,måned,dag).getMinTemp() : null;
				returarray[i][4] = rMaxtemp.isSelected() ? sted.getDatoliste().finnDato(år,måned,dag).getMaxTemp() : null;
				i++;
			}
			else
			{
				if(rmåned.isSelected())
				{
					if(rNedbør.isSelected())		datoiterator=sted.getDatoliste().finnDatoer(år,måned).getMaxNedbør().iterator();
					else if(rMintemp.isSelected())	datoiterator=sted.getDatoliste().finnDatoer(år,måned).getMinTemp().iterator();
					else							datoiterator=sted.getDatoliste().finnDatoer(år,måned).getMaxTemp().iterator();
				}
				else
				{
					if(rNedbør.isSelected())		datoiterator=sted.getDatoliste().finnDatoer(år).getMaxNedbør().iterator();
					else if(rMintemp.isSelected())	datoiterator=sted.getDatoliste().finnDatoer(år).getMinTemp().iterator();
					else							datoiterator=sted.getDatoliste().finnDatoer(år).getMaxTemp().iterator();
				}
				while(datoiterator.hasNext())
				{
					Dato dato=datoiterator.next();
					returarray[i][0] = sted.getNavn();
					returarray[i][1] = dato.getDato().getTime();
					returarray[i][2] = rNedbør.isSelected() ? dato.getNedbør() : null;
					returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
					returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
					i++;
				}
			}
		}
		return returarray;
	}
	
	private static Object[][] finnAvgverdiForSteder(Stedliste sl, JPanel panel, JRadioButton rmåned, JRadioButton rår, int måned, int år, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp, int antVerdier)
	{
		Object[][] returarray = new Object[antVerdier][5];
		int i=0;
		Iterator<Sted> iterator=sl.iterator();
		double avgNedbør, avgMinTemp, avgMaxTemp;
		while(iterator.hasNext())
		{
			Sted sted=iterator.next();
			if(rmåned.isSelected())
			{
				avgNedbør=sted.getDatoliste().finnDatoer(år,måned).getAvgNedbør();
				avgMinTemp=sted.getDatoliste().finnDatoer(år,måned).getAvgMinTemp();
				avgMaxTemp=sted.getDatoliste().finnDatoer(år,måned).getAvgMaxTemp();
			}
			else
			{
				avgNedbør=sted.getDatoliste().finnDatoer(år).getAvgNedbør();
				avgMinTemp=sted.getDatoliste().finnDatoer(år).getAvgMinTemp();
				avgMaxTemp=sted.getDatoliste().finnDatoer(år).getAvgMaxTemp();
			}
			returarray[i][0] = sted.getNavn();
			returarray[i][1] = null;
			returarray[i][2] = rNedbør.isSelected() ? avgNedbør : null;
			returarray[i][3] = rMintemp.isSelected() ? avgMinTemp : null;
			returarray[i][4] = rMaxtemp.isSelected() ? avgMaxTemp : null;
			i++;
		}
		return returarray;
	}
}
