package ca.umontreal.maps;

import java.lang.Iterable;
import java.util.ArrayList;

/**
* ChainHashMap is an implementation of the ADT Map
*   using seperate chaining UnsortedTableMaps as buckets
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class ChainHashMap<K,V> extends AbstractHashMap<K,V> {
    // fixed capacity array of UnsortedTableMap as buckets
    private UnsortedTableMap<K,V>[] table; // initialized in createTable
    public ChainHashMap() { super(); }
    public ChainHashMap( int cap ) { super( cap ); }
    public ChainHashMap( int cap, int p ) { super( cap, p ); }
    // create an empty table of current capacity
    protected void createTable() {
	table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[this.capacity]; // unsortedTableMap<K,V>[this.capacity]报错哦，因为不可以声明泛型数组。真是到处都是坑啊。
    }
    // return value associated with key k in bucket with hash value h, or else null
    protected V bucketGet( int h, K k ) {
	UnsortedTableMap<K,V> bucket = table[h];
	if( bucket == null ) return null;
	return bucket.get( k );
    }
    // associate key k with value v in bucket with hash value h; returns old value
    protected V bucketPut( int h, K k, V v ) {
	UnsortedTableMap<K,V> bucket = table[h];
	if( bucket == null )
	    bucket = table[h] = new UnsortedTableMap<>();
	int oldSize = bucket.size();
	V old = bucket.put( k, v );
	this.n += ( bucket.size() - oldSize ); // size may have increased
	return old;
    }
    // remove entry having key k from bucket with hash value h, if any
    protected V bucketRemove( int h, K k ) {
	UnsortedTableMap<K,V> bucket = table[h];
	if( bucket == null ) return null;
	int oldSize = bucket.size();
	V retV = bucket.remove( k );
	n -= ( oldSize - bucket.size() ); // size may have decreased
	return retV;
    }
    // return an iterable collection of all key-value entries of the map
    public Iterable<Entry<K,V>> entrySet() {
	ArrayList<Entry<K,V>> buffer = new ArrayList<>();
	for( int h = 0; h < this.capacity; h++ )
	    if( table[h] != null )
		for( Entry<K,V> entry : table[h].entrySet() )
		    buffer.add( entry );
	return buffer;
    }
}
