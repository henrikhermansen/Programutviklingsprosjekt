/**
 * 
 */
package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
@SuppressWarnings("serial")
public class Bildepanel extends JPanel
{
	private ImageIcon ikon;

	/**
	 * 
	 */
	public Bildepanel()
	{
		URL kilde = Bildepanel.class.getResource("/images/henrik.jpg");
		if(kilde != null)
			ikon = new ImageIcon(kilde);
		else
			ikon = null;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(ikon != null)
			ikon.paintIcon(this, g, (this.getWidth()-ikon.getIconWidth())/2, (this.getHeight()-ikon.getIconHeight())/2);
		else
			g.drawString("Finner ikke bildet", 100, 100);
	}
	
	public Dimension getPreferredSize()
	{
		if(ikon != null)
			return new Dimension(ikon.getIconHeight(), ikon.getIconHeight());
		else
			return new Dimension(200, 200);
	}
}
