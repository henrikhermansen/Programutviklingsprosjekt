package logic;

import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.*;

import data.Dato;
import data.Sted;
import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class Utvikling
{
	/**
	 * Tar imot alle input-objektene i panelet den kalles fra og konverterer dem, f�r s� og sende de korrekte verdiene videre. Returnerer data til grafikk.
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metoden kalles fra
	 * @param rland	Radioknapp "Hele landet"
	 * @param rfylke	Radioknapp for fylke
	 * @param rsted	Radioknapp for sted
	 * @param r�r	Radioknapp for �r
	 * @param rm�ned	Radioknapp for m�ned
	 * @param fylke	Fylkeslisten i panelet
	 * @param sted	Stedlisten i panelet
	 * @param l�r	�rlisten i panelet
	 * @param lm�ned	M�nedslisten i panelet
	 * @return	En 2-dim. double-array klar til � genereres grafikk fra
	 */
	public static double[][] dataTilGrafikk(Stedliste sl, JPanel panel, JRadioButton rland,JRadioButton rfylke, JRadioButton rsted, JRadioButton r�r, JRadioButton rm�ned, JComboBox fylke, JComboBox sted, JComboBox l�r, JComboBox lm�ned)
	{
		int �r;		
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			SkrivMelding.skriv("Ukjent programfeil (L007)/E", panel);
			return null;
		}
		
		if(rland.isSelected())
		{
			if(r�r.isSelected())
			{
				return landData(sl, panel, �r);
			}
			if(rm�ned.isSelected())
			{
				int m�ned = lm�ned.getSelectedIndex();
				return landData(sl, �r, m�ned);
			}
		}
		
		int f = fylke.getSelectedIndex();
		
		if(rfylke.isSelected())
		{
			if(r�r.isSelected())
			{
				return fylkeData(sl, panel, f, �r);
			}
			if(rm�ned.isSelected())
			{
				int m�ned = lm�ned.getSelectedIndex();
				return fylkeData(sl, panel, f, �r, m�ned);
			}
		}
		
		String s = (String) sted.getSelectedItem();
		if(s == null)
		{
			SkrivMelding.skriv("Sted er ikke valgt/W", panel);
			return null;
		}
		Sted st = sl.finnSted(s, f);
		if(st == null)
		{
			SkrivMelding.skriv("Ukjent programfeil (L008)/E", panel);
			return null;
		}
		
		if(rsted.isSelected())
		{
			if(r�r.isSelected())
			{
				return stedData(panel,f,st,�r);
			}
			if(rm�ned.isSelected())
			{
				int m�ned = lm�ned.getSelectedIndex();
				return stedData(panel,f,st,�r,m�ned);
			}
		}
		return null; //Programmet kommer aldri hit, men kompilatoren krever det.
	}
	
	/**
	 * Henter gjennomsnittsdata for hele landet et gitt �r, en verdi for hver m�ned, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metoden ble kalt fra
	 * @param �r	�ret s�ket skal gj�res p�
	 * @return	En 2-dim. double-array klar til � genereres grafikk fra
	 */
	public static double[][] landData(Stedliste sl, JPanel panel, int �r)
	{
		double[][] returarray = new double[2][12];
		double[][] temparray = Gjennomsnitt.gjennomsnittLand(sl, panel, �r);
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			returarray[0][i] = temparray[i][1];
			returarray[1][i] = temparray[i][2];
		}
		
		return returarray;
	}
	
	/**
	 * Henter gjennomsnittsdata for hele landet en gitt m�ned, en verdi for hver dag, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param �r	�ret det skal s�kes p�
	 * @param m�ned	M�neden det skal s�kes p�
	 * @return	En 2-dim. double-array klar til � genereres grafikk fra
	 */
	public static double[][] landData(Stedliste sl, int �r, int m�ned)
	{
		return m�nedsdata(sl, �r, m�ned);
	}
	
	/**
	 * Henter gjennomsnittsdata for et fylke et gitt �r, en verdi for hver m�ned, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metoden kalles fra
	 * @param fylke	Fylket det skal s�kes p�
	 * @param �r	�ret det skal s�kes p�
	 * @return	En 2-dim. double-array klar til � genereres grafikk fra
	 */
	public static double[][] fylkeData(Stedliste sl, JPanel panel, int fylke, int �r)
	{
		Stedliste fylkesl = sl.finnSted(fylke);
		if(fylkesl == null)
		{
			SkrivMelding.skriv("Fylket har ingen registrerte steder/I", panel);
			return null;
		}
		
		double[][] returarray = new double[2][12];
		double[][] temparray = Gjennomsnitt.gjennomsnittFylke(fylkesl, panel, fylke, �r);
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			returarray[0][i] = temparray[i][1];
			returarray[1][i] = temparray[i][2];
		}
		
		return returarray;
	}
	
	/**
	 * Henter gjennomsnittsdata for et fylke en gitt m�ned, en verdi for hver dag, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param sl	Stedlisten med alle lagrede data
	 * @param panel	Panelet metoden kalles fra
	 * @param fylke	Fylket det skal s�kes p�
	 * @param �r	�ret det skal s�kes p�
	 * @param m�ned	M�neden det skal s�kes p�
	 * @return	En 2-dim. double-array klar til � genereres grafikk fra
	 */
	public static double[][] fylkeData(Stedliste sl, JPanel panel, int fylke, int �r, int m�ned)
	{
		Stedliste fylkesl = sl.finnSted(fylke);
		if(fylkesl == null)
		{
			SkrivMelding.skriv("Fylket har ingen registrerte steder/I", panel);
			return null;
		}
		
		return m�nedsdata(fylkesl, �r, m�ned);
	}
	
	/**
	 * Henter gjennomsnittsdata for et sted et gitt �r, en verdi for hver m�ned, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param panel	Panelet metoden kalles fra
	 * @param fylke	Fylket det skal s�kes p�
	 * @param sted	Stedet det skal s�kes p�
	 * @param �r	�ret det skal s�kes p�
	 * @return	En 2-dim. double-array klar til � genereres grafikk fra
	 */
	public static double[][] stedData(JPanel panel, int fylke, Sted sted, int �r)
	{
		double[][] returarray = new double[2][12];
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			double[] temp = Gjennomsnitt.gjennomsnitt(panel, �r, i, fylke, sted);
			returarray[0][i] = temp[1];
			returarray[1][i] = temp[2];
		}
		
		return returarray;
	}
	
	/**
	 * Henter data for et sted en gitt m�ned, en verdi for hver dag, og returnerer data til grafikk
	 * @author Lars Smeby
	 * @param panel	Panelet metoden kalles fra
	 * @param fylke	Fylket det skal s�kes p�
	 * @param sted	Stedet det skal s�kes p�
	 * @param �r	�ret det skal s�kes p�
	 * @param m�ned	M�neden det skal s�kes p�
	 * @return	En 2-dim. double-array klar til � genereres grafikk fra
	 */
	public static double[][] stedData(JPanel panel, int fylke, Sted sted, int �r, int m�ned)
	{
		GregorianCalendar kal = new GregorianCalendar(�r, m�ned, 1);
		int antallDager = kal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		
		double[][] returarray = new double[2][antallDager];
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			Dato dato = sted.getDatoliste().finnDato(�r, m�ned, i+1);
			if(dato != null)
			{
				returarray[0][i] = dato.getNedb�r();
				returarray[1][i] = dato.getAvgTemp();
			}
			else
			{
				returarray[0][i] = -1;
				returarray[1][i] = Registrering.MAXMAXTEMP + 1;
			}
		}
		
		return returarray;
	}
	
	/**
	 * Hjelpemetode for s�k p� gjennomsnitt for en m�ned, for enten alle steder eller steder i et gitt fylke. Returnerer data til grafikk
	 * @author Lars Smeby
	 * @param stedliste	En vilk�rlig stedliste som skal brukes i beregningen
	 * @param �r	�ret det skal s�kes p�
	 * @param m�ned	M�neden det skal s�kes p�
	 * @return	En 2-dim. double-array klar til � genereres grafikk fra
	 */
	public static double[][] m�nedsdata(Stedliste stedliste, int �r, int m�ned)
	{
		GregorianCalendar kal = new GregorianCalendar(�r, m�ned, 1);
		int antallDager = kal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		
		double[][] returarray = new double[2][antallDager];
		double[][] tellerarray = new double[2][antallDager];
		
		Iterator<Sted> iterator = stedliste.iterator();
		
		while(iterator.hasNext())
		{
			Sted neste = iterator.next();
			
			for(int i = 0; i < returarray[0].length; i++)
			{
				Dato dato = neste.getDatoliste().finnDato(�r, m�ned, i+1);
				if(dato != null)
				{
					if(dato.getNedb�r() >= 0)
					{
						returarray[0][i] += dato.getNedb�r();
						tellerarray[0][i]++;
					}
					if(dato.getAvgTemp() <= Registrering.MAXMAXTEMP)
					{
						returarray[1][i] += dato.getAvgTemp();
						tellerarray[1][i]++;
					}
				}
			}
		}
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			if(tellerarray[0][i] > 0)
				returarray[0][i] = returarray[0][i]/tellerarray[0][i];
			else
				returarray[0][i] = -1;
			
			if(tellerarray[1][i] <= Registrering.MAXMAXTEMP)
				returarray[1][i] = returarray[1][i]/tellerarray[1][i];
			else
				returarray[1][i] = Registrering.MAXMAXTEMP + 1;
		}
		
		return returarray;
	}
}
