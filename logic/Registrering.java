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
	
	private static final double MAXMINTEMP = -60, MAXMAXTEMP =  40, MAXNEDB�R = 300;
	/**
	 * @author Lars Smeby
	 * Metode for � registrere sted
	 * @param navn JTextField med navn p� sted
	 * @param fylke JComboBox med fylke
	 * @param stedliste Stedlisten med alle data
	 * @return String med tilbakemelding p� resultat
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
	 * @author B�rd Skeie
	 * Metode som registrerer v�rdata for et sted.
	 * @return String med tilbakemelding p� resultat.
	 */
	public static String registrerData(JTextField min, JTextField max, JTextField ned, Stedliste stedliste, JComboBox navn, JComboBox fylke, JComboBox l�r, JComboBox lm�ned, JComboBox ldag )
	{
		String minTempString = min.getText();
		String maxTempString = max.getText();
		String nedb�rString = ned.getText();
				
		if(minTempString == "" && maxTempString == "" && nedb�rString == "")
			return "Ingen data er skrevet inn";
		
		double minTemp = -100;
		double maxTemp = -100;
		double nedb�r = -100;
		
		boolean minT = false;
		boolean maxT = false;
		boolean nedB = false;
		
		int �r = -100;
		int m�ned = lm�ned.getSelectedIndex() + 1;
		int dag = -100;
		
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
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
			if(nedb�rString != "")
			{
				nedb�r = Double.parseDouble(nedb�rString);
				nedB = true;
			}
		}
		
		catch(NumberFormatException nfe)
		{
			return "Feil i tallformatet";
		}
		
		/**
		 * Sjekker at innskrevne verdier er innenfor lovlige grenser 
		 * f�r registrering kan utf�res.
		 */
		if(nedB && ((nedb�r < 0) || (nedb�r > MAXNEDB�R)))
			return "Nedb�r kan ikke v�re negativ eller over " + MAXNEDB�R + ".";
		if(minT && maxT && minTemp > maxTemp)
			return "Makstemperatur kan ikke v�re st�rre enn minimumstemperatur";
		if(minT && maxT && ((maxTemp < MAXMINTEMP || maxTemp > MAXMAXTEMP) || (minTemp < MAXMINTEMP || minTemp > MAXMAXTEMP)))
			return "Temperaturer m� v�re mellom " + MAXMINTEMP + " og " + MAXMAXTEMP + ".";
		
		String n = (String) navn.getSelectedItem();
		Sted sted = stedliste.finnSted(n, fylke.getSelectedIndex());
		if(sted == null)
			return "Ukjent programfeil! (B001)";
		
		Datoliste dl = sted.getDatoliste();
		Dato d = dl.finnDato(�r, m�ned, dag);
		if(d == null)
		{
			d = new Dato(dag, m�ned, �r);
			dl.settInn(d);
		}
		
		
		
		return "Hei";
	}
}
