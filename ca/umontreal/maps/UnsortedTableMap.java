package ca.umontreal.maps;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

/**
* UnsortedTableMap is an implementation of the ADT Map
*   using an unsorted ArrayList
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class UnsortedTableMap<K,V> extends AbstractMap<K,V> {
    // physical storage for the map entries
    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();

    // construct an initially empty map
    public UnsortedTableMap() {}

    // private utilities
    // return the index of the entry with key k, or -1 if not found
    private int findIndex( K k ) {
	int n = this.table.size();
	for( int j = 0; j < n; j++ )
	    if( this.table.get( j ).getKey().equals( k ) ) // found it
		return j;
	// none found
	return -1; // special value denoting not such entry
    }

    // number of entries in the map
    public int size() { return this.table.size(); }
    // return the value associated with the specified key, or null if not found
    public V get( K key ) {
	int j = this.findIndex( key );
	if( j == -1 ) return null; // not found
	return this.table.get( j ).getValue();
    }
    // check if an entry with given key exists
    public boolean containsKey( K key ) { return this.findIndex( key ) != -1; } 
    // associate the pair key-value, replacing existing value if any
    public V put( K key, V value ) {
	int j = findIndex( key );
	if( j == -1 ) { // not found, so insert the new pair
	    this.table.add( new MapEntry<>( key, value ) );
	    return null;
	} else // key exists
	    return this.table.get( j ).setValue( value ); // return old value
    }
    // remove the entry with specified key, if any, return its value
    public V remove( K key ) {
	int j = findIndex( key );
	if( j == -1 ) return null; // no such entry found
	int n = this.size();
	V retV = this.table.get( j ).getValue();
	// remove in O(1)
	if( j != n-1 ) // move last entry of the array to index j
	    this.table.set( j, this.table.get( n-1 ) );
	this.table.remove( n-1 ); // remove the last entry
	// remove in O(n)
	// this.table.remove( j );
	return retV;
    }
    // support for entrySet public method
    private class EntryIterator implements Iterator<Entry<K,V>> {
	private int j = 0;
	public boolean hasNext() { return j < table.size(); }
	public Entry<K,V> next() {
	    if( j == table.size() ) throw new NoSuchElementException();
	    return table.get( j++ );
	}
	public void remove() { throw new UnsupportedOperationException(); }
    }
    private class EntryIterable implements Iterable<Entry<K,V>> {
	public Iterator<Entry<K,V>> iterator() { return new EntryIterator(); }
    }
    public Iterable<Entry<K,V>> entrySet() { return new EntryIterable(); }
}
