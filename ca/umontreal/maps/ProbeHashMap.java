package ca.umontreal.maps;

import java.lang.Iterable;
import java.util.ArrayList;

/**
* ProbeHashMap is an implementation of the ADT Map
*   using open addressing with linear probing
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class ProbeHashMap<K,V> extends AbstractHashMap<K,V> {
    private MapEntry<K,V>[] table; // using a fixed array of entries
    private MapEntry<K,V> DEFUNCT = new MapEntry<>( null, null ); // sentinel
    public ProbeHashMap() { super(); }
    public ProbeHashMap( int cap ) { super( cap ); }
    public ProbeHashMap( int cap, int p ) { super( cap, p ); }
    // create an empty table of current capacity
    protected void createTable() {
	table = (MapEntry<K,V>[]) new MapEntry[this.capacity];
    }
    // return true if location is either empty or the 'defunct' sentinel
    private boolean isAvailable( int j ) { return ( table[j] == null || table[j] == DEFUNCT ); }
    // return index with key k, or -(a+1) so that k could be added at index a
    private int findSlot( int h, K k ) {
	int avail = -1; // no slot available (thus far)
	int j = h; // index for scanning the table
	do {
	    if( isAvailable( j ) ) { // may be either empty or defunct
		if( avail == -1 ) avail = j; // first available slot
		if( table[j] == null ) break; // if empty, search fails immediately
	    } else if( table[j].getKey().equals( k ) )
		return j; // successful search
	    j = (j + 1) % this.capacity; // keep looking (cyclically)
	} while( j != h ); // stop if we return to the start
	return -(avail + 1); // search has failed
    }
    // return value associated with key k in bucket with hash value h, or else null
    protected V bucketGet( int h, K k ) {
	int j = findSlot( h, k );
	if( j < 0 ) return null; // no match found
	return table[j].getValue();
    }
    // associate key k with value v in bucket with hash value h; returns old value
    protected V bucketPut( int h, K k, V v ) {
	int j = findSlot( h, k );
	if( j > 0 ) // this key has an existing entry
	    return table[j].setValue( v );
	table[-(j+1)] = new MapEntry<>( k, v ); // convert to proper index
	this.n++;
	return null;
    }
    // remove entry having key k from bucket with hash value h, if any
    protected V bucketRemove( int h, K k ) {
	int j = findSlot( h, k );
	if( j < 0 ) return null; // nothing to remove
	V retV = table[j].getValue();
	table[j] = DEFUNCT; // mark this slot as deactivated
	this.n--;
	return retV;
    }
    // return an iterable collection of all key-value entries of the map
    public Iterable<Entry<K,V>> entrySet() {
	ArrayList<Entry<K,V>> buffer = new ArrayList<>();
	for( int h = 0; h < this.capacity; h++ )
	    if( !isAvailable( h ) ) buffer.add( table[h] );
	return buffer;
    }
}
