package ca.umontreal.maps;

import java.lang.Iterable;
import java.lang.IllegalArgumentException;


/**
* SortedMap is the interface for the ADT SortedMap
*   which extends Map
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public interface SortedMap<K,V> extends Map<K,V> {
    Entry<K,V> firstEntry();
    Entry<K,V> lastEntry();
    Entry<K,V> ceilingEntry( K key ) throws IllegalArgumentException; 
    Entry<K,V> floorEntry( K key ) throws IllegalArgumentException;
    Entry<K,V> lowerEntry( K key ) throws IllegalArgumentException;
    Entry<K,V> higherEntry( K key ) throws IllegalArgumentException;
    Iterable<Entry<K,V>> subMap( K fromKey, K toKey ) throws IllegalArgumentException;
}

