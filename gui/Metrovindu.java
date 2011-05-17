package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import logic.LastIkon;

import data.Stedliste;
import data.Filhåndterer;

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
	 * srPanel	Panelet for å registrere sted
	 * vrPanel	Panelet for å registrere værdata
	 * JButton	Knapper for å registrere sted, registrere data, finne sted og finne data
	 * hovedpanel	Oppretter panelet som er til høyre i hovedvindu
	 * c		Container c er containeren som holder på vindusobjektet
	 */
	private Stedliste sl;
	private MetroPanel srPanel, vrPanel, sdPanel, ddPanel, gsPanel, evPanel, utPanel, ssPanel;
	private JButton regSted, regData, finnSted, finnDato, gjennomsnittKnapp, ekstremKnapp, utviklingKnapp, rekordKnapp;
	private JPanel startpanel;
	private Container c;
	private JMenuItem filStart, filAvslutt, filLagre, registrerSted, slettSted, registrerData, finnDataSted, finnDataDato, statGjennomsnitt, statEkstrem, statUtvikling, statRekord, hjelpHjelp, hjelpOm;
	
	/**
	 * Konstruktør, hovedvinduet blir opprettet.
	 * UIManager.setLookAndFeel setter utseende på knapper etc lik windows utseende.
	 * Hovedvinduet bygges opp med menybar i toppen felt for "fil", "registrer","statistikk"
	 * og "hjelp".
	 * Menyen på venstre side i hovedvinduet blir opprettet samt plassen til panelvinduet 
	 * på venstre siden.
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
		
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e)
		{
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
		filmeny.setMnemonic(KeyEvent.VK_F);
		registrermeny.setMnemonic(KeyEvent.VK_R);
		finnmeny.setMnemonic(KeyEvent.VK_I);
		statistikkmeny.setMnemonic(KeyEvent.VK_S);
		hjelpmeny.setMnemonic(KeyEvent.VK_H);
		
		filStart = new JMenuItem("Velkomstskjerm", LastIkon.last("Home16.gif"));
		filStart.setMnemonic(KeyEvent.VK_V);
		filStart.addActionListener(mklytter);
		
		filLagre = new JMenuItem("Lagre", LastIkon.last("Save16.gif"));
		filLagre.setMnemonic(KeyEvent.VK_L);
		filLagre.addActionListener(mklytter);
		
		filAvslutt = new JMenuItem("Avslutt", LastIkon.last("exit16.gif"));
		filAvslutt.setMnemonic(KeyEvent.VK_A);
		filAvslutt.addActionListener(mklytter); 
		
		registrerSted = new JMenuItem("Registrer sted", LastIkon.last("nyttsted16.gif"));
		registrerSted.setMnemonic(KeyEvent.VK_S);
		registrerSted.addActionListener(mklytter);

		registrerData = new JMenuItem("Registrer værdata", LastIkon.last("nyttvar16.gif"));
		registrerData.setMnemonic(KeyEvent.VK_V);
		registrerData.addActionListener(mklytter);
		
		slettSted = new JMenuItem("Slett sted", LastIkon.last("Delete16.gif"));
		slettSted.setMnemonic(KeyEvent.VK_D);
		slettSted.addActionListener(mklytter);
		
		finnDataSted = new JMenuItem("Værdata for sted", LastIkon.last("steddata16.gif"));
		finnDataSted.setMnemonic(KeyEvent.VK_S);
		finnDataSted.addActionListener(mklytter);
		
		finnDataDato = new JMenuItem("Værdata for dato", LastIkon.last("datodata16.gif"));
		finnDataDato.setMnemonic(KeyEvent.VK_D);
		finnDataDato.addActionListener(mklytter);
		
		statGjennomsnitt = new JMenuItem("Gjennomsnittsverdier", LastIkon.last("gjennomsnitt16.gif"));
		statGjennomsnitt.setMnemonic(KeyEvent.VK_G);
		statGjennomsnitt.addActionListener(mklytter);
		
		statEkstrem = new JMenuItem("Ekstremverdier", LastIkon.last("ekstrem16.gif"));
		statEkstrem.setMnemonic(KeyEvent.VK_E);
		statEkstrem.addActionListener(mklytter);
		
		statUtvikling = new JMenuItem("Utvikling over tid", LastIkon.last("utvikling16.gif"));
		statUtvikling.setMnemonic(KeyEvent.VK_U);
		statUtvikling.addActionListener(mklytter);
		
		statRekord = new JMenuItem("Månedlige rekorder", LastIkon.last("rekorder16.gif"));
		statRekord.setMnemonic(KeyEvent.VK_R);
		statRekord.addActionListener(mklytter);
		
		hjelpHjelp = new JMenuItem("Hjelp til programmet", LastIkon.last("Help16.gif"));
		hjelpHjelp.setMnemonic(KeyEvent.VK_H);
		hjelpHjelp.addActionListener(mklytter);
		
		hjelpOm = new JMenuItem("Om", LastIkon.last("About16.gif"));
		hjelpOm.setMnemonic(KeyEvent.VK_O);
		hjelpOm.addActionListener(mklytter);

		filmeny.add(filStart);
		filmeny.add(filLagre);
		filmeny.add(filAvslutt);
		
		registrermeny.add(registrerSted);
		registrermeny.add(registrerData);
		registrermeny.add(slettSted);
		
		finnmeny.add(finnDataSted);
		finnmeny.add(finnDataDato);
		
		statistikkmeny.add(statGjennomsnitt);
		statistikkmeny.add(statEkstrem);
		statistikkmeny.add(statUtvikling);
		statistikkmeny.add(statRekord);
		
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
		GridLayout knapperekke = new GridLayout(0,1);
		knapperekke.setVgap(10);
		sidemeny.setLayout(knapperekke);
		regSted = new JButton("Registrer sted", LastIkon.last("nyttsted24.gif"));
		regSted.setHorizontalAlignment(SwingConstants.LEFT);
		regSted.setMnemonic(KeyEvent.VK_S);
		regSted.setPreferredSize(new Dimension(55, 55));
		regData = new JButton("Registrer værdata", LastIkon.last("nyttvar24.gif"));
		regData.setHorizontalAlignment(SwingConstants.LEFT);
		regData.setMnemonic(KeyEvent.VK_V);
		finnSted = new JButton("Værdata for sted", LastIkon.last("steddata24.gif"));
		finnSted.setHorizontalAlignment(SwingConstants.LEFT);
		finnSted.setMnemonic(KeyEvent.VK_T);
		finnDato = new JButton("Værdata for dato", LastIkon.last("datodata24.gif"));
		finnDato.setHorizontalAlignment(SwingConstants.LEFT);
		finnDato.setMnemonic(KeyEvent.VK_D);
		gjennomsnittKnapp = new JButton("Gjennomsnittsverdier", LastIkon.last("gjennomsnitt24.gif"));
		gjennomsnittKnapp.setHorizontalAlignment(SwingConstants.LEFT);
		gjennomsnittKnapp.setMnemonic(KeyEvent.VK_G);
		ekstremKnapp = new JButton("Ekstremverdier", LastIkon.last("ekstrem24.gif"));
		ekstremKnapp.setHorizontalAlignment(SwingConstants.LEFT);
		ekstremKnapp.setMnemonic(KeyEvent.VK_E);
		utviklingKnapp = new JButton("Utvikling over tid", LastIkon.last("utvikling24.gif"));
		utviklingKnapp.setHorizontalAlignment(SwingConstants.LEFT);
		utviklingKnapp.setMnemonic(KeyEvent.VK_U);
		rekordKnapp = new JButton("Månedlige rekorder", LastIkon.last("rekorder24.gif"));
		rekordKnapp.setHorizontalAlignment(SwingConstants.LEFT);
		rekordKnapp.setMnemonic(KeyEvent.VK_R);
		regSted.addActionListener(mklytter);
		regData.addActionListener(mklytter);
		finnSted.addActionListener(mklytter);
		finnDato.addActionListener(mklytter);
		gjennomsnittKnapp.addActionListener(mklytter);
		ekstremKnapp.addActionListener(mklytter);
		utviklingKnapp.addActionListener(mklytter);
		rekordKnapp.addActionListener(mklytter);
		sidemeny.add(regSted);
		sidemeny.add(regData);
		sidemeny.add(finnSted);
		sidemeny.add(finnDato);
		sidemeny.add(gjennomsnittKnapp);
		sidemeny.add(ekstremKnapp);
		sidemeny.add(utviklingKnapp);
		sidemeny.add(rekordKnapp);
		sidemeny.setBackground(Color.LIGHT_GRAY);
		sidepanel.add(sidemeny,BorderLayout.PAGE_START);
		sidepanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		sidepanel.setBackground(Color.LIGHT_GRAY);
		sidewrapper.add(sidepanel);
		sidewrapper.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));
		sidewrapper.setBackground(Color.LIGHT_GRAY);
		
		startpanel = new JPanel(new BorderLayout());
		startpanel.add(new Bildepanel(), BorderLayout.CENTER);
		
		c.add(sidewrapper, BorderLayout.LINE_START);
		c.add(startpanel, BorderLayout.CENTER);
		setJMenuBar(menylinje);
		setVisible(true);
		setResizable(true);
		
		int bredde = Toolkit.getDefaultToolkit().getScreenSize().width;
		int høyde = Toolkit.getDefaultToolkit().getScreenSize().height;
		if(bredde < 800 || høyde < 600)
		{
			setSize((int)(bredde*0.9),(int)(høyde*0.9));
			setLocation(0, 0);
		}
		else
		{
			setSize(800,600);
			setLocation((bredde-800)/2, (høyde-600)/2);
		}
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
	 * @author Lars Smeby, Bård Skeie
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
			if(e.getSource() == gjennomsnittKnapp || e.getSource() == statGjennomsnitt)
			{
				c.remove(1);
				c.add(gsPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
			if(e.getSource() == ekstremKnapp || e.getSource() == statEkstrem)
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
			if(e.getSource() == rekordKnapp || e.getSource() == statRekord)
			{
				//TODO
//				c.remove(1);
//				c.add(###.getPanel(), BorderLayout.CENTER);
//				c.validate();
//				c.repaint();
			}
			if(e.getSource() == slettSted)
			{
				c.remove(1);
				c.add(ssPanel.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
			if(e.getSource() == filStart)
			{
				c.remove(1);
				c.add(startpanel, BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
			if(e.getSource() == filAvslutt)
			{
				Filhåndterer.lagreFil(sl);
				System.exit(0);
			}
			if(e.getSource() == filLagre)
			{
				Filhåndterer.lagreFil(sl);
				JOptionPane.showMessageDialog(getMetrovindu(), "Data er lagret til fil", "Lagret", JOptionPane.INFORMATION_MESSAGE);
			}
			if(e.getSource() == hjelpHjelp)
			{
				String hjelp = "Hjelp til programmet\n" +
								"\n" +
								"For å bruke programmet må du...";
				JOptionPane.showMessageDialog(getMetrovindu(), hjelp, "Hjelp", JOptionPane.INFORMATION_MESSAGE);
			}
			if(e.getSource() == hjelpOm)
			{
				String om = "Metrologiske data\n" +
							"\n" +
							"Prosjektoppgave i programutvikling våren 2011\n" +
							"Gruppe 3: Henrik Hermansen, Bård Skeie og Lars Smeby\n" +
							"Programversjon: 1.0\n" +
							"Sist oppdatdert: 15.05.2011\n" +
							"Høyskolen i Oslo";
				JOptionPane.showMessageDialog(getMetrovindu(), om, "Om", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/**
	 * Lytteklasse for vinduet. Sørger for at programmet lagrer og avslutter 
	 * riktig ved bruk av avslutt-knappen oppe til høyre i vinduet.
	 * @author Bård Skeie
	 *
	 */
	private class Vinduslytter extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			Filhåndterer.lagreFil(sl);
			System.exit(0);
		}
	}
}
