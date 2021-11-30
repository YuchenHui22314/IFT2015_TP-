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
* SplayTreeMap is an implementation of the ADT SortedMap
*   which extends TreeMap
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class SplayTreeMap<K,V> extends TreeMap<K,V> {

    // constructors
    public SplayTreeMap() { super(); }
    public SplayTreeMap( Comparator<K> comp ) { super( comp ); }

    // utility used to rebalance after each map operation
    private void splay( Position<Entry<K,V>> p ) {
	while( !isRoot( p ) ) {
	    Position<Entry<K,V>> parent = parent( p );
	    Position<Entry<K,V>> grandp = parent( parent );
	    if( grandp == null ) rotate( p ); // zig
	    else if( ( parent == left( grandp ) ) == ( p == left( parent ) ) ) { // zig-zig
		rotate( parent ); // move parent upward
		rotate( p ); // then move p upward
	    } else { // zig-zag
		rotate( p ); // move p upward 
		rotate( p ); // move p upward (again)
	    }
	}
    }

    // override the TreeMap rebalancing hooks to perform appropriate splay
    protected void rebalanceAccess( Position<Entry<K,V>> p ) {
	if( isExternal( p ) ) p = parent( p );
	if( p != null ) splay( p );
    }
    protected void rebalanceInsert( Position<Entry<K,V>> p ) {
	splay( p );
    }
    protected void rebalanceDelete( Position<Entry<K,V>> p ) {
	if( !isRoot( p ) ) splay( parent( p ) );
    }

    public String toString() {
	if( this.isEmpty() ) return "{}";
	return "" + this.entrySet();
    }
}
