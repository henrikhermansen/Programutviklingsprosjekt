package data;

import java.io.Serializable;
import java.util.Comparator;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class Datosammenligner implements Comparator<Dato>, Serializable
{
	private static final long serialVersionUID = 8291850317051193976L;

	/**
	 * @author Henrik Hermansen
	 * Sammenligner to Dato-objekter ved � hente ut datoens GregorianCalendar-objekt(getDato()) og bruker GC sin comepareTo()-metode for sammenligning.
	 * Comparator sin compare()-metode skal returnere negativ verdi hvis param1 er mindre enn param2, eller i dette tilfellet hvis n� er f�r da,
	 * og dette er samme resultat som GC sin compareTo()-metode gir. Vi kan alts� returnere denne direkte.
	 * @param n�	et dato-objekt som er "n�", "i dag" eller ellers tilsvarer det som forventes av compare() sin param1.
	 * @param da	et dato-objekt som er fortid eller fremtid og som ellers tilsvarer compare() sin param2.
	 * @return		en negativ int dersom n� er f�r da, 0 dersom det er samme dag og positiv int dersom n� er senere enn da.
	 */
	public int compare(Dato n�, Dato da) {
		return n�.getDato().compareTo(da.getDato());
	}
}