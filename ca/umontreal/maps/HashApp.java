package ca.umontreal.maps;

import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import java.lang.Math;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.HashMap;
import java.util.TreeMap;

public class HashApp {

    static int hashCode( String s ) {
	int h = 0;
	for( int i = 0; i < s.length(); i++ ) {
	    // << left shift
	    // >>> unsigned right shift
	    // | bitwise OR
	    h = ( h << 5 ) | ( h >>> 27 );
	    h += (int)s.charAt( i );
	}
	return h;
    }

    public static void main( String[] args ) {

	// String[] words = { "hello", "world", "how", "are", "you", "dictionary", "tune", "elephant" };
	// for( int i = 0; i < words.length; i++ )
	//     System.out.println( "hashCode( " + words[i] + " ) = " + hashCode( words[i] ) );

	long startTime;
	int numberIn = 2000000;
	//int numberIn = 200000;

	// System.out.println( "UnsortedTableMap" );
	// UnsortedTableMap<Integer,Integer> UTM = new UnsortedTableMap<>();
	// // measure insertion time
	// startTime = System.nanoTime();
	// for( int i = 0; i < numberIn; i++ )
	//      UTM.put( i, i );
	// System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for insertions" );

    	// // measure search time
	// startTime = System.nanoTime();
	// // make a number of searches
	// for( int i = 0; i < numberIn; i++ ) {
	//     int r = (int)(Math.random() * numberIn );
	//     UTM.get( r );
	// }
	// System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for searches" );

    	// // measure remove time
	// startTime = System.nanoTime();
	// // make a number of removes
	// for( int i = 0; i < numberIn; i++ ) {
	//     int r = (int)(Math.random() * numberIn );
	//     UTM.remove( r );
	// }
	// System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for removes" );

	// System.out.println( "SortedTableMap" );
	// SortedTableMap<Integer,Integer> STM = new SortedTableMap<>();
	// // measure insertion time
	// startTime = System.nanoTime();
	// for( int i = 0; i < numberIn; i++ ) {
	//     int r = (int)(Math.random() * numberIn );
	//     STM.put( r, r );
	// }
	// System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for insertions" );

    	// // measure search time
	// startTime = System.nanoTime();
	// // make a number of searches
	// for( int i = 0; i < numberIn; i++ ) {
	//     int r = (int)(Math.random() * numberIn );
	//     STM.get( r );
	// }
	// System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for searches" );

    	// // measure remove time
	// startTime = System.nanoTime();
	// // make a number of removes
	// for( int i = 0; i < numberIn; i++ ) {
	//     int r = (int)(Math.random() * numberIn );
	//     STM.remove( r );
	// }
	// System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for removes" );

	System.out.println( "SkipListMap" );
	SkipListMap<Integer,Integer> SLM = new SkipListMap<>( Integer.MIN_VALUE, Integer.MAX_VALUE );
	// measure insertion time
	startTime = System.nanoTime();
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    SLM.put( r, r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for insertions" );

    	// measure search time
	startTime = System.nanoTime();
	// make a number of searches
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    SLM.get( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for searches" );

    	// measure remove time
	startTime = System.nanoTime();
	// make a number of removes
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    SLM.remove( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for removes" );

	// System.out.println( "ConcurrentSkipListMap" );
	// ConcurrentSkipListMap<Integer,Integer> CSL = new ConcurrentSkipListMap<>();
	// // measure insertion time
	// startTime = System.nanoTime();
	// for( int i = 0; i < numberIn; i++ ) {
	//     int r = (int)(Math.random() * numberIn );
	//     CSL.put( r, r );
	// }
	// System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for insertions" );

    	// // measure search time
	// startTime = System.nanoTime();
	// // make a number of searches
	// for( int i = 0; i < numberIn; i++ ) {
	//     int r = (int)(Math.random() * numberIn );
	//     CSL.get( r );
	// }
	// System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for searches" );

    	// // measure remove time
	// startTime = System.nanoTime();
	// // make a number of removes
	// for( int i = 0; i < numberIn; i++ ) {
	//     int r = (int)(Math.random() * numberIn );
	//     CSL.remove( r );
	// }
	// System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for removes" );


	System.out.println( "ChainHashMap" );
	ChainHashMap<Integer,Integer> CHM = new ChainHashMap<>();
	// measure insertion time
	startTime = System.nanoTime();
	for( int i = 0; i < numberIn; i++ )
	    CHM.put( i, i );
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for insertions" );

    	// measure search time
	startTime = System.nanoTime();
	// make a number of searches
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    CHM.get( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for searches" );

    	// measure remove time
	startTime = System.nanoTime();
	// make a number of removes
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    CHM.remove( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for removes" );

	// ProbeHashMap
	//System.out.println( "ProbeHashMap" );
	System.out.println( "ProbeHashMap using Java HashMap" );
	//ProbeHashMap<Integer,Integer> PHM = new ProbeHashMap<>();
	HashMap<Integer,Integer> PHM = new HashMap<>();
	// measure insertion time
	startTime = System.nanoTime();
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    PHM.put( r, r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for insertions" );

    	// measure search time
	startTime = System.nanoTime();
	// make a number of searches
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    PHM.get( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for searches" );

    	// measure remove time
	startTime = System.nanoTime();
	// make a number of removes
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    PHM.remove( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for removes" );

	// TreeMap
	System.out.println( "TreeMap" );
	TreeMap<Integer,Integer> TM = new TreeMap<>();
	// measure insertion time
	startTime = System.nanoTime();
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    TM.put( r, r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for insertions" );

    	// measure search time
	startTime = System.nanoTime();
	// make a number of searches
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    TM.get( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for searches" );

    	// measure remove time
	startTime = System.nanoTime();
	// make a number of removes
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    TM.remove( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for removes" );

	// AVLTreeMap
	System.out.println( "AVLTreeMap" );
	AVLTreeMap<Integer,Integer> AVL = new AVLTreeMap<>();
	// measure insertion time
	startTime = System.nanoTime();
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    AVL.put( r, r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for insertions" );

    	// measure search time
	startTime = System.nanoTime();
	// make a number of searches
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    AVL.get( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for searches" );

    	// measure remove time
	startTime = System.nanoTime();
	// make a number of removes
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    AVL.remove( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for removes" );

	// SplayTreeMap
	System.out.println( "SplayTreeMap" );
	SplayTreeMap<Integer,Integer> STM = new SplayTreeMap<>();
	// measure insertion time
	startTime = System.nanoTime();
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    STM.put( r, r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for insertions" );

    	// measure search time
	startTime = System.nanoTime();
	// make a number of searches
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    STM.get( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for searches" );

    	// measure remove time
	startTime = System.nanoTime();
	// make a number of removes
	for( int i = 0; i < numberIn; i++ ) {
	    int r = (int)(Math.random() * numberIn );
	    STM.remove( r );
	}
	System.out.println( ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for removes" );
    }
}
