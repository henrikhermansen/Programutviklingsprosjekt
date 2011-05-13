/**
 * 
 */
package logic;

import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedb�r og total-nedb�r.
	 * @author B�rd Skeie
	 * @param �r	aktuelt �r
	 * @param panel	panelet metoden kalles fra
	 * @param f	fylkesnummer
	 * @param sted	sted
	 * @return	double-array med totalnedb�r, gjennomsnittsnedb�r og gjennomsnittstemperatur
	 */
	public static double[] gjennomsnitt(JPanel panel, int �r, int f, Sted sted)
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
		}
		
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
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedb�r og total-nedb�r.
	 * @author B�rd Skeie
	 * @param �r	aktuelt �r
	 * @param m�ned	aktuell m�ned
	 * @param panel	referanse til panelet metoden kalles fra
	 * @param f	aktuelt fylke
	 * @param sted	aktuelt sted
	 * @return	double-array med totalnedb�r, gjennomsnittsnedb�r og gjennomsnittstemperatur
	 */
	public static double[] gjennomsnitt(JPanel panel, int �r, int m�ned, int f, Sted sted)
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
		}
		
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
	 * Metode som returnerer et flerdimensjonalt Object-array til bruk i tabell
	 * @author B�rd Skeie
	 * @param stedliste	Stedliste med alle data
	 * @param l�r	�rvelger
	 * @param panel	aktuelt panel
	 * @param fylke	fylkesvelger
	 * @param jsted	stedvelger
	 * @return	2-dim. Object-array med gjennomsnittsdata, en linje per m�ned samt en for �r
	 */
	public static Object[][] finnGjennomsnittSted(Stedliste stedliste, JComboBox l�r, JPanel panel, JComboBox fylke, JComboBox jsted)
	{
		int f = fylke.getSelectedIndex();
		String s = (String) jsted.getSelectedItem();
		int �r;		
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (B006)", "System feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		Sted sted = stedliste.finnSted(s, f);
		if(sted == null)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (B007)", "System feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		String[] m�neder = {"Januar","Februar","Mars","April","Mai","Juni","Juli","August","September","Oktober","November","Desember","Hele �ret"};
		Object[][] returarray = new Object[13][5];
		
		for(int i = 0; i < returarray.length - 1; i++)
		{
			double[] mndArray = gjennomsnitt(panel, �r, i, f, sted);
			returarray[i][0] = m�neder[i];
			returarray[i][1] = null;
			returarray[i][2] = mndArray[0] >= 0 ? mndArray[0] : null;
			returarray[i][3] = mndArray[1] >= 0 ? mndArray[1] : null;
			returarray[i][4] = mndArray[2] <= Registrering.MAXMAXTEMP ? mndArray[2] : null;
		}
		
		double[] �rArray = gjennomsnitt(panel, �r, f, sted);
		
		returarray[returarray.length-1][0] = m�neder[returarray.length-1];
		returarray[returarray.length-1][1] = null;
		returarray[returarray.length-1][2] = �rArray[0] >= 0 ? �rArray[0] : null;
		returarray[returarray.length-1][3] = �rArray[1] >= 0 ? �rArray[1] : null;
		returarray[returarray.length-1][4] = �rArray[2] <= Registrering.MAXMAXTEMP ? �rArray[2] : null;
				
		return returarray;		
	}
}
