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
	// =========================== NIV� 1 =========================== NIV� 1 =========================== NIV� 1 =========================== NIV� 1 ===========================
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
	 * @param rm�ned
	 * @param r�r
	 * @param ldag
	 * @param lm�ned
	 * @param l�r
	 * @param rEnkelverdi
	 * @param rAvgverdi
	 * @param rNedb�r
	 * @param rMintemp
	 * @param rMaxtemp
	 * @return
	 */
	public static Object[][] finnData(Stedliste sl, JPanel panel, JRadioButton stedLandet, JRadioButton stedFylke, JRadioButton stedSted, JComboBox fylke, JComboBox sted, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, JComboBox ldag, JComboBox lm�ned, JComboBox l�r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		int f = fylke.getSelectedIndex();
		String s = (String)sted.getSelectedItem();
		int m�ned = lm�ned.getSelectedIndex();
		int �r;
		int dag;
		if(stedSted.isSelected() && s == null)
		{
			SkrivMelding.skriv("Ingen steder valgt/W", panel);
			return null;
		}
		try
		{
			�r = Integer.parseInt((String)l�r.getSelectedItem());
			dag = Integer.parseInt((String)ldag.getSelectedItem());
		}
		catch(NumberFormatException nfe)
		{
			SkrivMelding.skriv("Ukjent programfeil (H001)/E", panel);
			return null;
		}
		
		Sted st = sl.finnSted(s, f);
		if(stedSted.isSelected() && st == null)
		{
			SkrivMelding.skriv("Ukjent programfeil (H002)/E", panel);
			return null;
		}
		if(stedLandet.isSelected())
			return finnDataForLandet(sl,panel,rdag,rm�ned,r�r,dag,m�ned,�r,rEnkelverdi,rAvgverdi,rNedb�r,rMintemp,rMaxtemp);
		if(stedFylke.isSelected())
			return finnDataForFylke(sl,panel,f,rdag,rm�ned,r�r,dag,m�ned,�r,rEnkelverdi,rAvgverdi,rNedb�r,rMintemp,rMaxtemp);
		if(stedSted.isSelected())
			return finnDataForSted(sl,panel,st,rdag,rm�ned,r�r,dag,m�ned,�r,rEnkelverdi,rAvgverdi,rNedb�r,rMintemp,rMaxtemp);
		return null;
	}

	// =========================== NIV� 2 =========================== NIV� 2 =========================== NIV� 2 =========================== NIV� 2 ===========================
	/**
	 * Undermetoder som sender parametere videre til korrekt undermetode
	 * @author Henrik Hermansen
	 * @param sl
	 * @param panel
	 * @param fylke
	 * @param sted
	 * @param rdag
	 * @param rm�ned
	 * @param r�r
	 * @param ldag
	 * @param lm�ned
	 * @param l�r
	 * @param rEnkelverdi
	 * @param rAvgverdi
	 * @param rNedb�r
	 * @param rMintemp
	 * @param rMaxtemp
	 * @return
	 */
	private static Object[][] finnDataForSted(Stedliste sl, JPanel panel, Sted sted, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		Datoliste datoliste=new Datoliste();
		String tabelldato=null;
		if(rdag.isSelected())
		{
			Dato dato=sted.getDatoliste().finnDato(�r,m�ned,dag);
			if(dato!=null)
			{
				datoliste.settInn(dato);
				tabelldato=dag+"-"+m�ned+"-"+�r;
			}
		}
		if(rm�ned.isSelected())
		{
			datoliste = sted.getDatoliste().finnDatoer(�r,m�ned);
			tabelldato=m�ned+"-"+�r;
		}
		if(r�r.isSelected())
		{
			datoliste = sted.getDatoliste().finnDatoer(�r);
			tabelldato=�r+"";
		}
		
		int lengde = datoliste.size();
		if(lengde == 0)
		{
			SkrivMelding.skriv("Det eksisterer ikke data for dette stedet i denne tidsperioden/I", panel);
			return null;
		}
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForSted(datoliste,tabelldato,rNedb�r,rMintemp,rMaxtemp);
		if(rAvgverdi.isSelected())
			return finnAvgverdiForSted(datoliste,tabelldato,rNedb�r,rMintemp,rMaxtemp);
		return null;
	}

	private static Object[][] finnDataForFylke(Stedliste sl, JPanel panel, int fylke, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForFylke(sl,panel,fylke,rdag,rm�ned,r�r,dag,m�ned,�r,rNedb�r,rMintemp,rMaxtemp);
		if(rAvgverdi.isSelected())
			return finnAvgverdiForFylke(sl,panel,fylke,rm�ned,r�r,m�ned,�r,rNedb�r,rMintemp,rMaxtemp);
		return null;
	}

	private static Object[][] finnDataForLandet(Stedliste sl, JPanel panel, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rEnkelverdi, JRadioButton rAvgverdi, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		if(rEnkelverdi.isSelected())
			return finnEnkelverdiForLandet(sl,panel,rdag,rm�ned,r�r,dag,m�ned,�r,rNedb�r,rMintemp,rMaxtemp);
		if(rAvgverdi.isSelected())
			return finnAvgverdiForLandet(sl,panel,rm�ned,r�r,m�ned,�r,rNedb�r,rMintemp,rMaxtemp);
		return null;
	}
	
	// =========================== NIV� 3 =========================== NIV� 3 =========================== NIV� 3 =========================== NIV� 3 ===========================
	/**
	 * Undermetoder bla bla
	 * @author Henrik Hermansen
	 * @param sl
	 * @param panel
	 * @param rdag
	 * @param rm�ned
	 * @param r�r
	 * @param ldag
	 * @param lm�ned
	 * @param l�r
	 * @param rNedb�r
	 * @param rMintemp
	 * @param rMaxtemp
	 * @return
	 */
	private static Object[][] finnAvgverdiForSted(Datoliste datoliste, String tabelldato, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{		
		Object[][] returarray = new Object[1][5];
		
		returarray[0][0] = tabelldato;
		returarray[0][1] = null;
		returarray[0][2] = rNedb�r.isSelected() ? datoliste.getAvgNedb�r() : null;
		returarray[0][3] = rMintemp.isSelected() ? datoliste.getAvgMinTemp() : null;
		returarray[0][4] = rMaxtemp.isSelected() ? datoliste.getAvgMaxTemp() : null;
		
		return returarray;
	}
	private static Object[][] finnEnkelverdiForSted(Datoliste datoliste, String tabelldato, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		Object[][] returarray = new Object[1][5];
		
		returarray[0][0] = tabelldato;
		returarray[0][1] = null;
		returarray[0][2] = rNedb�r.isSelected() ? datoliste.getMaxNedb�r() : null;
		returarray[0][3] = rMintemp.isSelected() ? datoliste.getMinTemp() : null;
		returarray[0][4] = rMaxtemp.isSelected() ? datoliste.getMaxTemp() : null;
		
		return returarray;
	}
	private static Object[][] finnAvgverdiForFylke(Stedliste sl, JPanel panel, int fylke, JRadioButton rm�ned, JRadioButton r�r, int m�ned, int �r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		// TODO Auto-generated method stub
		return null;
	}
	private static Object[][] finnEnkelverdiForFylke(Stedliste sl, JPanel panel, int fylke, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		// TODO Auto-generated method stub
		return null;
	}
	private static Object[][] finnAvgverdiForLandet(Stedliste sl, JPanel panel, JRadioButton rm�ned, JRadioButton r�r, int m�ned, int �r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		// TODO Auto-generated method stub
		return null;
	}
	private static Object[][] finnEnkelverdiForLandet(Stedliste sl, JPanel panel, JRadioButton rdag, JRadioButton rm�ned, JRadioButton r�r, int dag, int m�ned, int �r, JRadioButton rNedb�r, JRadioButton rMintemp, JRadioButton rMaxtemp)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
