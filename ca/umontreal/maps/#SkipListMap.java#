package ca.umontreal.maps;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Random;
import java.util.Comparator;
import java.util.ArrayList;
import ca.umontreal.pqueues.DefaultComparator;

/**
* SkipListMap is a class that implements the ADT SortedMap
*   using a skip list
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class SkipListMap<K,V> extends AbstractSortedMap<K,V> {
    
    //----- inner class SkipNode -----
    protected static class SkipNode<K,V> {
	// attributes
	protected Entry<K,V> element; // reference to the element
	protected SkipNode<K,V> prev; // reference to the previous SkipNode
	protected SkipNode<K,V> next; // reference to the next SkipNode
	protected SkipNode<K,V> belo; // reference to the below SkipNode
	protected SkipNode<K,V> abov; // reference to the above SkipNode
	
	// constructor
	protected SkipNode( Entry<K,V> element,
			    SkipNode<K,V> prev, SkipNode<K,V> next,
			    SkipNode<K,V> belo, SkipNode<K,V> abov ) {
	    this.element = element;
	    this.prev = prev;
	    this.next = next;
	    this.belo = belo;
	    this.abov = abov;
	}
	// getters & setters
	public Entry<K,V> getElement() { return this.element; }
	public K getKey() { return this.element.getKey(); }
	public V getValue() { return this.element.getValue(); }
	public V setValue( V value ) { return ((MapEntry<K,V>)this.element).setValue( value ); }
	public void setElement( Entry<K,V> element ) { this.element = element; }
	protected SkipNode<K,V> getPrevious() { return this.prev; }
	protected SkipNode<K,V> getNext() { return this.next; }
	protected SkipNode<K,V> getBelow() { return this.belo; }
	protected SkipNode<K,V> getAbove() { return this.abov; }
	protected void setPrevious( SkipNode<K,V> prev ) { this.prev = prev; }
	protected void setNext( SkipNode<K,V> next ) { this.next = next; }
	protected void setBelow( SkipNode<K,V> belo ) { this.belo = belo; }
	protected void setAbove( SkipNode<K,V> abov ) { this.abov = abov; }
	public String toString() { return "element = " + this.getElement() + " )"; }
    } //----- end inner SkipNode class

    // attributes
    private SkipNode<K,V> start;
    private int n; // number of elements
    private int h; // height
    private K minusInfinite;
    private K plusInfinite;
    private Random coinFlip = new Random();

    // initializor
    public void init( K minusInfinite, K plusInfinite ) {
	this.minusInfinite = minusInfinite;
	this.plusInfinite = plusInfinite;
	SkipNode<K,V> leftTop = new SkipNode<>( new MapEntry( minusInfinite, null ), null, null, null, null );
	SkipNode<K,V> rightTop = new SkipNode<>( new MapEntry( plusInfinite, null ),  leftTop, null, null, null );
	SkipNode<K,V> leftBottom = new SkipNode<>( new MapEntry( minusInfinite, null ), null, null, null, leftTop );
	SkipNode<K,V> rightBottom = new SkipNode<>( new MapEntry( plusInfinite, null ), leftBottom, null, null, rightTop );
	leftTop.setNext( rightTop );
	leftTop.setBelow( leftBottom );
	leftBottom.setNext( rightBottom );
	rightTop.setBelow( rightBottom );
	this.start = leftTop;
	this.n = 0;
	this.h = 1;
	this.coinFlip.setSeed( 34567 );
    }

    // constructor
    public SkipListMap( K minusInfinite, K plusInfinite ) {
	super();
	this.init( minusInfinite, plusInfinite );
    }
    public SkipListMap( K minusInfinite, K plusInfinite, Comparator<K> comp ) {
	super( comp );
	this.init( minusInfinite, plusInfinite );
    }

    // utilities
    private SkipNode<K,V> getFirst() {
	SkipNode<K,V> getDown = this.start.getBelow(); // go the left sentinel's node below start
	// get down until level 0
	while( getDown.getBelow() != null ) getDown = getDown.getBelow();
	// return the next skip node
	return getDown.getNext();
    }
    private SkipNode<K,V> getLast() {
	SkipNode<K,V> getDown = this.start.getNext().getBelow(); // go the right sentinel's node below start
	while( getDown.getBelow() != null ) getDown = getDown.getBelow();
	// return the previous skip node
	return getDown.getPrevious();
    }
    private SkipNode<K,V> skipSearch( K key ) {
	SkipNode<K,V> p = this.start;
	while( p.getBelow() != null ) {
	    p = p.getBelow();
	    while( compare( key, p.getNext().getKey() ) >= 0 ) {
		p = p.getNext();
	    }
	}
	return p;
    }
    private void increaseHeight() {
	SkipNode<K,V> oldLeft = this.start;
	SkipNode<K,V> oldRight = this.start.getNext();
	SkipNode<K,V> newLeft = new SkipNode<>( new MapEntry( this.minusInfinite, null ), null, null, oldLeft, null );
	SkipNode<K,V> newRight = new SkipNode<>( new MapEntry( this.plusInfinite, null ), newLeft, null, oldRight, null );
	newLeft.setNext( newRight );
	oldLeft.setAbove( newLeft );
	oldRight.setAbove( newRight );
	this.h++;
	this.start = newLeft;
    }
    private SkipNode<K,V> insertAfterAbove( SkipNode<K,V> p, SkipNode<K,V> q, Entry<K,V> entry ) {
	SkipNode<K,V> novel = new SkipNode<>( entry, p, p.getNext(), q, null );
	p.getNext().setPrevious( novel );
	p.setNext( novel );
	if( q != null ) q.setAbove( novel );
	return novel;
    }
    private SkipNode<K,V> skipInsert( K key, V value ) {
	SkipNode<K,V> p = skipSearch( key );
	if( p.getKey() == key ) return p;
	SkipNode<K,V> q = null;
	int i = -1; // current height
	do {
	    i++;
	    if( i >= this.h ) this.increaseHeight();
	    q = insertAfterAbove( p, q, new MapEntry( key, value ) );
	    while( p.getAbove() == null ) p = p.getPrevious();
	    p = p.getAbove();
	} while( this.coinFlip.nextBoolean() == true );
	this.n++;
	return q;
    }
    private void decreaseHeight() {
	SkipNode<K,V> p = this.start.getBelow();
	while( p.getBelow() != null && p.getNext().getValue() == this.plusInfinite ) {
	    this.start = p;
	    this.h--;
	    p = p.getBelow();
	}
    }
    private V skipRemove( K key ) {
	SkipNode<K,V> p = skipSearch( key );
	int level = 0;
	if( p.getKey() != key ) return null; // not found )
	V ret = p.getValue();
	// destroy tower
	while( p != null ) {
	    p.getPrevious().setNext( p.getNext() );
	    p.getNext().setPrevious( p.getPrevious() );
	    p.setNext( p );
	    p = p.getAbove();
	    level++;
	}
	this.n--;
	if( level == this.h ) this.decreaseHeight();
	return ret;
    }
    // return the entry in SkipNode p, or null if p is null or a sentinel
    private Entry<K,V> safeEntry( SkipNode<K,V> p ) {
	if( p == null ) return null;
	if( p.getValue() == null ) return null;
	return p.getElement();
    }
    
    // public methods
    public int size() { return this.n; }
    public boolean isEmpty() { return this.n == 0; }
    // return the value associated with the specified key, or null if not found
    public V get( K key ) {
	SkipNode<K,V> p = this.skipSearch( key );
	if( compare( key, p.getKey() ) != 0 ) return null; // no match
	return p.getValue();
    }
    // check if an entry with given key exists
    public boolean containsKey( K key ) { return this.get( key ) != null; } 
    // associate the pair key-value, replacing existing value if any
    public V put( K key, V value ) {
	SkipNode<K,V> p = this.skipSearch( key );
	if( compare( key, p.getKey() ) == 0 ) // match exists
	    return p.setValue( value );
	this.skipInsert( key, value ); // otherwise add new entry
	return null; // and return null
    }
    // remove the entry with specified key, if any, return its value
    public V remove( K key ) { return this.skipRemove( key ); }

    // public methods on getting bound entries
    // return the entry having the least key, or null if map is empty
    public Entry<K,V> firstEntry() { return safeEntry( this.getFirst() ); }
    // return the entry having the greatest key, or null if map is empty
    public Entry<K,V> lastEntry() { return safeEntry( this.getLast() ); }
    // return the entry with least key greater than or equal to given key, if any
    public Entry<K,V> ceilingEntry( K key ) {
	SkipNode<K,V> p = this.skipSearch( key );
	if( compare( key, p.getKey() ) != 0 ) p = p.getNext();
	return safeEntry( p );
    }
    // return the entry with greatest key less than or equal to given key, if any
    public Entry<K,V> floorEntry( K key ) { return safeEntry( this.skipSearch( key ) ); }
    // return the entry with greatest key strictly less than given key, if any
    public Entry<K,V> lowerEntry( K key ) {
	SkipNode<K,V> p = this.skipSearch( key );
	if( compare( key, p.getKey() ) == 0 ) p = p.getPrevious();
	return safeEntry( p );
    }
    // return the entry with greatest key strictly greater than given key, if any
    public Entry<K,V> higherEntry( K key ) {
	SkipNode<K,V> p = this.skipSearch( key );
	return safeEntry( p.getNext() );
    }

    // iterators
    // support for snapshot iterators for entrySet and subMap
    private Iterable<Entry<K,V>> snapshot( K start, K stop ) {
	ArrayList<Entry<K,V>> buffer = new ArrayList<>();
	SkipNode<K,V> p = this.skipSearch( start );
	if( compare( start, p.getKey() ) != 0 ) p = p.getNext(); // if stopped on a smaller key, get the next one
	while( p.getNext().getNext() != null && ( stop == null || compare( stop, p.getKey() ) > 0 ) )  {
	    buffer.add( p.getElement() );
	    p = p.getNext();
	}
	return buffer;
    }
    public Iterable<Entry<K,V>> entrySet() { return snapshot( this.firstEntry().getKey(), null ); }
    public Iterable<Entry<K,V>> subMap( K fromKey, K toKey ) { return snapshot( fromKey, toKey ); }

    public String toString() {
	if( this.isEmpty() ) return "[]";
	String s = "[";
	SkipNode<K,V> curr = this.getFirst();
	while( curr.getNext().getNext() != null ) {
	    s += curr.getElement() + ", ";
	    curr = curr.getNext();
	}
	s += curr.getElement() + "](h=" + this.h + ")" ;
	return s;
    }
}
