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
	 * Oppretter tabell ved motta verdier til cellene
	 * @author Lars Smeby
	 * @param celler
	 */
	public DataTabell(Object[][] celler)
	{
		this.celler = celler;
	}
	/**
	 * @author Lars Smeby
	 */
	public String getColumnName(int kolonne)
	{
		return kolonnenavn[kolonne];
	}
	
	/**
	 * @author Lars Smeby
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
	 * @author Lars Smeby
	 */
	public int getColumnCount()
	{
		return celler[0].length;
	}

	/**
	 * @author Lars Smeby
	 */
	public int getRowCount()
	{
		return celler.length;
	}

	/**
	 * @author Lars Smeby
	 */
	public Object getValueAt(int rad, int kolonne)
	{
		return celler[rad][kolonne];
	}

}
