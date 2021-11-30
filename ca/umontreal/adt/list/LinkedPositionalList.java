package ca.umontreal.adt.list;

import java.util.Iterator;
import java.lang.Iterable;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

/**
* LinkedPositionalList is an implementation of the ADT PositionalList using DoublyLinkedList
*   All operations execute in O(1)
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class LinkedPositionalList<E extends Comparable<E>> implements PositionalList<E> {
    //----- inner class Node -----
    private static class Node<E> implements Position<E> {
	private E element;    // reference to the element stored in this node
	private Node<E> prev; // reference to the previous node in the list
	private Node<E> next; // reference to the next node in the list
	private Object container; // reference to the node's container
	public Node( E e, Node<E> prev, Node<E> next, Object container ) {
	    this.element = e;
	    this.prev = prev;
	    this.next = next;
	    this.container = container;
	}
	public E getElement() throws IllegalStateException {
	    if( this.next == null ) // convention for defunct node
		throw new IllegalStateException( "Position no longer valid" );
	    return element;
	}
	public Node<E> getPrevious() { return this.prev; }
	public Node<E> getNext() { return this.next; }
	public Object getContainer() { return this.container; }
	public void setElement( E e ) { this.element = e; }
	public void setPrevious( Node<E> prev ) { this.prev = prev; }
	public void setNext( Node<E> next ) { this.next = next;	}
	public String toString() {
	    return "Position( element = " + this.getElement() + " )";
	}
    } //----- end of inner class Node -----
    // instance attributes
    private Node<E> header;  // header sentinel
    private Node<E> trailer; // trailer sentinel
    private int size = 0;    // number of elements in the list
    
    // PositionalList developer methods
    private Node<E> getHead() { return this.header.getNext(); }
    private Node<E> getTail() { return this.trailer.getPrevious(); }
    // validate a position and return it as a node
    private Node<E> validate( Position<E> p ) throws IllegalArgumentException {
	if( !( p instanceof Node ) ) throw new IllegalArgumentException( "Invalid position" );
	if( p.getContainer() != (Object)this ) throw new IllegalArgumentException( "Invalid container" );
	Node<E> node = (Node<E>) p; // safe cast
	if( node.getNext() == null ) // convention for defunct node
	    throw new IllegalArgumentException( "position has been removed from the list" );
	return node;
    }
    // construct an new empty list
    public LinkedPositionalList() {
	this.header = new Node<>( null, null, null, null ); // create header sentinel
	this.trailer = new Node<>( null, this.header, null, null ); // create trailer sentinel preceded by header
	this.header.setNext( this.trailer ); // link header to trailer
    }
    //----- inner position iterator class
    private class PositionIterator implements Iterator<Position<E>> {
	private Node<E> current = getHead(); // reference to next element
	private Node<E> last = null; // reference to the last accessed element
	public boolean hasNext() { return this.current != null; }
	public Position<E> next() throws NoSuchElementException {
	    if( this.current == null ) throw new NoSuchElementException( "No more position" );
	    this.last = current; // element to be removed (if remove is called)
	    Position<E> pos = position( current );
	    if( this.current == trailer.getPrevious() )
		this.current = null; // special case to handle trailer sentinel
	    else this.current = current.getNext();
	    return pos;
	}
	public void remove() { // remove current node from list
	    if( this.last == null ) throw new NoSuchElementException( "No position to remove" );
	    LinkedPositionalList.this.remove( position( this.last ) ); // remove the last accessed node from the list
	    this.last = null; // no position to remove until next() is called
	}
    } //----- end of inner position iterator class
    //----- inner element iterator class
    private class ElementIterator implements Iterator<E> {
	Iterator<Position<E>> posIterator = new PositionIterator();
	public boolean hasNext() { return posIterator.hasNext(); }
	public E next() { return posIterator.next().getElement(); }
	public void remove() { posIterator.remove(); }
    } //----- end of inner element iterator class
    // return an element iterator of the list
    public Iterator<E> iterator() { return new ElementIterator(); }
    //----- inner position iterable class
    private class PositionIterable implements Iterable<Position<E>> {
	// return a position iterator of the list
	public Iterator<Position<E>> iterator() { return new PositionIterator(); }
    } //----- end of inner position iterable class
    // return a position iterable of the list
    public Iterable<Position<E>> positions() {
	return new PositionIterable();
    }
    // return the given node's position, or null if it is a sentinel
    private Position<E> position( Node<E> node ) {
	if( node == this.header || node == this.trailer )
	    return null;
	return node;
    }
    // add element e to the list between the given nodes
    private Position<E> addBetween( E e, Node<E> pred, Node<E> succ ) {
	Node<E> novel = new Node<>( e, pred, succ, (Object)this ); // create and link the new node
	pred.setNext( novel );
	succ.setPrevious( novel );
	this.size++;
	return novel;
    }
    // interface PositionalList methods
    // return the number of elements in the list
    public int size() { return this.size; }
    // return a boolean indicating if the list is empty or not
    public boolean isEmpty() { return this.size == 0; };
    // return the first element of the list
    public Position<E> first() { return this.position( this.header.getNext() ); }
    // return the last element of the list
    public Position<E> last() { return this.position( this.trailer.getPrevious() ); }
    // return the Position immediately before Position p, or null if p is first
    public Position<E> before( Position<E> p ) throws IllegalArgumentException {
	Node<E> node = this.validate( p );
	return this.position( node.getPrevious() );
    }
    // return the Position immediately after Position p, or null if p is last
    public Position<E> after( Position<E> p ) throws IllegalArgumentException {
	Node<E> node = this.validate( p );
	return this.position( node.getNext() );
    }
    // insert element e at the front of the list and return its position
    public Position<E> addFirst( E e ) {
	return this.addBetween( e, this.header, this.header.getNext() );
    }
    // insert element e at the back of the list and return its position
    public Position<E> addLast( E e ) {
	return this.addBetween( e, this.trailer.getPrevious(), this.trailer );
    }
    // insert element e immediately before position p and return its position
    public Position<E> addBefore( Position<E> p, E e ) throws IllegalArgumentException {
	Node<E> node = this.validate( p );
	return this.addBetween( e, node.getPrevious(), node );
    }
    // insert element e immediately after position p and return its position
    public Position<E> addAfter( Position<E> p, E e ) throws IllegalArgumentException {
	Node<E> node = this.validate( p );
	return this.addBetween( e, node, node.getNext() );
    }
    // replace element at p and return replaced element
    public E set( Position<E> p, E e ) throws IllegalArgumentException {
	Node<E> node = this.validate( p );
	E retVal = node.getElement();
	node.setElement( e );
	return retVal;
    }
    // remove element at p and returns it (invalidate p)
    public E remove( Position<E> p ) throws IllegalArgumentException {
	Node<E> node = this.validate( p );
	Node<E> pred = node.getPrevious();
	Node<E> succ = node.getNext();
	pred.setNext( succ );
	succ.setPrevious( pred );
	this.size--;
	E retVal = node.getElement();
	node.setElement( null ); // garbage collection
	node.setNext( null );    // convention for defunct node
	node.setPrevious( null );    // unlink completely
	return retVal;
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
    // insertion sort O(n^2) of the list into natural ordering of its elements
    public void sort() {
	Position<E> marker = this.first();
	while( marker != this.last() ) {
	    Position<E> pivot = this.after( marker );
	    E value = pivot.getElement();
	    if( value.compareTo( marker.getElement() ) > 0 )
		marker = pivot;
	    else {
		Position<E> walk = marker;
		while( walk != this.first() && value.compareTo( this.before( walk ).getElement() ) < 0 )
		    walk = this.before( walk );
		this.remove( pivot );
		this.addBefore( walk, value );
	    }
	}
    }
}
