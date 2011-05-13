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
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedbør og total-nedbør.
	 * @author Bård Skeie
	 * @param år	aktuelt år
	 * @param panel	panelet metoden kalles fra
	 * @param f	fylkesnummer
	 * @param sted	sted
	 * @return	double-array med totalnedbør, gjennomsnittsnedbør og gjennomsnittstemperatur
	 */
	public static double[] gjennomsnitt(JPanel panel, int år, int f, Sted sted)
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
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedbør og total-nedbør.
	 * @author Bård Skeie
	 * @param år	aktuelt år
	 * @param måned	aktuell måned
	 * @param panel	referanse til panelet metoden kalles fra
	 * @param f	aktuelt fylke
	 * @param sted	aktuelt sted
	 * @return	double-array med totalnedbør, gjennomsnittsnedbør og gjennomsnittstemperatur
	 */
	public static double[] gjennomsnitt(JPanel panel, int år, int måned, int f, Sted sted)
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
	 * Metode som returnerer et flerdimensjonalt Object-array til bruk i tabell
	 * @author Bård Skeie
	 * @param stedliste	Stedliste med alle data
	 * @param lår	årvelger
	 * @param panel	aktuelt panel
	 * @param fylke	fylkesvelger
	 * @param jsted	stedvelger
	 * @return	2-dim. Object-array med gjennomsnittsdata, en linje per måned samt en for år
	 */
	public static Object[][] finnGjennomsnittSted(Stedliste stedliste, JComboBox lår, JPanel panel, JComboBox fylke, JComboBox jsted)
	{
		int f = fylke.getSelectedIndex();
		String s = (String) jsted.getSelectedItem();
		int år;		
		try
		{
			år = Integer.parseInt((String)lår.getSelectedItem());
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
		
		String[] måneder = {"Januar","Februar","Mars","April","Mai","Juni","Juli","August","September","Oktober","November","Desember","Hele året"};
		Object[][] returarray = new Object[13][5];
		
		for(int i = 0; i < returarray.length - 1; i++)
		{
			double[] mndArray = gjennomsnitt(panel, år, i, f, sted);
			returarray[i][0] = måneder[i];
			returarray[i][1] = null;
			returarray[i][2] = mndArray[0] >= 0 ? mndArray[0] : null;
			returarray[i][3] = mndArray[1] >= 0 ? mndArray[1] : null;
			returarray[i][4] = mndArray[2] <= Registrering.MAXMAXTEMP ? mndArray[2] : null;
		}
		
		double[] årArray = gjennomsnitt(panel, år, f, sted);
		
		returarray[returarray.length-1][0] = måneder[returarray.length-1];
		returarray[returarray.length-1][1] = null;
		returarray[returarray.length-1][2] = årArray[0] >= 0 ? årArray[0] : null;
		returarray[returarray.length-1][3] = årArray[1] >= 0 ? årArray[1] : null;
		returarray[returarray.length-1][4] = årArray[2] <= Registrering.MAXMAXTEMP ? årArray[2] : null;
				
		return returarray;		
	}
}
