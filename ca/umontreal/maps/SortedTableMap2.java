package ca.umontreal.maps;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
* SortedTableMap is an implementation of the ADT Map
*   using a sorted ArrayList
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class SortedTableMap2<K,V> extends AbstractSortedMap<K,V> {
    // physical storage for the map entries
    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();

    // construct an initially empty map
    public SortedTableMap2() { super(); }
    public SortedTableMap2( Comparator<K> comp ) { super( comp ); }

    // private utilities
    
    // binary search in O(log n)
    // return the smallest index of range [low..high] inclusive storing an entry
    //   with a key greater than or equal to k, otherwise index high+1, by convention
    private int findIndex( K key, int low, int high ) {
	if( high < low ) return high + 1; // no entry qualifies
	int mid = ( low + high ) / 2;
	int comp = compare( key, this.table.get( mid ) );
	if( comp == 0 ) return mid; // found entry with exact match
	else if( comp < 0 ) return findIndex( key, low, mid - 1 ); // search left of mid
	else return findIndex( key, mid + 1, high ); //search right of mid
    }
    // interface to search the entire table
    private int findIndex( K key ) { return findIndex( key, 0, this.table.size() - 1 ); }

    // public methods

    // return the number of entries in the table
    public int size() { return this.table.size(); }
    // return the value associated with the specified key, or null if not found
    public V get( K key ) {
	int j = this.findIndex( key );
	if( j == this.size() || compare( key, this.table.get( j ) ) != 0 ) return null; // no match
	return this.table.get( j ).getValue();
    }
    // check if an entry with given key exists
    public boolean containsKey( K key ) { return this.findIndex( key ) < this.size(); } 
    // associate the pair key-value, replacing existing value if any
    public V put( K key, V value ) {
	int j = this.findIndex( key );
	if( j < this.size() && compare( key, this.table.get( j ) ) == 0 ) // match exists
	    return this.table.get( j ).setValue( value );
	this.table.add( j, new MapEntry<K,V>( key, value ) ); // otherwise add new entry
	return null;
    }
    // remove the entry with specified key, if any, return its value
    public V remove( K key ) {
	int j = findIndex( key );
	if( j == this.size() || compare( key, this.table.get( j ) ) != 0 ) return null; // no match
	int n = this.size();
	V retV = this.table.get( j ).getValue();
	if( j != n-1 ) // move last entry of the array to index j
	    this.table.set( j, this.table.get( n-1 ) );
	this.table.remove( n-1 ); // remove the last entry
	// remove in O(n)
	//this.table.remove( j );
	return retV;
    }

    // return the entry at index j, or null if j is out of bounds
    private Entry<K,V> safeEntry( int j ) {
	if( j < 0 || j >= this.table.size() ) return null;
	return this.table.get( j );
    }
    // public methods on getting a bound entry
    // return the entry having the least key, or null if map is empty
    public Entry<K,V> firstEntry() { return safeEntry( 0 ); }
    // return the entry having the greatest key, or null if map is empty
    public Entry<K,V> lastEntry() { return safeEntry( this.table.size() - 1 ); }
    // return the entry with least key greater than or equal to given key, if any
    public Entry<K,V> ceilingEntry( K key ) { return safeEntry( this.findIndex( key ) ); }
    // return the entry with greatest key less than or equal to given key, if any
    public Entry<K,V> floorEntry( K key ) {
	int j = this.findIndex( key );
	if( j == this.size() || ! key.equals( this.table.get( j ).getKey() ) ) j--; // look one earlier unless found exact match
	return safeEntry( j );
    }
    // return the entry with greatest key strictly less than given key, if any
    public Entry<K,V> lowerEntry( K key ) { return safeEntry( this.findIndex( key ) - 1 ); } // go strictly before the ceiling entry
    public Entry<K,V> higherEntry( K key ) {
	int j = this.findIndex( key );
	if( j < this.size() && key.equals( this.table.get( j ).getKey() ) ) j++; // go past exact match
	return safeEntry( j );
    }
    // Iterators
    // support for snapshot iterators for entrySet and subMap
    private Iterable<Entry<K,V>> snapshot( int startIndex, K stop ) {
	ArrayList<Entry<K,V>> buffer = new ArrayList<>();
	int j = startIndex;
	while( j < this.table.size() && ( stop == null || compare( stop, this.table.get( j ) ) > 0 ) )
	    buffer.add( this.table.get( j++ ) );
	return buffer;
    }
    public Iterable<Entry<K,V>> entrySet() { return snapshot( 0, null ); }
    public Iterable<Entry<K,V>> subMap( K fromKey, K toKey ) { return snapshot( this.findIndex( fromKey ), toKey ); }

    public String toString() {
	if( this.isEmpty() ) return "{}";
	String s = "{";
	for( int i = 0; i < this.table.size() - 1; i++ ) {
	    Entry<K,V> entry = this.table.get( i );
	    s += entry.toString() + ", ";
	}
	s += this.table.get( this.table.size() - 1 ) + "}";
	return s;
    }
}
