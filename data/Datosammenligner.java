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
	 * Sammenligner to Dato-objekter ved å hente ut datoens GregorianCalendar-objekt(getDato()) og bruker GC sin comepareTo()-metode for sammenligning.
	 * Comparator sin compare()-metode skal returnere negativ verdi hvis param1 er mindre enn param2, eller i dette tilfellet hvis nå er før da,
	 * og dette er samme resultat som GC sin compareTo()-metode gir. Vi kan altså returnere denne direkte.
	 * @param nå	et dato-objekt som er "nå", "i dag" eller ellers tilsvarer det som forventes av compare() sin param1.
	 * @param da	et dato-objekt som er fortid eller fremtid og som ellers tilsvarer compare() sin param2.
	 * @return		en negativ int dersom nå er før da, 0 dersom det er samme dag og positiv int dersom nå er senere enn da.
	 */
	public int compare(Dato nå, Dato da) {
		return nå.getDato().compareTo(da.getDato());
	}
}