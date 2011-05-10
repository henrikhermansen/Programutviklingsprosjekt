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
	 * @author Lars Smeby
	 * Starter hovedtråd og oppretter hovedvinduet.
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Her laster vi inn klassene som er lagret på fil og sender dem med som parametere til Metrovindu
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