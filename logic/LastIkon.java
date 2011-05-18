/**
 * Inneholder klassen LastIkon.
 * @author Lars Smeby
 * @since 16.05.2011
 * @updated 16.05.2011
 * @version	1
 */
package logic;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *	Klassen inneholder en statisk hjelpemetode for å laste inn ikoner.
 */
public class LastIkon
{
	private static final String IKONSTI = "/images/icons/";
	/**
	 * Hjelpemetode for å laste inn ikoner til knappene
	 * @author Lars Smeby
	 * @param filnavn	Navnet på ikon-filen. Må ligge i images/icons.
	 * @return	ImageIcon-objekt med ikonet, ellers null
	 */
	public static Icon last(String filnavn)
	{
		URL ikonURL = LastIkon.class.getResource(IKONSTI + filnavn);
		if(ikonURL != null)
			return new ImageIcon(ikonURL);
		return null;
	}
} // end of class LastIkon