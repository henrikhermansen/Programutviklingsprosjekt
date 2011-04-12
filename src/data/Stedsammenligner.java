package data;

import java.io.Serializable;
import java.text.*;
import java.util.Comparator;

/**
 * @author	Gruppe 3
 * @version	1
 * @since	1.6
 */
public class Stedsammenligner implements Serializable, Comparator<Sted>
{
	private static final long serialVersionUID = 1295791362365552918L;
	private final String rekkef�lge = "<\0<0<1<2<3<4<5<6<7<8<9" +
								"<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j" +
								"<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t" +
								"<U,u<V,v<W,w<X,x<Y,y<Z,z<�,�<�,�<�=AA,�=aa;AA,aa";
	private RuleBasedCollator kollator;
	private boolean fylkes�k;
	
	public Stedsammenligner(boolean fs)
	{
		fylkes�k = fs;
		
		try
		{
			kollator = new RuleBasedCollator(rekkef�lge);
		}
		catch (ParseException pe)
		{
			System.out.println("Feil i rekkef�lgestreng for kollator");
			System.exit(1);
		}
	}

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
}