/**
 * Inneholder klassen Filhåndterer.
 * @author Bård Skeie
 * @since 04.05.2011
 * @version	1 18.05.2011
 */
package data;

import java.io.*;

import javax.swing.JPanel;

import logic.SkrivMelding;

/**
 * Klassen inneholder static-metoder for å skrive og lese data fra/til fil
 */
public class Filhåndterer
{
	final static String FILNAVN = "Metrodata.dta";
	
	/**
	 * Metode som laster inn fil ved oppstart av programmet.
	 * Skrevet av: Bård Skeie
	 */
	public static Stedliste lastInnFil()
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
			System.out.println("Ukjent programfeil, oppretter ny fil (B001)");
			sl = new Stedliste();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Fant ikke fil, oppretter ny (B002)");
			sl = new Stedliste();
		}
		catch(IOException ioe)
		{
			System.out.println("Ukjent programfeil, oppretter ny fil (B003)");
			sl = new Stedliste();
		}
		
		return sl;
	}
	
	/**
	 * Metode som lagrer fil. Metoden kobles til "avslutt-knappen", "lagre-knappen" 
	 * og "vinduslukke-knappen".
	 * Skrevet av: Bård Skeie
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
} // end of class Filhåndterer