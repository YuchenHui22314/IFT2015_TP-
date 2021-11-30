package ca.umontreal.adt.queue;
import java.util.Arrays;
import java.lang.IllegalStateException;

/**
* ArrayQueue is an implementation of the ADT/interface Queue using a (circular) Array
*   A collection of elements inserted and removed using the first-in first-out policy.
*   All operations execute in O(1).
*
* From Goodrich, Tamassia & Goldsasser
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class ArrayQueue<E> implements Queue<E> {
    // attributes
    public static final int CAPACITY = 16;      // default capacity
    protected E[] data;                         // array to store the elements
    protected int f = 0;                        // index for the front of the queue
    protected int size = 0;                     // current number of elements
    // constructors
    public ArrayQueue() { this( CAPACITY ); }   // construct queue with default capacity
    public ArrayQueue( int capacity ) {         // construct queue with given capacity
	this.data = (E[]) new Object[capacity]; // safe cast; compiler may give warning
    }
    // methods
    public int     size() { return( this.size ); }               // return the number of elements in the queue
    public boolean isEmpty() { return( this.size == 0 ); }       // return true if the queue is empty, false otherwise
    public void    enqueue( E e ) throws IllegalStateException { // insert element e at the end of the queue
	if( this.size == this.data.length ) throw new IllegalStateException( "Full queue" );
	int avail = ( this.f + this.size ) % this.data.length;   // use modulo for circularity
	this.data[avail] = e;
	this.size++;
    }
    public E       first() { // return the first element of the queue, null if empty
	if( this.isEmpty() ) return null;
	return this.data[this.f];
    }
    public E       dequeue() { // remove and return the first element of the queue, null if empty
	if( isEmpty() ) return null;
	E element = this.data[this.f];
	this.data[this.f] = null; // for garbage collection
	this.f = ( this.f + 1 ) % this.data.length;
	this.size--;
	return element;
    }
    public String toString() { return Arrays.toString( this.data ); }
}
