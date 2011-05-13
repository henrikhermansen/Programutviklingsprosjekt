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
	public static double[][] dataTilGrafikk(Stedliste sl, JPanel panel, JRadioButton rland,JRadioButton rfylke, JRadioButton rsted, JRadioButton rår, JRadioButton rmåned, JComboBox fylke, JComboBox sted, JComboBox lår, JComboBox lmåned)
	{
		int år;		
		try
		{
			år = Integer.parseInt((String)lår.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (L007)", "Feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		if(rland.isSelected())
		{
			if(rår.isSelected())
			{
				return null;
			}
			if(rmåned.isSelected())
			{
				int måned = lmåned.getSelectedIndex();
				return null;
			}
		}
		
		int f = fylke.getSelectedIndex();
		
		if(rfylke.isSelected())
		{
			if(rår.isSelected())
			{
				return null;
			}
			if(rmåned.isSelected())
			{
				int måned = lmåned.getSelectedIndex();
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
			if(rår.isSelected())
			{
				return stedData(sl,panel,f,s,år);
			}
			if(rmåned.isSelected())
			{
				int måned = lmåned.getSelectedIndex();
				return stedData(sl,panel,f,s,år,måned);
			}
		}
		return null; //Programmet kommer aldri hit, men kompilatoren krever det.
	}
	
	public static double[][] stedData(Stedliste sl, JPanel panel, int fylke, String sted, int år)
	{
		double[][] returarray = new double[2][12];
		
		for(int i = 0; i < returarray[0].length; i++)
		{
			double[] temp = Gjennomsnitt.gjennomsnitt(sl, panel, år, i, fylke, sted);
			returarray[0][i] = temp[1];
			returarray[1][i] = temp[2];
		}
		
		return returarray;
	}
	
	public static double[][] stedData(Stedliste sl, JPanel panel, int fylke, String sted, int år, int måned)
	{
		return null;
	}
}
