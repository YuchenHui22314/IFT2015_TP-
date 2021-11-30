package ca.umontreal.introduction.sequence;

import java.util.ArrayList;

/**
* ArraySequence is an Sequence implementation using an ArrayList
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class ArrayListSequence<T extends Comparable<T>> implements Sequence<T> {

    // physical data structure
    private ArrayList<T> sequence;

    // constructor
    public ArrayListSequence() { this.sequence = new ArrayList<>(); }

    // Sequence implementation
    public int  size()        { return this.sequence.size(); }
    public T    get( int i )  { return this.sequence.get( i ); }
    public void add( T t )    { this.sequence.add( t ); }
    public void delete( T t ) { this.sequence.remove( t ); }
    public int  index( T t )  { return this.sequence.indexOf( t );
    }
}
