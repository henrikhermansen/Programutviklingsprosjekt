package gui;

import javax.swing.table.AbstractTableModel;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class DataTabellModell extends AbstractTableModel
{
	/**
	 * @author Lars Smeby
	 */
	public String getColumnName(int kolonne)
	{
		return null;
	}
	
	/**
	 * @author Lars Smeby
	 */
	public Class getColumnClass(int kolonne)
	{
		return getValueAt(0, kolonne).getClass();
	}
	
	/**
	 * @author Lars Smeby
	 */
	public int getColumnCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @author Lars Smeby
	 */
	public int getRowCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @author Lars Smeby
	 */
	public Object getValueAt(int rad, int kolonne)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
