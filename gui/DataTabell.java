package gui;

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int kolonne)
	{
		return celler[0][kolonne].getClass();
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
