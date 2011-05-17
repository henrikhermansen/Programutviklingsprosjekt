package logic;

import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data.Dato;
import data.Sted;
import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class FinnRekordData
{
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
			}
		}
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
			}
		}
		if(returarray.length==0)
		{
			SkrivMelding.skriv("Fant ingen data for dette s�ket./W", panel);
			return null;
		}
		return returarray;
	}
	
	private static Object[][] utvidArray(Object[][] array)
	{
		Object[][] returarray=new Object[array.length+1][array[0].length];
		for(int i=0;i<array.length;i++)
			returarray[i]=array[i];
		return returarray;
	}
}