/**
 * Inneholder klassen SkrivMelding.
 * @author Lars Smeby
 * @since 15.05.2011
 * @updated 19.05.2011
 * @version	1
 */
package logic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *	Klassen inneholder statiske hjelpemetoder for utskrift av dialogbokser til brukeren.
 */
public class SkrivMelding
{
	/**
	 * Metode som tar imot en spesialkonstruert String og skriver ut en melding.
	 * Meldingen må være på formatet "Meldingstekst/X der X må være I, W, E eller et valgfritt tegn.
	 * @author Lars Smeby
	 * @param melding	Meldingsteksten med avsluttende kodebokstav
	 * @param panel	Panelet metoden kalles fra
	 */
	public static void skriv(String melding, JPanel panel)
	{
		String kode = melding.substring(melding.length()-1);
		String utskrift = melding.substring(0, melding.length()-2);
		String overskrift = "Info";
		int type;
		
		if(kode.equals("I"))
			type = JOptionPane.INFORMATION_MESSAGE;
		else if(kode.equals("W"))
		{
			type = JOptionPane.WARNING_MESSAGE;
			overskrift = "Advarsel";
		}
		else if(kode.equals("E"))
		{
			type = JOptionPane.ERROR_MESSAGE;
			overskrift = "Feil";
		}
		else
			type = JOptionPane.PLAIN_MESSAGE;
		
		JOptionPane.showMessageDialog(panel, utskrift, overskrift, type);
	}
	
	/**
	 * Hjelpemetode for å skrive ut "Hjelp"-dialogboksen
	 * @author Lars Smeby
	 * @param vindu	Vinduet metoden kalles fra
	 */
	public static void hjelp(JFrame vindu)
	{
		String  melding =   "Hjelp til programmet" +
							"\n" +
							"\nHurtigtaster:" +
							"\nRegistrere sted (Alt+s)" +
							"\nRegistrer værdata (Alt+v)" +
							"\nVærdata for sted (Alt+t)" +
							"\nVærdata for dato (Alt+d)" +
							"\nGjennomsnittsverdier (Alt+g)" +
							"\nEkstremverdier (Alt+e)" +
							"\nUtvikling over tid (Alt+u)" +
							"\nMånedlige rekorder (Alt+m)" +
							"\nSlett sted (Alt+r+l)" +
							"\nVelkomstskjerm (Alt+f+v)" +
							"\nLagre (Alt+f+l)" +
							"\nAvslutt (Alt+f+a" +
							"\n" +
							"\nFor mer informasjon eller hvis programmet viser en melding om en" +
							"\n\"Ukjent programfeil\", se henholdsvis \"Brukerveiledning\" og" +
							"\n\"Feilmeldinger\" i den vedlagte dokumentasjonen.";
		
		JOptionPane.showMessageDialog(vindu, melding, "Hjelp", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Hjelpemetode for å skrive ut "Om"-dialogboksen
	 * @author Lars Smeby
	 * @param vindu	Vinduet metoden kalles fra
	 */
	public static void om(JFrame vindu)
	{
		String melding =    "Metrologiske data\n" +
							"\n" +
							"Prosjektoppgave i programutvikling våren 2011\n" +
							"Gruppe 3: Henrik Hermansen, Bård Skeie og Lars Smeby\n" +
							"Programversjon: 1.0\n" +
							"Sist oppdatdert: 19.05.2011\n" +
							"Høyskolen i Oslo";
		
		JOptionPane.showMessageDialog(vindu, melding, "Om", JOptionPane.INFORMATION_MESSAGE);
	}
} // end of class SkrivMelding