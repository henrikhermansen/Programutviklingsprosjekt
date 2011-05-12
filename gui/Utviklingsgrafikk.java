package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
@SuppressWarnings("serial")
public class Utviklingsgrafikk extends JPanel
{	
	private double[][] dataarray;
	
	public Utviklingsgrafikk(double[][] dataarray)
	{
		this.dataarray = dataarray;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		tegnNedbørsData(g2d, Color.BLUE);
	}
	
	private void tegnNedbørsData(Graphics2D g2d, Color c)
	{
		int mellomrom = this.getWidth()/dataarray[0].length;
		int bredde = mellomrom/2;
		int forrigeX = 0;
		int forrigeY = 0;
		g2d.setStroke(new BasicStroke(2.0F));
		for(int i = 0; i < dataarray[0].length; i++)
		{
			if(dataarray[0][i] >= 0)
			{
				if(i != 0 && forrigeX != 0)
				{
					g2d.setColor(c);
					g2d.drawLine(forrigeX, forrigeY, bredde, this.getHeight()-(int)dataarray[0][i]-30);
				}
				forrigeX = bredde;
				forrigeY = this.getHeight()-(int)dataarray[0][i]-30;
				bredde += mellomrom;
				g2d.setColor(Color.BLACK);
				g2d.drawString(Double.toString(dataarray[0][i]), forrigeX, forrigeY-10);
			}
			else
				bredde += mellomrom;
		}
		g2d.setColor(Color.BLACK);
		int labelmellomrom = this.getWidth()/dataarray[0].length;
		int labelbredde = labelmellomrom/2;
		for(int i = 0; i < dataarray[0].length; i++)
		{
			g2d.drawString(Integer.toString(i+1), labelbredde, this.getHeight()-10);
			labelbredde += labelmellomrom;
		}
		
	}
}
