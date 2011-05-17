package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import javax.swing.JPanel;

import logic.Registrering;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
@SuppressWarnings("serial")
public class Utviklingsgrafikk extends JPanel
{	
	private double[][] dataarray;
	private Color nedbFarge, tempFarge, labelFarge;
	private DecimalFormat df;
	
	/**
	 * Konstruktør, tar imot data til uttegning og setter parametere
	 * @author Lars Smeby
	 * @param dataarray	Data som skal tegnes ut. På formatet double[2][x]
	 * der 2 er nedbørsdata og temperaturdata, og x er dataverdier.
	 */
	public Utviklingsgrafikk(double[][] dataarray)
	{
		this.dataarray = dataarray;
		nedbFarge = Color.BLUE;
		tempFarge = Color.RED;
		labelFarge = Color.BLACK;
		df = new DecimalFormat("#.##");
	}
	
	/**
	 * Tegner ut grafikken på panelet, kalles automatisk
	 * @author Lars Smeby
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setStroke(new BasicStroke(2.0F));
		
		tegnNedbørsData(g2d);
		tegnTemperaturData(g2d);
		tegnLabeler(g2d);
		tegnLegend(g2d);
	}
	
	/**
	 * Tegner en graf samt tallverdier for nedbørsdata
	 * @author Lars Smeby
	 * @param g2d	Grafikktegneren fra paintComponent
	 */
	private void tegnNedbørsData(Graphics2D g2d)
	{
		double høydefaktor = 0;
		
		for(int i = 0; i < dataarray[0].length; i++)
		{
			if(dataarray[0][i] >= 0)
			{
				if(dataarray[0][i] > høydefaktor)
				høydefaktor = dataarray[0][i];
			}
		}
		if(høydefaktor > 0)
			høydefaktor = (this.getHeight()-75)/høydefaktor;
		else
			høydefaktor = 1;
		
		int mellomrom = this.getWidth()/dataarray[0].length;
		int bredde = mellomrom/2;
		int forrigeX = 0;
		int forrigeY = 0;
		
		for(int i = 0; i < dataarray[0].length; i++)
		{
			if(dataarray[0][i] >= 0)
			{
				if(i != 0 && forrigeX != 0)
				{
					g2d.setColor(nedbFarge);
					g2d.drawLine(forrigeX, forrigeY, bredde, this.getHeight()-(int)(dataarray[0][i]*høydefaktor)-30);
				}
				forrigeX = bredde;
				forrigeY = this.getHeight()-(int)(dataarray[0][i]*høydefaktor)-30;
				bredde += mellomrom;
			}
			else
				bredde += mellomrom;
		}
		
		// Tegner verdiene i egen løkke for at de skal tegnes oppå grafen der de overlapper
		bredde = mellomrom/2;
		forrigeX = 0;
		forrigeY = 0;
		g2d.setColor(labelFarge);
		
		for(int i = 0; i < dataarray[0].length; i++)
		{
			if(dataarray[0][i] >= 0)
			{
				forrigeX = bredde;
				forrigeY = this.getHeight()-(int)(dataarray[0][i]*høydefaktor)-30;
				bredde += mellomrom;
				g2d.setColor(labelFarge);
				g2d.drawString(df.format(dataarray[0][i])+"mm", forrigeX, forrigeY-10);
			}
			else
				bredde += mellomrom;
		}
		
		
	}
	
	/**
	 * Tegner en graf samt tallverdier for temperaturdata
	 * @author Lars Smeby
	 * @param g2d	Grafikktegneren fra paintComponent
	 */
	private void tegnTemperaturData(Graphics2D g2d)
	{
		double høydefaktor = 0;
		
		for(int i = 0; i < dataarray[1].length; i++)
		{
			if(dataarray[1][i] <= Registrering.MAXMAXTEMP)
			{
				if(dataarray[1][i] > høydefaktor)
					høydefaktor = dataarray[1][i];
				if(-dataarray[1][i] > høydefaktor)
					høydefaktor = -dataarray[1][i];
			}
		}
		if(høydefaktor > 0)
			høydefaktor = (this.getHeight()/2-75)/høydefaktor;
		else
			høydefaktor = 1;
		
		int nullGrader = this.getHeight()/2;
		int mellomrom = this.getWidth()/dataarray[1].length;
		int bredde = mellomrom/2;
		int forrigeX = 0;
		int forrigeY = 0;
		
		g2d.setColor(tempFarge);
		
		for(int i = 0; i < dataarray[1].length; i++)
		{
			if(dataarray[1][i] <= Registrering.MAXMAXTEMP)
			{
				if(i != 0 && forrigeX != 0)
				{
					
					g2d.drawLine(forrigeX, forrigeY, bredde, nullGrader-((int)(dataarray[1][i]*høydefaktor)));
				}
				forrigeX = bredde;
				forrigeY = nullGrader-((int)(dataarray[1][i]*høydefaktor));
				bredde += mellomrom;
			}
			else
				bredde += mellomrom;
		}
		
		// Tegner verdiene i egen løkke for at de skal tegnes oppå grafen der de overlapper
		bredde = mellomrom/2;
		forrigeX = 0;
		forrigeY = 0;
		g2d.setColor(labelFarge);
		
		for(int i = 0; i < dataarray[1].length; i++)
		{
			if(dataarray[1][i] <= Registrering.MAXMAXTEMP)
			{
				forrigeX = bredde;
				forrigeY = nullGrader-((int)(dataarray[1][i]*høydefaktor));
				bredde += mellomrom;
				g2d.drawString(df.format(dataarray[1][i])+"\u00b0C", forrigeX, forrigeY-10);
			}
			else
				bredde += mellomrom;
		}
	}
	
	/**
	 * Tegner labeler (tall) nederst på panelet basert på antall dataverdier mottatt
	 * @author Lars Smeby
	 * @param g2d	Grafikktegneren fra paintComponent
	 */
	private void tegnLabeler(Graphics2D g2d)
	{
		g2d.setColor(labelFarge);
		int labelmellomrom = this.getWidth()/dataarray[0].length;
		int labelbredde = labelmellomrom/2;
		for(int i = 0; i < dataarray[0].length; i++)
		{
			g2d.drawString(Integer.toString(i+1), labelbredde, this.getHeight()-10);
			labelbredde += labelmellomrom;
		}
		
		g2d.setStroke(new BasicStroke(1.0F, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0F, new float[] {10.0F,10.0F}, 0.0F));
		g2d.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
		g2d.setStroke(new BasicStroke(2.0F));
	}
	
	/**
	 * Tegner forklaringer på strekfarger øverst i panelet
	 * @author Lars Smeby
	 * @param g2d	Grafikktegneren fra paintComponent
	 */
	private void tegnLegend(Graphics2D g2d)
	{
		g2d.setColor(labelFarge);
		g2d.drawString("Total nedbør", 5, 15);
		g2d.setColor(nedbFarge);
		g2d.drawLine(70, 12, 95, 12);
		
		g2d.setColor(labelFarge);
		g2d.drawString("Gjennomsnittstemperatur", 115, 15);
		g2d.setColor(tempFarge);
		g2d.drawLine(245, 12, 270, 12);
		
		g2d.setColor(labelFarge);
		g2d.drawString("0\u00b0C", 290, 15);
		g2d.setStroke(new BasicStroke(1.0F, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0F, new float[] {5.0F,5.0F}, 0.0F));
		g2d.drawLine(315, 12, 340, 12);
		
		g2d.setStroke(new BasicStroke(2.0F));
	}
}
