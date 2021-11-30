package ca.umontreal.adt.queue;

/**
* CircularQueue is an interface for circular queue management operations (ADT)
*   A collection of elements inserted and removed using the first-in first-out policy
*   with additional method rotate()
*
* From Goodrich, Tamassia & Goldsasser
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public interface CircularQueue<E> extends Queue<E> {
    // rotate the front element of the queue to the back of the queue
    //   do nothing if empty
    void rotate();
}
