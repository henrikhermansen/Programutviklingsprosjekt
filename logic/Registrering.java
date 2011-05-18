package logic;

import java.util.GregorianCalendar;

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
	public static final double MAXMINTEMP = -60, MAXMAXTEMP =  40, MAXNEDBØR = 300;
	/**
	 * Metode for å registrere sted
	 * @author Lars Smeby
	 * @param navn JTextField med navn på sted
	 * @param fylke JComboBox med fylke
	 * @param stedliste Stedlisten med alle data
	 * @return String med tilbakemelding på resultat
	 */
	public static String registrerSted(JTextField navn, JComboBox fylke, Stedliste stedliste)
	{
		String n = navn.getText();
		String pattern = "[a-zA-ZæÆøØåÅ]*";
		
		if (n == null || n.length() < 2)
			return "Skriv inn et stedsnavn/W";
		
		if(!n.matches(pattern))
			return "Stednavnet skal kun inneholde bokstaver/I";

		if (stedliste.finnSted(n, fylke.getSelectedIndex()) != null)
			return "Dette stedet eksisterer allerede i dette fylket/I";
		
		stedliste.settInn(new Sted(n, fylke.getSelectedIndex()));
		return n+" ble registrert i "+fylke.getSelectedItem().toString()+"/I";
	}
	
	/**
	 * Metode for å slette steder. Data tilknyttet stedet blir også slettet.
	 * @author Bård Skeie
	 * @param fylke Fylket som stedet hører til.
	 * @param sted Stedsnavn på stedet som skal slettes.
	 * @param sl Referanse til stedslisten.
	 * @param panel Referanse til panelet som metoden kalles fra.
	 * @return Returnerer en string som forteller om resultatet.
	 */
	public static String slettSted(JComboBox fylke, JComboBox sted, Stedliste sl, JPanel panel )
	{
		String stedString = (String) sted.getSelectedItem();
		if(stedString == null)
			return "Ingen steder valgt/W";
		
		Object[] valg = { "Ja", "Nei" }; //Valg til showOptionDialog-boksene.
		int svar = JOptionPane.showOptionDialog(panel, 
				"Er du helt sikker på at du vil slette " + stedString + "?\nAlle data som er registerrt om " + stedString + " vil også slettes!", 
				"Advarsel",
		        JOptionPane.DEFAULT_OPTION, 
		        JOptionPane.WARNING_MESSAGE,
		        null, valg, valg[1]);
		if(svar == 1 || svar == JOptionPane.CLOSED_OPTION)
			return null;	
		else
		{
			Sted slettSted = sl.finnSted(stedString, fylke.getSelectedIndex());
			if(slettSted == null)
				return "Ukjent programfeil! (B009)/E";
			sl.slettSted(slettSted);
			return stedString + " og alle tilhørende data ble slettet fra registeret./I";
		}
	}
	
	/**
	 * Metode som registrerer værdata for et sted.
	 * @author Bård Skeie
	 * @return String med tilbakemelding på resultat.
	 */
	public static String registrerData(JTextField min, JTextField max, JTextField ned, Stedliste stedliste, JComboBox navn, JComboBox fylke, JComboBox lår, JComboBox lmåned, JComboBox ldag, JPanel panel )
	{
		String minTempString = min.getText();
		String maxTempString = max.getText();
		String nedbørString = ned.getText();
				
		if((minTempString.length() == 0) && (maxTempString.length() == 0) && (nedbørString.length() == 0))
			return "Ingen data er skrevet inn/W";
		
		double minTemp = MAXMAXTEMP + 1;
		double maxTemp = MAXMAXTEMP + 1;
		double nedbør = -1;
		
		boolean minT = false;
		boolean maxT = false;
		boolean nedB = false;
		
		int år = -1;
		int måned = lmåned.getSelectedIndex();
		int dag = -1;
		
		try
		{
			år = Integer.parseInt((String)lår.getSelectedItem());
			dag = Integer.parseInt((String)ldag.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			return "Ukjent programfeil (B010)/E";
		}
		
		GregorianCalendar dato = new GregorianCalendar(år, måned, dag);
		GregorianCalendar idag = new GregorianCalendar();
		if(dato.after(idag))
			return "Du kan ikke registrere data for en dato som ikke har vært/W";
		
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
			if(nedbørString.length() > 0)
			{
				nedbør = Double.parseDouble(nedbørString);
				nedB = true;
			}
		}
		
		catch(NumberFormatException nfe)
		{
			return "Feil i tallformatet/W";
		}
		
		/**
		 * Sjekker at innskrevne verdier er innenfor lovlige grenser 
		 * før registrering kan utføres.
		 */
		if(nedB && ((nedbør < 0) || (nedbør > MAXNEDBØR)))
			return "Nedbør kan ikke være negativ eller over " + MAXNEDBØR + "./W";
		if(minT && maxT && minTemp > maxTemp)
			return "Maksimumstemperatur kan ikke være større enn minimumstemperatur/W";
		if(minT && maxT && ((maxTemp < MAXMINTEMP || maxTemp > MAXMAXTEMP) || (minTemp < MAXMINTEMP || minTemp > MAXMAXTEMP)))
			return "Temperaturer må være mellom " + MAXMINTEMP + " og " + MAXMAXTEMP + "./W";
		if(minT && (minTemp < MAXMINTEMP || minTemp > MAXMAXTEMP))
			return "Temperaturer må være mellom " + MAXMINTEMP + " og " + MAXMAXTEMP + "./W";
		if(maxT && (maxTemp < MAXMINTEMP || maxTemp > MAXMAXTEMP))
			return "Temperaturer må være mellom " + MAXMINTEMP + " og " + MAXMAXTEMP + "./W";
		
		String n = (String) navn.getSelectedItem();
		if(n == null)
			return "Ingen steder valgt/W";
		Sted sted = stedliste.finnSted(n, fylke.getSelectedIndex());
		if(sted == null)
			return "Ukjent programfeil! (B011)/E";
		
		Datoliste dl = sted.getDatoliste();
		Dato d = dl.finnDato(år, måned, dag);
		if(d == null)
		{
			d = new Dato(dag, måned, år);
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
			if(d.getNedbør() >= 0)
			{
				int svar = JOptionPane.showOptionDialog(panel, 
						"Datoen har allerede registrert " + d.getNedbør() + "mm nedbør.\nVil du overskrive denne dataen med " + nedbør + "mm?", 
						"Advarsel",
				        JOptionPane.DEFAULT_OPTION, 
				        JOptionPane.WARNING_MESSAGE,
				        null, valg, valg[0]);
				if(svar == 1 || svar == JOptionPane.CLOSED_OPTION)
					nedB = false;					
			}
			if(nedB)
			{
				d.setNedbør(nedbør);
			}
		}
		
		if(!nedB && !minT && !maxT)
			return "Ingen data registrert/I";
		
		/**
		 * 	Hvis det kun er endret minimumstemperatur og maksimumstemperatur allerede eksisterer slår denne til
		 */
		if(minT && !maxT && d.getMaxTemp() <= MAXMAXTEMP && d.getMinTemp() > d.getMaxTemp())
		{
			d.setMinTemp(gammelMinTemp);
			String feilretur = "Minimumstemperaturen er høyere enn allerede registrert maksimumstemperatur, og blir derfor ikke endret.";
			if(nedB)
				feilretur += " Nedbøren ble endret.";
			feilretur += "/W";
			return feilretur;
		}
		/**
		 * 	Hvis det kun er endret maksimumstemperatur og minimumstemperatur allerede eksisterer slår denne til
		 */
		if(!minT && maxT && d.getMinTemp() <= MAXMAXTEMP && d.getMinTemp() > d.getMaxTemp())
		{
			d.setMaxTemp(gammelMaksTemp);
			String feilretur = "Maksimumstemperaturen er lavere enn allerede registrert minimumstemperatur, og blir derfor ikke endret.";
			if(nedB)
				feilretur += " Nedbøren ble endret.";
			feilretur += "/W";
			return feilretur;
		}
		
		return "Data ble satt inn i tabellen/I";
	}
}
