package ca.umontreal.pqueues;

import java.util.Comparator;
import java.lang.Comparable;
import java.lang.ClassCastException;
import java.lang.IllegalArgumentException;

/**
* AbstractPriorityQueue is an abstract class to simpligy implementation
*    of ADT Priority Queues
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {

    //----- inner PQEntry class -----
    protected static class PQEntry<K,V> implements Entry<K,V> {
	private K k; // key
	private V v; // value
	public PQEntry( K k, V v ) { this.k = k; this.v = v; }
	// methods to realize the Entry interface
	public K getKey() { return this.k; }
	public V getValue() { return this.v; }
	// utilities
	protected void setKey( K k ) { this.k = k; }
	protected void setValue( V v ) { this.v = v; }
	public String toString() { return "(" + this.getKey() + "," + this.getValue() + ")"; }
    //to implement the "Comparable <Entry <K,V>>" interface:
    //attention：必须是Entry《K,V> 而不是PQ《K,V>！！！！！！！！！！！！！！！不知道为什么。。
    //懒得管了，语言细节罢了。
    @Override
    public int compareTo(Entry<K, V> o) {
        return new DefaultComparator<K>().compare(this.getKey(), o.getKey());
    }
    } //----- end inner class PQEntry -----

    // attributes
    private Comparator<K> comp; // comparator defining the ordering of the keys
    // constructor with a given comparator to order keys
    protected AbstractPriorityQueue( Comparator<K> comp ) { this.comp = comp; }
    // constructor with default comparator based on natural ordering
    protected AbstractPriorityQueue() { this( new DefaultComparator<K>() ); }
    // compare two entries according to their keys
    protected int compare( Entry<K,V> a, Entry<K,V> b ) { return this.comp.compare( a.getKey(), b.getKey() ); }
    // determine whether a key is valid
    protected boolean checkKey( K key ) throws IllegalArgumentException {
	try {
	    return( this.comp.compare( key, key ) == 0 ); // see if key can be compared to itself
	} catch( ClassCastException e ) {
	    throw new IllegalArgumentException( "Incompatible key" );
	}
    }
    // test if PQ is empty
    public boolean isEmpty() { return this.size() == 0; }
}
