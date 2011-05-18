/**
 * Inneholder klassen FinnRekordData.
 * @author Henrik Hermansen
 * @since 18.05.2011
 * @updated 18.05.2011
 * @version	1
 */
package logic;

import gui.MetroPanel;

import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data.Dato;
import data.Sted;
import data.Stedliste;

/**
 *	Klassen inneholder statiske metoder for å hente ut rekorddata for hver måned.
 */
public class FinnRekordData
{
	/**
	 * Metode som finner rekordverdier for hver måneder i alle år der det finnes data.
	 * Deretter returneres at to-dimensjonalt Object-array med det/de stedet/stedene som har rekordverdien det spørres etter, for hver måned.
	 * @author Henrik Hermansen
	 * @param sl		hoved-stedlisten i programmet
	 * @param panel		panelet som kaller opp metoden
	 * @param rNedbør	radioknappen som sier om det spørres etter data om maksimumsnedbør
	 * @param rMintemp	radioknappen som sier om det spørres etter data om minimumstemperaturer
	 * @param rMaxtemp	radioknappen som sier om det spørres etter data om maksimumstemperaturer
	 * @return et to-dimensjonalt Object-array med det/de stedet/stedene som har den rekordverdien det spørres etter, for hver måned hvert år.
	 */
	public static Object[][] finnData(Stedliste sl, JPanel panel, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		double[] maxNedbør=new double[12];
		double[] minTemp=new double[12];
		double[] maxTemp=new double[12];
		for(int j=0;j<12;j++)
		{
			maxNedbør[j]=-1;
			minTemp[j]=Registrering.MAXMAXTEMP+1;
			maxTemp[j]=Registrering.MAXMINTEMP-1;
		}
		Iterator<Sted> iterator=sl.iterator();
		Iterator<Dato> datoiterator;
		while(iterator.hasNext())
		{
			Sted sted=iterator.next();
			datoiterator=sted.getDatoliste().iterator();
			while(datoiterator.hasNext())
			{
				Dato dato=datoiterator.next();
				int måned=dato.getDato().get(GregorianCalendar.MONTH);
				if(rNedbør.isSelected() && dato.getNedbør()>maxNedbør[måned] && dato.getNedbør()>-1 && dato.getNedbør()<=Registrering.MAXNEDBØR)
					maxNedbør[måned]=dato.getNedbør();
				if(rMintemp.isSelected() && dato.getMinTemp()<minTemp[måned] && dato.getMinTemp()>=Registrering.MAXMINTEMP && dato.getMinTemp()<=Registrering.MAXMAXTEMP)
					minTemp[måned]=dato.getMinTemp();
				if(rMaxtemp.isSelected() && dato.getMaxTemp()>maxTemp[måned] && dato.getMaxTemp()>=Registrering.MAXMINTEMP && dato.getMaxTemp()<=Registrering.MAXMAXTEMP)
					maxTemp[måned]=dato.getMaxTemp();
			} // end of while(datoiterator.hasNext())
		} // end of while(iterator.hasNext())
		Object[][] returarray=new Object[0][7];
		int[] returVedlegg=new int[0];
		int i=0;
		iterator=sl.iterator();
		while(iterator.hasNext())
		{
			Sted sted=iterator.next();
			datoiterator=sted.getDatoliste().iterator();
			while(datoiterator.hasNext())
			{
				Dato dato=datoiterator.next();
				int måned=dato.getDato().get(GregorianCalendar.MONTH);
				if(rNedbør.isSelected() && dato.getNedbør()==maxNedbør[måned] && dato.getNedbør()>-1 && dato.getNedbør()<=Registrering.MAXNEDBØR)
				{
					returarray=returarray.length==0?new Object[1][7]:utvidArray(returarray);
					returVedlegg=returVedlegg.length==0?new int[1]:utvidArray(returVedlegg);
					returarray[i][0] = sted.getNavn();
					returarray[i][1] = dato.getDato().getTime();
					returarray[i][2] = rNedbør.isSelected() ? dato.getNedbør() : null;
					returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
					returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
					returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
					returarray[i][6] = null;
					returVedlegg[i] = dato.getDato().get(GregorianCalendar.MONTH);
					i++;
				}
				if(rMintemp.isSelected() && dato.getMinTemp()==minTemp[måned] && dato.getMinTemp()>=Registrering.MAXMINTEMP && dato.getMinTemp()<=Registrering.MAXMAXTEMP)
				{
					returarray=returarray.length==0?new Object[1][7]:utvidArray(returarray);
					returVedlegg=returVedlegg.length==0?new int[1]:utvidArray(returVedlegg);
					returarray[i][0] = sted.getNavn();
					returarray[i][1] = dato.getDato().getTime();
					returarray[i][2] = rNedbør.isSelected() ? dato.getNedbør() : null;
					returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
					returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
					returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
					returarray[i][6] = null;
					returVedlegg[i] = dato.getDato().get(GregorianCalendar.MONTH);
					i++;
				}
				if(rMaxtemp.isSelected() && dato.getMaxTemp()==maxTemp[måned] && dato.getMaxTemp()>=Registrering.MAXMINTEMP && dato.getMaxTemp()<=Registrering.MAXMAXTEMP)
				{
					returarray=returarray.length==0?new Object[1][7]:utvidArray(returarray);
					returVedlegg=returVedlegg.length==0?new int[1]:utvidArray(returVedlegg);
					returarray[i][0] = sted.getNavn();
					returarray[i][1] = dato.getDato().getTime();
					returarray[i][2] = rNedbør.isSelected() ? dato.getNedbør() : null;
					returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
					returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
					returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
					returarray[i][6] = null;
					returVedlegg[i] = dato.getDato().get(GregorianCalendar.MONTH);
					i++;
				}
			} // end of while(datoiterator.hasNext())
		} // end of while(iterator.hasNext())
		if(returarray.length==0)
		{
			SkrivMelding.skriv("Fant ingen data for dette søket./W", panel);
			return null;
		}
		return forberedArray(returarray, returVedlegg);
	} // end of finnData(...)
	
	/**
	 * Metoden utvider størrelsen på den første dimensjonen av det to-dimensjonalet Object-arrayet array med 1
	 * @author Henrik Hermansen
	 * @param array et to-dimensjonalt Object-array som skal utvides
	 * @return et to-dimensjonalt Object-array der den første dimensjonens størrelse er utvidet med 1 i forhold til parameteret array
	 */
	private static Object[][] utvidArray(Object[][] array)
	{
		if(array.length==0)
			return null;
		Object[][] returarray=new Object[array.length+1][array[0].length];
		for(int i=0;i<array.length;i++)
			returarray[i]=array[i];
		return returarray;
	}
	
	/**
	 * Metoden utvider størrelsen på arrayet array med 1
	 * @author Henrik Hermansen
	 * @param array et int-array som skal utvides
	 * @return et int-array der størrelsen er utvidet med 1 i forhold til parameteret array
	 */
	private static int[] utvidArray(int[] array)
	{
		if(array.length==0)
			return null;
		int[] returarray=new int[array.length+1];
		for(int i=0;i<array.length;i++)
			returarray[i]=array[i];
		return returarray;
	}
	
	/**
	 * Metoden sorterer den første dimensjonen av det to-dimensjonalet Object-arrayet array basert på tilhørende verdi i int-arrayet arrayVedlegg.
	 * arrayVedlegg skal inneholde int-verdien for den måneden tilhørende array-verdi har en rekordverdi for. Dersom arrayVedlegg[2]==3 vil array[2] inneholde data om en rekordverdi for april (månedene er 0-11).
	 * Metoden setter også array[x][6] til en String-verdi som representerer måneden som array[x] har rekorddata for.
	 * @param array			et to-dimensjonalt array som skal sorteres og bli tildelt String-verdi for måneden
	 * @param arrayVedlegg	et int-array der posisjon x beskriver hvilken måned tilsvarende posisjon i arrayet array inneholder rekorddata for
	 * @return	et sortert to-dimensjonalt Object-array som også har fått tildelt String-verdi for måneden hver enkel posisjon har rekorddata for
	 */
	private static Object[][] forberedArray(Object[][] array, int[] arrayVedlegg)
	{
		if(array.length==0)
			return null;
		Object[][] returarray=new Object[array.length][array[0].length];
		int i=0; // i representerer posisjon i returarray
		for(int m=0;m<12;m++) // m representerer månedene i året
		{
			for(int j=0;j<array.length;j++) // j representerer posisjon i array
			{
				if(arrayVedlegg[j]==m)
				{
					returarray[i]=array[j];
					returarray[i][6]=MetroPanel.MÅNEDER[m];
					i++;
				} // end of if()
			} // end of for(int j)
		} // end of for(int m)
		return returarray;
	}
} // end of class FinnRekordData