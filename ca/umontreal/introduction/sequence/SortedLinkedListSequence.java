package ca.umontreal.introduction.sequence;

import java.util.LinkedList;

/**
* SortedLinkedListSequence is a Sequence implementation using a sorted LinkedList
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class SortedLinkedListSequence<T extends Comparable<T>> implements Sequence<T> {

    // physical data structure
    private LinkedList<T> sequence;

    // constructor 
    public SortedLinkedListSequence() {
	this.sequence = new LinkedList<>();
    }

    // Sequence implementation
    public int  size()        { return this.sequence.size(); }
    public T    get( int i )  { return this.sequence.get( i ); }
    public void delete( T t ) { this.sequence.remove( t ); }
    public int  index( T t )  { return this.sequence.indexOf( t ); }

    // find the index of the first element greater than argument t
    // add t at the found index, or at the end of the sequence if no element was found
    //   (in this case t is the largest element of the sequence)
    public void add( T t ) {
	int insertIndex = -1;
	for( int i = 0; i < this.sequence.size(); i++ )
	    if( this.sequence.get( i ).compareTo( t ) > 0 ) {
		// found the first greater element; note the insertion index
		insertIndex = i;
		// exit loop
		break;
	    }
	// if no element found, add at the end (append)
	if( insertIndex == -1 ) this.sequence.add( t );
	// at index i otherwise
	else this.sequence.add( insertIndex, t );
    }
    
}
