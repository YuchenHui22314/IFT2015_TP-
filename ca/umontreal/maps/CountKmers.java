package ca.umontreal.maps;

import java.lang.Iterable;
//import java.util.Map;
//import java.util.HashMap;

/**
* Maps is an interface for the ADT Maps
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class CountKmers {
    public static void main( String[] args ) {

	if( args.length == 0 ) {
	    System.out.println( "usage: CountKmers [FastaFormattedFile] [kmerMinSize] [kmerMaxSize]" );
	    System.exit( 0 );
	}

	// assume no error in input
	String fileName  = args[0];
	// find kmer distribution for sizes between kmerMinSize and kmerMaxSize
	int kmerMinSize = 21;
	int kmerMaxSize = 31;
	if( args.length > 1 ) kmerMinSize = Integer.parseInt( args[1] );
	if( args.length > 2 ) kmerMaxSize = Integer.parseInt( args[2] );
	System.out.println( "CountKmers " + fileName + " " + kmerMinSize + " " + kmerMaxSize );

	// read input sequence
	SimpleFastaReader transcript = new SimpleFastaReader( fileName );
	String sequence = transcript.getSequence();
	System.out.println( transcript.getHeader() + " has " + sequence.length() + " nucleotides" );
	// use an array for unique kmer counts
	int[] uniqCount = new int[kmerMaxSize-kmerMinSize+1];
	for( int kmerSize = kmerMinSize; kmerSize <= kmerMaxSize; kmerSize++ ) {
	    //Map<String,Integer> freq = new HashMap<>(); // java HashMapn
	    Map<String,Integer> freq = new UnsortedTableMap<>(); // our Map
	    //Map<String,Integer> freq = new ChainHashMap<>( 41 ); // our ChainHashMap
	    uniqCount[kmerSize-kmerMinSize] = 0;
	    for( int pos = 0; pos <= sequence.length() - kmerSize; pos++ ) {
		String kmer = sequence.substring( pos, pos + kmerSize );
		Integer count = freq.get( kmer );
		if( count == null ) count = 0;
		freq.put( kmer, count + 1 );
	    }
	    // the map contains entries for the distinct kmer
	    // find the most frequent of each kmerSize
	    // count the number of unique kmer for each kmerSize
	    int maxCount = 0;
	    String maxKmer = "no kmer";
	    for( String kmer : freq.keySet() ) {
		if( maxCount < freq.get( kmer ) ) {
		    maxKmer = kmer;
		    maxCount = freq.get( kmer );
		}
		if( freq.get( kmer ) == 1 ) uniqCount[kmerSize-kmerMinSize]++;
	    }
	    System.out.println( "Size = " + kmerSize +
				"; total of " + freq.size() + " distinct kmers; " +
				uniqCount[kmerSize-kmerMinSize] + " unique kmers" +
				"; most frequent kmer is " + maxKmer + " with " + maxCount + " occurrences;" );
	}
    }
}

