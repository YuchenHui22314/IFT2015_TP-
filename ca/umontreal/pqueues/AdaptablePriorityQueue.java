package ca.umontreal.pqueues;

import java.lang.IllegalArgumentException;


/**
* AdaptablePriorityQueue is an interface extension of Priority Queue interface
*   for the ADT Adaptable Priority Queue
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public interface AdaptablePriorityQueue<K,V> extends PriorityQueue<K,V> {
    void remove( Entry<K,V> e  ) throws IllegalArgumentException;
    void replaceKey( Entry<K,V> e, K k ) throws IllegalArgumentException;
    void replaceValue( Entry<K,V> e, V v ) throws IllegalArgumentException;
}
    
