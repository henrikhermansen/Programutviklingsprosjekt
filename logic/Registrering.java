package logic;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import data.Dato;
import data.Datoliste;
import data.Sted;
import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class Registrering
{
	
	private static final double MAXMINTEMP = -60, MAXMAXTEMP =  40, MAXNEDBØR = 300;
	/**
	 * @author Lars Smeby
	 * Metode for å registrere sted
	 * @param navn JTextField med navn på sted
	 * @param fylke JComboBox med fylke
	 * @param stedliste Stedlisten med alle data
	 * @return String med tilbakemelding på resultat
	 */
	public static String registrerSted(JTextField navn, JComboBox fylke, Stedliste stedliste)
	{
		String n = navn.getText();
		
		if (n == null || n.length() < 2)
			return "Skriv inn et stedsnavn";

		if (stedliste.finnSted(n, fylke.getSelectedIndex()) != null)
			return "Dette stedet eksisterer allerede i dette fylket";
		
		stedliste.settInn(new Sted(n, fylke.getSelectedIndex()));
		navn.setText("");
		fylke.setSelectedIndex(0);
		return n+" ble registrert i "+fylke.getSelectedItem().toString();
	}
	
	/**
	 * @author Bård Skeie
	 * Metode som registrerer værdata for et sted.
	 * @return String med tilbakemelding på resultat.
	 */
	public static String registrerData(JTextField min, JTextField max, JTextField ned, Stedliste stedliste, JComboBox navn, JComboBox fylke, JComboBox lår, JComboBox lmåned, JComboBox ldag )
	{
		String minTempString = min.getText();
		String maxTempString = max.getText();
		String nedbørString = ned.getText();
				
		if(minTempString == "" && maxTempString == "" && nedbørString == "")
			return "Ingen data er skrevet inn";
		
		double minTemp = -100;
		double maxTemp = -100;
		double nedbør = -100;
		
		boolean minT = false;
		boolean maxT = false;
		boolean nedB = false;
		
		int år = -100;
		int måned = lmåned.getSelectedIndex() + 1;
		int dag = -100;
		
		try
		{
			år = Integer.parseInt((String)lår.getSelectedItem());
			dag = Integer.parseInt((String)ldag.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			return "Ukjent programfeil (B002)";
		}
		
		try
		{
			if(minTempString != "")
			{
				minTemp = Double.parseDouble(minTempString);
				minT = true;
			}
			if(maxTempString != "")
			{
				maxTemp = Double.parseDouble(maxTempString);
				maxT = true;
			}
			if(nedbørString != "")
			{
				nedbør = Double.parseDouble(nedbørString);
				nedB = true;
			}
		}
		
		catch(NumberFormatException nfe)
		{
			return "Feil i tallformatet";
		}
		
		/**
		 * Sjekker at innskrevne verdier er innenfor lovlige grenser 
		 * før registrering kan utføres.
		 */
		if(nedB && ((nedbør < 0) || (nedbør > MAXNEDBØR)))
			return "Nedbør kan ikke være negativ eller over " + MAXNEDBØR + ".";
		if(minT && maxT && minTemp > maxTemp)
			return "Makstemperatur kan ikke være større enn minimumstemperatur";
		if(minT && maxT && ((maxTemp < MAXMINTEMP || maxTemp > MAXMAXTEMP) || (minTemp < MAXMINTEMP || minTemp > MAXMAXTEMP)))
			return "Temperaturer må være mellom " + MAXMINTEMP + " og " + MAXMAXTEMP + ".";
		
		String n = (String) navn.getSelectedItem();
		Sted sted = stedliste.finnSted(n, fylke.getSelectedIndex());
		if(sted == null)
			return "Ukjent programfeil! (B001)";
		
		Datoliste dl = sted.getDatoliste();
		Dato d = dl.finnDato(år, måned, dag);
		if(d == null)
		{
			d = new Dato(dag, måned, år);
			dl.settInn(d);
		}
		
		
		
		return "Hei";
	}
}
