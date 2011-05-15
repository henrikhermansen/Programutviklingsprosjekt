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
	private MetroPanel srPanel, vrPanel, sdPanel, ddPanel, gsPanel, evPanel, utPanel, ssPanel;
	private JButton regSted, regData, finnSted, finnDato, gjennomsnittKnapp, ekstremKnapp, utviklingKnapp;
	private JPanel hovedpanel;
	private Container c;
	private JMenuItem filAvslutt, filLagre, registrerSted, slettSted, registrerData, finnDataSted, finnDataDato, finnGjennomsnitt, finnEkstrem, statUtvikling, hjelpHjelp, hjelpOm;
	
	/**
	 * Konstrukt�r, hovedvinduet blir opprettet.
	 * UIManager.setLookAndFeel setter utseende p� knapper etc lik windows utseende.
	 * Hovedvinduet bygges opp med menybar i toppen felt for "fil", "registrer","statistikk"
	 * og "hjelp".
	 * Menyen p� venstre side i hovedvinduet blir opprettet samt plassen til panelvinduet 
	 * p� venstre siden.
	 * @author Lars Smeby, Henrik Hermansen
	 */
	public Metrovindu(Stedliste sl)
	{
		super("Meteorologiske data");
		
		this.sl = sl;
		srPanel = new StedRegPanel(this.sl);
		vrPanel = new VaerRegPanel(this.sl);
		sdPanel = new StedDataPanel(this.sl);
		ddPanel = new DatoDataPanel(this.sl);
		gsPanel = new GjennomsnittsPanel(this.sl);
		evPanel = new EkstremverdiPanel(this.sl);
		utPanel = new UtviklingPanel(this.sl);
		ssPanel = new StedSlettPanel(this.sl);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MenyKnappelytter mklytter = new MenyKnappelytter();
		
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		addWindowListener(new Vinduslytter());
		
		JMenuBar menylinje = new JMenuBar();
		JMenu filmeny = new JMenu("Fil");
		JMenu registrermeny = new JMenu("Registrer");
		JMenu finnmeny = new JMenu("Finn");
		JMenu statistikkmeny = new JMenu("Statistikk");
		JMenu hjelpmeny = new JMenu("Hjelp");
		filmeny.setMnemonic('f');
		registrermeny.setMnemonic('r');
		finnmeny.setMnemonic('i');
		statistikkmeny.setMnemonic('s');
		hjelpmeny.setMnemonic('h');
		
		filLagre = new JMenuItem("Lagre");
		filLagre.setMnemonic('l');
		filLagre.addActionListener(mklytter);
		
		filAvslutt = new JMenuItem("Avslutt");
		filAvslutt.setMnemonic('a');
		filAvslutt.addActionListener(mklytter); 
		
		registrerSted = new JMenuItem("Registrer sted");
		registrerSted.setMnemonic('s');
		registrerSted.addActionListener(mklytter);
		
		slettSted = new JMenuItem("Slett sted");
		slettSted.setMnemonic('d');
		slettSted.addActionListener(mklytter);
		
		registrerData = new JMenuItem("Registrer v�rdata");
		registrerData.setMnemonic('v');
		registrerData.addActionListener(mklytter);
		
		finnDataSted = new JMenuItem("V�rdata for sted");
		finnDataSted.setMnemonic('s');
		finnDataSted.addActionListener(mklytter);
		
		finnDataDato = new JMenuItem("V�rdata for dato");
		finnDataDato.setMnemonic('d');
		finnDataDato.addActionListener(mklytter);
		
		finnGjennomsnitt = new JMenuItem("Gjennomsnittsverdier");
		finnGjennomsnitt.setMnemonic('g');
		finnGjennomsnitt.addActionListener(mklytter);
		
		finnEkstrem = new JMenuItem("Ekstremverdier");
		finnEkstrem.setMnemonic('e');
		finnEkstrem.addActionListener(mklytter);
		
		statUtvikling = new JMenuItem("Utvikling over tid");
		statUtvikling.setMnemonic('u');
		statUtvikling.addActionListener(mklytter);
		
		hjelpHjelp = new JMenuItem("Hjelp til programmet");
		hjelpHjelp.setMnemonic('h');
		hjelpHjelp.addActionListener(mklytter);
		
		hjelpOm = new JMenuItem("Om");
		hjelpOm.setMnemonic('o');
		hjelpOm.addActionListener(mklytter);

		filmeny.add(filLagre);
		filmeny.add(filAvslutt);
		
		registrermeny.add(registrerSted);
		registrermeny.add(slettSted);
		registrermeny.add(registrerData);
		
		finnmeny.add(finnDataSted);
		finnmeny.add(finnDataDato);
		
		statistikkmeny.add(finnGjennomsnitt);
		statistikkmeny.add(finnEkstrem);
		statistikkmeny.add(statUtvikling);
		
		hjelpmeny.add(hjelpHjelp);
		hjelpmeny.add(hjelpOm);
		
		menylinje.add(filmeny);
		menylinje.add(registrermeny);
		menylinje.add(finnmeny);
		menylinje.add(statistikkmeny);
		menylinje.add(hjelpmeny);
		
		JPanel sidewrapper = new JPanel();
		JPanel sidepanel = new JPanel(new BorderLayout());
		JPanel sidemeny = new JPanel();
		GridLayout knapperekke = new GridLayout(7,0);
		sidemeny.setLayout(knapperekke);
		regSted = new JButton("Registrer sted");
		regData = new JButton("Registrer v�rdata");
		finnSted = new JButton("V�rdata for sted");
		finnDato = new JButton("V�rdata for dato");
		gjennomsnittKnapp = new JButton("Gjennomsnittsverdier");
		ekstremKnapp = new JButton("Ekstremverdier");
		utviklingKnapp = new JButton("Utvikling over tid");
		regSted.addActionListener(mklytter);
		regData.addActionListener(mklytter);
		finnSted.addActionListener(mklytter);
		finnDato.addActionListener(mklytter);
		gjennomsnittKnapp.addActionListener(mklytter);
		ekstremKnapp.addActionListener(mklytter);
		utviklingKnapp.addActionListener(mklytter);
		sidemeny.add(regSted);
		sidemeny.add(regData);
		sidemeny.add(finnSted);
		sidemeny.add(finnDato);
		sidemeny.add(gjennomsnittKnapp);
		sidemeny.add(ekstremKnapp);
		sidemeny.add(utviklingKnapp);
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
		setResizable(true);
		
		int bredde = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h�yde = Toolkit.getDefaultToolkit().getScreenSize().height;
		if(bredde < 800 || h�yde < 600)
		{
			setSize((int)(bredde*0.9),(int)(h�yde*0.9));
			setLocation(0, 0);
		}
		else
		{
			setSize(800,600);
			setLocation((bredde-800)/2, (h�yde-600)/2);
		}
	}
	
	/**
	 * Oppdaterer og setter hovedpanel/vindu til h�yre.
	 * @author Lars Smeby
	 */
	public void settHovedPanel()
	{
		c.remove(1);
		c.add(hovedpanel, BorderLayout.CENTER);
		c.validate();
		c.repaint();
	}
	
	/**
	 * Returnerer en referanse til dette vinduet
	 * @author Lars Smeby
	 * @return	Referanse til dette vinduet
	 */
	public Metrovindu getMetrovindu()
	{
		return this;
	}
	
	/**
	 * Privat lytteklasse av typen ActionListener for menyknappene 
	 * til venstre i hovedvinduet og til knappene til i menyen.
	 * Tar seg av skifting av de forskjellige panelene.
	 * @author Lars Smeby, B�rd Skeie
	 */
	private class MenyKnappelytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == regSted || e.getSource() == registrerSted)
			{
				c.remove(1);
				c.add(srPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
			if(e.getSource() == regData || e.getSource() == registrerData)
			{
				c.remove(1);
				c.add(vrPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
				vrPanel.hentSteder(vrPanel.getFylke());
			}
			if(e.getSource() == finnSted || e.getSource() == finnDataSted)
			{
				c.remove(1);
				c.add(sdPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
				sdPanel.hentSteder(sdPanel.getFylke());
			}
			if(e.getSource() == finnDato || e.getSource() == finnDataDato)
			{
				c.remove(1);
				c.add(ddPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
			if(e.getSource() == gjennomsnittKnapp || e.getSource() == finnGjennomsnitt)
			{
				c.remove(1);
				c.add(gsPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
			if(e.getSource() == ekstremKnapp || e.getSource() == finnEkstrem)
			{
				c.remove(1);
				c.add(evPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
			if(e.getSource() == utviklingKnapp || e.getSource() == statUtvikling)
			{
				c.remove(1);
				c.add(utPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
			if(e.getSource() == slettSted)
			{
				c.remove(1);
				c.add(ssPanel.getPanel(), BorderLayout.CENTER);
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
				JOptionPane.showMessageDialog(getMetrovindu(), "Data er lagret til fil", "Lagret", JOptionPane.INFORMATION_MESSAGE);
			}
			if(e.getSource() == hjelpHjelp)
			{
				String hjelp = "Hjelp til programmet\n" +
								"\n" +
								"For � bruke programmet m� du...";
				JOptionPane.showMessageDialog(getMetrovindu(), hjelp, "Hjelp", JOptionPane.INFORMATION_MESSAGE);
			}
			if(e.getSource() == hjelpOm)
			{
				String om = "Metrologiske data\n" +
							"\n" +
							"Prosjektoppgave i programutvikling v�ren 2011\n" +
							"Gruppe 3: Henrik Hermansen, B�rd Skeie og Lars Smeby\n" +
							"Programversjon: 1.0\n" +
							"Sist oppdatdert: 15.05.2011\n" +
							"H�yskolen i Oslo";
				JOptionPane.showMessageDialog(getMetrovindu(), om, "Om", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/**
	 * Lytteklasse for vinduet. S�rger for at programmet lagrer og avslutter 
	 * riktig ved bruk av avslutt-knappen oppe til h�yre i vinduet.
	 * @author B�rd Skeie
	 *
	 */
	private class Vinduslytter extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			Filh�ndterer.lagreFil(sl);
			System.exit(0);
		}
	}
}
