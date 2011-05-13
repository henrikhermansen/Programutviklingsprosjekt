package gui;

import data.*;

import java.awt.EventQueue;

/**
 *	@author		Gruppe 3
 *	@version	1
 *	@since		1.6
 */
public class RunMetrodata {

	/**
	 * Starter hovedtråd og oppretter hovedvinduet.
	 * @author Lars Smeby
	 * @param args
	 */
	public static void main(String[] args)
	{
		final Stedliste sl = Filhåndterer.lastInnFil();
		
		EventQueue.invokeLater(new Runnable() {
												@Override
												public void run()
												{
													new Metrovindu(sl);
												}
											});
	}
}