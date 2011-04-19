package gui;

import data.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class StedRegPanel
{
	public JPanel panel;
	private JTextField navn;
	private JComboBox fylke;
	JButton regSted;
	Stedliste sl;
	private Metrovindu mv;
	
	public StedRegPanel(Metrovindu mv)
	{
		sl = new Stedliste();
		this.mv = mv;
		
		panel = new JPanel();
		navn = new JTextField();
		fylke = new JComboBox(Sted.fylkesliste);
		regSted = new JButton("Registrer sted");
		
		RegStedLytter regStedLytter = new RegStedLytter();
		regSted.addActionListener(regStedLytter);
		navn.addActionListener(regStedLytter);
		
		GridLayout layout = new GridLayout(3,2);
		panel.setLayout(layout);
		panel.add(new JLabel("Stedsnavn:"));
		panel.add(navn);
		panel.add(new JLabel("Fylke:"));
		panel.add(fylke);
		panel.add(new JLabel(""));
		panel.add(regSted);
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
	
	private class RegStedLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String n = navn.getText();
			if (n == null || n.length() < 2)
			{
				JOptionPane.showMessageDialog(panel, "Skriv inn et stedsnavn", "Ufullstendig informasjon", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (sl.finnSted(n, fylke.getSelectedIndex()) != null)
			{
				JOptionPane.showMessageDialog(panel, "Dette stedet eksisterer allerede i dette fylket", "Eksisterende stedsnavn", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			sl.settInn(new Sted(n, fylke.getSelectedIndex()));
			JOptionPane.showMessageDialog(panel, n+" ble registrert i "+fylke.getSelectedItem().toString(), "Sted registrert", JOptionPane.INFORMATION_MESSAGE);
			mv.settHovedPanel();
		}
	}
}
