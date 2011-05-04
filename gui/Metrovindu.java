package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
@SuppressWarnings("serial")
public class Metrovindu extends JFrame
{	
	/**
	 * Knapper for å registrere sted, registrere data, finne sted og finne data.
	 */
	private JButton regSted, regData, finnSted, finnData;
	
	/**
	 * Oppretter panelet som er til høyre i hovedvindu.
	 */
	private JPanel hovedpanel;
	
	/**
	 * Container c er containeren som holder på vindusobjektet.
	 */
	private Container c;
	
	/**
	 * Hovedvinduet blir opprettet.
	 * UIManager.setLookAndFeel setter utseende på knapper etc lik windows utseende.
	 * Hovedvinduet bygges opp med menybar i toppen felt for "fil", "registrer","statistikk"
	 * og "hjelp".
	 * Menyen på venstre side i hovedvinduet blir opprettet samt plassen til panelvinduet 
	 * på venstre siden.
	 */
	public Metrovindu()
	{
		super("Meteorologiske data");
		
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			
		}
		
		MenyKnappelytter mklytter = new MenyKnappelytter();
		
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JMenuBar menylinje = new JMenuBar();
		JMenu filmeny = new JMenu("Fil");
		JMenu registrermeny = new JMenu("Registrer");
		JMenu statistikkmeny = new JMenu("Statistikk");
		JMenu hjelpmeny = new JMenu("Hjelp");
		filmeny.setMnemonic('f');
		registrermeny.setMnemonic('r');
		statistikkmeny.setMnemonic('s');
		hjelpmeny.setMnemonic('h');
		
		JMenuItem filLagre = new JMenuItem("Lagre");
		filLagre.setMnemonic('l');
		
		JMenuItem filAvslutt = new JMenuItem("Avslutt");
		filAvslutt.setMnemonic('a');
		filAvslutt.addActionListener(new ActionListener() {
			// TODO Fjerne/fikse dette
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"test","test",JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		});
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
		regData = new JButton("Register værdata");
		finnSted = new JButton("Værdata for sted");
		finnData = new JButton("Værdata for dato");
		regSted.addActionListener(mklytter);
		regData.addActionListener(mklytter);
		finnSted.addActionListener(mklytter);
		finnData.addActionListener(mklytter);
		sidemeny.add(regSted);
		sidemeny.add(regData);
		sidemeny.add(finnSted);
		sidemeny.add(finnData);
		sidepanel.add(sidemeny,BorderLayout.PAGE_START);
		sidepanel.setBorder(BorderFactory.createLoweredBevelBorder());
		sidewrapper.add(sidepanel);
		sidewrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		hovedpanel = new JPanel();
		hovedpanel.add(new JLabel("Dynamisk side som endres etter hvilken knapp du trykker på.", JLabel.CENTER));
		
		c.add(sidewrapper, BorderLayout.LINE_START);
		c.add(hovedpanel, BorderLayout.CENTER);
		setJMenuBar(menylinje);
		setVisible(true);
		setSize(800,600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fjernes etterhvert
	}
	
	/**
	 * Oppdaterer og setter hovedpanel/vindu til høyre.
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
				StedRegPanel p = new StedRegPanel();
				c.add(p.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
			if(e.getSource() == regData)
			{
				c.remove(1);
				VaerRegPanel p = new VaerRegPanel();
				c.add(p.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
		}
	}
}
