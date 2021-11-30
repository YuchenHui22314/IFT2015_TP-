package ca.umontreal.pqueues;

import java.util.Comparator;
import java.lang.ClassCastException;
import java.lang.IllegalArgumentException;
import ca.umontreal.adt.list.ArrayList;
/**
* HeapPriorityQueue is an implementation of the ADT Priority Queue
*    using a heap implemented in an ArrayList
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

    // primary storage in an ArrayList
    protected ArrayList<Entry<K,V>> heap = new ArrayList<>();

    // construct an empty PQ based on the natural ordering of the keys
    public HeapPriorityQueue() { super(); }
    // construct an empty PQ based on a given comparator to order the keys
    public HeapPriorityQueue( Comparator<K> comp ) { super( comp ); }
    // construct a PQ with given entries
    public HeapPriorityQueue( K[] keys, V[] values ) {
	super();
	for( int j = 0; j < Math.min( keys.length, values.length ); j++ )
	    this.heap.add( new PQEntry<>( keys[j], values[j] ) );
	this.heapify();
    }

    // utilities
    protected int parent( int j ) { return ( j-1 ) / 2; } // truncating division
    protected int left( int j ) { return 2 * j + 1; }
    protected int right( int j ) { return 2 * j + 2; }
    protected boolean hasLeft( int j ) { return this.left( j ) < this.heap.size(); }
    protected boolean hasRight( int j ) { return this.right( j ) < this.heap.size(); }

    // bottom-up transformation of the heap in O(n)
    protected void heapify() {
	int startIndex = this.parent( this.size() - 1 ); // start at parent of the last entry
	for( int j = startIndex; j >= 0; j-- ) // loop until the root
	    this.sink( j );
    }

    // swap entries i and j of the ArrayList
    protected void swap( int i, int j ) {
	Entry<K,V> tmp = this.heap.get( i );
	this.heap.set( i, this.heap.get( j ) );
	this.heap.set( j, tmp );
    }

    // move entry j higher, if necessary, to restore the heap property in O(log n)
    protected void swim( int j ) {
	while( j > 0 ) { // continue until reaching the root (or break)
	    int p = this.parent( j );
	    if( compare( this.heap.get( j ), this.heap.get( p ) ) >= 0 ) break; // heap property restored
	    this.swap( j, p );
	    j = p; // continue from parent
	}
    }

    // move entry j lower, if necessary, to restore the heap property in O(log n)
    protected void sink( int j ) {
	while( this.hasLeft( j ) ) { // continue to bottom (or break)
	    int leftIndex = this.left( j );
	    int smallChildIndex = leftIndex; // although right may be smaller
	    if( this.hasRight( j ) ) {
		int rightIndex = this.right( j );
		if( this.compare( this.heap.get( leftIndex ), this.heap.get( rightIndex ) ) > 0 )
		    smallChildIndex = rightIndex; // right child is smaller
	    }
	    if( this.compare( this.heap.get( smallChildIndex ), this.heap.get( j ) ) >= 0 )
		break;
	    this.swap( j, smallChildIndex );
	    j = smallChildIndex; // continue at the smallest child
	}
    }

    // interface methods
    
    // return the number of entries in the PQ
    public int size() { return this.heap.size(); }
    // return but do not remove an entry of minimal key (if any)
    
    public Entry<K,V> min() {
	if( this.heap.isEmpty() ) return null;
	return this.heap.get( 0 );
    }

    // insert a (key, value) entry and return it in O(log n)
    public Entry<K,V> insert( K k, V v ) throws IllegalArgumentException {
	this.checkKey( k ); // key-checking (could throw exception)
	Entry<K,V> newest = new PQEntry<>( k, v );
	this.heap.add( newest ); // add to the end of the list
	this.swim( this.heap.size() - 1 ); // swim the newly added entry
	return newest;
    }

    // remove and return an entry of minimal key (if any) in O(log n)
    public Entry<K,V> removeMin() {
	if( this.heap.isEmpty() ) return null;
	Entry<K,V> ret = this.heap.get( 0 );
	this.swap( 0, this.heap.size() - 1 ); // exchange first and last entries
	this.heap.remove( this.size() - 1 ); // and remove it from list
	this.sink( 0 ); // sink the new root
	return ret;
    }

}