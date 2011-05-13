package logic;

import javax.swing.*;

import data.*;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class Registrering
{
	/**
	 * Fastsatte variabler som testes mot i hele programmet.
	 */
	public static final double MAXMINTEMP = -60, MAXMAXTEMP =  40, MAXNEDB�R = 300;
	/**
	 * Metode for � registrere sted
	 * @author Lars Smeby
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
		return n+" ble registrert i "+fylke.getSelectedItem().toString();
	}
	
	/**
	 * Metode som registrerer v�rdata for et sted.
	 * @author B�rd Skeie
	 * @return String med tilbakemelding p� resultat.
	 */
	public static String registrerData(JTextField min, JTextField max, JTextField ned, Stedliste stedliste, JComboBox navn, JComboBox fylke, JComboBox l�r, JComboBox lm�ned, JComboBox ldag, JPanel panel )
	{
		String minTempString = min.getText();
		String maxTempString = max.getText();
		String nedb�rString = ned.getText();
				
		if((minTempString.length() == 0) && (maxTempString.length() == 0) && (nedb�rString.length() == 0))
			return "Ingen data er skrevet inn";
		
		double minTemp = -100;
		double maxTemp = -100;
		double nedb�r = -100;
		
		boolean minT = false;
		boolean maxT = false;
		boolean nedB = false;
		
		int �r = -100;
		int m�ned = lm�ned.getSelectedIndex();
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
			if(minTempString.length() > 0)
			{
				minTemp = Double.parseDouble(minTempString);
				minT = true;
			}
			if(maxTempString.length() > 0)
			{
				maxTemp = Double.parseDouble(maxTempString);
				maxT = true;
			}
			if(nedb�rString.length() > 0)
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
			return "Maksimumstemperatur kan ikke v�re st�rre enn minimumstemperatur";
		if(minT && maxT && ((maxTemp < MAXMINTEMP || maxTemp > MAXMAXTEMP) || (minTemp < MAXMINTEMP || minTemp > MAXMAXTEMP)))
			return "Temperaturer m� v�re mellom " + MAXMINTEMP + " og " + MAXMAXTEMP + ".";
		
		String n = (String) navn.getSelectedItem();
		if(n == null)
			return "Ingen steder valgt";
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
		
		double gammelMaksTemp = MAXMAXTEMP + 1;
		double gammelMinTemp = MAXMAXTEMP + 1;
		
		Object[] valg = { "Ja", "Nei" }; //Valg til showOptionDialog-boksene.
		
		if(minT)
		{
			if(d.getMinTemp() < MAXMAXTEMP)
			{
				int svar = JOptionPane.showOptionDialog(panel, 
						"Datoen inneholder allerede en minimumstemperatur, " + d.getMinTemp() + " grader.\nVil du overskrive denne dataen med " + minTemp + " grader?", 
						"Advarsel",
				        JOptionPane.DEFAULT_OPTION, 
				        JOptionPane.WARNING_MESSAGE,
				        null, valg, valg[0]);
				if(svar == 1 || svar == JOptionPane.CLOSED_OPTION)
					minT = false;	
				else
					gammelMinTemp = d.getMinTemp();
			}
			if(minT)
			{
				d.setMinTemp(minTemp);
			}
		}
		
		if(maxT)
		{
			if(d.getMaxTemp() < MAXMAXTEMP)
			{
				int svar = JOptionPane.showOptionDialog(panel, 
						"Datoen inneholder allerede en maksimumstemperatur, " + d.getMaxTemp() + " grader.\nVil du overskrive denne dataen med " + maxTemp + " grader?", 
						"Advarsel",
				        JOptionPane.DEFAULT_OPTION, 
				        JOptionPane.WARNING_MESSAGE,
				        null, valg, valg[0]);
				if(svar == 1 || svar == JOptionPane.CLOSED_OPTION)
					maxT = false;	
				else
					gammelMaksTemp = d.getMaxTemp();
			}
			if(maxT)
			{
				d.setMaxTemp(maxTemp);
			}
		}
		
		if(nedB)
		{
			if(d.getNedb�r() >= 0)
			{
				int svar = JOptionPane.showOptionDialog(panel, 
						"Datoen har allerede registrert " + d.getNedb�r() + "mm nedb�r.\nVil du overskrive denne dataen med " + nedb�r + "mm?", 
						"Advarsel",
				        JOptionPane.DEFAULT_OPTION, 
				        JOptionPane.WARNING_MESSAGE,
				        null, valg, valg[0]);
				if(svar == 1 || svar == JOptionPane.CLOSED_OPTION)
					nedB = false;					
			}
			if(nedB)
			{
				d.setNedb�r(nedb�r);
			}
		}
		
		if(!nedB && !minT && !maxT)
			return "Ingen data registrert";
		
		/**
		 * 	Hvis det kun er endret minimumstemperatur og maksimumstemperatur allerede eksisterer sl�r denne til
		 */
		if(minT && !maxT && d.getMaxTemp() <= MAXMAXTEMP && d.getMinTemp() > d.getMaxTemp())
		{
			d.setMinTemp(gammelMinTemp);
			String feilretur = "Minimumstemperaturen er h�yere enn allerede registrert maksimumstemperatur, og blir derfor ikke endret.";
			if(nedB)
				feilretur += " Nedb�ren ble endret.";
			return feilretur;
		}
		/**
		 * 	Hvis det kun er endret maksimumstemperatur og minimumstemperatur allerede eksisterer sl�r denne til
		 */
		if(!minT && maxT && d.getMinTemp() <= MAXMAXTEMP && d.getMinTemp() > d.getMaxTemp())
		{
			d.setMaxTemp(gammelMaksTemp);
			String feilretur = "Maksimumstemperaturen er lavere enn allerede registrert minimumstemperatur, og blir derfor ikke endret.";
			if(nedB)
				feilretur += " Nedb�ren ble endret.";
			return feilretur;
		}
		
		return "Data ble satt inn i tabellen";
	}
}
