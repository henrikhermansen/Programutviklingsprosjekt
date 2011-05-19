/**
 * Inneholder klassen Stedsammenligner.
 * @author Lars Smeby
 * @since 28.04.2011
 * @version	1 18.05.2011
 */
package data;

import java.text.*;
import java.util.Comparator;

import logic.SkrivMelding;

/**
 * Klassen implementerer en Comparator, og brukes til � sortere steder etter navn.
 */
public class Stedsammenligner implements Comparator<Sted>
{
	private static final long serialVersionUID = 1295791362365552918L;
	/**
	 * Rekkef�lge for kollatoren som brukes til � bestemme sortering p� stedsnavn
	 */
	private final String rekkef�lge = "<\0<0<1<2<3<4<5<6<7<8<9" +
								"<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j" +
								"<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t" +
								"<U,u<V,v<W,w<X,x<Y,y<Z,z<�,�<�,�<�=AA,�=aa;AA,aa";
	/**
	 * Kollator som brukes til s�k
	 */
	private RuleBasedCollator kollator;
	/**
	 * Avgj�r om fylket stedet ligger i skal v�re med p� � avgj�re sorteringen
	 */
	private boolean fylkes�k;
	
	/**
	 * Konstrukt�r
	 * Skrevet av: Lars Smeby
	 * @param fs	Avgj�r om fylket stedet ligger i skal v�re med i betraktningen
	 */
	public Stedsammenligner(boolean fs)
	{
		fylkes�k = fs;
		
		try
		{
			kollator = new RuleBasedCollator(rekkef�lge);
		}
		catch (ParseException pe)
		{
			SkrivMelding.skriv("Ukjent programfeil (L002)/E", null);
			System.exit(1);
		}
	}
	
	/**
	 * Sammenlikningsmetoden implementert fra Comparator-interfacet
	 * Skrevet av: Lars Smeby
	 */
	@Override
	public int compare(Sted a, Sted b)
	{
		int verdi = kollator.compare(a.getNavn(), b.getNavn());
		if(!fylkes�k)
			return verdi;
		if(verdi != 0)
			return verdi;
		return kollator.compare(Integer.toString(a.getFylke()), Integer.toString(b.getFylke()));
	}
} // end of class Stedsammenligner