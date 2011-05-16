package logic;

import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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
		if(stedLandet.isSelected())
			return finnDataForLandet(sl,panel,rdag,rm�ned,r�r,dag,m�ned,�r,rEnkelverdi,rAvgverdi,rNedb�r,rMintemp,rMaxtemp);
		if(stedFylke.isSelected())
			return finnDataForFylke(sl,panel,f,rdag,rm�ned,r�r,dag,m�ned,�r,rEnkelverdi,rAvgverdi,rNedb�r,rMintemp,rMaxtemp);
		if(stedSted.isSelected())
			return finnDataForSted(sl,panel,st,rdag,rm�ned,r�r,dag,m�ned,�r,rEnkelverdi,rAvgverdi,rNedb�r,rMintemp,rMaxtemp);
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
	private static Object[][] finnDataForSted(Stedliste sl, JPanel panel, Sted sted, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
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
			return finnEnkelverdiForSted(datoliste,rNedb�r,rMintemp,rMaxtemp);
		return null;
	}

	private static Object[][] finnDataForFylke(Stedliste sl, JPanel panel, int fylke, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		Stedliste stedliste=sl.finnSted(fylke);
		if(stedliste.size()==0)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
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
				if(rdag.isSelected())			tempDato=sted.getDatoliste().finnDato(�r,m�ned,dag);
				else if(rm�ned.isSelected())	tempDato=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxNedb�r().f�rste();
				else							tempDato=sted.getDatoliste().finnDatoer(�r).getMaxNedb�r().f�rste();
				if(tempDato==null)
				{
					SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
					return null;
				}
				else
					verdi=tempDato.getNedb�r();
				if(verdi==maxNedb�r)
				{
					if(rdag.isSelected())			antVerdier+=1;
					else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxNedb�r().size();
					else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMaxNedb�r().size();
				}
				if(verdi>maxNedb�r)
				{
					antVerdier=0;
					if(rdag.isSelected())			antVerdier+=1;
					else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxNedb�r().size();
					else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMaxNedb�r().size();
					maxNedb�r=verdi;
				}
			}
		}
		if(rMintemp.isSelected())
		{
			minTemp=Registrering.MAXMAXTEMP+1;
			while(iterator.hasNext())
			{
				Sted sted=iterator.next();
				if(rdag.isSelected())			tempDato=sted.getDatoliste().finnDato(�r,m�ned,dag);
				else if(rm�ned.isSelected())	tempDato=sted.getDatoliste().finnDatoer(�r,m�ned).getMinTemp().f�rste();
				else							tempDato=sted.getDatoliste().finnDatoer(�r).getMinTemp().f�rste();
				if(tempDato==null)
				{
					SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
					return null;
				}
				else
					verdi=tempDato.getMinTemp();
				if(verdi==minTemp)
				{
					if(rdag.isSelected())			antVerdier+=1;
					else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMinTemp().size();
					else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMinTemp().size();
				}
				if(verdi<minTemp)
				{
					antVerdier=0;
					if(rdag.isSelected())			antVerdier+=1;
					else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMinTemp().size();
					else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMinTemp().size();
					minTemp=verdi;
				}
			}
		}
		if(rMaxtemp.isSelected())
		{
			maxTemp=Registrering.MAXMINTEMP-1;
			while(iterator.hasNext())
			{
				Sted sted=iterator.next();
				if(rdag.isSelected())			tempDato=sted.getDatoliste().finnDato(�r,m�ned,dag);
				else if(rm�ned.isSelected())	tempDato=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxTemp().f�rste();
				else							tempDato=sted.getDatoliste().finnDatoer(�r).getMaxTemp().f�rste();
				if(tempDato==null)
				{
					SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
					return null;
				}
				else
					verdi=tempDato.getMaxTemp();
				if(verdi==maxTemp)
				{
					if(rdag.isSelected())			antVerdier+=1;
					else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxTemp().size();
					else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMaxTemp().size();
				}
				if(verdi>maxTemp)
				{
					antVerdier=0;
					if(rdag.isSelected())			antVerdier+=1;
					else if(rm�ned.isSelected())	antVerdier+=sted.getDatoliste().finnDatoer(�r,m�ned).getMaxTemp().size();
					else							antVerdier+=sted.getDatoliste().finnDatoer(�r).getMaxTemp().size();
					maxTemp=verdi;
				}
			}
		}
		if(verdi==-100)
		{
			SkrivMelding.skriv("Ukjent programfeil H-FED003./E", panel);
			return null;
		}
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForFylke(stedliste,panel,rdag,rm�ned,r�r,dag,m�ned,�r,rNedb�r,rMintemp,rMaxtemp,verdi,antVerdier);
		if(rAvgverdi.isSelected())
			return finnAvgverdiForFylke(stedliste,panel,fylke,rm�ned,r�r,m�ned,�r,rNedb�r,rMintemp,rMaxtemp);
		return null;
	}

	private static Object[][] finnDataForLandet(Stedliste sl, JPanel panel, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForLandet(sl,panel,rdag,rm�ned,r�r,dag,m�ned,�r,rNedb�r,rMintemp,rMaxtemp);
		if(rAvgverdi.isSelected())
			return finnAvgverdiForLandet(sl,panel,rm�ned,r�r,m�ned,�r,rNedb�r,rMintemp,rMaxtemp);
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

	private static Object[][] finnEnkelverdiForSted(Datoliste datoliste, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		Object[][] returarray = new Object[datoliste.size()][5];
		int i=0;
		Iterator<Dato> iterator=datoliste.iterator();
		while(iterator.hasNext())
		{
			Dato dato=iterator.next();
			returarray[i][0] = null;
			returarray[i][1] = dato.getDato().getTime();
			returarray[i][2] = rNedb�r.isSelected() ? dato.getNedb�r() : null;
			returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
			returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
			i++;
		}
		
		return returarray;
	}
	private static Object[][] finnAvgverdiForFylke(Stedliste sl, JPanel panel, int fylke, JRadioButton rm�ned, JRadioButton r�r, int m�ned, int �r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		// TODO Auto-generated method stub
		return null;

	}
	private static Object[][] finnEnkelverdiForFylke(Stedliste sl, JPanel panel, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp, double verdi, int antVerdier)
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
	private static Object[][] finnAvgverdiForLandet(Stedliste sl, JPanel panel, JRadioButton rm�ned, JRadioButton r�r, int m�ned, int �r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		// TODO Auto-generated method stub
		return null;
	}
	private static Object[][] finnEnkelverdiForLandet(Stedliste sl, JPanel panel, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
