package ca.umontreal.trees;

import java.util.Iterator;
import java.lang.Iterable;
import java.lang.IllegalArgumentException;

import ca.umontreal.adt.list.Position;

/**
* Tree is an interface for the ADT Tree
*    uses the Position interface defined in the adt list.
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public interface Tree<E> extends Iterable<E> {
    Position<E> root();
    Position<E> parent( Position<E> p ) throws IllegalArgumentException;
    Iterable<Position<E>> children( Position<E> p ) throws IllegalArgumentException;
    int numChildren( Position<E> p ) throws IllegalArgumentException;
    boolean isInternal( Position<E> p ) throws IllegalArgumentException;
    boolean isExternal( Position<E> p ) throws IllegalArgumentException;
    boolean isRoot( Position<E> p ) throws IllegalArgumentException;
    int size();
    boolean isEmpty();
    Iterator<E> iterator();
    Iterable<Position<E>> positions();
}
