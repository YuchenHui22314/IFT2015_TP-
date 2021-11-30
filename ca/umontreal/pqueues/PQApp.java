package ca.umontreal.pqueues;

public class PQApp {

    public static void main( String[] args ) throws Exception {
	System.out.println( "UnsortedPriorityQueue:" );
	UnsortedPriorityQueue<Integer,String> UPQ = new UnsortedPriorityQueue<>();
	UPQ.insert( 5, "A" );
	System.out.println( "insert(5,A) " + UPQ );
	UPQ.insert( 9, "C" );
	System.out.println( "insert(9,C) " + UPQ );
	UPQ.insert( 3, "B" );
	System.out.println( "insert(3,B) " + UPQ );
	System.out.println( "min() " + UPQ.min() );
	System.out.println( "removeMin() " + UPQ.removeMin() );
	UPQ.insert( 7, "D" );
	System.out.println( "insert(7,D) " + UPQ );
	System.out.println( "removeMin() " + UPQ.removeMin() );
	System.out.println( "removeMin() " + UPQ.removeMin() );
	System.out.println( "removeMin() " + UPQ.removeMin() );
	System.out.println( "removeMin() " + UPQ.removeMin() );
	System.out.println( "isEmpty() " + UPQ.isEmpty() );
	
	System.out.println( "SortedPriorityQueue:" );
	SortedPriorityQueue<Integer,String> SPQ = new SortedPriorityQueue<>();
	SPQ.insert( 5, "A" );
	System.out.println( "insert(5,A) " + SPQ );
	SPQ.insert( 9, "C" );
	System.out.println( "insert(9,C) " + SPQ );
	SPQ.insert( 3, "B" );
	System.out.println( "insert(3,B) " + SPQ );
	System.out.println( "min() " + SPQ.min() );
	System.out.println( "removeMin() " + SPQ.removeMin() );
	SPQ.insert( 7, "D" );
	System.out.println( "insert(7,D) " + SPQ );
	System.out.println( "removeMin() " + SPQ.removeMin() );
	System.out.println( "removeMin() " + SPQ.removeMin() );
	System.out.println( "removeMin() " + SPQ.removeMin() );
	System.out.println( "removeMin() " + SPQ.removeMin() );
	System.out.println( "isEmpty() " + SPQ.isEmpty() );

	System.out.println( "HeapPriorityQueue:" );
	HeapPriorityQueue<Integer,String> HPQ = new HeapPriorityQueue<>();
	HPQ.insert( 5, "A" );
	System.out.println( "insert(5,A) " + HPQ );
	HPQ.insert( 9, "C" );
	System.out.println( "insert(9,C) " + HPQ );
	HPQ.insert( 3, "B" );
	System.out.println( "insert(3,B) " + HPQ );
	System.out.println( "min() " + HPQ.min() );
	System.out.println( "removeMin() " + HPQ.removeMin() );
	HPQ.insert( 7, "D" );
	System.out.println( "insert(7,D) " + HPQ );
	System.out.println( "removeMin() " + HPQ.removeMin() );
	System.out.println( "removeMin() " + HPQ.removeMin() );
	System.out.println( "removeMin() " + HPQ.removeMin() );
	System.out.println( "removeMin() " + HPQ.removeMin() );
	System.out.println( "isEmpty() " + HPQ.isEmpty() );

	System.out.println( "HeapAdaptablePriorityQueue:" );
	HeapAdaptablePriorityQueue<Integer,String> HAPQ = new HeapAdaptablePriorityQueue<>();
	Entry<Integer,String> e;
	e = HAPQ.insert( 5, "A" );
	System.out.println( "insert(5,A) " + HAPQ );
	HAPQ.insert( 9, "C" );
	System.out.println( "insert(9,C) " + HAPQ );
	HAPQ.insert( 3, "B" );
	System.out.println( "insert(3,B) " + HAPQ );
	System.out.println( "min() " + HAPQ.min() );
	HAPQ.remove( e );
	System.out.println( "remove( " + e + " ) " + HAPQ );
	e = HAPQ.insert( 7, "D" );
	System.out.println( "insert(7,D) " + HAPQ );
	HAPQ.replaceKey( e, 2 );
	System.out.println( "replaceKey( e, 2 ) " + HAPQ );
	HAPQ.replaceValue( e, "K" );
	System.out.println( "replaceValue( e, 'K' ) " + HAPQ );
	System.out.println( "min() " + HAPQ.min() );
	System.out.println( "removeMin() " + HAPQ.removeMin() );
	System.out.println( "removeMin() " + HAPQ.removeMin() );
	System.out.println( "removeMin() " + HAPQ.removeMin() );
	System.out.println( "isEmpty() " + HAPQ.isEmpty() );
    }
}
