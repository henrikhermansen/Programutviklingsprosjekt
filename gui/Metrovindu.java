package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * @author Gruppe 3
 *
 */
public class Metrovindu extends JFrame implements Serializable
{
	private static final long serialVersionUID = 7406892436505608180L;
	private JButton regSted, regData, finnSted, finnData;
	private JPanel hovedpanel;
	private Container c;
	private Metrovindu mv = this;
	
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
		BorderLayout bl = new BorderLayout();
		c.setLayout(bl);
		
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
			@Override
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
		
		JPanel sidemeny = new JPanel();
		GridLayout knapperekke = new GridLayout(5,0);
		sidemeny.setLayout(knapperekke);
		regSted = new JButton("Registrer sted");
		regData = new JButton("Register v�rdata");
		finnSted = new JButton("V�rdata for sted");
		finnData = new JButton("V�rdata for dato");
		regSted.addActionListener(mklytter);
		regData.addActionListener(mklytter);
		finnSted.addActionListener(mklytter);
		finnData.addActionListener(mklytter);
		sidemeny.add(regSted);
		sidemeny.add(regData);
		sidemeny.add(finnSted);
		sidemeny.add(finnData);
		
		hovedpanel = new JPanel();
		hovedpanel.add(new JLabel("Dynamisk side som endres etter hvilken knapp du trykker p�.", JLabel.CENTER));
		
		c.add(sidemeny, BorderLayout.LINE_START);
		c.add(hovedpanel, BorderLayout.CENTER);
		setJMenuBar(menylinje);
		setVisible(true);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fjernes etterhvert
	}
	
	public void settHovedPanel()
	{
		c.remove(1);
		c.add(hovedpanel, BorderLayout.CENTER);
		c.validate();
		c.repaint();
	}
	
	private class MenyKnappelytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == regSted)
			{
				c.remove(hovedpanel);
				StedRegPanel p = new StedRegPanel(mv);
				c.add(p.getPanel(), BorderLayout.CENTER);
				c.validate();
				c.repaint();
			}
		}
	}
}
