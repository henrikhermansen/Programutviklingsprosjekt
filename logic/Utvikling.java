package logic;

import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data.Dato;
import data.Sted;
import data.Stedliste;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class Utvikling
{
	public static double[][] dataTilGrafikk(Stedliste sl, JPanel panel, JRadioButton rland,JRadioButton rfylke, JRadioButton rsted, JRadioButton r�r, JRadioButton rm�ned, JComboBox fylke, JComboBox sted, JComboBox l�r, JComboBox lm�ned)
	{
		int �r;		
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (L007)", "Feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		if(rland.isSelected())
		{
			if(r�r.isSelected())
			{
				return landData(sl, panel, �r);
			}
			if(rm�ned.isSelected())
			{
				int m�ned = lm�ned.getSelectedIndex();
				return null;
			}
		}
		
		int f = fylke.getSelectedIndex();
		
		if(rfylke.isSelected())
		{
			if(r�r.isSelected())
			{
				return fylkeData(sl, panel, f, �r);
			}
			if(rm�ned.isSelected())
			{
				int m�ned = lm�ned.getSelectedIndex();
				return null;
			}
		}
		
		String s = (String) sted.getSelectedItem();
		if(s == null)
		{
			JOptionPane.showMessageDialog(panel, "Sted er ikke valgt", "Feil", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		Sted st = sl.finnSted(s, f);
		if(st == null)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (L008)", "Feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		if(rsted.isSelected())
		{
			if(r�r.isSelected())
			{
				return stedData(panel,f,st,�r);
			}
			if(rm�ned.isSelected())
			{
				int m�ned = lm�ned.getSelectedIndex();
				return stedData(panel,f,st,�r,m�ned);
			}
		}
		return null; //Programmet kommer aldri hit, men kompilatoren krever det.
	}
	
	public static double[][] landData(Stedliste sl, JPanel panel, int �r)
	{
		double[][] returarray = new double[2][12];
		double[][] temparray = Gjennomsnitt.gjennomsnittLand(sl, panel, �r);
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			returarray[0][i] = temparray[i][1];
			returarray[1][i] = temparray[i][2];
		}
		
		return returarray;
	}
	
	public static double[][] landData(Stedliste sl, JPanel panel, int �r, int m�ned)
	{
		return null; //TODO
	}
	
	public static double[][] fylkeData(Stedliste sl, JPanel panel, int fylke, int �r)
	{
		Stedliste fylkesl = sl.finnSted(fylke);
		if(fylkesl == null)
		{
			JOptionPane.showMessageDialog(panel, "Fylket har ingen registrerte steder", "Ingen registreringer", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		double[][] returarray = new double[2][12];
		double[][] temparray = Gjennomsnitt.gjennomsnittFylke(fylkesl, panel, fylke, �r);
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			returarray[0][i] = temparray[i][1];
			returarray[1][i] = temparray[i][2];
		}
		
		return returarray;
	}
	
	public static double[][] fylkeData(JPanel panel, int fylke, int �r, int m�ned)
	{
		return null; //TODO
	}
	
	public static double[][] stedData(JPanel panel, int fylke, Sted sted, int �r)
	{
		double[][] returarray = new double[2][12];
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			double[] temp = Gjennomsnitt.gjennomsnitt(panel, �r, i, fylke, sted);
			returarray[0][i] = temp[1];
			returarray[1][i] = temp[2];
		}
		
		return returarray;
	}
	
	public static double[][] stedData(JPanel panel, int fylke, Sted sted, int �r, int m�ned)
	{
		GregorianCalendar kal = new GregorianCalendar(�r, m�ned, 1);
		int antallDager = kal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		
		double[][] returarray = new double[2][antallDager];
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			Dato dato = sted.getDatoliste().finnDato(�r, m�ned, i+1);
			if(dato != null)
			{
				returarray[0][i] = dato.getNedb�r();
				returarray[1][i] = dato.getAvgTemp();
			}
			else
			{
				returarray[0][i] = -1;
				returarray[1][i] = Registrering.MAXMAXTEMP + 1;
			}
		}
		
		return returarray;
	}
}
