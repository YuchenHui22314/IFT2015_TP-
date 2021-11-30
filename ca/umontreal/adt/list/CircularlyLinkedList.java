package ca.umontreal.adt.list;

/**
* CircularlyLinkedList is an extension of the list ADT
*   additional operation rotate() which moves the first element to the end of the list
*   attribute head not used
*   use generic's framework to allow for user's desired element type
*   all operations executes in O(1) time
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class CircularlyLinkedList<E> extends SinglyLinkedList<E> {

    // List developer methods
    //   the attribute head is not used in circular lists
    //   instead, the head is the Node that follows the tail
    protected Node<E> getHead() {
	if( this.isEmpty() ) return null;
	return this.tail.getNext();
    }

    // constructor
    public CircularlyLinkedList() { } // make an initially empty circular list instance
    // interface List implementation
    public E first() { // return without removing the first element in the list, null if empty
	if( this.isEmpty() )
	    return null;
	return this.tail.getNext().getElement(); // the first element is the one after tail
    }
    public void addFirst( E element ) { // add a new element at the front of the list
	if( this.isEmpty() ) {
	    this.tail = new Node<>( element, null );
	    this.tail.setNext( this.tail ); // link to itself circularly
	} else {
	    Node<E> newNode = new Node<>( element, this.tail.getNext() );
	    this.tail.setNext( newNode );
	}
	this.size++;
    }
    public void addLast( E element ) { // add a new element at the end of the list
	this.addFirst( element );        // insert a new element at the front of the list
	this.tail = this.tail.getNext(); // new element becomes the tail!
    }
    public E removeFirst() { // remove and return the first element of the list
	if( this.isEmpty() ) return null;         // nothing to remove
	Node<E> head = this.tail.getNext();
	if( head == this.tail ) this.tail = null; // only one Node left
	else this.tail.setNext( head.getNext() ); // remove first element from the list
	size--;
	return head.getElement();
    }
    // extension
    public void rotate() { // rotate the first element to the back of the list
	if( ! this.isEmpty() )               // if empty, nothing to do
	    this.tail = this.tail.getNext(); // the old head becomes the new tail
    }
}
