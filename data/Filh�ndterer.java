package data;

import java.io.*;

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
			System.out.println("ClassNotFoundException, oppretter ny fil");
			sl = new Stedliste();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("FileNotFoundException, oppretter ny fil");
			sl = new Stedliste();
		}
		catch(IOException ioe)
		{
			System.out.println("IOException, oppretter ny fil");
			sl = new Stedliste();
		}
		
		return sl;
	}
	
	/**
	 * Metode som lagrer fil. Metoden kobles til "avslutt-knappen", "lagre-knappen" 
	 * og "vinduslukke-knappen".
	 * @author Bård Skeie
	 */
	public static void lagreFil(Stedliste sl)
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
			System.out.println("NotSerializableException, fikk ikke lagret");
			System.exit(1);
		}
		catch(IOException ioe)
		{
			System.out.println("IOException, fikk ikke lagret");
			System.exit(1);
		}
	}
	
}
