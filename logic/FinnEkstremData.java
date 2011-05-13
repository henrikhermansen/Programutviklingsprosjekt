package logic;

import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data.Dato;
import data.Datoliste;
import data.Sted;
import data.Stedliste;

public class FinnEkstremData
{
	/**
	 * Hovedmetode som sender parametere videre til korrekt undermetode
	 * @author Henrik Hermansen
	 * @param sl
	 * @param panel
	 * @param stedLandet
	 * @param stedFylke
	 * @param stedSted
	 * @param fylke
	 * @param sted
	 * @param rdag
	 * @param rmåned
	 * @param rår
	 * @param ldag
	 * @param lmåned
	 * @param lår
	 * @param rEnkelverdi
	 * @param rAvgverdi
	 * @param rNedbør
	 * @param rMintemp
	 * @param rMaxtemp
	 * @return
	 */
	public static Object[][] finnData(Stedliste sl, JPanel panel, JRadioButton stedLandet, JRadioButton stedFylke, JRadioButton stedSted, JComboBox fylke, JComboBox sted, JRadioButton rdag, JRadioButton rmåned, JRadioButton rår, JComboBox ldag, JComboBox lmåned, JComboBox lår, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		int f = fylke.getSelectedIndex();
		String s = (String)sted.getSelectedItem();
		int måned = lmåned.getSelectedIndex();
		int år;
		int dag;
		if(stedSted.isSelected() && s == null)
		{
			JOptionPane.showMessageDialog(panel, "Ingen steder valgt", "Fant ikke sted", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		try
		{
			år = Integer.parseInt((String)lår.getSelectedItem());
			dag = Integer.parseInt((String)ldag.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (H001)", "Feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		Sted st = sl.finnSted(s, f);
		if(stedSted.isSelected() && st == null)
		{
			JOptionPane.showMessageDialog(panel, "Ukjent programfeil (H002)", "Feil", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(stedLandet.isSelected())
			return finnDataForLandet(sl,panel,rdag,rmåned,rår,dag,måned,år,rEnkelverdi,rAvgverdi,rNedbør,rMintemp,rMaxtemp);
		if(stedFylke.isSelected())
			return finnDataForFylke(sl,panel,f,rdag,rmåned,rår,dag,måned,år,rEnkelverdi,rAvgverdi,rNedbør,rMintemp,rMaxtemp);
		if(stedSted.isSelected())
			return finnDataForSted(sl,panel,st,rdag,rmåned,rår,dag,måned,år,rEnkelverdi,rAvgverdi,rNedbør,rMintemp,rMaxtemp);
		return null;
	}

	/**
	 * Undermetoder som sender parametere videre til korrekt undermetode
	 * @author Henrik Hermansen
	 * @param sl
	 * @param panel
	 * @param fylke
	 * @param sted
	 * @param rdag
	 * @param rmåned
	 * @param rår
	 * @param ldag
	 * @param lmåned
	 * @param lår
	 * @param rEnkelverdi
	 * @param rAvgverdi
	 * @param rNedbør
	 * @param rMintemp
	 * @param rMaxtemp
	 * @return
	 */
	private static Object[][] finnDataForSted(Stedliste sl, JPanel panel, Sted sted, JRadioButton rdag, JRadioButton rmåned, JRadioButton rår, int dag, int måned, int år, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForSted(sl,panel,sted,rdag,rmåned,rår,dag,måned,år,rNedbør,rMintemp,rMaxtemp);
		if(rAvgverdi.isSelected())
			return finnAvgverdiForSted(sl,panel,sted,rmåned,rår,måned,år,rNedbør,rMintemp,rMaxtemp);
		return null;
	}

	private static Object[][] finnDataForFylke(Stedliste sl, JPanel panel, int fylke, JRadioButton rdag, JRadioButton rmåned, JRadioButton rår, int dag, int måned, int år, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForFylke(sl,panel,fylke,rdag,rmåned,rår,dag,måned,år,rNedbør,rMintemp,rMaxtemp);
		if(rAvgverdi.isSelected())
			return finnAvgverdiForFylke(sl,panel,fylke,rmåned,rår,måned,år,rNedbør,rMintemp,rMaxtemp);
		return null;
	}

	private static Object[][] finnDataForLandet(Stedliste sl, JPanel panel, JRadioButton rdag, JRadioButton rmåned, JRadioButton rår, int dag, int måned, int år, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForLandet(sl,panel,rdag,rmåned,rår,dag,måned,år,rNedbør,rMintemp,rMaxtemp);
		if(rAvgverdi.isSelected())
			return finnAvgverdiForLandet(sl,panel,rmåned,rår,måned,år,rNedbør,rMintemp,rMaxtemp);
		return null;
	}
	
	/**
	 * Undermetoder bla bla
	 * @author Henrik Hermansen
	 * @param sl
	 * @param panel
	 * @param rdag
	 * @param rmåned
	 * @param rår
	 * @param ldag
	 * @param lmåned
	 * @param lår
	 * @param rNedbør
	 * @param rMintemp
	 * @param rMaxtemp
	 * @return
	 */
	private static Object[][] finnAvgverdiForSted(Stedliste sl, JPanel panel, Sted sted, JRadioButton rmåned, JRadioButton rår, int måned, int år, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		Datoliste datoliste=new Datoliste();
		String tabelldato=null;
		if(rmåned.isSelected())
		{
			datoliste = sted.getDatoliste().finnDatoer(år,måned);
			tabelldato=måned+"-"+år;
		}
		if(rår.isSelected())
		{
			datoliste = sted.getDatoliste().finnDatoer(år);
			tabelldato=år+"";
		}
		
		int lengde = datoliste.size();
		if(lengde == 0)
		{
			JOptionPane.showMessageDialog(panel, "Det eksisterer ikke data for dette stedet i denne tidsperioden", "Fant ikke data", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		
		Object[][] returarray = new Object[1][5];
		
		returarray[0][0] = tabelldato;
		returarray[0][1] = null;
		returarray[0][2] = rNedbør.isSelected() ? datoliste.getAvgNedbør() : null;
		returarray[0][3] = rMintemp.isSelected() ? datoliste.getAvgMinTemp() : null;
		returarray[0][4] = rMaxtemp.isSelected() ? datoliste.getAvgMaxTemp() : null;
		
		return returarray;
	}
	private static Object[][] finnEnkelverdiForSted(Stedliste sl, JPanel panel, Sted sted, JRadioButton rdag, JRadioButton rmåned, JRadioButton rår, int dag, int måned, int år, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		// TODO Auto-generated method stub
		return null;
	}
	private static Object[][] finnAvgverdiForFylke(Stedliste sl, JPanel panel, int fylke, JRadioButton rmåned, JRadioButton rår, int måned, int år, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		// TODO Auto-generated method stub
		return null;
	}
	private static Object[][] finnEnkelverdiForFylke(Stedliste sl, JPanel panel, int fylke, JRadioButton rdag, JRadioButton rmåned, JRadioButton rår, int dag, int måned, int år, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		// TODO Auto-generated method stub
		return null;
	}
	private static Object[][] finnAvgverdiForLandet(Stedliste sl, JPanel panel, JRadioButton rmåned, JRadioButton rår, int måned, int år, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		// TODO Auto-generated method stub
		return null;
	}
	private static Object[][] finnEnkelverdiForLandet(Stedliste sl, JPanel panel, JRadioButton rdag, JRadioButton rmåned, JRadioButton rår, int dag, int måned, int år, JRadioButton rNedbør, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
