package ca.umontreal.adt.list;

import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;
import java.util.Iterator;

/**
* SinglyLinkedList is an implementation of the list ADT
*   use singly linked Node
*
* Based on Goodrich, Tamassia & Goldwasser
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class SinglyLinkedList<E> implements List<E> {
    
    //----- inner Node class
    protected static class Node<E> {
	// attributes
	protected E element;    // reference to the element
	protected Node<E> next; // reference to the next Node in the list
	// constructor
	protected Node( E element, Node<E> next ) {
	    this.element = element;
	    this.next = next;
	}
	// getters & setters
	protected E    getElement() { return this.element; }               // element getter
	protected void setElement( E element ) { this.element = element; } // element setter
	protected Node<E> getNext() { return this.next; }               // next Node getter
	protected void    setNext( Node<E> next ) { this.next = next; } // next Node setter
    } //----- end inner Node class

    //----- inner iterator class
    protected class SinglyLinkedListIterator implements Iterator<E> {
	
	private Node<E> current = getHead(); // reference to next element

	public boolean hasNext() { return current != null; }
	public E next() throws NoSuchElementException {
	    if( current == null ) throw new NoSuchElementException( "No more element" );
	    E element = current.getElement();
	    if( current == tail )
		current = null; // special case to handle circular list as well
	    else current = current.getNext();
	    return element;
	}
    } //----- end of inner iterator class

		    
    // attributes
    protected Node<E> head = null;  // head Node, or null if empty
    protected Node<E> tail = null;  // tail Node, or null if empty
    protected int size = 0;         // number of nodes in the list

    // List developer methods
    protected Node<E> getHead() { return this.head; }
    protected Node<E> getTail() { return this.tail; }
    protected void checkIndex( int i, int n ) throws IndexOutOfBoundsException {
	if( i < 0 || i >= n ) throw new IndexOutOfBoundsException( "index " + i + " out of bounds" );
    }
	
    // constructor 
    public SinglyLinkedList() { } // make an initially empty list instance
    public Iterator<E> iterator() { // return an iterator over the elements in proper order
	return new SinglyLinkedListIterator();
    }
    // interface List implementation
    public int size() { return this.size; } // return the number of elements in list
    public boolean isEmpty() { return this.size == 0; } // return true if this list has no element, false otherwise
    public E first() { if( this.isEmpty() ) return null; return this.head.getElement(); } // return without removing the first element in the list, null if empty
    public E last() { if( this.isEmpty() ) return null; return this.tail.getElement(); } // return without removing the last element in the list, null if empty
    public void addFirst( E element ) { // add a new element at the front of the list	
	this.head = new Node<>( element, this.head ); // create and link a new Node for element
	if( this.isEmpty() ) this.tail = this.head;   // if empty, new Node becomes head and tail
	this.size++;
    }
    public String toString() {
	if( this.isEmpty() ) return "[]";
	String pp = "[";
	Node<E> current = this.getHead();
	while( current != this.getTail() ) {
	    pp += current.getElement() + ",";
	    current = current.getNext();
	}
	pp += current.getElement() + "]";
	return pp;
    }
    public void addLast( E element ) { // add a new element at the end of the list
	Node<E> newNode = new Node<>( element, null ); // create new Node for the eventual tail
	if( this.isEmpty() ) this.head = newNode;      // if empty, new Node is tail and head
	else this.tail.setNext( newNode );             // otherwise, add after current tail
	this.tail = newNode;                           // new Node becomes tail
	this.size++;
    }
    public void add( E element ) { this.addLast( element ); } // compatible with Java List
    public void add( int index, E element ) throws IndexOutOfBoundsException { // add element at index i in O(n)
	checkIndex( index, this.size + 1 );
	if( index == 0 ) this.addFirst( element ); // special case
	else if( index == this.size ) this.addLast( element ); // special case
	else {
	    Node<E> current = this.head;
	    Node<E> previous = null;    // need the previous to link the new Node
	    for( int i = 0; i < index; i++ ) {
		previous = current;
		current = current.getNext();
	    }
	    Node<E> newNode = new Node<>( element, current ); // current follow new Node
	    previous.setNext( newNode );
	    this.size++;
	}
    }	
    public E removeFirst() { // remove and return the first element of the list
	if( this.isEmpty() ) return null;   // if empty, nothing to remove
	E element = this.head.getElement();
	this.head = this.head.getNext();    // new head is next to head, or null if list has single Node
	this.size--;
	if( this.isEmpty() ) this.tail = null; // if list becomes empty, tail becomes null
	return element;
    }
    public E get( int index ) throws IndexOutOfBoundsException { // // return element at index in O(n)
	checkIndex( index, this.size );
	Node<E> current = getHead();
	for( int k = 0; k < index; k++ )
	    current = current.getNext();
	return current.getElement();
    }
    public E set( int index, E element ) throws IndexOutOfBoundsException { // replaces element at index i, error if out of bounds
	checkIndex( index, this.size );
	Node<E> current = getHead();
	for( int k = 0; k < index; k++ )
	    current = current.getNext();
	E oldElement = current.getElement();
	current.setElement( element );
	return oldElement;
    }	
    public E remove( E element ) { // remove and return element in O(n), null if not present
	Node<E> current = getHead();
	Node<E> previous = null;
	while( current != null && current.getElement() != element ) {
	    previous = current;
	    current = current.getNext();
	}
	if( current != null ) {
	    if( previous == null ) { // case of the first element
		this.removeFirst();
		return current.getElement();
	    }
	    previous.setNext( current.getNext() );
	    this.size--;
	    return current.getElement();
	}
	return null;
    }
    public E remove( int index ) throws IndexOutOfBoundsException { // remove element at index, error if out of bounds
	checkIndex( index, this.size );
	if( index == 0 ) this.removeFirst(); // special case
	Node<E> current = this.getHead();
	Node<E> previous = null; // need the previous to link Nodes around Node at index
	for( int k = 0; k < index; k++ ) {
	    previous = current;
	    current = current.getNext();
	}
	E oldElement = current.getElement();
	previous.setNext( current.getNext() );
	if( current == this.tail ) this.tail = previous;
	this.size--;
	return oldElement;
    }	
    public int indexOf( E element ) { // return index of first occurrence of element in O(n), -1 if not present
	Node<E> current = this.head;
	int index = 0;
	while( current != null && current.getElement() != element ) {
	    current = current.getNext();
	    index++;
	}
	if( current != null ) return index;
	return -1;
    }
    public boolean equals( List<E> other ) {
	if( other == null ) return false;
	if( this.getClass() != other.getClass() ) return false;
	SinglyLinkedList that = (SinglyLinkedList) other; // use non-parameterized type
	if( this.size() != that.size() ) return false;
	Node<E> currentThis = this.head;
	Node<E> currentThat = that.head;
	// traverse both lists concurrently
	while( currentThis != null ) {
	    if( !currentThis.getElement().equals( currentThat.getElement() ) ) return false; // mismatch
	    currentThis = currentThis.getNext();
	    currentThat = currentThat.getNext();
	}
	return true; // if we get here, then this and that are equal
    }
    public void recursiveReverse() { // reverse the elements
	if( !this.isEmpty() && this.size() > 1 ) { // base case stop recursivity
	    // future last Node is the head of this list
	    Node<E> last = this.getHead();
	    // remove the head of this list
	    this.head = last.getNext();
	    this.size--;
	    // next of the future last Node is null
	    last.next = null;
	    // reverse the rest of this list (this list minus old head)
	    this.recursiveReverse();
	    // add the old head as the last
	    this.tail.next = last;
	    this.size++;
	    // set the new tail to the last Node
	    this.tail = last;
	}
    }
    public void reverse() { // iterative version
	Node<E> current = this.getHead();
	Node<E> previous = null;
	Node<E> next = null;
	while( current != null ) {
	    next = current.getNext();
	    current.next = previous;
	    previous = current;
	    current = next;
	}
	this.tail = this.head;
	this.head = previous;
    }
}
