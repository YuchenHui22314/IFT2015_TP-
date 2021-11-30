package ca.umontreal.pqueues;

import java.lang.IllegalArgumentException;


/**
* PriorityQueue is an interface for the ADT Priority Queue
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public interface PriorityQueue<K,V> {
    int size();
    boolean isEmpty();
    Entry<K,V> insert( K key, V value ) throws IllegalArgumentException;
    Entry<K,V> min();
    Entry<K,V> removeMin();
}
    
