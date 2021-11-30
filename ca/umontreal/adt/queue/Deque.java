package ca.umontreal.adt.queue;

/**
* Deque is an interface for deque management operations (ADT)
*   A collection of elements inserted and removed from both ends of the queue
*   This interface differs from java.util.Deque.
*
* From Goodrich, Tamassia & Goldsasser
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public interface Deque<E> {
    public int     size();          // return the number of elements in the deque
    public boolean isEmpty();       // return true if the deque is empty, false otherwise
    public E       first();         // return the first element of the deque, null if empty
    public E       last();          // return the last element of the deque, null if empty
    public void    addFirst( E e ); // insert element e at the front of the deque
    public void    addLast( E e );  // insert element e at the back of the deque
    public E       removeFirst();   // remove and return the first element of the deque, null if empty
    public E       removeLast();    // remove and return the last element of the deque, null if empty
}
