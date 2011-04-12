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
	
	public Metrovindu()
	{
		super("Meteorologiske data");
		
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			
		}
		
		Container c = getContentPane();
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
		sidemeny.add(new JButton("Registrer sted"));
		sidemeny.add(new JButton("Register værdata"));
		sidemeny.add(new JButton("Værdata for sted"));
		sidemeny.add(new JButton("Værdata for dato"));
		
		c.add(sidemeny, BorderLayout.LINE_START);
		c.add(new JLabel("Dynamisk side som endres etter hvilken knapp du trykker på", JLabel.CENTER), BorderLayout.CENTER);
		setJMenuBar(menylinje);
		setVisible(true);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fjernes etterhvert
	}

}
