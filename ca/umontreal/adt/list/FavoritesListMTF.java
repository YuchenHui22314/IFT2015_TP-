package ca.umontreal.adt.list;

import java.util.Iterator;
import java.lang.Iterable;
import java.lang.IllegalArgumentException;

/**
* FavoritesListMTF implements a list of elements ordered according to access frequencies
* 
*     performing n accesses executes in O(k*n) Move-to-first strategy
*
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class FavoritesListMTF<E extends Comparable<E>> extends FavoritesList<E> {

    // move item at Position p at the front of the list
    protected void moveUp( Position<Item<E>> p ) {
	if( p != this.list.first() )
	    this.list.addFirst( this.list.remove( p ) );
    }
    // return an iterable collection of the k most frequently accessed elements
    public Iterable<E> getFavorites( int k ) throws IllegalArgumentException {
	if( k < 0 || k > this.size() )
	    throw new IllegalArgumentException( "Invalid k = " + k );
	// make a copy of the original list
	PositionalList<Item<E>> tmp = new LinkedPositionalList<>();
	for( Item<E> item : this.list )
	    tmp.addLast( item );
	// repeat find, report, and remove element with largest count
	PositionalList<E> result = new LinkedPositionalList<>();
	for( int j = 0; j < k; j++ ) { // O(k)
	    Position<Item<E>> topPos = tmp.first();
	    Position<Item<E>> walk = tmp.after( topPos );
	    while( walk != null ) { // O(n)
		if( this.count( walk ) > this.count( topPos ) )
		    topPos = walk;
		walk = tmp.after( walk );
	    }
	    // element with highest count found
	    result.addLast( this.value( topPos ) );
	    tmp.remove( topPos );
	}
	return result;
    }
}
