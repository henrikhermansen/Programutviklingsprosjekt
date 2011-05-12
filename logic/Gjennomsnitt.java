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
 * @author B�rd Skeie
 *
 */
public class Gjennomsnitt 
{
	/**
	 * Metode som regner ut gjennomsnittstemperatur, gjennomsnittsnedb�r og total-nedb�r.
	 * @author B�rd Skeie
	 * @param stedliste - referanse til stedlisten.
	 * @param l�r - valgt �r fra panelet.
	 * @param panel - panelet metoden kalles fra.
	 * @param fylke - valgt fylke.
	 * @param jsted - valgt sted.
	 * @return
	 */
	public static double[] gjennomsnitt(Stedliste stedliste, JComboBox l�r, JPanel panel, JComboBox fylke, JComboBox jsted)
	{
		//Finner steder i fylket
		int f = fylke.getSelectedIndex();
		String s = (String) jsted.getSelectedItem();
		if(s == null)
		{
			JOptionPane.showMessageDialog(panel, "Fylket har ingen registrerte steder", "Ingen registreringer", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		Sted sted = stedliste.finnSted(s, f);
		if(sted == null)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (B007)", "System feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		//Finner �ret og registreringer tilh�rende stedet p� datoer 
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
	 * @param stedliste - referanse til stedlisten.
	 * @param l�r - valgt �r fra panelet.
	 * @param lm�ned - valgt m�ned fra panelet.
	 * @param panel - referanse til panelet metoden kalles fra.
	 * @param fylke - valgt fylke.
	 * @param jsted - valgt sted.
	 * @return
	 */
	public static double[] gjennomsnitt(Stedliste stedliste, JComboBox l�r, int m�ned, JPanel panel, JComboBox fylke, JComboBox jsted)
	{
		//Finner steder i fylket
		int f = fylke.getSelectedIndex();
		String s = (String) jsted.getSelectedItem();
		if(s == null)
		{
			JOptionPane.showMessageDialog(panel, "Fylket har ingen registrerte steder", "Ingen registreringer", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		Sted sted = stedliste.finnSted(s, f);
		if(sted == null)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (B008)", "System feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		//Finner �ret og registreringer tilh�rende stedet p� datoer 
		int �r;	
		
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (B007)", "System feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
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
	
	public static Object[][] finnGjennomsnitt(Stedliste stedliste, JComboBox l�r, JPanel panel, JComboBox fylke, JComboBox jsted)
	{
		String[] m�neder = {"Januar","Februar","Mars","April","Mai","Juni","Juli","August","September","Oktober","November","Desember","Hele �ret"};
		
		Object[][] returarray = new Object[13][5];
		
		for(int i = 0; i < returarray.length - 1; i++)
		{
			double[] mndArray = gjennomsnitt(stedliste, l�r, i, panel, fylke, jsted);
			returarray[i][0] = m�neder[i];
			returarray[i][1] = null;
			returarray[i][2] = mndArray[0] >= 0 ? mndArray[0] : null;
			returarray[i][3] = mndArray[1] >= 0 ? mndArray[1] : null;
			returarray[i][4] = mndArray[2] <= Registrering.MAXMAXTEMP ? mndArray[2] : null;
		}
		
		double[] �rArray = gjennomsnitt(stedliste, l�r, panel, fylke, jsted);
		
		returarray[returarray.length-1][0] = m�neder[returarray.length-1];
		returarray[returarray.length-1][1] = null;
		returarray[returarray.length-1][2] = �rArray[0] >= 0 ? �rArray[0] : null;
		returarray[returarray.length-1][3] = �rArray[1] >= 0 ? �rArray[1] : null;
		returarray[returarray.length-1][4] = �rArray[2] <= Registrering.MAXMAXTEMP ? �rArray[2] : null;
				
		return returarray;		
	}
}
