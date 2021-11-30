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
 *  
 * AVLTreeMap is an implementation of the ADT SortedMap
 *   which extendds TreeMap and uses BalanceableBinaryTree
 *   增加决定子树高度的最长链所导致的树不平衡会引起树内一个（0个）或多个节点的不平衡，
 *   而这种多节点不平衡形成的原因是在决定子树高度的链增长时，该树中所有包含这个增加节点的子树
 *   高度都会+1,从而可能导致他们和兄弟树之间的高差超标. 而在树的其他部分中，当该链不成为决定高度
 *   的链时，不会出现不平衡的节点。
 *   但是解决这种不平衡只需要试图抵消最深的包含增加节点的子树的高度增加就可以了：对该子树的根节点进行旋转。旋转过后该子树的节点
 *   高度-1,而包含子树的其他高度+1的子树此时也会高度-1。由于所有之前高度增加的子树
 *   都包含这颗子树，这样之前引发的多节点不平衡问题就被解决了。
 *   
 *   反观删除动作, 判断一个删除动作是否会造成不平衡节点的产生则要观察该删除动作会导致哪些
 *   节点的左右两颗子树中相对矮的那颗高度-1。这样的观察应该由被删除的节点开始向上溯源，直到
 *   根节点为止。在一次删除动作后，观察整颗树的snapshot，我们会发现不平衡的节点数量为0/1，而
 *   这一不平衡节点就是我们在向上溯源中发现的第一个不平衡节点。不同于增加动作，删除动作的不平衡
 *   不会导致多个节点的不平衡。原因是，如果我们观察这个不平衡节点，就会发现他所代表的子树高度不
 *   由发生了删除动作的这一支所决定，而是由没有发生动作的较长的那一支所决定。所以这个不平衡节点
 *   的parents都不会是不平衡节点。然而通过旋转这个不平衡节点来消除不平衡则会导致该节点代表的子树
 *   高度-1（例：2,1  高度为2  ----删--》 2,0,  ---rotate----》1,1 高度为1.）这样则可能会
 *   导致新的不平衡的产生，所以我们在算法中需要继续向上计算，直到根节点为止。
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class AVLTreeMap<K,V> extends TreeMap<K,V> {

    // constructors
    public AVLTreeMap() { super(); }
    public AVLTreeMap( Comparator<K> comp ) { super( comp ); }

    // return the height of a given position
    public int height( Position<Entry<K,V>> p ) { return this.tree.getAux( p ); }

    // recompute the height of the given position based on its children's heights
    public void recomputeHeight( Position<Entry<K,V>> p ) {
	this.tree.setAux( p, 1 + Math.max( height( left( p ) ), height( right( p ) ) ) );
    }

    // return whether a position has balance factor between -1 and +1 inclusive
    protected boolean isBalanced( Position<Entry<K,V>> p ) {
	return Math.abs( height( left( p ) ) - height( right( p ) ) ) <= 1;
    }

    // return a child of p with height no smaller than that of the other child
    protected Position<Entry<K,V>> tallerChild( Position<Entry<K,V>> p ) {
	if( height( left( p ) ) > height( right( p ) ) ) return left( p );
	if( height( left( p ) ) < height( right( p ) ) ) return right( p );
	// equal heights
	if( isRoot( p ) ) return left( p ); // choice is irrelevant
	if( p == left( parent( p ) ) ) return left( p ); // return aligned child
	else return right( p );
    }

    // rebalance utilities after insertion or deletion
    //   traverse upward from p, perform trinode restructuring when imbalanced,
    //   continuing until balance is restored
    protected void rebalance( Position<Entry<K,V>> p ) {
	int oldHeight, newHeight;
	do {
	    oldHeight = height( p ); // not yet recalculated if internal
	    if( !isBalanced( p ) ) { // imbalance detected
		// perform trinode restructuring, setting p to resulting root,
		// and recompute new local heights
		p = restructure( tallerChild( tallerChild( p ) ) );
		recomputeHeight( left( p ) );
		recomputeHeight( right( p ) );
	    }
	    recomputeHeight( p );
	    newHeight = height( p );
	    p = parent( p );
	} while( oldHeight != newHeight && p != null );
    }

    // overrides the TreeMap rebalancing hook
    protected void rebalanceInsert( Position<Entry<K,V>> p ) { rebalance( p ); }
    protected void rebalanceDelete( Position<Entry<K,V>> p ) {
	if( !isRoot( p ) )
	    rebalance( parent( p ) );
    }

    public String toString() {
	if( this.isEmpty() ) return "{}";
	return "" + this.entrySet();
    }
}
