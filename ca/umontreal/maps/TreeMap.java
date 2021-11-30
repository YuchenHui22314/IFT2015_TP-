package ca.umontreal.maps;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.Comparator;

import ca.umontreal.adt.list.Position;
import ca.umontreal.trees.LinkedBinaryTree;

/**
* TreeMap is an implementation of the ADT SortedMap
*   using a BalanceableBinaryTree
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class TreeMap<K,V> extends AbstractSortedMap<K,V> {

    //----- inner class BalanceableBinaryTree -----
    protected static class BalanceableBinaryTree<K,V> extends LinkedBinaryTree<Entry<K,V>> {

	//----- inner class BSTNode -----
	//  extends inherited LinkedBinaryTree.Node class
	protected static class BSTNode<E> extends Node<E> {
	    int aux = 0; // auxiliary field
	    BSTNode( E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild, Object container ) {
		super( e, parent, leftChild, rightChild, container );
	    }
	    public int getAux() { return aux; }
	    public void setAux( int value ) { aux = value; }
	}
	//----- end of inner class BSTNode -----

	// positional-based methods related to aux field
	public int getAux( Position<Entry<K,V>> p ) {
	    return( ( BSTNode<Entry<K,V>> ) p ).getAux();
	}
	public void setAux( Position<Entry<K,V>> p, int value ) {
	    ( ( BSTNode<Entry<K,V>> ) p ).setAux( value );
	}

	// override createNode to produce BSTNode (rather than LinkedBinaryTree.Node)
	// override 的对象是linkedBinaryTree，因为lBT里面有createNode方法。这有些奇怪
	@Override
	protected Node<Entry<K,V>> createNode( Entry<K,V> e, Node<Entry<K,V>> parent,
					       Node<Entry<K,V>> left, Node<Entry<K,V>> right ) {
	    return new BSTNode<>( e, parent, left, right, this );
	}
	// relink a parent node with its oriented child node
	private void relink( Node<Entry<K,V>> parent, Node<Entry<K,V>> child, boolean makeLeftChild ) {
	    child.setParent( parent );
	    if( makeLeftChild )
		parent.setLeft( child );
	    else
		parent.setRight( child );
	}
	// rotate Position p above its parent
	public void rotate( Position<Entry<K,V>> p ) {
	    Node<Entry<K,V>> x = validate( p );
	    Node<Entry<K,V>> y = x.getParent(); // assume it exists
	    Node<Entry<K,V>> z = y.getParent(); // grandparent (possibly null)
	    if( z == null ) {
		root = x; // x becomes root of the tree
		x.setParent( null );
	    } else
		relink( z, x, y == z.getLeft() ); // x becomes direct child of z
	    // now rotate x and y, including transfer of middle tree
	    if( x == y.getLeft() ) {
		relink( y, x.getRight(), true ); // x's right child becomes y's left
		relink( x, y, false );           // y becomes x's right child
	    }
	    else {
		relink( y, x.getLeft(), false ); // x's left child becomes y's right
		relink( x, y, true );            // y becomes x's left child
	    }
	}
	// trinode restructuring of Position x with its parent/grandparent
	public Position<Entry<K,V>> restructure( Position<Entry<K,V>> x ) {
	    Position<Entry<K,V>> y = parent( x );
	    Position<Entry<K,V>> z = parent( y );
	    if( ( x == right( y ) ) == ( y == right( z ) ) ) { // matching alignment
		rotate( y );                                   // single rotation (of y)
		return y;                                      // y is new subtree root
	    } else {                                           // opposite alignments (zig-zag)
		rotate( x );                                   // double rotation (of x)
		rotate( x );
		return x;                                      // x is new subtree root
	    }
	}
    } // ----- End of nested BalanceableBinaryTree class -----

    // underlying physical structure
    protected BalanceableBinaryTree<K,V> tree = new BalanceableBinaryTree<>();

    // constructor
    public TreeMap() {
	super(); // AbstractSortedMap constructor
	tree.addRoot( null ); // create a sentinel leaf as root
    }
    public TreeMap( Comparator<K> comp ) {
	super( comp ); // AbstractSortedMap constructor
	tree.addRoot( null ); // create a sentinel leaf as root
    }

    // override size to account sentinel
    public int size() {	return( tree.size() - 1 ) / 2; } // only internal nodes have entries

    // utility for when inserting a new entry at a leaf
    private void expandExternal( Position<Entry<K,V>> p, Entry<K,V> entry ) {
	tree.set( p, entry ); // store new entry at p
	tree.addLeft( p, null ); // add new sentinel leaves as children
	tree.addRight( p, null );
    }

    // protected methods as shorthands to wrap operations on the underlying linked binary tree.
    protected Position<Entry<K,V>> root() { return tree.root(); }
    protected Position<Entry<K,V>> parent( Position<Entry<K,V>> p ) { return tree.parent( p ); }
    protected Position<Entry<K,V>> left( Position<Entry<K,V>> p ) { return  tree.left( p ); }
    protected Position<Entry<K,V>> right( Position<Entry<K,V>> p ) { return tree.right( p ); }
    protected Position<Entry<K,V>> sibling( Position<Entry<K,V>> p ) { return tree.sibling( p ); }
    protected boolean isRoot( Position<Entry<K,V>> p ) { return tree.isRoot( p ); }
    protected boolean isExternal( Position<Entry<K,V>> p ) { return tree.isExternal( p ); }
    protected boolean isInternal( Position<Entry<K,V>> p ) { return tree.isInternal( p ); }
    protected void set( Position<Entry<K,V>> p, Entry<K,V> e ) { tree.set( p, e ); }
    protected Entry<K,V> remove( Position<Entry<K,V>> p ) { return tree.remove( p ); }
    protected void rotate( Position<Entry<K,V>> p ) { tree.rotate( p ); }
    protected Position<Entry<K,V>> restructure( Position<Entry<K,V>> x ) { return tree.restructure( x ); }

    // return the position in p's subtree having given key, or else the terminal leaf
    private Position<Entry<K,V>> treeSearch( Position<Entry<K,V>> p, K key ) {
	if( isExternal( p ) ) return p; // key not found, return the final leaf
	int comp = compare( key, p.getElement() );
	if( comp == 0 ) return p; // key found, return its position
	else if( comp < 0 ) return treeSearch( left( p ), key ); // search left subtree
	else return treeSearch( right( p ), key ); // search right subtree
    }
    // return the value associated with the specified key, or else null
    public V get( K key ) throws IllegalArgumentException {
	checkKey( key ); // may throw IllegalArgumentException
	Position<Entry<K,V>> p = treeSearch( root(), key );
	rebalanceAccess( p ); // hook for balanced tree subclasses
	if( isExternal( p ) ) return null; // not found
	return p.getElement().getValue(); // found
    }
    // return whether an entry with key exists in this map
    public boolean containsKey( K key ) { return this.get( key ) != null; }
    // associate the given value and key, returning any overridden value
    public V put( K key, V value ) throws IllegalArgumentException {
	checkKey( key ); // may throw IllegalArgumentException
	Entry<K,V> newEntry = new MapEntry<>( key, value );
	Position<Entry<K,V>> p = treeSearch( root(), key );
	if( isExternal( p ) ) { // key is new
	    expandExternal( p, newEntry );
	    rebalanceInsert( p ); // hook for balanced tree subclasses
	    return null;
	} else {
	    V old = p.getElement().getValue();
	    set( p, newEntry );
	    rebalanceAccess( p ); // hook for balanced tree subclasses
	    return old;
	}
    }
    // remove and return the value of the entry with key, if it exists, null otherwise
    public V remove( K key ) throws IllegalArgumentException {
	checkKey( key ); // may throw IllegalArgumentException
	Position<Entry<K,V>> p = treeSearch( root(), key );
	if( isExternal( p ) ) { // key not found
	    rebalanceAccess( p ); // hook for balanced tree subclasses
	    return null;
	} else {
	    V old = p.getElement().getValue();
	    if( isInternal( left( p ) ) && isInternal( right( p ) ) ) { // has two children
		Position<Entry<K,V>> replacement = treeMax( left( p ) );
		//也可以取比他大但离他最近的。Position<Entry<K,V>> replacement = treeMin( right( p ) );
		set( p, replacement.getElement() );
		p = replacement;
	    } // now p has at most one child
	    Position<Entry<K,V>> leaf = ( isExternal( left( p ) ) ? left( p ) : right( p ) );
	    Position<Entry<K,V>> sib = sibling( leaf );
	    remove( leaf ); // LinkedBinaryTree.remove( p )
		//删除leaf的目的是把leaf位置的sentinel变成null，方便调用LBT的remove方法。
		//因为在linkedbinarytree中没有sentinel，对于谁被promoted全由sibling是否
		//为null决定。这样把leaf搞成null，sibling自然就晋级了。
	    remove( p ); // LinkedBinaryTree.remove( p ) => sib is promoted in p's place
	    rebalanceDelete( sib ); // hook for balanced tree subclasses
	    return old;
	}
    }

    // return the position with the minimum key in subtree rooted at p
    protected Position<Entry<K,V>> treeMin( Position<Entry<K,V>> p ) {
	Position<Entry<K,V>> walk = p;
	while( isInternal(walk) )
	    walk = left( walk );
	return parent( walk ); // we want the parent of the leaf
    }
    // return the position with the maximum key in subtree rooted at p
    protected Position<Entry<K,V>> treeMax( Position<Entry<K,V>> p ) {
	Position<Entry<K,V>> walk = p;
	while( isInternal( walk ) )
	    walk = right( walk );
	return parent( walk ); // we want the parent of the leaf
    }
    // return the entry having the greatest key, or null if map is empty
    public Entry<K,V> lastEntry() {
	if( isEmpty() ) return null;
	return treeMax( root() ).getElement();
    }
        // return the entry having the smallest key, or null if map is empty
    public Entry<K,V> firstEntry() {
	if( isEmpty() ) return null;
	return treeMin( root() ).getElement();
    }
    // return the entry with greatest key less than or equal to given key, if any
    public Entry<K,V> floorEntry( K key ) throws IllegalArgumentException {
	checkKey( key ); // may throw IllegalArgumentException
	Position<Entry<K,V>> p = treeSearch( root(), key );
	if( isInternal( p ) ) return p.getElement(); // found exact match
	while( !isRoot( p ) ) {
	    if( p == right( parent( p ) ) ) return parent( p ).getElement(); // parent has next lesser key
	    else p = parent( p );
	}
	return null; // no such floor
    }
    // return the entry with least key greater than or equal to given key, if any
    public Entry<K,V> ceilingEntry( K key ) throws IllegalArgumentException {
	checkKey( key ); // may throw IllegalArgumentException
	Position<Entry<K,V>> p = treeSearch( root(), key );
	if( isInternal( p ) ) return p.getElement(); // found exact match
	while( !isRoot( p ) ) {
	    if( p == left( parent( p ) ) ) return parent( p ).getElement(); // parent has next greater key
	    else p = parent( p );
	}
	return null; // no such ceiling
    }
    // return the entry with greatest key strictly less than given key, if any
    public Entry<K,V> lowerEntry( K key ) throws IllegalArgumentException {
	checkKey( key ); // may throw IllegalArgumentException
	Position<Entry<K,V>> p = treeSearch( root(), key );
	if( isInternal( p ) && isInternal( left( p ) ) )
	    return treeMax( left( p ) ).getElement(); // this is the predecessor to p
	// otherwise, we had failed search, or match with no left child
	while( !isRoot( p ) ) {
	    if( p == right( parent( p ) ) ) return parent( p ).getElement(); // parent has next lesser key
	    else p = parent( p );
	}
	return null; // no such floor
    }
    // return the entry with the lest key strictly greater than key, if any
    public Entry<K,V> higherEntry( K key ) throws IllegalArgumentException {
	checkKey( key ); // may throw IllegalArgumentException
	Position<Entry<K,V>> p = treeSearch( root(), key );
	if( isInternal( p ) && isInternal( right( p ) ) )
	    return treeMin( right( p ) ).getElement(); // this is the successor to p
	// otherwise, we had failed search, or match with no right child
	while( !isRoot( p ) ) {
	    if( p == left( parent( p ) ) ) return parent( p ).getElement(); // parent has next higher key
	    else p = parent( p );
	}
	return null; // no such higher
    }

    // Iterators
    // return an iterable collection of all key-value entries of the map
    public Iterable<Entry<K,V>> entrySet() {
	ArrayList<Entry<K,V>> buffer = new ArrayList<>( this.size() );
	for( Position<Entry<K,V>> p : this.tree.inorder() )
	    if( isInternal( p ) ) buffer.add( p.getElement() );
	return buffer;
    }
    // return an iterable of entries with keys in range [fromKey, toKey) (toKey excluded)
    public Iterable<Entry<K,V>> subMap( K fromKey, K toKey ) {
	ArrayList<Entry<K,V>> buffer = new ArrayList<>( this.size() );
	if( compare( fromKey, toKey ) < 0 ) // ensure that fromKey < toKey
	    subMapRecurse( fromKey, toKey, root(), buffer );
	return buffer;
    }
    private void subMapRecurse( K fromKey, K toKey, Position<Entry<K,V>> p, ArrayList<Entry<K,V>> buffer ) {
	if( isInternal( p ) ) {
	    if( compare( p.getElement(), fromKey ) < 0 )
		// p's key is less than fromKey, so any relevant entries are to the right
		subMapRecurse( fromKey, toKey, right( p ), buffer );
	    else {
		subMapRecurse( fromKey, toKey, left( p ), buffer ); // first consider left subtree
		if( compare( p.getElement(), toKey ) < 0 ) { // p is within range
		    buffer.add( p.getElement() ); // add it to buffer, and consider right subtree
		    subMapRecurse( fromKey, toKey, right( p ), buffer );
		}
	}
	}
}
    // rebalance methods which do nothing, and will be overriden by subclass AVLtrees....etc.
    protected void rebalanceInsert( Position<Entry<K,V>> p ) {}
    protected void rebalanceDelete( Position<Entry<K,V>> p ) {}
    protected void rebalanceAccess( Position<Entry<K,V>> p ) {}

    public String toString() {
	if( this.isEmpty() ) return "{}";
	return "" + this.entrySet();
    }
}
