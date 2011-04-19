package gui;

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
	
	public StedRegPanel()
	{
		panel = new JPanel();
		navn = new JTextField();
		String[] fylker = {"Østfold","Akershus","Oslo","Hedmark","Oppland","Buskerud","Vestfold",
				"Telemark","Aust-Agder","Vest-Agder","Rogaland","Hordaland",
				"Sogn og Fjordane","Møre og Romsdal","Sør-Trøndelag","Nord-Trøndelag",
				"Norland","Troms","Finnamark"};
		fylke = new JComboBox(fylker);
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
	
	private class RegStedLytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String n = navn.getText();
			if (n == null || n.length() < 2)
			{
				JOptionPane.showMessageDialog(panel, "Skriv inn et stedsnavn", "Ufullstendig informasjon", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
