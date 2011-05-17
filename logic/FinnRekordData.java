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
	public static Object[][] finnData(Stedliste sl, JPanel panel, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		GregorianCalendar kalender=new GregorianCalendar();
		int iÅr=kalender.get(GregorianCalendar.YEAR);
		double[][] maxNedbør=new double[iÅr+1][12];
		double[][] minTemp=new double[iÅr+1][12];
		double[][] maxTemp=new double[iÅr+1][12];
		for(int i=1900;i<=iÅr;i++)
		{
			for(int j=0;j<12;j++)
			{
				maxNedbør[i][j]=-1;
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
				int år=dato.getDato().get(GregorianCalendar.YEAR);
				int måned=dato.getDato().get(GregorianCalendar.MONTH);
				if(rNedbør.isSelected() && dato.getNedbør()>maxNedbør[år][måned] && dato.getNedbør()>-1 && dato.getNedbør()<=Registrering.MAXNEDBØR)
					maxNedbør[år][måned]=dato.getNedbør();
				if(rMintemp.isSelected() && dato.getMinTemp()<minTemp[år][måned] && dato.getMinTemp()>=Registrering.MAXMINTEMP && dato.getMinTemp()<=Registrering.MAXMAXTEMP)
					minTemp[år][måned]=dato.getMinTemp();
				if(rMaxtemp.isSelected() && dato.getMaxTemp()>maxTemp[år][måned] && dato.getMaxTemp()>=Registrering.MAXMINTEMP && dato.getMaxTemp()<=Registrering.MAXMAXTEMP)
					maxTemp[år][måned]=dato.getMaxTemp();
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
				int år=dato.getDato().get(GregorianCalendar.YEAR);
				int måned=dato.getDato().get(GregorianCalendar.MONTH);
				if(rNedbør.isSelected() && dato.getNedbør()==maxNedbør[år][måned] && dato.getNedbør()>-1 && dato.getNedbør()<=Registrering.MAXNEDBØR)
				{
					returarray=returarray.length==0?new Object[1][6]:utvidArray(returarray);
					returarray[i][0] = sted.getNavn();
					returarray[i][1] = dato.getDato().getTime();
					returarray[i][2] = rNedbør.isSelected() ? dato.getNedbør() : null;
					returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
					returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
					returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
					i++;
				}
				if(rMintemp.isSelected() && dato.getMinTemp()==minTemp[år][måned] && dato.getMinTemp()>=Registrering.MAXMINTEMP && dato.getMinTemp()<=Registrering.MAXMAXTEMP)
				{
					returarray=returarray.length==0?new Object[1][6]:utvidArray(returarray);
					returarray[i][0] = sted.getNavn();
					returarray[i][1] = dato.getDato().getTime();
					returarray[i][2] = rNedbør.isSelected() ? dato.getNedbør() : null;
					returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
					returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
					returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
					i++;
				}
				if(rMaxtemp.isSelected() && dato.getMaxTemp()==maxTemp[år][måned] && dato.getMaxTemp()>=Registrering.MAXMINTEMP && dato.getMaxTemp()<=Registrering.MAXMAXTEMP)
				{
					returarray=returarray.length==0?new Object[1][6]:utvidArray(returarray);
					returarray[i][0] = sted.getNavn();
					returarray[i][1] = dato.getDato().getTime();
					returarray[i][2] = rNedbør.isSelected() ? dato.getNedbør() : null;
					returarray[i][3] = rMintemp.isSelected() ? dato.getMinTemp() : null;
					returarray[i][4] = rMaxtemp.isSelected() ? dato.getMaxTemp() : null;
					returarray[i][5] = Sted.FYLKESLISTE[sted.getFylke()];
					i++;
				}
			}
		}
		if(returarray.length==0)
		{
			SkrivMelding.skriv("Fant ingen data for dette søket./W", panel);
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