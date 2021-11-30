package ca.umontreal.adt.stack;

import java.lang.IllegalStateException;

/**
* ArrayStack is an implementation of the ADT/interface Stack using an Array
*   A collection of elements inserted and removed using the last-in first-out policy.
*   All operations execute in O(1).
*
* From Goodrich, Tamassia & Goldsasser
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class ArrayStack<E> implements Stack<E> {
    public static final int CAPACITY = 1000;    // default capacity
    private E[] data;                           // array to store the elements
    private int t = -1;                         // index for the top of the stack
    public ArrayStack() { this( CAPACITY ); }   // construct stack with default capacity
    public ArrayStack( int capacity ) {         // construct stack with given capacity
	this.data = (E[]) new Object[capacity]; // safe cast; compiler may give warning
    }
    public int     size() { return( this.t + 1 ); }           // return the number of elements in the stack
    public boolean isEmpty() { return( this.t == -1 ); }      // return true if the stack is empty, false otherwise
    public void    push( E e ) throws IllegalStateException { // insert element e at the top of the stack
	if( this.size() == this.data.length ) throw new IllegalStateException( "Full stack" );
	this.data[++this.t] = e;
    }
    public E       top() { // return the element at the top of the stack, null if empty
	if( this.isEmpty() ) return null;
	return this.data[this.t];
    }
    public E       pop() { // remove and return the element at the top of the stack, null if empty
	if( isEmpty() ) return null;
	E element = this.data[this.t];
	this.data[this.t] = null;    // for garbage collection
	this.t--;
	return element;
    }
}
