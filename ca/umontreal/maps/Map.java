package ca.umontreal.maps;

import java.lang.Iterable;


/**
* Map is the interface for the ADT Map
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public interface Map<K,V> {
    int size();
    boolean isEmpty();
    boolean containsKey( K key );
    V get( K key );
    V put( K key, V value );
    V remove( K key );
    Iterable<K> keySet();
    Iterable<V> values();
    Iterable<Entry<K,V>> entrySet();
}

