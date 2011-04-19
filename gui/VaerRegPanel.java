package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import data.Sted;

public class VaerRegPanel
{
	private static final long serialVersionUID = 5190542424951093587L;
	private JPanel panel,panel2,panel3;
	private JComboBox fylke,sted;
	private JTextField minTemp, maxTemp, nedb�r, dato; // TODO fikse dato!
	private JButton registrer;
	private Metrovindu mv;	// Trenger vi denne?
	
	public VaerRegPanel(Metrovindu mv)
	{
		this.mv=mv;
		
		/**
		 * panel	GridLayout. Skal inneholde knapper og felt.
		 * panel2	BorderLayout. Skal inneholde panel for � kunne plassere dette p� PAGE_START.
		 * panel3	KANSKJE FJERNE!?
		 */
		panel=new JPanel(new GridLayout(0,2,3,3));
		
		panel2=new JPanel(new BorderLayout());
		panel2.add(panel,BorderLayout.PAGE_START);
		panel2.setBorder(BorderFactory.createEmptyBorder(25, 35, 10, 35));
		
		panel3=new JPanel(new BorderLayout());
		panel3.add(panel2,BorderLayout.PAGE_START);
		panel3.setBorder(BorderFactory.createRaisedBevelBorder());
		
		/**
		 * handlingslytter	En ActionListener for JComboBox'ene og JButton.
		 * skrivefeltlytter	En KeyListener for skrivefeltene.
		 */
		HandlingsLytter handlingslytter=new HandlingsLytter();
		SkrivefeltLytter skrivefeltlytter=new SkrivefeltLytter();
		
		/**
		 * En JComboBox med liste over alle fylkene. handlingslytter legges p� som ActionListener.
		 */
		fylke=new JComboBox(Sted.fylkesliste);
		fylke.addActionListener(handlingslytter);
		
		/**
		 * En JComboBox med liste over stedene i fylket. handlingslytter legges p� som ActionListener. Den settes false inntil et fylke er valgt.
		 */
		sted=new JComboBox();
		sted.addActionListener(handlingslytter);
		sted.setEnabled(false);
		
		/**
		 * Oppretter tekstfelt for v�rdataene.
		 */
		minTemp=new JTextField(10);
		maxTemp=new JTextField(10);
		nedb�r=new JTextField(10);
		
		/**
		 * Alle tekstfeltene settes false inntil et fylke og sted er valgt.
		 */
		minTemp.setEditable(false);
		maxTemp.setEditable(false);
		nedb�r.setEditable(false);
		
		/**
		 * Alle tekstfeltene f�r skrivefeltlytter som KeyListener.
		 */
		minTemp.addKeyListener(skrivefeltlytter);
		maxTemp.addKeyListener(skrivefeltlytter);
		nedb�r.addKeyListener(skrivefeltlytter);
		
		/**
		 * Oppretter knappen for � utf�re en registrering av v�rdata.
		 */
		registrer=new JButton("Registrer v�rdata");
		registrer.setEnabled(false);
		registrer.addActionListener(handlingslytter);
		
		/**
		 * Legger inn alt av innhold i panelet.
		 */
		panel.add(new JLabel("Velg fylke"));
		panel.add(fylke);
		panel.add(new JLabel("Velg sted"));
		panel.add(sted);
		panel.add(new JLabel("Minimumstemperatur"));
		panel.add(minTemp);
		panel.add(new JLabel("Maksimumstemperatur"));
		panel.add(maxTemp);
		panel.add(new JLabel("Nedb�rsmengde i mm"));
		panel.add(nedb�r);
		panel.add(new JLabel(""));
		panel.add(registrer);
	}
	
	/**
	 * Returnerer det panelet som skal brukes i hovedvinduet.
	 * @return et objekt av typen JPanel.
	 */
	public JPanel getPanel()
	{
		return panel2;
	}
	
	/**
	 * Privat lytterklasse av typen ActionListener.
	 */
	private class HandlingsLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==fylke)
				return;
			if(e.getSource()==registrer)
				return;
		}
	}
	
	/**
	 * Privat lytterklasse av typen KeyListener.
	 */
	private class SkrivefeltLytter implements KeyListener
	{
		public void keyPressed(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
		}
		public void keyReleased(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
		}
		public void keyTyped(KeyEvent arg0)
		{
			// TODO Auto-generated method stub
		}
	}
}
