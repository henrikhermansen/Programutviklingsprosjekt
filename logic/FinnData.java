package logic;

import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	public static Object[][] FinnDatoVaer(Stedliste stedliste, JComboBox fylke, JComboBox l�r, JComboBox lm�ned, JComboBox ldag, JPanel panel)
	{
		int f = fylke.getSelectedIndex();
		Stedliste sted = stedliste.finnSted(f);
		if(sted == null)
		{
			JOptionPane.showMessageDialog(panel, "Fylket har ingen registrerte steder", "Ingen registreringer", JOptionPane.INFORMATION_MESSAGE);
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
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (B004)", "System feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		Stedliste stedDatoListe = new Stedliste();
		while(iterator.hasNext())
		{
			Sted neste = iterator.next();
			Datoliste dato = neste.getDatoliste();
			if(dato.finnDato(�r, m�ned, dag) != null)
				stedDatoListe.settInn(neste);
		}
		int lengde = stedDatoListe.size();
		if(lengde == 0)
		{
			JOptionPane.showMessageDialog(panel, "Fylket har ingen data", "Ingen registreringer", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		iterator = stedDatoListe.iterator();
		
		Object[][] returarray = new Object[lengde][5];
		
		for(int i = 0; i < lengde; i++)
		{
			Sted neste = iterator.next();
			returarray[i][0] = neste.getNavn();
			returarray[i][1] = new GregorianCalendar(�r, m�ned, dag).getTime();
			returarray[i][2] = new Double(neste.getDatoliste().finnDato(�r, m�ned, dag).getNedb�r());
			returarray[i][3] = new Double(neste.getDatoliste().finnDato(�r, m�ned, dag).getMinTemp());
			returarray[i][4] = new Double(neste.getDatoliste().finnDato(�r, m�ned, dag).getMaxTemp());
		}

		return returarray;
	}
}

