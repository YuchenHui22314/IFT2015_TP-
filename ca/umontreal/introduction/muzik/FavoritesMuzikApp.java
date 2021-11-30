package ca.umontreal.introduction.muzik;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;

import ca.umontreal.adt.list.FavoritesList;
import ca.umontreal.adt.list.FavoritesListMTF;

public class FavoritesMuzikApp {

    public static void main( String[] args ) {
	
	// read a list of tracks from TSV file
	ArrayList<Track> trackList = new ArrayList<>();
	TSVTrackReader tracks = new TSVTrackReader( "data/tracks.tsv", trackList );
	
	// test FavoritesList
	// build a player that loads n Tracks from the trackList
	// play m randomly selected Tracks from the n loaded
	// get the list of k favorites tracks

	int n = 5;
	int m = 10;
	int k = 5;
	FavoritesList<Track> pickup = new FavoritesList<>();
	ArrayList<Track> playList = new ArrayList<>();
	// load, first access makes counts = 1 for all tracks
	System.out.println( "Playing this week:" );
	for( int i = 0; i < n; i++ ) {
	    int index = (int)( Math.random() * trackList.size() );
	    Track t = trackList.get( index );
	    System.out.println( i + ". " + t.getTitle() + " by " + t.getArtist() );
	    playList.add( t );
	    pickup.access( t );
	}
	for( int i = 0; i < m; i++ ) {
	    int index = (int)( Math.random() * n );
	    pickup.access( playList.get( index ) );
	    System.out.println( "playing song #" + index + ": " + playList.get( index ).getTitle() );
	}
	int rank = 1;
	System.out.println( "Top-" + k + " songs this week: " );
	for( Track t : pickup.getFavorites( k ) )
	    System.out.println( rank++ + ". " + t.getTitle() + " by " + t.getArtist() );

	// compare sorted list vs move-to-front heuristic

	// sorted list
	int nn = 10000;
	int mm = 1000000;
	int kk = 5;
	FavoritesList<Track> pickup2 = new FavoritesList<>();
	ArrayList<Track> playList2 = new ArrayList<>();
	// load, first access makes counts = 1 for all tracks
	long startTime = System.nanoTime();
	for( int i = 0; i < nn; i++ ) {
	    int index = (int)( Math.random() * trackList.size() );
	    Track t = trackList.get( index );
	    playList2.add( t );
	    pickup2.access( t );
	}
	for( int i = 0; i < mm; i++ ) {
	    int index = (int)( Math.random() * nn );
	    pickup2.access( playList2.get( index ) );
	}
	int rank2 = 1;
	System.out.println( "Top-" + kk + " songs this week: " );
	for( Track t : pickup2.getFavorites( k ) )
	    System.out.println( rank2++ + ". " + t.getTitle() + " by " + t.getArtist() );
	System.out.println( "for nn: " + nn + " mm: " + mm + " k: " + k + " it takes " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for a sorted list strategy" );

	FavoritesList<Track> pickup3 = new FavoritesListMTF<>();
	ArrayList<Track> playList3 = new ArrayList<>();
	// load, first access makes counts = 1 for all tracks
	startTime = System.nanoTime();
	for( int i = 0; i < nn; i++ ) {
	    int index = (int)( Math.random() * trackList.size() );
	    Track t = trackList.get( index );
	    playList3.add( t );
	    pickup3.access( t );
	}
	for( int i = 0; i < mm; i++ ) {
	    int index = (int)( Math.random() * nn );
	    pickup3.access( playList3.get( index ) );
	}
	int rank3 = 1;
	System.out.println( "Top-" + kk + " songs this week: " );
	for( Track t : pickup3.getFavorites( k ) )
	    System.out.println( rank3++ + ". " + t.getTitle() + " by " + t.getArtist() );
	System.out.println( "for n: " + nn + " m: " + mm + " k: " + k + " it takes " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for a move-to-front heuristic strategy" );
    }
}
