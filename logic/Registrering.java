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
	 * Metode som registrerer værdata for et sted
	 * @author Bård Skeie
	 * @return String med tilbakemelding på resultat
	 */
	public static String registrerData(JTextField min, JTextField max, JTextField ned)
	{
		String minTempString = min.getText();
		String maxTempString = max.getText();
		String nedbørString = ned.getText();
		
		if(minTempString == "" && maxTempString == "" && nedbørString == "")
			return "Ingen data er skrevet inn";
		
		int minTemp;
		int maxTemp;
		int nedbør;
		
		if(minTempString != "")
			minTemp = Integer.parseInt(minTempString);
		if(maxTempString != "")
			maxTemp = Integer.parseInt(maxTempString);
		if(nedbørString != "")
			nedbør = Integer.parseInt(nedbørString);
		
		if(maxTemp < minTemp)
			return "Makstemperatur kan ikke være større enn minimumstemperatur";
		
		return "HEi";
	}
}
