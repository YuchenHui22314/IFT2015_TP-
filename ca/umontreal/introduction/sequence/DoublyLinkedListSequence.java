package ca.umontreal.introduction.sequence;

import ca.umontreal.adt.list.DoublyLinkedList;

/**
* DoublyLinkedListSequence is a Sequence implementation using a DoublyLinkedList
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class DoublyLinkedListSequence<T extends Comparable<T>> implements Sequence<T> {

    // physical data structure
    private DoublyLinkedList<T> sequence;

    // constructor 
    public DoublyLinkedListSequence() {
	this.sequence = new DoublyLinkedList<>();
    }

    // Sequence implementation
    public int  size()        { return this.sequence.size(); }
    public T    get( int i )  { return this.sequence.get( i ); }
    public void add( T t )    { this.sequence.addFirst( t ); }
    public void delete( T t ) { this.sequence.remove( t ); }
    public int  index( T t )  { return this.sequence.indexOf( t ); }
}
