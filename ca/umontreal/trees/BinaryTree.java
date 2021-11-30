package ca.umontreal.trees;

import java.lang.IllegalArgumentException;

import ca.umontreal.adt.list.Position;

/**
* BinaryTree is an interface for the ADT BinaryTree
*    in which each node has at most two children
*    uses the Position interface defined in the adt list.
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public interface BinaryTree<E> extends Tree<E> {
    // return the position of p's left child, null if no such child exists
    Position<E> left( Position<E> p ) throws IllegalArgumentException;
    // return the position of p's right child, null if no such child exists
    Position<E> right( Position<E> p ) throws IllegalArgumentException;
    // return the position of p's sibling, null if no sibling exists
    Position<E> sibling( Position<E> p ) throws IllegalArgumentException;
}
