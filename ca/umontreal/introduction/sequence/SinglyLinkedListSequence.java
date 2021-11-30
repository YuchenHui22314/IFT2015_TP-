package ca.umontreal.introduction.sequence;

import ca.umontreal.adt.list.SinglyLinkedList;

/**
* SinglyLinkedListSequence is a Sequence implementation using a SinglyLinkedList
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class SinglyLinkedListSequence<T extends Comparable<T>> implements Sequence<T> {

    // physical data structure
    private SinglyLinkedList<T> sequence;

    // constructor 
    public SinglyLinkedListSequence() {
	this.sequence = new SinglyLinkedList<>();
    }

    // Sequence implementation
    public int  size()        { return this.sequence.size(); }
    public T    get( int i )  { return this.sequence.get( i ); }
    public void add( T t )    { this.sequence.addFirst( t ); }
    public void delete( T t ) { this.sequence.remove( t ); }
    public int  index( T t )  { return this.sequence.indexOf( t ); }
}
