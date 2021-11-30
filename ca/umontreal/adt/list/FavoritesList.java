package ca.umontreal.adt.list;

import java.util.Iterator;
import java.lang.Iterable;
import java.lang.IllegalArgumentException;

/**
* FavoritesList implements a list of elements ordered according to access frequencies
* 
*     performing n accesses executes in O(n^2) (like insertion sort)
*
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class FavoritesList<E extends Comparable<E>> {
    //----- inner class Item
    protected static class Item<E extends Comparable<E>> implements Comparable<Item<E>> {
	private E value;
	private int count = 0;
	public Item( E val ) { this.value = val; }
	public int getCount() { return this.count; }
	public E getValue() { return this.value; }
	public void increment() { this.count++; }
	public int compareTo( Item<E> other ) {
	    return this.value.compareTo( other.getValue() );
	}
    } //----- end inner class Item
    PositionalList<Item<E>> list = new LinkedPositionalList<>(); // the item list
    public FavoritesList() {} // construct initially empty list
    // developer's utilities
    // shorthand notation to get the value of an item stored at Position p
    protected E value( Position<Item<E>> p ) { return p.getElement().getValue(); }
    // shorthand notation to get the count of an item stored at Position p
    protected int count( Position<Item<E>> p ) { return p.getElement().getCount(); }
    // search the Position with element's value equal to e, null if not found
    protected Position<Item<E>> findPosition( E e ) {
	Position<Item<E>> walk = this.list.first();
	while( walk != null && !e.equals( this.value( walk ) ) )
	    walk = this.list.after( walk );
	return walk;
    }
    // move item at Position p earlier in the list based on access count in O(n)
    protected void moveUp( Position<Item<E>> p ) {
	int cnt = this.count( p ); // get count at Position p
	Position<Item<E>> walk = p;
	while( walk != list.first() && this.count( this.list.before( walk ) ) < cnt )
	    walk = this.list.before( walk ); // found smaller count ahead of item
	if( walk != p )
	    this.list.addBefore( walk, this.list.remove( p ) );
    }
    // public methods
    // return number of items in the list
    public int size() { return this.list.size(); }
    // return true if the list is empty
    public boolean isEmpty() { return this.list.isEmpty(); }
    // access element e (possibly new), and increase its access count
    public void access( E e ) {
	Position<Item<E>> p = this.findPosition( e ); // try locate element e
	if( p == null )
	    p = this.list.addLast( new Item<E>( e ) ); // if new, place at end
	p.getElement().increment(); // increment count
	this.moveUp( p ); // move forward if needed
    }
    // remove element equal to e from the list of favorites, if found
    public void remove( E e ) {
	Position<Item<E>> p = this.findPosition( e );
	if( p != null )
	    this.list.remove( p );
    }
    // return an iterable collection of the k most frequently accessed elements
    public Iterable<E> getFavorites( int k ) throws IllegalArgumentException {
	if( k < 0 || k > this.size() )
	    throw new IllegalArgumentException( "Invalid k = " + k );
	PositionalList<E> result = new LinkedPositionalList<>();
	Iterator<Item<E>> iter = this.list.iterator();
	for( int j = 0; j < k; j++ )
	    result.addLast( iter.next().getValue() );
	return result;
    }
}
