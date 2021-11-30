package ca.umontreal.adt.stack;
import ca.umontreal.adt.list.SinglyLinkedList;

/**
* LinkedStack is an implementation of the ADT/interface Stack using a SinglyLinkedList
*   A collection of elements inserted and removed using the last-in first-out policy.
*   All operations execute in O(1).
*
* From Goodrich, Tamassia & Goldsasser
*
*   Adapter design pattern of ADT List
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class LinkedStack<E> implements Stack<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>(); // empty list
    public LinkedStack() { }                                      // construct stack based on list
    public int     size() { return( this.list.size() ); }         // return the number of elements in the stack
    public boolean isEmpty() { return( this.list.isEmpty() ); }   // return true if the stack is empty, false otherwise
    public void    push( E e ) { this.list.addFirst( e ); }       // insert element e at the top of the stack
    public E       top() { return this.list.first(); }            // return the element at the top of the stack, null if empty
    public E       pop() { return this.list.removeFirst(); }      // remove and return the element at the top of the stack, null if empty

    public String toString() {
	return this.list.toString();
    }
}

