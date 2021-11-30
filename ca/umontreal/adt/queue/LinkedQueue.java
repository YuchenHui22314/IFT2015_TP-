package ca.umontreal.adt.queue;
import ca.umontreal.adt.list.SinglyLinkedList;

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
public class LinkedQueue<E> implements Queue<E> {
    // attribute
    private SinglyLinkedList<E> list = new SinglyLinkedList<>(); // empty list
    public LinkedQueue() {} // new queue based on the initially empty list
    public int size() { return this.list.size(); }
    public boolean isEmpty() { return this.list.isEmpty(); }
    public void enqueue( E element ) { this.list.addLast( element ); }
    public E first() { return this.list.first(); }
    public E dequeue() { return this.list.removeFirst(); }
    public String toString() { return this.list.toString(); }
}


