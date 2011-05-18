/**
 * Inneholder klassen Bildepanel.
 * @author Lars Smeby
 * @since 15.05.2011
 * @updated 16.05.2011
 * @version	1
 */
package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;

/**
 *	Klassen representerer et panel med et bilde på.
 */
@SuppressWarnings("serial")
public class Bildepanel extends JPanel
{
	private ImageIcon ikon;

	/**
	 * Konstruktør. Laster inn bilde og oppretter det.
	 * @author Lars Smeby
	 */
	public Bildepanel()
	{
		URL kilde = Bildepanel.class.getResource("/images/startsidebilde.gif");
		if(kilde != null)
			ikon = new ImageIcon(kilde);
		else
			ikon = null;
	}
	
	/**
	 * Overridet fra JPanel. Tegner bildet midt på panelet.
	 * @author Lars Smeby
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(ikon != null)
			ikon.paintIcon(this, g, (this.getWidth()-ikon.getIconWidth())/2, (this.getHeight()-ikon.getIconHeight())/2);
		else
			g.drawString("Finner ikke bildet", 100, 100);
	}
	
	/**
	 * Overridet fra JPanel. Returnerer ønsket dimensjon.
	 * @author Lars Smeby
	 */
	public Dimension getPreferredSize()
	{
		if(ikon != null)
			return new Dimension(ikon.getIconHeight(), ikon.getIconHeight());
		else
			return new Dimension(200, 200);
	}
} // end of class Bildepanel