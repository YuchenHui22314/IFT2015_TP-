package ca.umontreal.introduction.sequence;

import java.util.ArrayList;

/**
* SortedArrayListSequence is an Sequence implementation using a sorted ArrayList
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class SortedArrayListSequence<T extends Comparable<T>> implements Sequence<T> {

    // physical data structure
    private ArrayList<T> sequence;

    // constructor
    public SortedArrayListSequence() {
	this.sequence = new ArrayList<>();
    }

    // Sequence implementation
    public int  size()        { return this.sequence.size(); }
    public T    get( int i )  { return this.sequence.get( i ); }
    public void delete( T t ) { this.sequence.remove( t ); }
    public int  index( T t )  { return this.sequence.indexOf( t ); }

    // sorted add: after the last item smaller of equal to t
    public void add( T t ) {
	int insertIndex = -1;
	for( int i = 0; i < this.sequence.size(); i++ )
	    if( this.sequence.get( i ).compareTo( t ) > 0 ) {
		// found the first greater element; note the insertion index
		insertIndex = i;
		// add the element at index i
		this.sequence.add( i, t );
		// exit loop
		break;
	    }
	// if no insertion index found, then append t
	if( insertIndex == -1 ) this.sequence.add( t );
    }
    
}
