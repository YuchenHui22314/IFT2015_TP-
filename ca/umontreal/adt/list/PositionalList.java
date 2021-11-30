package ca.umontreal.adt.list;

import java.util.Iterator;
import java.lang.Iterable;
import java.lang.IllegalArgumentException;

/**
* ADT PositionalList is an interface for a List that implement positions
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public interface PositionalList<E> extends Iterable<E> {
    int size();          // return the number of elements in the list
    boolean isEmpty();   // return a boolean indicating if the list is empty or not
    Position<E> first(); // return the first element of the list
    Position<E> last();  // return the last element of the list
    // return the Position immediately before Position p, or null if p is first
    Position<E> before( Position<E> p ) throws IllegalArgumentException;
    // return the Position immediately after Position p, or null if p is last
    Position<E> after( Position<E> p ) throws IllegalArgumentException;
    Position<E> addFirst( E e ); // insert element e at the front of the list and return its position
    Position<E> addLast( E e );  // insert element e at the back of the list and return its position
    // insert element e immediately before position p and return its position
    Position<E> addBefore( Position<E> p, E e ) throws IllegalArgumentException;
    // insert element e immediately after position p and return its position
    Position<E> addAfter( Position<E> p, E e ) throws IllegalArgumentException;
    E set( Position<E> p, E e ) throws IllegalArgumentException; // replace element at p and return replaced element
    E remove( Position<E> p ) throws IllegalArgumentException;   // remove element at p and returns it (invalidate p)
    void sort(); // sort the positional list using natural ordering of its elements
    Iterator<E> iterator(); // return an iterator on list elements
    Iterable<Position<E>> positions(); // return an iterable on list positions
}
