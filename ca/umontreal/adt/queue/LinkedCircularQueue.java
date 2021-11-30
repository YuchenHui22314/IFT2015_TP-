package ca.umontreal.adt.queue;
import ca.umontreal.adt.list.CircularlyLinkedList;

/**
* LinkedQueue is an implementation of the ADT/interface Queue using a singly linked list
*   A collection of elements inserted and removed using the first-in first-out policy.
*   All operations execute in O(1).
*
* From Goodrich, Tamassia & Goldsasser
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class LinkedCircularQueue<E> implements CircularQueue<E> {
    // attribute
    private CircularlyLinkedList<E> list = new CircularlyLinkedList<>(); // empty circular list
    public LinkedCircularQueue() {} // new circular queue based on the initially empty circular list
    public int size() { return this.list.size(); }
    public boolean isEmpty() { return this.list.isEmpty(); }
    public void enqueue( E element ) { this.list.addLast( element ); }
    public E first() { return this.list.first(); }
    public E dequeue() { return this.list.removeFirst(); }
    public void rotate() { this.list.rotate(); }
    public String toString() { return this.list.toString(); }
}
