/**
 * Inneholder klassen Utvikling.
 * @author Lars Smeby
 * @since 12.05.2011
 * @updated 18.05.2011
 * @version	1
 */
package logic;

import gui.MetroPanel;

import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.*;

import data.Dato;
import data.Sted;
import data.Stedliste;

/**
 *	Klassen inneholder statiske metoder for å regne ut data som brukes til å tegne
 *	utviklings-grafikk.
 *	Metoden dataTilGrafikk(...) kalles opp, som igjen kaller opp korrekt undermetode.
 */
public class Utvikling
{
	/**
	 * Tar imot alle input-objektene i panelet den kalles fra og konverterer dem, før så og sende de korrekte verdiene videre. Returnerer data til grafikk.
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metoden kalles fra
	 * @param rland	Radioknapp "Hele landet"
	 * @param rfylke	Radioknapp for fylke
	 * @param rsted	Radioknapp for sted
	 * @param rår	Radioknapp for år
	 * @param rmåned	Radioknapp for måned
	 * @param fylke	Fylkeslisten i panelet
	 * @param sted	Stedlisten i panelet
	 * @param lår	Årlisten i panelet
	 * @param lmåned	Månedslisten i panelet
	 * @return	En 2-dim. double-array klar til å genereres grafikk fra
	 */
	public static double[][] dataTilGrafikk(Stedliste sl, JPanel panel, JRadioButton rland,JRadioButton rfylke, JRadioButton rsted, JRadioButton rår, JRadioButton rmåned, JRadioButton rmangeår, JComboBox fylke, JComboBox sted, JComboBox lår, JComboBox lmåned)
	{
		int år;		
		try
		{
			år = Integer.parseInt((String)lår.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			SkrivMelding.skriv("Ukjent programfeil (L012)/E", panel);
			return null;
		}
		
		if(rland.isSelected())
		{
			if(rmangeår.isSelected())
			{
				return landDataMangeÅr(sl, panel, år);
			}
			if(rår.isSelected())
			{
				return landData(sl, panel, år);
			}
			if(rmåned.isSelected())
			{
				int måned = lmåned.getSelectedIndex();
				return landData(sl, år, måned);
			}
		} // end of if(rland.isSelected())
		
		int f = fylke.getSelectedIndex();
		
		if(rfylke.isSelected())
		{
			if(rmangeår.isSelected())
			{
				return fylkeDataMangeÅr(sl, panel, f, år);
			}
			if(rår.isSelected())
			{
				return fylkeData(sl, panel, f, år);
			}
			if(rmåned.isSelected())
			{
				int måned = lmåned.getSelectedIndex();
				return fylkeData(sl, panel, f, år, måned);
			}
		} // end of if(rfylke.isSelected())
		
		String s = (String) sted.getSelectedItem();
		if(s == null)
		{
			SkrivMelding.skriv("Sted er ikke valgt/W", panel);
			return null;
		}
		Sted st = sl.finnSted(s, f);
		if(st == null)
		{
			SkrivMelding.skriv("Ukjent programfeil (L013)/E", panel);
			return null;
		}
		
		if(rsted.isSelected())
		{
			if(rmangeår.isSelected())
			{
				return stedDataMangeÅr(panel, st, år);
			}
			if(rår.isSelected())
			{
				return stedData(panel,st,år);
			}
			if(rmåned.isSelected())
			{
				int måned = lmåned.getSelectedIndex();
				return stedData(panel,st,år,måned);
			}
		} // end of if(rsted.isSelected())
		return null; //Programmet kommer aldri hit, men kompilatoren krever det siden koden ikke har noen else.
	} // end of dataTilGrafikk(...)
	
	/**
	 * Henter gjennomsnittsdata for hele landet fra et gitt år og 10 år bakover, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet som metoden ble kalt fra
	 * @param år	Året søket skal gjøres frem til, altså det siste av maks 10 år
	 * @return	En 2-dim. double-array klar til å genereres grafikk fra
	 */
	public static double[][] landDataMangeÅr(Stedliste sl, JPanel panel, int år)
	{
		int antallÅr;
		if(år <= MetroPanel.FØRSTEÅR+9)
			antallÅr = år-MetroPanel.FØRSTEÅR+1;
		else
			antallÅr = 10;
		double[][] returarray = new double[2][antallÅr];
		
		for(int i = 0; i < antallÅr; i++)
		{
			double[][] temparray = Gjennomsnitt.gjennomsnittLand(sl, panel, år-antallÅr+1+i);
			returarray[0][i] = temparray[12][0];
			returarray[1][i] = temparray[12][2];
		}
		
		return returarray;
	}
	
	/**
	 * Henter gjennomsnittsdata for hele landet et gitt år, en verdi for hver måned, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metoden ble kalt fra
	 * @param år	Året søket skal gjøres på
	 * @return	En 2-dim. double-array klar til å genereres grafikk fra
	 */
	public static double[][] landData(Stedliste sl, JPanel panel, int år)
	{
		double[][] returarray = new double[2][12];
		double[][] temparray = Gjennomsnitt.gjennomsnittLand(sl, panel, år);
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			returarray[0][i] = temparray[i][0];
			returarray[1][i] = temparray[i][2];
		}
		
		return returarray;
	}
	
	/**
	 * Henter gjennomsnittsdata for hele landet en gitt måned, en verdi for hver dag, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param år	Året det skal søkes på
	 * @param måned	Måneden det skal søkes på
	 * @return	En 2-dim. double-array klar til å genereres grafikk fra
	 */
	public static double[][] landData(Stedliste sl, int år, int måned)
	{
		return månedsdata(sl, år, måned);
	}
	
	/**
	 * Henter gjennomsnittsdata for et fylke fra et gitt år og 10 år tilbake i tid, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metoden kalles fra
	 * @param fylke	Det aktuelle fylket
	 * @param år	Året søket skal gjøres frem til, altså det siste av maks 10 år
	 * @return	En 2-dim. double-array klar til å genereres grafikk fra
	 */
	public static double[][] fylkeDataMangeÅr(Stedliste sl, JPanel panel, int fylke, int år)
	{
		Stedliste fylkesl = sl.finnSted(fylke);
		if(fylkesl == null)
		{
			SkrivMelding.skriv("Fylket har ingen registrerte steder/I", panel);
			return null;
		}
		
		int antallÅr;
		if(år <= MetroPanel.FØRSTEÅR+9)
			antallÅr = år-MetroPanel.FØRSTEÅR+1;
		else
			antallÅr = 10;
		double[][] returarray = new double[2][antallÅr];
		
		for(int i = 0; i < antallÅr; i++)
		{
			double[][] temparray = Gjennomsnitt.gjennomsnittFylke(fylkesl, panel, år-antallÅr+1+i);
			returarray[0][i] = temparray[12][0];
			returarray[1][i] = temparray[12][2];
		}
		
		return returarray;
	}
	
	/**
	 * Henter gjennomsnittsdata for et fylke et gitt år, en verdi for hver måned, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metoden kalles fra
	 * @param fylke	Fylket det skal søkes på
	 * @param år	Året det skal søkes på
	 * @return	En 2-dim. double-array klar til å genereres grafikk fra
	 */
	public static double[][] fylkeData(Stedliste sl, JPanel panel, int fylke, int år)
	{
		Stedliste fylkesl = sl.finnSted(fylke);
		if(fylkesl == null)
		{
			SkrivMelding.skriv("Fylket har ingen registrerte steder/I", panel);
			return null;
		}
		
		double[][] returarray = new double[2][12];
		double[][] temparray = Gjennomsnitt.gjennomsnittFylke(fylkesl, panel, år);
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			returarray[0][i] = temparray[i][0];
			returarray[1][i] = temparray[i][2];
		}
		
		return returarray;
	}
	
	/**
	 * Henter gjennomsnittsdata for et fylke en gitt måned, en verdi for hver dag, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metoden kalles fra
	 * @param fylke	Fylket det skal søkes på
	 * @param år	Året det skal søkes på
	 * @param måned	Måneden det skal søkes på
	 * @return	En 2-dim. double-array klar til å genereres grafikk fra
	 */
	public static double[][] fylkeData(Stedliste sl, JPanel panel, int fylke, int år, int måned)
	{
		Stedliste fylkesl = sl.finnSted(fylke);
		if(fylkesl == null)
		{
			SkrivMelding.skriv("Fylket har ingen registrerte steder/I", panel);
			return null;
		}
		
		return månedsdata(fylkesl, år, måned);
	}
	
	/**
	 * Henter gjennomsnittsdata for et sted et gitt år og 10 år bakover i tid, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param panel	Panelet metoden kalles fra
	 * @param sted	Stedet det skal søkes på
	 * @param år	Året søket skal gjøres frem til, altså det siste av maks 10 år
	 * @return	En 2-dim. double-array klar til å genereres grafikk fra
	 */
	public static double[][] stedDataMangeÅr(JPanel panel, Sted sted, int år)
	{
		int antallÅr;
		if(år <= MetroPanel.FØRSTEÅR+9)
			antallÅr = år-MetroPanel.FØRSTEÅR+1;
		else
			antallÅr = 10;
		double[][] returarray = new double[2][antallÅr];
		
		for(int i = 0; i < antallÅr; i++)
		{
			double[] temparray = Gjennomsnitt.gjennomsnitt(panel, år-antallÅr+1+i, sted);
			returarray[0][i] = temparray[0];
			returarray[1][i] = temparray[2];
		}
		
		return returarray;
	}
	
	/**
	 * Henter gjennomsnittsdata for et sted et gitt år, en verdi for hver måned, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param panel	Panelet metoden kalles fra
	 * @param fylke	Fylket det skal søkes på
	 * @param sted	Stedet det skal søkes på
	 * @param år	Året det skal søkes på
	 * @return	En 2-dim. double-array klar til å genereres grafikk fra
	 */
	public static double[][] stedData(JPanel panel, Sted sted, int år)
	{
		double[][] returarray = new double[2][12];
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			double[] temp = Gjennomsnitt.gjennomsnitt(panel, år, i, sted);
			returarray[0][i] = temp[0];
			returarray[1][i] = temp[2];
		}
		
		return returarray;
	}
	
	/**
	 * Henter data for et sted en gitt måned, en verdi for hver dag, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param panel	Panelet metoden kalles fra
	 * @param fylke	Fylket det skal søkes på
	 * @param sted	Stedet det skal søkes på
	 * @param år	Året det skal søkes på
	 * @param måned	Måneden det skal søkes på
	 * @return	En 2-dim. double-array klar til å genereres grafikk fra
	 */
	public static double[][] stedData(JPanel panel, Sted sted, int år, int måned)
	{
		GregorianCalendar kal = new GregorianCalendar(år, måned, 1);
		int antallDager = kal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		
		double[][] returarray = new double[2][antallDager];
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			Dato dato = sted.getDatoliste().finnDato(år, måned, i+1);
			if(dato != null)
			{
				returarray[0][i] = dato.getNedbør();
				returarray[1][i] = dato.getAvgTemp();
			}
			else
			{
				returarray[0][i] = -1;
				returarray[1][i] = Registrering.MAXMAXTEMP + 1;
			}
		} // end of for(...)
		
		return returarray;
	}
	
	/**
	 * Hjelpemetode for søk på gjennomsnitt for en måned, for enten alle steder eller steder i et gitt fylke. Returnerer data til grafikk
	 * @author Lars Smeby
	 * @param stedliste	En vilkårlig stedliste som skal brukes i beregningen
	 * @param år	Året det skal søkes på
	 * @param måned	Måneden det skal søkes på
	 * @return	En 2-dim. double-array klar til å genereres grafikk fra
	 */
	public static double[][] månedsdata(Stedliste stedliste, int år, int måned)
	{
		GregorianCalendar kal = new GregorianCalendar(år, måned, 1);
		int antallDager = kal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		
		double[][] returarray = new double[2][antallDager];
		double[][] tellerarray = new double[2][antallDager];
		
		Iterator<Sted> iterator = stedliste.iterator();
		
		while(iterator.hasNext())
		{
			Sted neste = iterator.next();
			
			for(int i = 0; i < returarray[0].length; i++)
			{
				Dato dato = neste.getDatoliste().finnDato(år, måned, i+1);
				if(dato != null)
				{
					if(dato.getNedbør() >= 0)
					{
						returarray[0][i] += dato.getNedbør();
						tellerarray[0][i]++;
					}
					if(dato.getAvgTemp() <= Registrering.MAXMAXTEMP)
					{
						returarray[1][i] += dato.getAvgTemp();
						tellerarray[1][i]++;
					}
				} // end of if(dato != null)
			} // end og for(...)
		} // end of while(...)
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			if(!(tellerarray[0][i] > 0))
				returarray[0][i] = -1;
			
			if(tellerarray[1][i] <= Registrering.MAXMAXTEMP)
				returarray[1][i] = returarray[1][i]/tellerarray[1][i];
			else
				returarray[1][i] = Registrering.MAXMAXTEMP + 1;
		}
		
		return returarray;
	} // end of månedsdata(...)
} // end of class Utvikling