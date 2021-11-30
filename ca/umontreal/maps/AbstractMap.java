package ca.umontreal.maps;

import java.lang.Iterable;
import java.util.Iterator;
import java.lang.UnsupportedOperationException;

/**
* AbstractMap is an abstract base class for the ADT Map
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public abstract class AbstractMap<K,V> implements Map<K,V> {
    //----- inner MapEntry class -----
    protected static class MapEntry<K,V> implements Entry<K,V> {
	private K k; // for the key
	private V v; // for the value
	public MapEntry( K key, V value ) {
	    this.k = key;
	    this.v = value;
	}
	// getters
	public K getKey() { return this.k; }
	public V getValue() { return this.v; }
	// developer's utilities
	protected void setKey( K key ) { this.k = key; }
	protected V setValue( V value ) {
	    V old = v;
	    this.v = value;
	    return old;
	}
    } //----- end inner MapEntry class -----
    
    public boolean isEmpty() { return this.size() == 0; }
    // support for public keySet method
    private class KeyIterator implements Iterator<K> {
	private Iterator<Entry<K,V>> entries = entrySet().iterator(); // reuse entrySet (in interface Map)
	public boolean hasNext() { return entries.hasNext(); }
	public K next() { return entries.next().getKey(); } // return key
	public void remove() { throw new UnsupportedOperationException(); }
    }
    private class KeyIterable implements Iterable<K> {
	public Iterator<K> iterator() { return new KeyIterator(); }
    }
    public Iterable<K> keySet() { return new KeyIterable(); }

    // support for public values
    private class ValueIterator implements Iterator<V> {
	private Iterator<Entry<K,V>> entries = entrySet().iterator(); // reuse entrySet (in interface Map)
	public boolean hasNext() { return entries.hasNext(); }
	public V next() { return entries.next().getValue(); } // return value
	public void remove() { throw new UnsupportedOperationException(); }
    }
    private class ValueIterable implements Iterable<V> {
	public Iterator<V> iterator() { return new ValueIterator(); }
    }
    public Iterable<V> values() { return new ValueIterable(); }
}
