/**
 * MuzStream requests playlistCapacity playlistLimit numberofFillings K
 */
import ca.umontreal.adt.list.FavoritesListMTF;
import ca.umontreal.adt.list.FavoritesList.Item;
import ca.umontreal.adt.queue.LinkedQueue;
import ca.umontreal.adt.queue.Queue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MuzStream {

    public static void main(String[] args) {
        //analyse arguments
        //String[] args = { "data/simple.input", "3", "33", "3", "2"};
        String fileName = args[0];
        int playlistCapacity = Integer.parseInt(args[1]);
        int playlistLimit = Integer.parseInt(args[2]);
        int numberofFillings = Integer.parseInt(args[3]);
        int topK = Integer.parseInt(args[4]);
        //output : firstline
        String firstLine = ("MuzStream "+fileName+" " + playlistCapacity + " " 
        + playlistLimit + " " + numberofFillings + " " + topK);

        //total time passed since the application is launched
        int timePassed = 0;
        
        //list for stocking all the songs in the request file
        Queue<Song> songList = new LinkedQueue<>();
        
        //create a playlist.
        Playlist playlist = new Playlist(playlistCapacity);

        //crate a favoriteList for TOP-K song(s)
        FavoritesListMTF<Song> topKList = new FavoritesListMTF<>();
        
        /**
         * fill the songlist by reading all the songs in the reauest file.
         * code inspired by SimpleFastReader.java in the package
         * ca.umontreal.maps
         */
        try {
	    File input = new File( fileName );
	    Scanner reader = new Scanner( input );
	    // read the sequence
        String line;
	    while( reader.hasNextLine() ) {
		line = reader.nextLine();
        String[] infoPieces = line.split("\\t");
        //debug
        //System.out.println(infoPieces[0]+infoPieces[1]+infoPieces[2]);
        songList.enqueue(new Song(infoPieces));
	    }
        } catch( FileNotFoundException e ) {
            System.out.println( "Something's wrong, file not found!" );
            e.printStackTrace();
	    }
        //firstLine of output
        System.out.println(firstLine);
        int fillTimes = 0;
        //fill cycle:
        while (true){
            boolean songListEmpty = fillPlaylist(playlist, songList); 
            fillTimes += 1;
            // if songList run out of songs, we ignore the playlistLimit
            // and exhauste the playlist and print the last Top-k
            // ##### end of execution!!!!!!!!#####
            if (songListEmpty) {
                while (playlist.size() >= 0){
                    timePassed += play(topKList, playlist, timePassed );
                }
                printTopK(topKList, topK, timePassed);
                break;
            }
            // if songList is not exhausted, we will respect the playlistLimit
            while 
            (playlist.size()*100/playlistCapacity >= playlistLimit){
                timePassed += play(topKList, playlist, timePassed);

            }
            printTopK(topKList, topK, timePassed);
            if (fillTimes >= numberofFillings) break;


        }
            
        //debug
        //System.out.println(songList);

    }
    
    /**
     * IMPORTANT : I have modified the class FavoritesListMTF
     * to adapt to our application. More specifically, I have changed
     * the visibility of the innerClass Item<E> to get directly count number.
     * I think it does not violate the priniciple of encapsulation, because
     * we implement the class Item<E> as an inner static class just 
     * for convenience. We could have implemented it as a public class independent
     * of class FavoritesListMTF, which is almost equal to what I have modified.
     */
    public static void printTopK(FavoritesListMTF<Song> sf, int k, int time){
        String result = "Top-" + k ;
        Iterable<Item<Song>> songitems= sf.getFavoritesItem(k);
        for (Item<Song> songitem : songitems) {
            int playedTimes = songitem.getCount();
            Song song = songitem.getValue();
            result += ("\n" + song.getArtist() + "\t" + song.getSongName()
            + "\t" + (time-playedTimes*song.getTime()-song.getFirstTime())/playedTimes ); 
            
        }
        System.out.println(result);
    }


    public static boolean fillPlaylist(Playlist p, Queue<Song> ss){
        while (!ss.isEmpty()){
            int length = p.size();
            if (length < p.playlistCapacity) {
               p.insert(ss.dequeue()); 
            /*}else if (length == p.playlistCapacity){
                boolean success = p.insert(ss.first());
                if (!success){
                    break;
                }else{
                    ss.dequeue();
                }*/

            }else{
                break;
            }
        }
        return ss.isEmpty();
    }

    public static int play(FavoritesListMTF<Song> flmtf, Playlist p, int currentTime){
        Song s = p.removeMin();
        int addedTime = s.getTime();
        if (s.getFirstTime() == 0){
            s.setFirstTime(currentTime);
        }
        flmtf.access(s);
        return addedTime;
    }
}