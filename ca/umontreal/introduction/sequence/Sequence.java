package ca.umontreal.introduction.sequence;

/**
* Sequence is an interface for simple sequence management operations (ADT)
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public interface Sequence<T extends Comparable<T>> {
    public int     size();         // returns the number of elements in sequence
    public void    add( T t );     // add element t
    public void    delete( T t );  // delete element t
    public T       get( int i );   // returns the element at index i
    public int     index( T t );   // returns the index of the first occurrence of t, or -1 if absent
}
