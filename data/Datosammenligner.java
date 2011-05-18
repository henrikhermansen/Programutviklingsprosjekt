/**
 * Inneholder klassen Datosammenligner.
 * @author Henrik Hermansen
 * @since 28.04.2011
 * @updated 13.05.2011
 * @version	1
 */
package data;

import java.util.Comparator;

/**
 *	Klassen implementerer en Comparator, og brukes til å sortere datoer
 */
public class Datosammenligner implements Comparator<Dato>
{
	/**
	 * Sammenligner to Dato-objekter ved å hente ut datoens GregorianCalendar-objekt(getDato()) og bruker GC sin comepareTo()-metode for sammenligning.
	 * Comparator sin compare()-metode skal returnere negativ verdi hvis param1 er mindre enn param2, eller i dette tilfellet hvis nå er før da,
	 * og dette er samme resultat som GC sin compareTo()-metode gir. Vi kan altså returnere denne direkte.
	 * @author Henrik Hermansen
	 * @param nå	et dato-objekt som er "nå", "i dag" eller ellers tilsvarer det som forventes av compare() sin param1.
	 * @param da	et dato-objekt som er fortid eller fremtid og som ellers tilsvarer compare() sin param2.
	 * @return		en negativ int dersom nå er før da, 0 dersom det er samme dag og positiv int dersom nå er senere enn da.
	 */
	public int compare(Dato nå, Dato da)
	{
		return nå.getDato().compareTo(da.getDato());
	}
} // end og class Datosammenligner