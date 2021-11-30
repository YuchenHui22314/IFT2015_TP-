package ca.umontreal.adt.list;

import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;
import java.util.Iterator;

import ca.umontreal.adt.queue.Deque;

/**
* DoublyLinkedList is an implementation of the list ADT
*   additional operation removeLast
*   use doubly linked Node
*   use header and trailer sentinel (dummy Nodes)
*
* Based on Goodrich, Tamassia & Goldwasser
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class DoublyLinkedList<E> implements List<E>, Deque<E> {
    
    //----- inner Node class
    protected static class Node<E> {
	// attributes
	protected E element;    // reference to the element
	protected Node<E> next; // reference to the next Node in the list
	protected Node<E> prev; // reference to the previous Node in the list
	// constructor
	protected Node( E element, Node<E> prev, Node<E> next ) {
	    this.element = element;
	    this.prev = prev;
	    this.next = next;
	}
	// getters & setters
	protected E    getElement() { return this.element; }               // element getter
	protected void setElement( E element ) { this.element = element; } // element setter
	protected Node<E> getNext() { return this.next; }               // next Node getter
	protected void    setNext( Node<E> next ) { this.next = next; } // next Node setter
	protected Node<E> getPrev() { return this.prev; }               // next Node getter
	protected void    setPrev( Node<E> prev ) { this.prev = prev; } // next Node setter
    } //----- end inner Node class

    //----- inner iterator class
    protected class DoublyLinkedListIterator implements Iterator<E> {
	private Node<E> current = getHead(); // reference to next element
	public boolean hasNext() { return current != null; }
	public E next() throws NoSuchElementException {
	    if( current == null ) throw new NoSuchElementException( "No more element" );
	    E element = current.getElement();
	    if( current == trailer.getPrev() )
		current = null; // special case to handle circular list as well
	    else current = current.getNext();
	    return element;
	}
    } //----- end of inner iterator class

		    
    // attributes
    protected Node<E> header;  // dummy header Node
    protected Node<E> trailer; // dummy trailer Node
    protected int size = 0;    // number of nodes in the list

    // List developer methods
    protected Node<E> getHead() { return this.header.next; }
    protected Node<E> getTail() { return this.trailer.prev; }
    protected void checkIndex( int i, int n ) throws IndexOutOfBoundsException {
	if( i < 0 || i >= n ) throw new IndexOutOfBoundsException( "index " + i + " out of bounds" );
    }
    protected void addBetween( E element, Node<E> predecessor, Node<E> successor ) { // add element between given Nodes
	Node<E> newNode = new Node<>( element, predecessor, successor );
	predecessor.setNext( newNode );
	successor.setPrev( newNode );
	this.size++;
    }
    protected E remove( Node<E> node ) { // remove the given Node from the list and return its element
	Node<E> predecessor = node.getPrev();
	Node<E> successor = node.getNext();
	predecessor.setNext( successor );
	successor.setPrev( predecessor );
	this.size--;
	return node.getElement();
    }
    
    // constructor 
    public DoublyLinkedList() { // make an initially empty list instance
	this.header = new Node<>( null, null, null );    // dummy header Node
	this.trailer = new Node<>( null, header, null ); // dummy trailer Node with previous header
	this.header.setNext( this.trailer );             // trailer follows header
    }
    // interface List implementation
    public int size() { return this.size; } // return the number of elements in list
    public boolean isEmpty() { return this.size == 0; } // return true if this list has no element, false otherwise
    public E first() { if( this.isEmpty() ) return null; return this.header.getNext().getElement(); } // return without removing the first element in the list, null if empty
    public E last() { if( this.isEmpty() ) return null; return this.trailer.getPrev().getElement(); } // return without removing the last element in the list, null if empty
    public void addFirst( E element ) { // add a new element at the front of the list	
	this.addBetween( element, header, header.getNext() );
    }
    public void addLast( E element ) { // add a new element at the end of the list
	addBetween( element, trailer.getPrev(), trailer );
    }
    public void add( E element ) { this.addLast( element ); } // compatible with Java List
    public void add( int index, E element ) throws IndexOutOfBoundsException { // add element at index i in O(n)
	checkIndex( index, this.size + 1 );
	if( index == 0 ) this.addFirst( element ); // special case
	if( index == this.size ) this.addLast( element ); // special case
	else {
	    Node<E> current = getHead();
	    for( int i = 0; i < index; i++ )
		current = current.getNext();
	    addBetween( element, current.getPrev(), current );
	}
    }	
    public E removeFirst() { // remove and return the first element of the list
	if( this.isEmpty() ) return null;   // if empty, nothing to remove
	return remove( this.header.getNext() );
    }
    public E removeLast() { // remove and return the last element of the list
	if( this.isEmpty() ) return null;   // if empty, nothing to remove
	return remove( this.trailer.getPrev() );
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
	while( current != this.trailer && current.getElement() != element )
	    current = current.getNext();
	if( current != null ) return remove( current );
	return null;
    }
    public E remove( int index ) throws IndexOutOfBoundsException { // remove element at index, error if out of bounds
	checkIndex( index, this.size );
	if( index == 0 ) this.removeFirst(); // special case
	Node<E> current = getHead();
	for( int k = 0; k < index; k++ )
	    current = current.getNext();
	return this.remove( current );
    }	
    public int indexOf( E element ) { // return index of first occurrence of element in O(n), -1 if not present
	Node<E> current = getHead();
	int index = 0;
	while( current != this.trailer && current.getElement() != element ) {
	    current = current.getNext();
	    index++;
	}
	if( current != null ) return index;
	return -1;
    }
    public boolean equals( List<E> other ) {
	if( other == null ) return false;
	if( this.getClass() != other.getClass() ) return false;
	DoublyLinkedList that = (DoublyLinkedList)other; // use non-parameterized type
	if( this.size() != that.size() ) return false;
	Node<E> currentThis = this.getHead();
	Node<E> currentThat = that.getHead();
	// traverse both lists concurrently
	while( currentThis != this.trailer ) {
	    if( !currentThis.getElement().equals( currentThat.getElement() ) ) return false; // mismatch
	    currentThis = currentThis.getNext();
	    currentThat = currentThat.getNext();
	}
	return true; // if we get here, then this and that are equal
    }
    public Iterator<E> iterator() { // return an iterator over the elements in proper order
	return new DoublyLinkedListIterator();
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
}
