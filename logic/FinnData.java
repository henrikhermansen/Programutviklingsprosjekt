package logic;

import java.util.Iterator;

import javax.swing.JComboBox;

import data.Dato;
import data.Datoliste;
import data.Sted;
import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class FinnData
{
	/**
	 * @author B�rd Skeie
	 * Klasse for � finne v�rdata for et gitt sted p� en gitt dato.
	 * @param navn
	 * @param fylke
	 * @param JComboBox
	 * @return
	 */
	public static Object[][] FinnDatoVaer(Stedliste stedliste, JComboBox fylke, JComboBox l�r, JComboBox lm�ned, JComboBox ldag)
	{
		int f = fylke.getSelectedIndex();
		Stedliste sted = stedliste.finnSted(f);
		if(sted == null)
		{
			System.out.println( "Fylket har ingen registrerte steder");
			return null;
		}
		Iterator<Sted> iterator = sted.iterator();
		
		int �r;
		int m�ned = lm�ned.getSelectedIndex();
		int dag;
		
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
			dag = Integer.parseInt((String)ldag.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			System.out.println("Ukjent programfeil (B004)");
			return null;
		}
		
		Object[][] returarray;
		
		Stedliste stedDatoListe;
		while(iterator.hasNext())
			
		
		//Opprett ny liste med alle steder som har data registrert p� den angitte datoen
		
		return returarray;
	}
}

