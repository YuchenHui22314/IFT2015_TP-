package ca.umontreal.trees;

import ca.umontreal.adt.list.List;
import ca.umontreal.adt.list.ArrayList;
import ca.umontreal.adt.list.Position;
import ca.umontreal.adt.queue.Queue;
import ca.umontreal.adt.queue.LinkedQueue;

import java.util.Iterator;
import java.lang.Iterable;

/**
* AbstractTree is an abstract class for the ADT Tree
*   providing some functionality for the Tree interface
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal( Position<E> p ) { return numChildren( p ) > 0; }
    public boolean isExternal( Position<E> p ) { return numChildren( p ) == 0; }
    public boolean isRoot( Position<E> p ) { return p == this.root(); }
    public boolean isEmpty() { return this.size() == 0; }
    // return the number of levels separating Position p from the root (depth of p)
    //    executes in O( depth of p + 1 ); O(n) in the worst case
    public int depth( Position<E> p ) {
	if( this.isRoot( p ) ) return 0;
	return 1 + this.depth( this.parent( p ) );
    }
    // return the height of the tree
    //    worst case execution time in O(n^2), bad!
    public int heightBad() {
	int h = 0;
	for( Position<E> p : this.positions() )
	    if( isExternal( p ) )
		h = Math.max( h, this.depth( p ) );
	return h;
    }
    // return the height of the subtree rooted at Position p
    //    worst case execution time in O(n)
    public int height( Position<E> p ) {
	int h = 0;
	for( Position<E> c : this.children( p ) )
	    h = Math.max( h, 1 + this.height( c ) );
	return h;
    }
    //----- inner element iterator class
    private class ElementIterator implements Iterator<E> {
	Iterator<Position<E>> posIterator = positions().iterator();
	public boolean hasNext() { return posIterator.hasNext(); }
	public E next() { return posIterator.next().getElement(); }
	public void remove() { posIterator.remove(); }
    } //----- end inner element iterator class
    public Iterator<E> iterator() { return new ElementIterator(); }
    // return a position iterable of the list
    public Iterable<Position<E>> positions() { return preorder(); }
    
    // add the positions of the subtree rooted at p to the given snapshot
    private void preorderSubtree( Position<E> p, List<Position<E>> snapshot ) {
	snapshot.add( p ); // for preorder, we add position p before exploring subtrees
	for( Position<E> c : children( p ) )
	    preorderSubtree( c, snapshot );
    }
    // return an iterable collection of positions of the tree, reported in preorder
    public Iterable<Position<E>> preorder() {
	List<Position<E>> snapshot = new ArrayList<>();
	if( !this.isEmpty() )
	    preorderSubtree( this.root(), snapshot ); // fill the snapshot recursively
	return snapshot;
    }
    
    // add the positions of the subtree rooted at p to the given snapshot
    private void postorderSubtree( Position<E> p, List<Position<E>> snapshot ) {
	for( Position<E> c : children( p ) )
	    postorderSubtree( c, snapshot );
	snapshot.add( p ); // for postorder, we add position p after exploring subtrees
    }
    // return an iterable collection of positions of the tree, reported in postorder
    public Iterable<Position<E>> postorder() {
	List<Position<E>> snapshot = new ArrayList<>();
	if( !this.isEmpty() )
	    postorderSubtree( this.root(), snapshot ); // fill the snapshot recursively
	return snapshot;
    }
    
    public Iterable<Position<E>> breadthfirst() {
	List<Position<E>> snapshot = new ArrayList<>();
	if( !this.isEmpty() ) {
	    Queue<Position<E>> fringe = new LinkedQueue<>();
	    fringe.enqueue( this.root() ); // start with root
	    while( !fringe.isEmpty() ) {
		Position<E> p = fringe.dequeue(); // remove from front of the queue
		snapshot.add( p ); // report this position
		for( Position<E> c : children( p ) )
		    fringe.enqueue( c ); // add children to back of queue
	    }
	}
	return snapshot;
    }
}
