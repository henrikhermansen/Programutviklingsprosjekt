/**
 * Inneholder klassen RunMetrodata.
 * @author Lars Smeby
 * @since 28.04.2011
 * @updated 18.05.2011
 * @version	1
 */
package gui;

import data.*;

import java.awt.EventQueue;

/**
 *	Klassen inneholder programmets main-metode og laster inn data
 *	fra fil f�r det oppretter hovedvinduet.
 */
public class RunMetrodata {

	/**
	 * Starter hovedtr�d og oppretter hovedvinduet.
	 * @author Lars Smeby
	 * @param args
	 */
	public static void main(String[] args)
	{
		final Stedliste sl = Filh�ndterer.lastInnFil();
		
		EventQueue.invokeLater(new Runnable() {
												@Override
												public void run()
												{
													new Metrovindu(sl);
												}
											});
	} // end of main(...)
} // end of class RunMetrodata