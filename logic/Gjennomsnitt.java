/**
 * Inneholder klassen Gjennomsnitt.
 * @author B�rd Skeie
 * @since 12.05.2011
 * @version	1 18.05.2011
 */
package logic;

import gui.MetroPanel;

import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data.Dato;
import data.Datoliste;
import data.Sted;
import data.Stedliste;

/**
 *	Klassen inneholder statiske metoder for � hente ut gjennomsnittsdata.
 *	Metoden finnGjennomsnitt(...) kalles opp, og gj�r deretter kall p� korrekt undermetode.
 */
public class Gjennomsnitt
{
	/**
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedb�r og total-nedb�r for et gitt sted et gitt �r
	 * Skrevet av: B�rd Skeie
	 * @param panel	panelet metoden kalles fra
	 * @param �r	aktuelt �r
	 * @param sted	sted
	 * @return	double-array med totalnedb�r, gjennomsnittsnedb�r og gjennomsnittstemperatur
	 */
	public static double[] gjennomsnitt(JPanel panel, int �r, Sted sted)
	{
		Datoliste stedDatoer = sted.getDatoliste().finnDatoer(�r);
			
		Iterator<Dato> iterator = stedDatoer.iterator();
		
		int nedb�rTeller = 0;
		int tempTeller = 0;
		double totalNedb�r = 0;
		double totalTemp = 0;
		
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getAvgTemp() <= Registrering.MAXMAXTEMP)
			{
				totalTemp += neste.getAvgTemp();
				tempTeller++;
			}
			if(neste.getNedb�r() >= 0)
			{
				totalNedb�r += neste.getNedb�r();
				nedb�rTeller++;
			}
		} // end of while(...)
		
		//Oppretter og setter inn i retur-arrayet.
		double[] returarray = new double[3];
		if(nedb�rTeller != 0)
			returarray[0] = totalNedb�r;
		else
			returarray[0] = -1;
		if(nedb�rTeller != 0)
			returarray[1]= totalNedb�r/nedb�rTeller;
		else
			returarray[1] = -1;
		if(tempTeller != 0)
			returarray[2] = totalTemp/tempTeller;
		else
			returarray[2] = Registrering.MAXMAXTEMP + 1;
		
		return returarray;
	}
	
	/**
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedb�r og total-nedb�r for et gitt sted en gitt m�ned
	 * Skrevet av: B�rd Skeie
	 * @param panel	referanse til panelet metoden kalles fra
	 * @param �r	aktuelt �r
	 * @param m�ned	aktuell m�ned
	 * @param sted	aktuelt sted
	 * @return	double-array med totalnedb�r, gjennomsnittsnedb�r og gjennomsnittstemperatur
	 */
	public static double[] gjennomsnitt(JPanel panel, int �r, int m�ned, Sted sted)
	{
		Datoliste stedDatoer = sted.getDatoliste().finnDatoer(�r, m�ned);
			
		Iterator<Dato> iterator = stedDatoer.iterator();
		
		int nedb�rTeller = 0;
		int tempTeller = 0;
		double totalNedb�r = 0;
		double totalTemp = 0;
		
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getAvgTemp() <= Registrering.MAXMAXTEMP)
			{
				totalTemp += neste.getAvgTemp();
				tempTeller++;
			}
			if(neste.getNedb�r() >= 0)
			{
				totalNedb�r += neste.getNedb�r();
				nedb�rTeller++;
			}
		} // end of while(...)
		
		//Oppretter og setter inn i retur-arrayet.
		double[] returarray = new double[3];
		if(nedb�rTeller != 0)
			returarray[0] = totalNedb�r;
		else
			returarray[0] = -1;
		if(nedb�rTeller != 0)
			returarray[1]= totalNedb�r/nedb�rTeller;
		else
			returarray[1] = -1;
		if(tempTeller != 0)
			returarray[2] = totalTemp/tempTeller;
		else
			returarray[2] = Registrering.MAXMAXTEMP + 1;
		
		return returarray;
	}
	
	/**
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedb�r og total-nedb�r pr fylke.
	 * Skrevet av: B�rd Skeie
	 * @param fylkesl Stedliste med alle stedene tilh�rende fylket.
	 * @param panel Referanse til panelet som metoden kalles fra.
	 * @param �r �ret sp�rringen gjelder.
	 * @return Todimensjonalt double-array med totalnedb�r, gjennomsnittsnedb�r og gjennomsnittstemperatur
	 */
	public static double[][] gjennomsnittFylke(Stedliste fylkesl, JPanel panel, int �r)
	{
		Iterator<Sted> iterator = fylkesl.iterator();
		double[][] dataarray = new double[13][3];
		int[][] tellerarray = new int[13][3];
		
		while(iterator.hasNext())
		{
			Sted tempsted = iterator.next();
			
			for(int i = 0; i < dataarray.length - 1; i++)
			{
				double[] temp = gjennomsnitt(panel, �r, i, tempsted);
				if(temp[0] >= 0)
				{
					dataarray[i][0] += temp[0];
					tellerarray[i][0]++;
				}
				if(temp[1] >= 0)
				{
					dataarray[i][1] += temp[1];
					tellerarray[i][1]++;
				}
				if(temp[2] <= Registrering.MAXMAXTEMP)
				{
					dataarray[i][2] += temp[2];
					tellerarray[i][2]++;
				}				
			} // end of for(...)
			
			double[] temp = gjennomsnitt(panel, �r, tempsted);
			if(temp[0] >= 0)
			{
				dataarray[dataarray.length-1][0] += temp[0];
				tellerarray[dataarray.length-1][0]++;
			}
			if(temp[1] >= 0)
			{
				dataarray[dataarray.length-1][1] += temp[1];
				tellerarray[dataarray.length-1][1]++;
			}
			if(temp[2] <= Registrering.MAXMAXTEMP)
			{
				dataarray[dataarray.length-1][2] += temp[2];
				tellerarray[dataarray.length-1][2]++;
			}
		} // end of while(...)
		
		for(int i = 0; i < dataarray.length; i++)
		{
			if(tellerarray[i][0] != 0)
				dataarray[i][0] = dataarray[i][0];
			else
				dataarray[i][0] = -1;
			if(tellerarray[i][1] != 0)
				dataarray[i][1] = dataarray[i][1]/tellerarray[i][1];
			else
				dataarray[i][1] = -1;
			if(tellerarray[i][2] != 0)
				dataarray[i][2] = dataarray[i][2]/tellerarray[i][2];
			else
				dataarray[i][2] = Registrering.MAXMAXTEMP +1;
		}
		
		return dataarray;
	}
	
	/**
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedb�r og total-nedb�r
	 * for hele landet.
	 * Skrevet av: B�rd Skeie
	 * @param sl Stedsliste med alle registrerte steder.
	 * @param panel Panelet som metoden kalles fra.
	 * @param �r �ret s�ket gjelder for.
	 * @return Todimensjonalt double-array med totalnedb�r, gjennomsnittsnedb�r og gjennomsnittstemperatur
	 */
	public static double[][] gjennomsnittLand(Stedliste sl, JPanel panel, int �r)
	{
		double[][] dataarray = new double[13][3];
		int[][] tellerarray = new int[13][3];
		
		for(int i = 0; i < Sted.FYLKESLISTE.length; i++)
		{
			Stedliste fylkesl = sl.finnSted(i);
			double[][] templiste = gjennomsnittFylke(fylkesl, panel, �r);
			
			for( int j = 0; j < templiste.length; j++)
			{
				if(templiste[j][0] >= 0)
				{
					dataarray[j][0] += templiste[j][0];
					tellerarray[j][0]++;
				}
				if(templiste[j][1] >= 0)
				{
					dataarray[j][1] += templiste[j][1];
					tellerarray[j][1]++;
				}
				if(templiste[j][2] <= Registrering.MAXMAXTEMP)
				{
					dataarray[j][2] += templiste[j][2];
					tellerarray[j][2]++;
				}
			} // end of for(...)
		} // end of for(...)
		
		for(int i = 0; i < dataarray.length; i++)
		{
			if(tellerarray[i][0] != 0)
				dataarray[i][0] = dataarray[i][0];
			else
				dataarray[i][0] = -1;
			if(tellerarray[i][1] != 0)
				dataarray[i][1] = dataarray[i][1]/tellerarray[i][1];
			else
				dataarray[i][1] = -1;
			if(tellerarray[i][2] != 0)
				dataarray[i][2] = dataarray[i][2]/tellerarray[i][2];
			else
				dataarray[i][2] = Registrering.MAXMAXTEMP +1;
		}
		
		return dataarray;
	}
	
	/**
	 * Metode som sjekker hva gjennomsnittssp�rringen gjelder og gj�r kall
	 * p� aktuell metode og sender med relevante parametere.
	 * Skrevet av: B�rd Skeie
	 * @param stedliste Stedliste med alle registrerte fylker.
	 * @param l�r �ret s�ket gjelder for
	 * @param panel Panelet metoden kalles fra.
	 * @param fylke Fylket s�ket gjelder for.
	 * @param jsted Stedet s�ket gjelder for.
	 * @param rland Radioknapp for � velge hele landet.
	 * @param rfylke Radioknapp for � velge et fylke.
	 * @param rsted Radioknapp for � velge et sted.
	 * @return 2-dim. Object-array med gjennomsnittsdata, en linje per m�ned samt en for �r
	 */
	public static Object[][] finnGjennomsnitt(Stedliste stedliste, JComboBox l�r, JPanel panel, JComboBox fylke, JComboBox jsted, JRadioButton rland, JRadioButton rfylke, JRadioButton rsted)
	{
		int �r;		
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			SkrivMelding.skriv("Ukjent programfeil (B007)/E", panel);
			return null;
		}
		if(rland.isSelected())
		{
			return finnGjennomsnittLand(stedliste, panel, �r);
		}
		
		int f = fylke.getSelectedIndex();
		if(rfylke.isSelected())
		{
			return finnGjennomsnittFylke(stedliste, panel, f, �r);
		}
		
		String s = (String) jsted.getSelectedItem();
		if(s == null)
		{
			SkrivMelding.skriv("Sted er ikke valgt/W", panel);
			return null;
		}
		
		Sted sted = stedliste.finnSted(s, f);
		if(sted == null)
		{
			SkrivMelding.skriv("Ukjent programfeil (B008)/E", panel);
			return null;
		}
		if(rsted.isSelected())
		{
			return finnGjennomsnittSted(�r, panel, f, sted);
		}
		
		return null;
	}
	
	/**
	 * Metode som returnerer et flerdimensjonalt Object-array til bruk i tabell
	 * Skrevet av: B�rd Skeie
	 * @param �r	int-verdi av �ret
	 * @param panel	aktuelt panel
	 * @param fylke	fylkesvelger
	 * @param sted	Sted-objektet
	 * @return	2-dim. Object-array med gjennomsnittsdata, en linje per m�ned samt en for �r
	 */
	public static Object[][] finnGjennomsnittSted(int �r, JPanel panel, int fylke, Sted sted)
	{
		String[] m�neder = utvidArray(MetroPanel.M�NEDER,"Hele �ret");
		Object[][] returarray = new Object[13][5];
		
		for(int i = 0; i < returarray.length - 1; i++)
		{
			double[] mndArray = gjennomsnitt(panel, �r, i, sted);
			returarray[i][0] = m�neder[i];
			returarray[i][1] = null;
			returarray[i][2] = mndArray[0] >= 0 ? mndArray[0] : null;
			returarray[i][3] = mndArray[1] >= 0 ? mndArray[1] : null;
			returarray[i][4] = mndArray[2] <= Registrering.MAXMAXTEMP ? mndArray[2] : null;
		}
		
		double[] �rArray = gjennomsnitt(panel, �r, sted);
		
		returarray[returarray.length-1][0] = m�neder[returarray.length-1];
		returarray[returarray.length-1][1] = null;
		returarray[returarray.length-1][2] = �rArray[0] >= 0 ? �rArray[0] : null;
		returarray[returarray.length-1][3] = �rArray[1] >= 0 ? �rArray[1] : null;
		returarray[returarray.length-1][4] = �rArray[2] <= Registrering.MAXMAXTEMP ? �rArray[2] : null;
				
		return returarray;		
	}
	
	/**
	 * Metode som lager og returnerer et 2-dim. Object-array med data som viser aktuelt gjennomsnitt.
	 * Skrevet av: B�rd Skeie
	 * @param sl Stedliste med alle data.
	 * @param panel Aktuelt panel.
	 * @param f Fylkesvelger.
	 * @param �r �rvelger.
	 * @return 2-dim. Object-array med gjennomsnittsdata, en linje per m�ned samt en for �r
	 */
	public static Object[][] finnGjennomsnittFylke(Stedliste sl, JPanel panel, int f, int �r)
	{
		Stedliste fylkesl = sl.finnSted(f);
		if(fylkesl.size() == 0)
		{
			SkrivMelding.skriv("Fylket har ingen registrerte steder/I", panel);
			return null;
		}
		
		double[][] dataarray = gjennomsnittFylke(fylkesl, panel, �r);
		Object[][] returarray = new Object[13][5];
		String[] m�neder = utvidArray(MetroPanel.M�NEDER,"Hele �ret");
		
		for(int i = 0; i < returarray.length; i++)
		{
			returarray[i][0] = m�neder[i];
			returarray[i][1] = null;
			returarray[i][2] = dataarray[i][0] >= 0 ? dataarray[i][0] : null;
			returarray[i][3] = dataarray[i][1] >= 0 ? dataarray[i][1] : null;
			returarray[i][4] = dataarray[i][2] <= Registrering.MAXMAXTEMP ? dataarray[i][2] : null;
		}
		
		return returarray;
	}
	
	/**
	 * Metode som lager og returnerer et 2-dim. Object-array med data som viser aktuelt gjennomsnitt.
	 * Skrevet av: B�rd Skeie
	 * @param sl Stedliste med alle data.
	 * @param panel Aktuelt panel.
	 * @param �r �rvelger.
	 * @return  2-dim. Object-array med gjennomsnittsdata, en linje per m�ned samt en for �r
	 */
	public static Object[][] finnGjennomsnittLand(Stedliste sl, JPanel panel, int �r)
	{
		double[][] dataarray = gjennomsnittLand(sl, panel, �r);
		Object[][] returarray = new Object[13][5];
		String[] m�neder = utvidArray(MetroPanel.M�NEDER,"Hele �ret");
		
		for(int i = 0; i < returarray.length; i++)
		{
			returarray[i][0] = m�neder[i];
			returarray[i][1] = null;
			returarray[i][2] = dataarray[i][0] >= 0 ? dataarray[i][0] : null;
			returarray[i][3] = dataarray[i][1] >= 0 ? dataarray[i][1] : null;
			returarray[i][4] = dataarray[i][2] <= Registrering.MAXMAXTEMP ? dataarray[i][2] : null;
		}
		
		return returarray;
	}
	
	/**
	 * Metode som utvider st�rrelsen p� String-arrayet array med 1 og setter inn String-verdien tilleggsveri p� den nye plassen bakerst i array.
	 * Skrevet av: Henrik Hermansen
	 * @param array Et String-array som skal utvides med 1
	 * @param tilleggsverdi En String-verdi som skal settes inn p� den nye plassen i String-arrayet array
	 * @return Et String-array
	 */
	private static String[] utvidArray(String[] array, String tilleggsverdi)
	{
		if(array.length==0)
			return null;
		String[] returarray=new String[array.length+1];
		for(int i=0;i<array.length;i++)
			returarray[i]=array[i];
		returarray[returarray.length-1]=tilleggsverdi;
		return returarray;
	}
} // end of class Gjennomsnitt