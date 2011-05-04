package gui;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public abstract class MetroPanel
{
	protected JPanel panel, grid;
	
	public MetroPanel()
	{
		panel = new JPanel(new BorderLayout());
		grid = new JPanel(new GridLayout(0,2,3,3));
		
		panel.add(grid, BorderLayout.PAGE_START);
		panel.setBorder(BorderFactory.createEmptyBorder(25, 35, 10, 35));
	}
	
	/**
	 * Returnerer det panelet som skal brukes i hovedvinduet.
	 * @return et objekt av typen JPanel.
	 */
	public JPanel getPanel()
	{
		return panel;
	}
}
