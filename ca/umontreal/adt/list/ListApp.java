package ca.umontreal.adt.list;

import java.util.Iterator;

public class ListApp {

    public static void main( String[] args ) throws Exception {
	List<String> AL = new ArrayList<>();
	AL.add( 0, "A" );
	AL.add( 0, "B" );
	System.out.println( AL );
	System.out.println( AL.get( 1 ) );
	//System.out.println( AL.set( 2, "C" ) );
	AL.add( 2, "C" );
	//AL.add( 4, "D" );
	System.out.println( AL.remove( 1 ) );
	AL.add( 1, "D" );
	AL.add( 1, "E" );
	System.out.println( AL );
	//System.out.println( AL.get( 4 ) );
	AL.add( 4, "F" );
	System.out.println( AL.set( 2, "G" ) );
	System.out.println( AL );
	System.out.println( AL.get( 2 ) );
	System.out.println( AL.remove( 2 ) );
	System.out.println( AL );
	System.out.println( AL.remove( 2 ) );
	System.out.println( AL );

	// test ArrayList iterator
	Iterator<String> itAL = AL.iterator();
	System.out.print( "list: [ " );
	while( itAL.hasNext() )
	    System.out.print( itAL.next() + " " );
	System.out.println( "]" );

	// test LinkedPositionalList
	PositionalList<Integer> PL = new LinkedPositionalList<>();
	Position p, q, r, s;
	System.out.println( "List Contents: " + PL );
	p = PL.addLast( 8 );
	System.out.println( "addLast( 8 ) Return Value: " + p + " List Contents: " + PL );
	p = PL.first();
	System.out.println( "first() Return Value: " + p + " List Contents: " + PL );
	q = PL.addAfter( p, 5 );
	System.out.println( "addAfter( p, 5 ) Return Value: " + q + " List Contents: " + PL );
	System.out.println( "before( q ) Return Value: " + PL.before( q ) + " List Contents: " + PL );
	r = PL.addBefore( q, 3 );
	System.out.println( "addBefore( q, 3 ) Return Value: " + r + " List Contents: " + PL );
	System.out.println( "r.getElement() Return Value: " + r.getElement() + " List Contents: " + PL );
	System.out.println( "before( p ) Return Value: " + PL.before( p ) + " List Contents: " + PL );
	s = PL.addFirst( 9 );
	System.out.println( "addFirst( 9 ) Return Value: " + s + " List Contents: " + PL );
	System.out.println( "remove( last() ) Return Value: " + PL.remove( PL.last() ) + " List Contents: " + PL );
	System.out.println( "set( p, 7 ) Return Value: " + PL.set( p, 7 ) + " List Contents: " + PL );
	//System.out.println( "remove( q ) Return Value: " + PL.remove( q ) + " List Contents: " + PL );

	// test foreach on the Iterable PositionalList
	for( Integer i : PL )
	    System.out.println( i );

	// test foreach on the positions of PositionalList
	System.out.println( PL );
	for( Position<Integer> thePos : PL.positions() )
	    System.out.println( thePos );

	Iterator<Position<Integer>> itpPL = PL.positions().iterator();
	while( itpPL.hasNext() ) {
	    Integer current = itpPL.next().getElement();
	    System.out.print( current );
	    if( current > 5 ) {
		itpPL.remove();
		System.out.println( " removed" );
	    }
	    else System.out.println( " kept" );
	}
	System.out.println( PL );

	// test the element iterator of PositionalList
	Iterator<Integer> itPL = PL.iterator();
	while( itPL.hasNext() ) {
	    Integer current = itPL.next();
	    System.out.print( current );
	    if( current > 5 ) {
		itPL.remove();
		System.out.println( " removed" );
	    }
	    else System.out.println( " kept" );
	}
	System.out.println( PL );

	// mixing two positional lists
	PositionalList<Integer> L1 = new LinkedPositionalList<>();
	PositionalList<Integer> L2 = new LinkedPositionalList<>();
	p = L1.addLast( 1 );
	q = L1.addLast( 2 );
	r = L2.addLast( 3 );
	s = L2.addLast( 4 );
	System.out.println( "L1: " + L1 );
	System.out.println( "L2: " + L2 );
	L1.remove( p );
	L2.remove( r );
	System.out.println( "L1: " + L1 );
	System.out.println( "L2: " + L2 );

	PositionalList<Integer> L3 = new LinkedPositionalList<>();
	L3.addLast(  1 );
	L3.addLast(  2 );
	L3.addLast(  3 );
	L3.addLast(  4 );
	L3.addLast( 22 );
	L3.addLast( 25 );
	L3.addLast( 29 );
	L3.addLast( 36 );
	L3.addLast( 23 );
	L3.addLast( 53 );
	L3.addLast( 11 );
	L3.addLast( 42 );
	System.out.println( L3 );
	L3.addLast( 15 );
	L3.sort();
	System.out.println( L3 );
    }
}
