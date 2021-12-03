/*
 * MuzStream requests playlistCapacity playlistLimit numberofFillings K
 */
import ca.umontreal.adt.list.FavoritesListMTF;
import ca.umontreal.adt.list.FavoritesList.Item;
import ca.umontreal.adt.queue.LinkedQueue;
import ca.umontreal.adt.queue.Queue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class MuzStream {

    public static void main(String[] args) {
        //debug : 
        //String[] args = { "data/simple.input", "3", "33", "3", "2"};
        //analyse arguments
        String fileName = args[0];
        int playlistCapacity = Integer.parseInt(args[1]);
        int playlistLimit = Integer.parseInt(args[2]);
        int numberofFillings = Integer.parseInt(args[3]);
        int topK = Integer.parseInt(args[4]);
        //output : firstline
        String resultString = ("MuzStream "+fileName+" " + playlistCapacity + " " 
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
                resultString += printTopK(topKList, topK, timePassed);
                break;
            }
            // if songList is not exhausted, we will respect the playlistLimit
            while 
            (playlist.size()*100/playlistCapacity > playlistLimit){
                timePassed += play(topKList, playlist, timePassed);

            }
            resultString += printTopK(topKList, topK, timePassed);
            if (fillTimes >= numberofFillings) break;


        }

        try {
            output(resultString, fileName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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

    /**
     * print TopK.
     * @param sf favoritesList
     * @param k K in Top-K
     * @param time total time elapsed
     */
    public static String printTopK(FavoritesListMTF<Song> sf, int k, int time){
        String result = "\n"+"Top-" + k + ":";
        Iterable<Item<Song>> songitems= sf.getFavoritesItem(k);
        for (Item<Song> songitem : songitems) {
            int playedTimes = songitem.getCount();
            Song song = songitem.getValue();
            result += ("\n" + song.getArtist() + "\t" + song.getSongName()
            + "\t" + (time-playedTimes*song.getTime()-song.getFirstTime())/playedTimes ); 
            
        }
        return result;
    }

    /**
     * we have already read from request file and all songs are stored in 
     * the linkedQueue to be added in to the playlist sequentially.
     * @param p playlist
     * @param ss songlist, with queue structure.
     * @return if we have exhausted the songlist
     */
    public static boolean fillPlaylist(Playlist p, Queue<Song> ss){
        while (!ss.isEmpty()){
            int length = p.size();
            if (length < p.playlistCapacity) {
               p.insert(ss.dequeue()); 

               // this code segment should be used if necessary. 
               // not necessary in our case

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
    
    /**
     * this method ( fonction ) remove a fong from playlist and add it into 
     * favoriteList. It returns the duration of the track played.
     * If it is the first time we play a certain track, "currentTime" will
     * be stored in objet Song for calculating average waiting time in future.
     * 
     * @param flmtf  favorate list
     * @param p playlist
     * @param currentTime total time passed before we play the song.
     * @return time elapsed during playing
     */
    public static int play(FavoritesListMTF<Song> flmtf, Playlist p, int currentTime){
        Song s = p.removeMin();
        int addedTime = s.getTime();
        if (s.getFirstTime() == 0){
            s.setFirstTime(currentTime);
        }
        flmtf.access(s);
        return addedTime;
    }
    //write in a file.
    public static void output(String toPrint, String fileName) throws IOException{
        File file = new File(fileName+"yuchen.output");
        FileWriter writer = new FileWriter(file);
        writer.write(toPrint);
        writer.close();;
    }
}