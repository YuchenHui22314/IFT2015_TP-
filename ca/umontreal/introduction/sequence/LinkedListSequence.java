package ca.umontreal.introduction.sequence;

import java.util.LinkedList;

/**
* LinkedListSequence is an Sequence implementation using a LinkedList
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class LinkedListSequence<T extends Comparable<T>> implements Sequence<T> {

    // physical data structure
    private LinkedList<T> sequence;

    // constructor 
    public LinkedListSequence() {
	this.sequence = new LinkedList<>();
    }

    // Sequence implementation
    public int  size()        { return this.sequence.size(); }
    public T    get( int i )  { return this.sequence.get( i ); }
    public void add( T t )    { this.sequence.add( t ); }
    public void delete( T t ) { this.sequence.remove( t ); }
    public int  index( T t )  { return this.sequence.indexOf( t ); }
}
