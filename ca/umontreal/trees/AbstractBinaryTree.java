package ca.umontreal.trees;

import ca.umontreal.adt.list.Position;
import ca.umontreal.adt.list.List;
import ca.umontreal.adt.list.ArrayList;

/**
* AbstractBinaryTree is an abstract class for the ADT BinaryTree
*   providing some functionality for the BinaryTree interface
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    // return position p's sibling, null if no sibling exists
    public Position<E> sibling( Position<E> p ) {
	Position<E> parent = this.parent( p );
	if( parent == null ) return null; // p is the root, no sibling
	if( p == this.left( parent ) ) // p is a left child
	    return this.right( parent ); // return right child, might be null
        return this.left( parent ); // p is a right child, return left, might be null
    }
    // return the number of children of position p
    public int numChildren( Position<E> p ) {
	int count = 0;
	if( this.left( p ) != null ) count++;
	if( this.right( p ) != null ) count++;
	return count;
    }
    // return an iterable collection of the positions of p's children
    public Iterable<Position<E>> children( Position<E> p ) {
	List<Position<E>> snapshot = new ArrayList<>( 2 ); // max capacity of 2
	if( this.left( p ) != null ) snapshot.add( this.left( p ) );
	if( this.right( p ) != null ) snapshot.add( this.right( p ) );
	return snapshot;
    }

    // add positions of the subtree rooted at p to the given snapshot
    private void inorderSubtree( Position<E> p, List<Position<E>> snapshot ) {
	if( left( p ) != null )
	    inorderSubtree( left( p ), snapshot );
	snapshot.add( p );
	if( right( p ) != null )
	    inorderSubtree( right( p ), snapshot );
    }
    
    // return an iterable collection of positions of the tree, reported in inorder
    public Iterable<Position<E>> inorder() {
	List<Position<E>> snapshot = new ArrayList<>();
	if( !this.isEmpty() )
	    inorderSubtree( this.root(), snapshot );
	return snapshot;
    }
    // override positions to make inorder the default for binary trees
    public Iterable<Position<E>> positions() { return inorder(); }
}
