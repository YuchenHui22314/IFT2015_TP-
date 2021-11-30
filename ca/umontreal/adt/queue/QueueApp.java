package ca.umontreal.adt.queue;

import ca.umontreal.adt.list.DoublyLinkedList;

/**
*
* From Goodrich, Tamassia & Goldsasser
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class QueueApp {
	
    public static void main( String[] args ) {

	// test array implementation
	Queue<Integer> AQ = new ArrayQueue<>();
	AQ.enqueue( 5 );
	AQ.enqueue( 3 );
	System.out.println( AQ );
	System.out.println( AQ.size() );
	System.out.println( AQ.dequeue() );
	System.out.println( AQ.isEmpty() );
	System.out.println( AQ.dequeue() );
	System.out.println( AQ.isEmpty() );
	System.out.println( AQ.dequeue() );
	AQ.enqueue( 7 );
	AQ.enqueue( 9 );
	System.out.println( AQ.first() );
	AQ.enqueue( 4 );
	System.out.println( AQ );

	// test singly linked list implementation
	Queue<Integer> LQ = new LinkedQueue<>();
	LQ.enqueue( 5 );
	LQ.enqueue( 3 );
	System.out.println( LQ );
	System.out.println( LQ.size() );
	System.out.println( LQ.dequeue() );
	System.out.println( LQ.isEmpty() );
	System.out.println( LQ.dequeue() );
	System.out.println( LQ.isEmpty() );
	System.out.println( LQ.dequeue() );
	LQ.enqueue( 7 );
	LQ.enqueue( 9 );
	System.out.println( LQ.first() );
	LQ.enqueue( 4 );
	System.out.println( LQ );

	// test linked circular queue
	CircularQueue<Integer> CQ = new LinkedCircularQueue<>();
	CQ.enqueue( 5 );
	CQ.enqueue( 3 );
	System.out.println( CQ );
	System.out.println( CQ.size() );
	System.out.println( CQ.dequeue() );
	System.out.println( CQ.isEmpty() );
	System.out.println( CQ.dequeue() );
	System.out.println( CQ.isEmpty() );
	System.out.println( CQ.dequeue() );
	CQ.enqueue( 7 );
	CQ.enqueue( 9 );
	System.out.println( CQ.first() );
	CQ.enqueue( 4 );
	System.out.println( CQ );
	CQ.rotate();
	System.out.println( CQ );

	// Josephus problem
	String[] group1 = {"Alice", "Bob", "Toto", "Titi", "Foo", "Bar"};
	String[] group2 = {"Gene", "Enzyme", "Molecule", "Ion", "Cell", "Water"};
	String[] group3 = {"IFT1015", "IFT1025", "IFT2015", "IFT2125"};
	System.out.println( "Winner is: " + Josephus.execute( Josephus.sit( group1 ), 3 ) );
	System.out.println( "Winner is: " + Josephus.execute( Josephus.sit( group2 ), 10 ) );
	System.out.println( "Winner is: " + Josephus.execute( Josephus.sit( group3 ), 7 ) );

	// test array deque
	ArrayDeque<Integer> AD = new ArrayDeque<>();
	AD.addLast( 5 );
	System.out.println( AD );
	AD.addFirst( 3 );	
	System.out.println( AD );
	AD.addFirst( 7 );
	System.out.println( AD );
	System.out.println( "first(): " + AD.first() );
	System.out.println( "removeLast(): " + AD.removeLast() );
	System.out.println( "size(): " + AD.size() );
	System.out.println( "removeLast(): " + AD.removeLast() );
	System.out.println( "removeFirst(): " + AD.removeFirst() );
	AD.addFirst( 6 );
	System.out.println( "last(): " + AD.last() );
	AD.addFirst( 8 );
	System.out.println( "isEmpty(): " + AD.isEmpty() );
	System.out.println( "last(): " + AD.last() );

	// test using doubly linked list as a deque
	Deque<Integer> D = new DoublyLinkedList<>();
	D.addLast( 5 );
	System.out.println( D );
	D.addFirst( 3 );	
	System.out.println( D );
	D.addFirst( 7 );
	System.out.println( D );
	System.out.println( "first(): " + D.first() );
	System.out.println( "removeLast(): " + D.removeLast() );
	System.out.println( "size(): " + D.size() );
	System.out.println( "removeLast(): " + D.removeLast() );
	System.out.println( "removeFirst(): " + D.removeFirst() );
	D.addFirst( 6 );
	System.out.println( "last(): " + D.last() );
	D.addFirst( 8 );
	System.out.println( "isEmpty(): " + D.isEmpty() );
	System.out.println( "last(): " + D.last() );
    }
}
