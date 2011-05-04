package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import data.Sted;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class VaerRegPanel extends MetroPanel
{
//	private JPanel panel3;
	private JComboBox fylke,sted;
	@SuppressWarnings("unused")
	private JTextField minTemp, maxTemp, nedb�r, dato; // TODO fikse dato!
	private JButton registrer;
	
	public VaerRegPanel()
	{
		super();
		
//		panel3=new JPanel(new BorderLayout());
//		panel3.add(panel2,BorderLayout.PAGE_START);
//		panel3.setBorder(BorderFactory.createRaisedBevelBorder());
		
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
		grid.add(new JLabel("Velg fylke"));
		grid.add(fylke);
		grid.add(new JLabel("Velg sted"));
		grid.add(sted);
		grid.add(new JLabel("Minimumstemperatur"));
		grid.add(minTemp);
		grid.add(new JLabel("Maksimumstemperatur"));
		grid.add(maxTemp);
		grid.add(new JLabel("Nedb�rsmengde i mm"));
		grid.add(nedb�r);
		grid.add(new JLabel(""));
		grid.add(registrer);
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
