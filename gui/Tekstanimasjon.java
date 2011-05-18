package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
@SuppressWarnings("serial")
public class Tekstanimasjon extends JPanel implements ActionListener
{
	private Timer klokke;
	private int x, y, stopX;
	public static final int X = 600;
	public static final int Y = 40;
	public static final int STOPX = 25;
	public static final int X2 = 600;
	public static final int Y2 = 45;
	
	/**
	 * Konstruktør, setter posisjonsvariabler og starter klokken
	 * @author Lars Smeby
	 */
	public Tekstanimasjon()
	{
		x = X;
		y = Y;
		stopX = STOPX;
		klokke = new Timer(8, this);
		klokke.setInitialDelay(0);
		klokke.setCoalesce(true);
		klokke.start();
	}
	
	/**
	 * Overridet paintComponent-metode. Tegner teksten.
	 * @author Lars Smeby
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(new Font("sansserif", Font.BOLD, 15));
		g.drawString("< Velg et alternativ i menyen til venstre for å komme i gang", x, y);
	}
	
	/**
	 * Overridet getPreferredSize-metode
	 * @author Lars Smeby
	 * @return	Ønsket dimensjon på panelet
	 */
	public Dimension getPreferredSize()
	{
		return new Dimension(X2, Y2);
	}
	
	/**
	 * Lytter for klokken. Tegner panelet på nytt til stopX er nådd.
	 * @author Lars Smeby
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(x <= stopX)
			klokke.stop();
		else
		{
			x--;
			repaint();
		}
	}
}
