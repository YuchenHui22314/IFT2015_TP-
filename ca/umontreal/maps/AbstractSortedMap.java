package ca.umontreal.maps;

import java.util.Comparator;
import ca.umontreal.pqueues.DefaultComparator;
import java.lang.ClassCastException;
import java.lang.IllegalArgumentException;

/**
* AbstractSortedMap is an abstract base class for the ADT SortedMap
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public abstract class AbstractSortedMap<K,V> extends AbstractMap<K,V> implements SortedMap<K,V> {
    // comparator defining the ordering of the keys
    private Comparator<K> comp;

    // construct with given comparator
    protected AbstractSortedMap( Comparator<K> c ) { this.comp = c; }
    // construct with default comparator using natural ordering
    protected AbstractSortedMap() { this( new DefaultComparator<K>() ); }

    // compare two entries using keys
    protected int compare( Entry<K,V> a, Entry<K,V> b ) {
	return this.comp.compare( a.getKey(), b.getKey() );
    }
    // compare key with an entry's key
    protected int compare( K a, Entry<K,V> b ) { return this.comp.compare( a, b.getKey() ); }
    // compare an entry's key with a key
    protected int compare( Entry<K,V> a, K b ) { return this.comp.compare( a.getKey(), b ); }
    // compare two keys
    protected int compare( K a, K b ) { return this.comp.compare( a, b ); }

    // validate a key
    protected boolean checkKey( K key ) throws IllegalArgumentException {
	try {
	    return( comp.compare( key, key ) == 0 ); // check if it can be compared to itself
	} catch( ClassCastException e ) {
	    throw new IllegalArgumentException( "Incompatible key" );
	}
    }
}
