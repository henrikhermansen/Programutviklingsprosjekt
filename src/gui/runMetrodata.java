package gui;

import java.awt.EventQueue;

/**
 * @author Gruppe 3
 *
 */
public class RunMetrodata {

	/**
	 * Starter hovedtråd og oppretter hovedvinduet
	 * @param args
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
												@Override
												public void run()
												{
													new Metrovindu();
												}
											});
	}
}