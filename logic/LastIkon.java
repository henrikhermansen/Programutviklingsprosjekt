package logic;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class LastIkon
{
	/**
	 * Hjelpemetode for å laste inn ikoner til knappene
	 * @author Lars Smeby
	 * @param filnavn	Navnet på ikon-filen. Må ligge i images/icons.
	 * @return	ImageIcon-objekt med ikonet, ellers null
	 */
	public static Icon last(String filnavn)
	{
		URL ikonURL = LastIkon.class.getResource("/images/icons/" + filnavn);
		if(ikonURL != null)
			return new ImageIcon(ikonURL);
		return null;
	}
}
