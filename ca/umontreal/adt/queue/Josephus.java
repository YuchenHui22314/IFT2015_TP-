package ca.umontreal.adt.queue;

/**
*
* From Goodrich, Tamassia & Goldsasser
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class Josephus {
    // find the survivor of the problem using a circular queue
    public static <E> E execute( CircularQueue<E> queue, int k ) {
	if( queue.isEmpty() ) return null;
	while( queue.size() > 1 ) {
	    System.out.println( queue );
	    for( int i = 0; i < k; i++ ) // skip past k-1 elements
		queue.rotate();
	    System.out.println( queue );
	    E e = queue.dequeue(); // remove the front element
	    System.out.println( "     " + e + " is out" );
	}
	return queue.dequeue();
    }
    // fill the queue with array elements (sit the invitees!)
    public static <E> CircularQueue<E> sit( E a[] ) {
	CircularQueue<E> queue = new LinkedCircularQueue<>();
	for( int i = 0; i < a.length; i++ )
	    queue.enqueue( a[i] );
	return queue;
    }
}
