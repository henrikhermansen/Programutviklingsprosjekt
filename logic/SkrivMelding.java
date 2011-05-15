package logic;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
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
}
