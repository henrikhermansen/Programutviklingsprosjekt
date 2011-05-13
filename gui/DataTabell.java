package gui;

import java.sql.Date;

import javax.swing.table.AbstractTableModel;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
@SuppressWarnings("serial")
public class DataTabell extends AbstractTableModel
{
	private String[] kolonnenavn = {"Sted", "Dato", "Nedbør", "Minimumstemperatur", "Maksimumstemperatur"};
	private Object[][] celler;
	
	/**
	 * Oppretter tabell ved å motta verdier til cellene
	 * @author Lars Smeby
	 * @param celler	2-dim Object-array
	 */
	public DataTabell(Object[][] celler)
	{
		this.celler = celler;
	}
	
	/**
	 * Returnerer kolonnenavnet
	 * @author Lars Smeby
	 * @param kolonne	Kolonnenummeret det skal hentes navn for
	 * @return	Kolonnenavn
	 */
	public String getColumnName(int kolonne)
	{
		return kolonnenavn[kolonne];
	}
	
	/**
	 * Returnerer klassen kolonnen skal formatteres etter
	 * @author Lars Smeby
	 * @param kolonne	Kolonnenummeret det gjelder
	 * @return	Klassetypen til kolonnen
	 */
	public Class<?> getColumnClass(int kolonne)
	{
		// Grunnet muligheten for blanke celler settes klassetypene fast
		switch(kolonne)
		{
		case 0:	return String.class;
		case 1: return Date.class;
		}
		return Double.class;
	}
	
	/**
	 * Returnerer antall kolonner i tabellen
	 * @author Lars Smeby
	 * @return	Antall kolonner i tabellen
	 */
	public int getColumnCount()
	{
		return celler[0].length;
	}

	/**
	 * Returnerer antall rader i tabellen
	 * @author Lars Smeby
	 * @return	Antall rader i tabellen
	 */
	public int getRowCount()
	{
		return celler.length;
	}

	/**
	 * Returnerer innholdet i en celle
	 * @author Lars Smeby
	 * @param rad	Radnummer
	 * @param kolonne	Kolonnenummer
	 * @return	Celleinnholdet som et objekt
	 */
	public Object getValueAt(int rad, int kolonne)
	{
		return celler[rad][kolonne];
	}
}
