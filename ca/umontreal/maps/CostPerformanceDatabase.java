package ca.umontreal.maps;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
* CostPerformanceDatabase is a class to maintain a maxima set
*   using a SortedMap
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class CostPerformanceDatabase {
    //SortedMap<Integer,Integer> map = new SortedTableMap<>();
    //SortedMap<Integer,Integer> map = new SkipListMap( Integer.MIN_VALUE, Integer.MAX_VALUE );
    ConcurrentSkipListMap<Integer,Integer> map = new ConcurrentSkipListMap<>();

    // construct an initially empty database
    public CostPerformanceDatabase() {}

    // return the (cost,performance) entry with largest cost not exceeding cost,
    //   or null if no entry exist with cost or less
    public Map.Entry<Integer,Integer> best( int cost ) { return this.map.floorEntry( cost ); }

    // return number of entries in the map
    public int size() { return this.map.size(); }

    // ass a new entry with given cost and performance
    public void add( int c, int p ) {
	Map.Entry<Integer,Integer> other = this.map.floorEntry( c ); // other at least as cheap
	if( other != null && other.getValue() >= p ) // if its performance is as good
	    return; // (c,p) is dominated, so ignore it
	this.map.put( c, p ); // otherwise, add (c,p) to database
	// and remove the entries that are dominated by the new one
	other = this.map.higherEntry( c ); // other is more expensive than c
	while( other != null && other.getValue() <= p ) { // if not better performance
	    this.map.remove( other.getKey() ); // remove the other entry
	    other = this.map.higherEntry( c );
	}
    }
    public String toString() { return this.map.toString(); }
}
