package ca.umontreal.adt.list;

import java.lang.Iterable;
import java.util.Iterator;
import java.lang.IndexOutOfBoundsException;

/**
* List is an interface for the list ADT
*   use generic's framework to allow for user's desired element type
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public interface List<E> extends Iterable<E> {
    public int         size();              // return the number of elements in list
    public boolean     isEmpty();           // return true if this list has no element, false otherwise
    public E           first();             // return without removing the first element in the list, null if empty
    public E           last();              // return without removing the last element in the list, error if out of bounds
    public void        add( int i, E e )
	throws IndexOutOfBoundsException;   // add element at index i
    public void        addFirst( E e );     // add element at the front of the list
    public void        addLast( E e );      // add element at the end of the list
    public void        add( E e );          // add element at the end of the list (for compatibility with ArrayList)
    public E           removeFirst();       // remove and return the first element of the list
    public E           get( int i )
	throws IndexOutOfBoundsException;   // return element at index i, error if out of bounds
    public E           set( int i, E e )
	throws IndexOutOfBoundsException;   // replaces element at index i, error if out of bound
    public E           remove( E e );       // remove and return element, null if not found
    public E           remove( int i )
	throws IndexOutOfBoundsException;   // remove element at index i, error if out of bounds
    public int         indexOf( E e );      // return index of element, -1 if not found
    public boolean     equals( List<E> o ); // return true if other list equals this list
    public Iterator<E> iterator();          // return an iterator over the elements in proper order
}
