package logic;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
				return null;
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
				return null;
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
		Sted sted = stedliste.finnSted(s, f);
		if(sted == null)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (B007)", "System feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		if(rsted.isSelected())
		{
			if(r�r.isSelected())
			{
				return stedData(sl,panel,f,s,�r);
			}
			if(rm�ned.isSelected())
			{
				int m�ned = lm�ned.getSelectedIndex();
				return stedData(sl,panel,f,s,�r,m�ned);
			}
		}
		return null; //Programmet kommer aldri hit, men kompilatoren krever det.
	}
	
	public static double[][] stedData(Stedliste sl, JPanel panel, int fylke, String sted, int �r)
	{
		double[][] returarray = new double[2][12];
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			double[] temp = Gjennomsnitt.gjennomsnitt(sl, panel, �r, i, fylke, sted);
			returarray[0][i] = temp[1];
			returarray[1][i] = temp[2];
		}
		
		return returarray;
	}
	
	public static double[][] stedData(Stedliste sl, JPanel panel, int fylke, String sted, int �r, int m�ned)
	{
		return null;
	}
}
