package ca.umontreal.adt.queue;

/**
* Queue is an interface for queue management operations (ADT)
*   A collection of elements inserted and removed using the first-in first-out policy.
*   This interface differs from java.util.Queue.
*
* From Goodrich, Tamassia & Goldsasser
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public interface Queue<E> {
    public int     size();         // return the number of elements in the queue
    public boolean isEmpty();      // return true if the queue is empty, false otherwise
    public void    enqueue( E e ); // insert element e at the end of the queue
    public E       dequeue();      // remove and return the first element of the queue, null if empty
    public E       first();        // return the first element of the queue, null if empty
}
