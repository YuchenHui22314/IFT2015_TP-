package ca.umontreal.adt.list;

import java.lang.IllegalStateException;

/**
* Position is an interface for the Position ADT
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public interface Position<E> {
    // return the element stored at this Position
    E getElement() throws IllegalStateException;
    // return the container of this Position
    Object getContainer();
}
