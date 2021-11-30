package ca.umontreal.introduction.muzik;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;

import ca.umontreal.introduction.sequence.*;
import ca.umontreal.adt.list.*;

public class MuzikApp {

    public static void main( String[] args ) {
	
	// comparing Sequence data structures for the tracks
	Sequence<Track> trackALSequence = new ArrayListSequence();
	Sequence<Track> trackSASequence = new SortedArrayListSequence();
	Sequence<Track> trackLLSequence = new LinkedListSequence();
	Sequence<Track> trackSLSequence = new SortedLinkedListSequence();
	Sequence<Track> trackSNSequence = new SinglyLinkedListSequence();
	Sequence<Track> trackDNSequence = new DoublyLinkedListSequence();

	// variables for the time measurements
	long startTime;

	// initial size of the track sequences
	int initialSize;

	// read a list of tracks from TSV file
	ArrayList<Track> trackList = new ArrayList<>();
	startTime = System.nanoTime();
	TSVTrackReader tracks = new TSVTrackReader( "data/tracks.tsv", trackList );
	System.out.println( "finished reading " + trackList.size() + " tracks " +
			    ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );
	
	// Compare the data structures
	System.out.println( "begin benchmarking" );

	// ArrayListSequence
	// add the tracks
	startTime = System.nanoTime();
	for( Track t : trackList ) trackALSequence.add( t );
	System.out.println( "finished adding " + trackALSequence.size() +
			    " tracks to ArrayListSequence " + ( System.nanoTime() - startTime ) / 1000000 +
			    " milliseconds" );
	for( int i = 0; i < 10; i++ )
	    System.out.println( trackALSequence.get( i ).getTitle() );

	// delete the tracks
	startTime = System.nanoTime();
	initialSize = trackALSequence.size();
	for( int i = 0; i < initialSize; i++ ) {
	    int deleteIndex = (int)( Math.random() * trackALSequence.size() );
	    trackALSequence.delete( trackALSequence.get( deleteIndex ) );
	}
	System.out.println( "finished deleting " + trackList.size() + " tracks from ArrayListSequence " +
			    ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );

	// SortedArrayListSequence
	// add the tracks
	startTime = System.nanoTime();
	for( Track t : trackList ) trackSASequence.add( t );
	System.out.println( "finished adding " + trackSASequence.size() + " tracks to SortedArrayListSequence " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );
	for( int i = 0; i < 10; i++ )
	    System.out.println( trackSASequence.get( i ).getTitle() );

	// delete the tracks
	startTime = System.nanoTime();
	initialSize = trackSASequence.size();
	for( int i = 0; i < initialSize; i++ ) {
	    int deleteIndex = (int)( Math.random() * trackSASequence.size() );
	    trackSASequence.delete( trackSASequence.get( deleteIndex ) );
	}
	System.out.println( "finished deleting " + trackList.size() + " tracks from SortedArrayListSequence " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );

	// LinkedListSequence
	// add the tracks
	startTime = System.nanoTime();
	for( Track t : trackList ) trackLLSequence.add( t );
	System.out.println( "finished adding " + trackLLSequence.size() + " tracks to LinkedListSequence " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );

	// delete the tracks
	startTime = System.nanoTime();
	initialSize = trackLLSequence.size();
	for( int i = 0; i < initialSize; i++ ) {
	    int deleteIndex = (int)( Math.random() * trackLLSequence.size() );
	    trackLLSequence.delete( trackLLSequence.get( deleteIndex ) );
	}
	System.out.println( "finished deleting " + trackList.size() + " tracks from LinkedListSequence " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );

	// SortedLinkedListSequence
	// add the tracks
	// startTime = System.nanoTime();
	// for( Track t : trackList ) trackSLSequence.add( t );
	// System.out.println( "finished adding " + trackSLSequence.size() + " tracks to SortedLinkedListSequence " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );
	// for( int i = 0; i < 10; i++ )
	//     System.out.println( trackSLSequence.get( i ).getTitle() );

	// // delete the tracks
	// startTime = System.nanoTime();
	// initialSize = trackSLSequence.size();
	// for( int i = 0; i < initialSize; i++ ) {
	//     int deleteIndex = (int)( Math.random() * trackSLSequence.size() );
	//     trackSLSequence.delete( trackSLSequence.get( deleteIndex ) );
	// }
	// System.out.println( "finished deleting " + trackList.size() + " tracks from SortedLinkedListSequence " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );

	
	// test course implemented lists

	// SinglyLinkedListSequence
	// add the tracks
	startTime = System.nanoTime();
	for( Track t : trackList ) trackSNSequence.add( t );
	System.out.println( "finished adding " + trackSNSequence.size() + " tracks to SinglyLinkedListSequence " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );
	for( int i = 0; i < 10; i++ )
	    System.out.println( trackSNSequence.get( i ).getTitle() );

	// test SinglyLinkedNode iterator
	SinglyLinkedList<Track> SN = new SinglyLinkedList();
	// copy trackList to SN
	for( Track t : trackList ) SN.addLast( t );
	Iterator<Track> iterateSN = SN.iterator();
	int count = 0;
	while( iterateSN.hasNext() ) {
	    count++;
	    iterateSN.next();
	}
	System.out.println( "Iterated through " + count + " elements in SN" );

	// test reverse speed
	startTime = System.nanoTime();
	for( int i = 0; i < 1000; i++ )
	    SN.reverse();
	System.out.println( "finished reversing 1000 x " + SN.size() + " tracks of a SinglyLinkedList " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );

	// delete the tracks
	startTime = System.nanoTime();
	initialSize = trackSNSequence.size();
	for( int i = 0; i < initialSize; i++ ) {
	    int deleteIndex = (int)( Math.random() * trackSNSequence.size() );
	    trackSNSequence.delete( trackSNSequence.get( deleteIndex ) );
	}
	System.out.println( "finished deleting " + trackList.size() + " tracks from SinglyLinkedListSequence " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );

	// DoublyLinkedListSequence
	// add the tracks
	startTime = System.nanoTime();
	for( Track t : trackList ) trackDNSequence.add( t );
	System.out.println( "finished adding " + trackDNSequence.size() + " tracks to DoublyLinkedListSequence " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );
	for( int i = 0; i < 10; i++ )
	    System.out.println( trackDNSequence.get( i ).getTitle() );

	// test DoublyLinkedNode iterator
	DoublyLinkedList<Track> DN = new DoublyLinkedList();
	// copy trackList to DN
	for( Track t : trackList ) DN.addLast( t );
	Iterator<Track> iterateDN = DN.iterator();
	count = 0;
	while( iterateDN.hasNext() ) {
	    count++;
	    iterateDN.next();
	}
	System.out.println( "Iterated through " + count + " elements in DN" );

	// delete the tracks
	startTime = System.nanoTime();
	initialSize = trackDNSequence.size();
	for( int i = 0; i < initialSize; i++ ) {
	    int deleteIndex = (int)( Math.random() * trackDNSequence.size() );
	    trackDNSequence.delete( trackDNSequence.get( deleteIndex ) );
	}
	System.out.println( "finished deleting " + trackList.size() + " tracks from DoublyLinkedListSequence " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds" );


	// test CircularlyLinkedList
	// build a player that loads n Tracks from the trackList
	// play circularly the Tracks m times

	int n = 7;
	int m = 2;
	CircularlyLinkedList<Track> pickup = new CircularlyLinkedList();
	// load
	for( int i = 0; i < n; i++ ) {
	    int index = (int)( Math.random() * trackList.size() );
	    pickup.addFirst( trackList.get( index ) );
	}
	// play
	for( int i = 0; i < m; i++ ) {
	    // play 'em all
	    System.out.println( "Pickup tour " + i );
	    for( int k = 0; k < n; k++ ) {
		System.out.println( "playing " + pickup.first().getTitle() + " by " + pickup.first().getArtist() );
		pickup.rotate();
	    }
	}

	// test iterator on the circular list
	System.out.println( "Iterate pickup:" );
	Iterator<Track> iteratePickup = pickup.iterator();
	while( iteratePickup.hasNext() )
	    System.out.println( iteratePickup.next().getTitle() );
	System.out.println( "finished benchmarking" );

	// Test recursive reverse
	System.out.println( "test reverse" );
	
	SinglyLinkedList<String> template = new SinglyLinkedList();
	template.addLast( "A" );
	template.addLast( "B" );
	System.out.println( template );
	template.recursiveReverse();
	System.out.println( template );
    }
}
