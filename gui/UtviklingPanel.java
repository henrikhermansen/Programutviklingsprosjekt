package gui;

import javax.swing.JLabel;

import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class UtviklingPanel extends MetroPanel
{

	/**
	 * @author Lars Smeby
	 * @param sl	Referanse til Stedlisten med all lagret data
	 */
	public UtviklingPanel(Stedliste sl)
	{
		super(sl);
		
		grid.add(new JLabel("Hei på deg"));
	}

}
