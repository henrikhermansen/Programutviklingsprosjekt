/**
 * 
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
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class Gjennomsnitt 
{
	/**
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedbør og total-nedbør for et gitt sted et gitt år
	 * @author Bård Skeie
	 * @param panel	panelet metoden kalles fra
	 * @param år	aktuelt år
	 * @param sted	sted
	 * @return	double-array med totalnedbør, gjennomsnittsnedbør og gjennomsnittstemperatur
	 */
	public static double[] gjennomsnitt(JPanel panel, int år, Sted sted)
	{
		Datoliste stedDatoer = sted.getDatoliste().finnDatoer(år);
			
		Iterator<Dato> iterator = stedDatoer.iterator();
		
		int nedbørTeller = 0;
		int tempTeller = 0;
		double totalNedbør = 0;
		double totalTemp = 0;
		
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getAvgTemp() <= Registrering.MAXMAXTEMP)
			{
				totalTemp += neste.getAvgTemp();
				tempTeller++;
			}
			if(neste.getNedbør() >= 0)
			{
				totalNedbør += neste.getNedbør();
				nedbørTeller++;
			}
		}
		
		//Oppretter og setter inn i retur-arrayet.
		double[] returarray = new double[3];
		if(nedbørTeller != 0)
			returarray[0] = totalNedbør;
		else
			returarray[0] = -1;
		if(nedbørTeller != 0)
			returarray[1]= totalNedbør/nedbørTeller;
		else
			returarray[1] = -1;
		if(tempTeller != 0)
			returarray[2] = totalTemp/tempTeller;
		else
			returarray[2] = Registrering.MAXMAXTEMP + 1;
		
		return returarray;
	}
	
	/**
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedbør og total-nedbør for et gitt sted en gitt måned
	 * @author Bård Skeie
	 * @param panel	referanse til panelet metoden kalles fra
	 * @param år	aktuelt år
	 * @param måned	aktuell måned
	 * @param sted	aktuelt sted
	 * @return	double-array med totalnedbør, gjennomsnittsnedbør og gjennomsnittstemperatur
	 */
	public static double[] gjennomsnitt(JPanel panel, int år, int måned, Sted sted)
	{
		Datoliste stedDatoer = sted.getDatoliste().finnDatoer(år, måned);
			
		Iterator<Dato> iterator = stedDatoer.iterator();
		
		int nedbørTeller = 0;
		int tempTeller = 0;
		double totalNedbør = 0;
		double totalTemp = 0;
		
		while(iterator.hasNext())
		{
			Dato neste = iterator.next();
			if(neste.getAvgTemp() <= Registrering.MAXMAXTEMP)
			{
				totalTemp += neste.getAvgTemp();
				tempTeller++;
			}
			if(neste.getNedbør() >= 0)
			{
				totalNedbør += neste.getNedbør();
				nedbørTeller++;
			}
		}
		
		//Oppretter og setter inn i retur-arrayet.
		double[] returarray = new double[3];
		if(nedbørTeller != 0)
			returarray[0] = totalNedbør;
		else
			returarray[0] = -1;
		if(nedbørTeller != 0)
			returarray[1]= totalNedbør/nedbørTeller;
		else
			returarray[1] = -1;
		if(tempTeller != 0)
			returarray[2] = totalTemp/tempTeller;
		else
			returarray[2] = Registrering.MAXMAXTEMP + 1;
		
		return returarray;
	}
	
	/**
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedbør og total-nedbør.
	 * pr fylke.
	 * @author Bård Skeie
	 * @param fylkesl Stedliste med alle stedene tilhørende fylket.
	 * @param panel Referanse til panelet som metoden kalles fra.
	 * @param år Året spørringen gjelder.
	 * @return Todimensjonalt double-array med totalnedbør, gjennomsnittsnedbør og gjennomsnittstemperatur
	 */
	public static double[][] gjennomsnittFylke(Stedliste fylkesl, JPanel panel, int år)
	{
		Iterator<Sted> iterator = fylkesl.iterator();
		double[][] dataarray = new double[13][3];
		int[][] tellerarray = new int[13][3];
		
		while(iterator.hasNext())
		{
			Sted tempsted = iterator.next();
			
			for(int i = 0; i < dataarray.length - 1; i++)
			{
				double[] temp = gjennomsnitt(panel, år, i, tempsted);
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
			}
			
			double[] temp = gjennomsnitt(panel, år, tempsted);
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
		}
		
		for(int i = 0; i < dataarray.length; i++)
		{
			if(tellerarray[i][0] != 0)
				dataarray[i][0] = dataarray[i][0]/tellerarray[i][0];
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
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedbør og total-nedbør
	 * for hele landet.
	 * @author Bård Skeie
	 * @param sl Stedsliste med alle registrerte steder.
	 * @param panel Panelet som metoden kalles fra.
	 * @param år Året søket gjelder for.
	 * @return Todimensjonalt double-array med totalnedbør, gjennomsnittsnedbør og gjennomsnittstemperatur
	 */
	public static double[][] gjennomsnittLand(Stedliste sl, JPanel panel, int år)
	{
		double[][] dataarray = new double[13][3];
		int[][] tellerarray = new int[13][3];
		
		for(int i = 0; i < Sted.FYLKESLISTE.length; i++)
		{
			Stedliste fylkesl = sl.finnSted(i);
			double[][] templiste = gjennomsnittFylke(fylkesl, panel, år);
			
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
			}
		}
		
		for(int i = 0; i < dataarray.length; i++)
		{
			if(tellerarray[i][0] != 0)
				dataarray[i][0] = dataarray[i][0]/tellerarray[i][0];
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
	 * Metode som sjekker hva gjennomsnittsspørringen gjelder og gjør kall
	 * på aktuell metode og sender med relevante parametere.
	 * @author Bård Skeie
	 * @param stedliste Stedliste med alle registrerte fylker.
	 * @param lår Året søket gjelder for
	 * @param panel Panelet metoden kalles fra.
	 * @param fylke Fylket søket gjelder for.
	 * @param jsted Stedet søket gjelder for.
	 * @param rland Radioknapp for å velge hele landet.
	 * @param rfylke Radioknapp for å velge et fylke.
	 * @param rsted Radioknapp for å velge et sted.
	 * @return 2-dim. Object-array med gjennomsnittsdata, en linje per måned samt en for år
	 */
	public static Object[][] finnGjennomsnitt(Stedliste stedliste, JComboBox lår, JPanel panel, JComboBox fylke, JComboBox jsted, JRadioButton rland, JRadioButton rfylke, JRadioButton rsted)
	{
		int år;		
		try
		{
			år = Integer.parseInt((String)lår.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			SkrivMelding.skriv("Ukjent programfeil (B007)/E", panel);
			return null;
		}
		if(rland.isSelected())
		{
			return finnGjennomsnittLand(stedliste, panel, år);
		}
		
		int f = fylke.getSelectedIndex();
		if(rfylke.isSelected())
		{
			return finnGjennomsnittFylke(stedliste, panel, f, år);
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
			return finnGjennomsnittSted(år, panel, f, sted);
		}
		
		return null;
	}
	
	/**
	 * Metode som returnerer et flerdimensjonalt Object-array til bruk i tabell
	 * @author Bård Skeie
	 * @param stedliste	Stedliste med alle data
	 * @param lår	årvelger
	 * @param panel	aktuelt panel
	 * @param fylke	fylkesvelger
	 * @param jsted	stedvelger
	 * @return	2-dim. Object-array med gjennomsnittsdata, en linje per måned samt en for år
	 */
	public static Object[][] finnGjennomsnittSted(int år, JPanel panel, int fylke, Sted sted)
	{
		String[] måneder = {"Januar","Februar","Mars","April","Mai","Juni","Juli","August","September","Oktober","November","Desember","Hele året"};
		Object[][] returarray = new Object[13][5];
		
		for(int i = 0; i < returarray.length - 1; i++)
		{
			double[] mndArray = gjennomsnitt(panel, år, i, sted);
			returarray[i][0] = måneder[i];
			returarray[i][1] = null;
			returarray[i][2] = mndArray[0] >= 0 ? mndArray[0] : null;
			returarray[i][3] = mndArray[1] >= 0 ? mndArray[1] : null;
			returarray[i][4] = mndArray[2] <= Registrering.MAXMAXTEMP ? mndArray[2] : null;
		}
		
		double[] årArray = gjennomsnitt(panel, år, sted);
		
		returarray[returarray.length-1][0] = måneder[returarray.length-1];
		returarray[returarray.length-1][1] = null;
		returarray[returarray.length-1][2] = årArray[0] >= 0 ? årArray[0] : null;
		returarray[returarray.length-1][3] = årArray[1] >= 0 ? årArray[1] : null;
		returarray[returarray.length-1][4] = årArray[2] <= Registrering.MAXMAXTEMP ? årArray[2] : null;
				
		return returarray;		
	}
	
	/**
	 * Metode som lager og returnerer et 2-dim. Object-array med data som viser aktuelt gjennomsnitt.
	 * @author Bård Skeie
	 * @param sl Stedliste med alle data.
	 * @param panel Aktuelt panel.
	 * @param f Fylkesvelger.
	 * @param år Årvelger.
	 * @return 2-dim. Object-array med gjennomsnittsdata, en linje per måned samt en for år
	 */
	public static Object[][] finnGjennomsnittFylke(Stedliste sl, JPanel panel, int f, int år)
	{
		Stedliste fylkesl = sl.finnSted(f);
		if(fylkesl == null)
		{
			SkrivMelding.skriv("Fylket har ingen registrerte steder/I", panel);
			return null;
		}
		
		double[][] dataarray = gjennomsnittFylke(fylkesl, panel, år);
		Object[][] returarray = new Object[13][5];
		String[] måneder = {"Januar","Februar","Mars","April","Mai","Juni","Juli","August","September","Oktober","November","Desember","Hele året"};
		
		for(int i = 0; i < returarray.length; i++)
		{
			returarray[i][0] = måneder[i];
			returarray[i][1] = null;
			returarray[i][2] = dataarray[i][0] >= 0 ? dataarray[i][0] : null;
			returarray[i][3] = dataarray[i][1] >= 0 ? dataarray[i][1] : null;
			returarray[i][4] = dataarray[i][2] <= Registrering.MAXMAXTEMP ? dataarray[i][2] : null;
		}
		
		return returarray;
	}
	
	/**
	 * Metode som lager og returnerer et 2-dim. Object-array med data som viser aktuelt gjennomsnitt.
	 * @author Bård Skeie
	 * @param sl Stedliste med alle data.
	 * @param panel Aktuelt panel.
	 * @param år Årvelger.
	 * @return  2-dim. Object-array med gjennomsnittsdata, en linje per måned samt en for år
	 */
	public static Object[][] finnGjennomsnittLand(Stedliste sl, JPanel panel, int år)
	{
		double[][] dataarray = gjennomsnittLand(sl, panel, år);
		Object[][] returarray = new Object[13][5];
		String[] måneder = {"Januar","Februar","Mars","April","Mai","Juni","Juli","August","September","Oktober","November","Desember","Hele året"};
		
		for(int i = 0; i < returarray.length; i++)
		{
			returarray[i][0] = måneder[i];
			returarray[i][1] = null;
			returarray[i][2] = dataarray[i][0] >= 0 ? dataarray[i][0] : null;
			returarray[i][3] = dataarray[i][1] >= 0 ? dataarray[i][1] : null;
			returarray[i][4] = dataarray[i][2] <= Registrering.MAXMAXTEMP ? dataarray[i][2] : null;
		}
		
		return returarray;
	}
}
