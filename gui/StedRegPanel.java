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
	private JPanel panel;
	private JTextField navn;
	private JComboBox fylke;
	JButton regSted;
	Stedliste sl;
	
	public StedRegPanel(Metrovindu mv)
	{
		sl = new Stedliste();
		
		panel = new JPanel(new BorderLayout());
		navn = new JTextField(20);
		fylke = new JComboBox(Sted.fylkesliste);
		regSted = new JButton("Registrer sted");
		
		RegStedLytter regStedLytter = new RegStedLytter();
		regSted.addActionListener(regStedLytter);
		navn.addActionListener(regStedLytter);
		
		JPanel grid = new JPanel();

		grid.setLayout(new GridLayout(0,2,3,3));
		grid.add(new JLabel("Stedsnavn:"));
		grid.add(navn);
		grid.add(new JLabel("Fylke:"));
		grid.add(fylke);
		grid.add(new JLabel(""));
		grid.add(regSted);
		
		panel.add(grid, BorderLayout.PAGE_START);
		panel.setBorder(BorderFactory.createEmptyBorder(25, 35, 10, 35));
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
			navn.setText("");
			fylke.setSelectedIndex(0);
		}
	}
}
