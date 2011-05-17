package data;

import java.io.*;

import javax.swing.JPanel;

import logic.SkrivMelding;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class Filhåndterer
{
	//Denne klassen laster og lagrer data fra/til fil. Static-metoder.
	
	final static String FILNAVN = "Metrodata.dta";
	
	/**
	 * Metode som laster inn fil ved oppstart av programmet.
	 * @author Bård Skeie
	 */
	public static Stedliste lastInnFil(JPanel panel)
	{
		Stedliste sl;
		ObjectInputStream inn;
		
		try
		{
			inn = new ObjectInputStream(new FileInputStream(FILNAVN));
			sl = (Stedliste)inn.readObject();
			inn.close();
		}
		catch(ClassNotFoundException cnfe)
		{
			SkrivMelding.skriv("Ukjent programfeil, oppretter ny fil (B001)/E", panel);
			sl = new Stedliste();
		}
		catch(FileNotFoundException fnfe)
		{
			SkrivMelding.skriv("Ukjent programfeil, oppretter ny fil (B002)/E", panel);
			sl = new Stedliste();
		}
		catch(IOException ioe)
		{
			SkrivMelding.skriv("Ukjent programfeil, oppretter ny fil (B003)/E", panel);
			sl = new Stedliste();
		}
		
		return sl;
	}
	
	/**
	 * Metode som lagrer fil. Metoden kobles til "avslutt-knappen", "lagre-knappen" 
	 * og "vinduslukke-knappen".
	 * @author Bård Skeie
	 */
	public static void lagreFil(Stedliste sl, JPanel panel)
	{
		ObjectOutputStream ut;
		
		try
		{
			ut = new ObjectOutputStream(new FileOutputStream(FILNAVN));
			ut.writeObject(sl);
			ut.flush();
			ut.close();
		}
		catch(NotSerializableException nse)
		{
			SkrivMelding.skriv("Ukjent programfeil, fikk ikke lagret (B004)/E", panel);
			System.exit(1);
		}
		catch(IOException ioe)
		{
			SkrivMelding.skriv("Ukjent programfeil, fikk ikke lagret (B005)/E", panel);
			System.exit(1);
		}
	}
	
}
