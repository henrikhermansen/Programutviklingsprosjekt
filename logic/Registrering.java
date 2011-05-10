/**
 * 
 */
package logic;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import data.Sted;
import data.Stedliste;

/**
 *
 */
public class Registrering
{
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
	 * Metode som registrerer v�rdata for et sted
	 * @author B�rd Skeie
	 * @return String med tilbakemelding p� resultat
	 */
	public static String registrerData(JTextField min, JTextField max, JTextField ned)
	{
		String minTempString = min.getText();
		String maxTempString = max.getText();
		String nedb�rString = ned.getText();
		
		if(minTempString == "" && maxTempString == "" && nedb�rString == "")
			return "Ingen data er skrevet inn";
		
		int minTemp;
		int maxTemp;
		int nedb�r;
		
		if(minTempString != "")
			minTemp = Integer.parseInt(minTempString);
		if(maxTempString != "")
			maxTemp = Integer.parseInt(maxTempString);
		if(nedb�rString != "")
			nedb�r = Integer.parseInt(nedb�rString);
		
		if(maxTemp < minTemp)
			return "Makstemperatur kan ikke v�re st�rre enn minimumstemperatur";
		
		return "HEi";
	}
}
