package ca.umontreal.maps;

/**
* Entry is an interface for the entries of the ADT Map
*    in which a key of type K and
*             a value of type V are joined.
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public interface Entry<K,V> {
    K getKey(); // return the key stored in the Entry
    V getValue(); // return the value stored in the Entry
}

