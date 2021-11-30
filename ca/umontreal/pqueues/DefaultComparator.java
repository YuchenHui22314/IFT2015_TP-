package ca.umontreal.pqueues;

import java.util.Comparator;
import java.lang.ClassCastException;

/**
* DefaultComparator is a class to compare two elements upon natural ordering
*   the type parameter E must implement Comparable
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class DefaultComparator<E> implements Comparator<E> {
    // compare two elements upon natural ordering
    public int compare( E a, E b ) throws ClassCastException {
	return((Comparable<E>)a).compareTo( b );
    }
}
