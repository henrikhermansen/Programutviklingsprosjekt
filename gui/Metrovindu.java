package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import data.Stedliste;
import data.Filh�ndterer;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
@SuppressWarnings("serial")
public class Metrovindu extends JFrame
{	
	/**
	 * sl		Stedliste-objektet som inneholder alle lagrede data
	 * srPanel	Panelet for � registrere sted
	 * vrPanel	Panelet for � registrere v�rdata
	 * JButton	Knapper for � registrere sted, registrere data, finne sted og finne data
	 * hovedpanel	Oppretter panelet som er til h�yre i hovedvindu
	 * c		Container c er containeren som holder p� vindusobjektet
	 */
	private Stedliste sl;
	private MetroPanel srPanel, vrPanel, sdPanel, ddPanel;
	private JButton regSted, regData, finnSted, finnDato;
	private JPanel hovedpanel;
	private Container c;
	private JMenuItem filAvslutt, filLagre;
	
	/**
	 * Hovedvinduet blir opprettet.
	 * UIManager.setLookAndFeel setter utseende p� knapper etc lik windows utseende.
	 * Hovedvinduet bygges opp med menybar i toppen felt for "fil", "registrer","statistikk"
	 * og "hjelp".
	 * Menyen p� venstre side i hovedvinduet blir opprettet samt plassen til panelvinduet 
	 * p� venstre siden.
	 */
	public Metrovindu(Stedliste sl)
	{
		super("Meteorologiske data");
		
		this.sl = sl;
		srPanel = new StedRegPanel(this.sl);
		vrPanel = new VaerRegPanel(this.sl);
		sdPanel = new StedDataPanel(this.sl);
		ddPanel = new DatoDataPanel(this.sl);
		
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			
		}
		
		MenyKnappelytter mklytter = new MenyKnappelytter();
		
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		addWindowListener(new Vinduslytter());
		
		JMenuBar menylinje = new JMenuBar();
		JMenu filmeny = new JMenu("Fil");
		JMenu registrermeny = new JMenu("Registrer");
		JMenu statistikkmeny = new JMenu("Statistikk");
		JMenu hjelpmeny = new JMenu("Hjelp");
		filmeny.setMnemonic('f');
		registrermeny.setMnemonic('r');
		statistikkmeny.setMnemonic('s');
		hjelpmeny.setMnemonic('h');
		
		filLagre = new JMenuItem("Lagre");
		filLagre.setMnemonic('l');
		filLagre.addActionListener(mklytter);
		
		filAvslutt = new JMenuItem("Avslutt");
		filAvslutt.setMnemonic('a');
		filAvslutt.addActionListener(mklytter); 

		filmeny.add(filLagre);
		filmeny.add(filAvslutt);
		
		menylinje.add(filmeny);
		menylinje.add(registrermeny);
		menylinje.add(statistikkmeny);
		menylinje.add(hjelpmeny);
		
		JPanel sidewrapper = new JPanel();
		JPanel sidepanel = new JPanel(new BorderLayout());
		JPanel sidemeny = new JPanel();
		GridLayout knapperekke = new GridLayout(5,0);
		sidemeny.setLayout(knapperekke);
		regSted = new JButton("Registrer sted");
		regData = new JButton("Register v�rdata");
		finnSted = new JButton("V�rdata for sted");
		finnDato = new JButton("V�rdata for dato");
		regSted.addActionListener(mklytter);
		regData.addActionListener(mklytter);
		finnSted.addActionListener(mklytter);
		finnDato.addActionListener(mklytter);
		sidemeny.add(regSted);
		sidemeny.add(regData);
		sidemeny.add(finnSted);
		sidemeny.add(finnDato);
		sidepanel.add(sidemeny,BorderLayout.PAGE_START);
		sidepanel.setBorder(BorderFactory.createLoweredBevelBorder());
		sidewrapper.add(sidepanel);
		sidewrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		hovedpanel = new JPanel();
		hovedpanel.add(new JLabel("Dynamisk side som endres etter hvilken knapp du trykker p�.", JLabel.CENTER));
		
		c.add(sidewrapper, BorderLayout.LINE_START);
		c.add(hovedpanel, BorderLayout.CENTER);
		setJMenuBar(menylinje);
		setVisible(true);
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fjernes etterhvert
	}
	
	/**
	 * Oppdaterer og setter hovedpanel/vindu til h�yre.
	 */
	public void settHovedPanel()
	{
		c.remove(1);
		c.add(hovedpanel, BorderLayout.CENTER);
		c.validate();
		c.repaint();
	}
	
	/**
	 * Privat lytteklasse av typen ActionListener for menyknappene 
	 * til venstre i hovedvinduet.
	 * @author bOa
	 *
	 */
	private class MenyKnappelytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == regSted)
			{
				c.remove(1);
				c.add(srPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
			if(e.getSource() == regData)
			{
				c.remove(1);
				c.add(vrPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
				vrPanel.hentSteder(vrPanel.getFylke());
			}
			if(e.getSource() == finnSted)
			{
				c.remove(1);
				c.add(sdPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
				sdPanel.hentSteder(sdPanel.getFylke());
			}
			if(e.getSource() == finnDato)
			{
				c.remove(1);
				c.add(ddPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
			if(e.getSource() == filAvslutt)
			{
				Filh�ndterer.lagreFil(sl);
				System.exit(0);
			}
			if(e.getSource() == filLagre)
			{
				Filh�ndterer.lagreFil(sl);
				JOptionPane.showMessageDialog(null,"Data er lagret til fil","Lagret",JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private class Vinduslytter extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			Filh�ndterer.lagreFil(sl);
			System.exit(0);
		}
	}
}
