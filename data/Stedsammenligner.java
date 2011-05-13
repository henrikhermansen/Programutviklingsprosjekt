package data;

import java.text.*;
import java.util.Comparator;

/**
 * @author	Gruppe 3
 * @version	1
 * @since	1.6
 */
public class Stedsammenligner implements Comparator<Sted>
{
	private static final long serialVersionUID = 1295791362365552918L;
	/**
	 * Rekkefølge for kollatoren som brukes til å bestemme sortering på stedsnavn
	 */
	private final String rekkefølge = "<\0<0<1<2<3<4<5<6<7<8<9" +
								"<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j" +
								"<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t" +
								"<U,u<V,v<W,w<X,x<Y,y<Z,z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa";
	/**
	 * Kollator som brukes til søk
	 */
	private RuleBasedCollator kollator;
	/**
	 * Avgjør om fylket stedet ligger i skal være med på å avgjøre sorteringen
	 */
	private boolean fylkesøk;
	
	/**
	 * Konstruktør
	 * @author Lars Smeby
	 * @param fs	Avgjør om fylket stedet ligger i skal være med i betraktningen
	 */
	public Stedsammenligner(boolean fs)
	{
		fylkesøk = fs;
		
		try
		{
			kollator = new RuleBasedCollator(rekkefølge);
		}
		catch (ParseException pe)
		{
			System.out.println("Feil i rekkefølgestreng for kollator");
			System.exit(1);
		}
	}
	
	/**
	 * Sammenlikningsmetoden implementert fra Comparator-interfacet
	 * @author Lars Smeby
	 */
	@Override
	public int compare(Sted a, Sted b)
	{
		int verdi = kollator.compare(a.getNavn(), b.getNavn());
		if(!fylkesøk)
			return verdi;
		if(verdi != 0)
			return verdi;
		return kollator.compare(Integer.toString(a.getFylke()), Integer.toString(b.getFylke()));
	}
}