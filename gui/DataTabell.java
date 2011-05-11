package gui;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
	private Object[][] celler = {
									{"Oslo", new Date(), new Double(0.2), new Double(8.3), new Double(12.9)},
									{"Asker", new GregorianCalendar(2001, Calendar.SEPTEMBER, 11).getTime(), new Double(5.3), new Double(5), new Double(5.1)},
									{"Bærum", new GregorianCalendar(1956, Calendar.FEBRUARY, 1).getTime(), new Double(3.2), new Double(22), new Double(25.9)}
								};
	
	public DataTabell(Object[][] celler)
	{
		//this.celler = celler;
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
