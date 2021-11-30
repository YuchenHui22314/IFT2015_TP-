package ca.umontreal.trees;

import java.lang.IllegalStateException;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.lang.Iterable;

import ca.umontreal.adt.list.Position;

/**
* LinkedBinaryTree is an implementation the ADT BinaryTree
*   providing complete functionality for the BinaryTree interface
*   all executing in O(1), but for depth( p ),
*   which is in O(dp+1), where dp is the depth of position p (see AbstractTree class)
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    //----- inner Node class
    protected static class Node<E> implements Position<E> {
	private E element; // element stored in this node
	private Node<E> parent; // reference to parent node, if any
	private Node<E> left; // reference to the left child, if any
	private Node<E> right; // reference to the right child, if any
	private Object container; // reference to the node's container
	// construct a node with element and neighbors
	public Node( E e, Node<E> parent, Node<E> left, Node<E> right, Object container) {
	    this.element = e;
	    this.parent = parent;
	    this.left = left;
	    this.right = right;
		this.container =container;
	}
	// getters
	public E getElement() { return this.element; }
	public Node<E> getParent() { return this.parent; }
	public Node<E> getLeft() { return this.left; }
	public Node<E> getRight() { return this.right; }
	public Object getContainer() { return this.container; }
	// setters
	public void setElement( E e ) { this.element = e; }
	public void setParent( Node<E> parent ) { this.parent = parent; }
	public void setLeft( Node<E> left ) { this.left = left; }
	public void setRight( Node<E> right ) { this.right = right; }
    } //----- end of inner class Node

    // Developer's method to create a new node with element e
    protected Node<E> createNode( E e, Node<E> parent, Node<E> left, Node<E> right ) {
	return new Node<E>( e, parent, left, right,this);
    }

    // LinkedBinaryTree attributes
    protected Node<E> root = null; // root of the tree, initially null
    private int size = 0; // number of nodes in the tree, initially 0

    // default constructor for an empty tree
    public LinkedBinaryTree() {}

    // developer's utilities
    // validate a position and returns its node
    protected Node<E> validate( Position<E> p ) throws IllegalArgumentException {
	if( ! ( p instanceof Node ) ) throw new IllegalArgumentException( "Invalid position type" );
	if( p.getContainer() != (Object) this ) throw new IllegalArgumentException( "Invalid position container" );
	Node<E> node = (Node<E>)p; // safe cast
	if( node.getParent() == node ) // convention for defunct node
	    throw new IllegalArgumentException( "Position has been deleted" );
	return node;
    }

    // public methods not already implemented in AbstractBinaryTree
    public int size() { return this.size; }
    public Position<E> root() { return this.root; }
    public Position<E> parent( Position<E> p ) throws IllegalArgumentException {
	Node<E> node = this.validate( p );
	return node.getParent();
    }
    public Position<E> left( Position<E> p ) throws IllegalArgumentException {
	Node<E> node = this.validate( p );
	return node.getLeft();
    }
    public Position<E> right( Position<E> p ) throws IllegalArgumentException {
	Node<E> node = this.validate( p );
	return node.getRight();
    }

    // public update methods
    // place element e at the root of an empty tree, return its position
    public Position<E> addRoot( E e ) throws IllegalStateException {
     	if( !this.isEmpty() ) throw new IllegalStateException( "Tree is not empty" );
	this.root = createNode( e, null, null, null );
	this.size = 1;
	return root;
    }
    // create a new left child of position p storing element e, return its position
    public Position<E> addLeft( Position<E> p, E e ) throws IllegalArgumentException {
	Node<E> parent = this.validate( p );
	if( parent.getLeft() != null )
	    throw new IllegalArgumentException( "p already has a left child" );
	Node<E> child = createNode( e, parent, null, null );
	parent.setLeft( child );
	this.size++;
	return child;
    }
    // create a new right child of position p storing element e, return its position
    public Position<E> addRight( Position<E> p, E e ) throws IllegalArgumentException {
	Node<E> parent = this.validate( p );
	if( parent.getRight() != null )
	    throw new IllegalArgumentException( "p already has a right child" );
	Node<E> child = createNode( e, parent, null, null );
	parent.setRight( child );
	this.size++;
	return child;
    }
    // replace element at position p with e, return the replaced element
    public E set( Position<E> p, E e ) throws IllegalArgumentException {
	Node<E> node = this.validate( p );
	E tmp = node.getElement();
	node.setElement( e );
	return tmp;
    }
    public void attach( Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2 ) throws IllegalArgumentException {
	Node<E> node = this.validate( p );
	if( isInternal( p ) ) throw new IllegalArgumentException( "p must be a leaf" );
	this.size += t1.size() + t2.size();
	if( !t1.isEmpty() ) { // attach t1 as left subtree
	    t1.root.setParent( node );
	    node.setLeft( t1.root );
	    t1.root = null;
	    t1.size = 0;
	}
	if( !t2.isEmpty() ) { // attach t2 as left subtree
	    t2.root.setParent( node );
	    node.setLeft( t2.root );
	    t2.root = null;
	    t2.size = 0;
	}
    }
    // remove the node at position p, replace it with its child, if any
    public E remove( Position<E> p ) throws IllegalArgumentException {
	Node<E> node = this.validate( p );
	if( numChildren( p ) == 2 )
	    throw new IllegalArgumentException( "p has two children" );
	Node<E> child = ( node.getLeft() != null ? node.getLeft() : node.getRight() );
	if( child != null )
	    child.setParent( node.getParent() ); // child's grandparent becomes parent
	if( node == root )
	    this.root = child; // child becomes root
	else {
	    Node<E> parent = node.getParent();
	    if( node == parent.getLeft() )
		parent.setLeft( child );
	    else
		parent.setRight( child );
	}
	this.size--;
	E tmp = node.getElement();
	node.setElement( null ); // garbage collection
	node.setLeft( null );
	node.setRight( null );
	node.setParent(node); // convention for defunct node
	return tmp;
    }
}
