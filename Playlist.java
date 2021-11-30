import ca.umontreal.pqueues.HeapAdaptablePriorityQueue;

public class Playlist {

    // PQ represents the playlist.
    private HeapAdaptablePriorityQueue<Integer, Song> playlist;
    
    public Playlist(){
        this.playlist = new HeapAdaptablePriorityQueue<>();
        
    }

    public int size (){
        return this.playlist.size();
    }
    
}
