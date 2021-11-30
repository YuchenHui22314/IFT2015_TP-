/**
 * MuzStream requests playlistCapacity playlistLimit numberofFillings K
 */
import ca.umontreal.adt.list.FavoritesListMTF;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MuzStream {

    public static void main(String[] args) {
        //analyse arguments
        String fileName = args[0];
        int playlistCapacity = Integer.parseInt(args[1]);
        int playlistLimit = Integer.parseInt(args[2]);
        int numberofFillings = Integer.parseInt(args[3]);
        int topK = Integer.parseInt(args[4]);

        //total time passed since the application is launched
        int timePassed;
        
        //list for stocking all the songs in the request file
        ArrayList<Song> songList = new ArrayList<>();
        
        //create a playlist.
        Playlist playlist = new Playlist();

        //crate a favoriteList for TOP-K song(s)
        FavoritesListMTF<Song> topKList = new FavoritesListMTF<>();
        

        //fill the songlist by reading all the songs in the reauest file.
        //code inspired by SimpleFastReader.java in the package
        //ca.umontreal.maps
        
        try {
	    File input = new File( fileName );
	    Scanner reader = new Scanner( input );
	    // read the sequence
        String line;
	    while( reader.hasNextLine() ) {
		line = reader.nextLine();
        String[] infoPieces = line.split("\\t");
        System.out.println(infoPieces[0]+infoPieces[1]+infoPieces[2]);
        songList.add(new Song(infoPieces));
	    }
        } catch( FileNotFoundException e ) {
            System.out.println( "Something's wrong, file not found!" );
            e.printStackTrace();
	    }

        System.out.println(songList);

    }
}