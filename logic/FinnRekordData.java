/**
 * Inneholder klassen FinnRekordData.
 * @author Henrik Hermansen
 * @since 18.05.2011
 * @updated 18.05.2011
 * @version	1
 */
package logic;

import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data.Dato;
import data.Sted;
import data.Stedliste;

/**
 *	Klassen inneholder statiske metoder for � hente ut rekorddata for hver m�ned.
 */
public class FinnRekordData
{
	/**
	 * Metode som finner rekordverdier for alle m�neder i alle �r der det finnes data.
	 * Deretter returneres at to-dimensjonalt Object-array med det/de stedet/stedene som har rekordverdien det sp�rres etter, for hver m�ned hvert �r.
	 * @author Henrik Hermansen
	 * @param sl		hoved-stedlisten i programmet
	 * @param panel		panelet som kaller opp metoden
	 * @param rNedb�r	radioknappen som sier om det sp�rres etter data om maksimumsnedb�r
	 * @param rMintemp	radioknappen som sier om det sp�rres etter data om minimumstemperaturer
	 * @param rMaxtemp	radioknappen som sier om det sp�rres etter data om maksimumstemperaturer
	 * @return et to-dimensjonalt Object-array med det/de stedet/stedene som har den rekordverdien det sp�rres etter, for hver m�ned hvert �r.
	 */
	public static Object[][] finnData(Stedliste sl, JPanel panel, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		GregorianCalendar kalender=new GregorianCalendar();
		int i�r=kalender.get(GregorianCalendar.YEAR);
		double[][] maxNedb�r=new double[i�r+1][12];
		double[][] minTemp=new double[i�r+1][12];
		double[][] maxTemp=new double[i�r+1][12];
		for(int i=1900;i<=i�r;i++)
		{
			for(int j=0;j<12;j++)
			{
				maxNedb�r[i][j]=-1;
				minTemp[i][j]=Registrering.MAXMAXTEMP+1;
				maxTemp[i][j]=Registrering.MAXMINTEMP-1;
			}
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
				int �r=dato.getDato().get(GregorianCalendar.YEAR);
				int m�ned=dato.getDato().get(GregorianCalendar.MONTH);
				if(rNedb�r.isSelected() && dato.getNedb�r()>maxNedb�r[�r][m�ned] && dato.getNedb�r()>-1 && dato.getNedb�r()<=Registrering.MAXNEDB�R)
					maxNedb�r[�r][m�ned]=dato.getNedb�r();
				if(rMintemp.isSelected() && dato.getMinTemp()<minTemp[�r][m�ned] && dato.getMinTemp()>=Registrering.MAXMINTEMP && dato.getMinTemp()<=Registrering.MAXMAXTEMP)
					minTemp[�r][m�ned]=dato.getMinTemp();
				if(rMaxtemp.isSelected() && dato.getMaxTemp()>maxTemp[�r][m�ned] && dato.getMaxTemp()>=Registrering.MAXMINTEMP && dato.getMaxTemp()<=Registrering.MAXMAXTEMP)
					maxTemp[�r][m�ned]=dato.getMaxTemp();
			} // end of while(datoiterator.hasNext())
		} // end of while(iterator.hasNext())
		Object[][] returarray=new Object[0][6];
		int i=0;
		iterator=sl.iterator();
		while(iterator.hasNext())
		{
			Sted sted=iterator.next();
			datoiterator=sted.getDatoliste().iterator();
			while(datoiterator.hasNext())
			{
				Dato dato=datoiterator.next();
				int �r=dato.getDato().get(GregorianCalendar.YEAR);
				int m�ned=dato.getDato().get(GregorianCalendar.MONTH);
				if(rNedb�r.isSelected() && dato.getNedb�r()==maxNedb�r[�r][m�ned] && dato.getNedb�r()>-1 && dato.getNedb�r()<=Registrering.MAXNEDB�R)
				{
					returarray=returarray.length==0?new Object[1][6]:utvidArray(returarray);
					returarray[i][0] = sted.getNavn();
					returarray[i][1] = dato.getDato().getTime();
					returarray[i][2] = rNedb�r.isSelected() ? dato.getNedb�r() : null;
					returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
					returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
					returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
					i++;
				}
				if(rMintemp.isSelected() && dato.getMinTemp()==minTemp[�r][m�ned] && dato.getMinTemp()>=Registrering.MAXMINTEMP && dato.getMinTemp()<=Registrering.MAXMAXTEMP)
				{
					returarray=returarray.length==0?new Object[1][6]:utvidArray(returarray);
					returarray[i][0] = sted.getNavn();
					returarray[i][1] = dato.getDato().getTime();
					returarray[i][2] = rNedb�r.isSelected() ? dato.getNedb�r() : null;
					returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
					returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
					returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
					i++;
				}
				if(rMaxtemp.isSelected() && dato.getMaxTemp()==maxTemp[�r][m�ned] && dato.getMaxTemp()>=Registrering.MAXMINTEMP && dato.getMaxTemp()<=Registrering.MAXMAXTEMP)
				{
					returarray=returarray.length==0?new Object[1][6]:utvidArray(returarray);
					returarray[i][0] = sted.getNavn();
					returarray[i][1] = dato.getDato().getTime();
					returarray[i][2] = rNedb�r.isSelected() ? dato.getNedb�r() : null;
					returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
					returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
					returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
					i++;
				}
			} // end of while(datoiterator.hasNext())
		} // end of while(iterator.hasNext())
		if(returarray.length==0)
		{
			SkrivMelding.skriv("Fant ingen data for dette s�ket./W", panel);
			return null;
		}
		return returarray;
	} // end of finnData(...)
	
	/**
	 * Metoden utvider st�rrelsen p� den f�rste dimensjonen av det to-dimensjonalet Object-arrayet array med 1
	 * @author Henrik Hermansen
	 * @param array et to-dimensjonalt Object-array som skal utvides
	 * @return et to-dimensjonalt Object-array der den f�rste dimensjonens st�rrelse er utvidet med 1 i forhold til parameteret array
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
} // end of class FinnRekordData