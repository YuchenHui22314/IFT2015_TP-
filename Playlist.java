import ca.umontreal.pqueues.HeapAdaptablePriorityQueue;
import ca.umontreal.pqueues.Entry;
import java.util.ArrayList;

public class Playlist {

    // PQ represents the playlist.
    private HeapAdaptablePriorityQueue<Integer, Song> playlist;
    // array for positions. We have chosen array implementation
    // instead of a linkedlist by being aware of the fact that
    // the number of access to an element (traverse) in our list 
    // is bigger than the number of delete from the list.
    // (a track may be required several times but played only once)
    
    private ArrayList <Entry<Integer,Song>> tokenArray;
    
    //capacity(upper bound)
    int playlistCapacity;

    public Playlist(int capacity){
        this.playlist = new HeapAdaptablePriorityQueue<>();
        this.tokenArray = new ArrayList<>();
        this.playlistCapacity = capacity;
    }

    public int size (){
        return this.playlist.size();
    }

    public boolean insert(Song track){
        int index = this.tokenArray.indexOf(track);
        if (index >= 0){
            Entry<Integer,Song> entry= tokenArray.get(index);
            int priorityNum = entry.getKey()-1;
            this.playlist.replaceKey(entry, priorityNum);
            return true;
        }

        //else we hava a new track that is not in the play list, and
        //we will insert it with priority of zero.
        // before that, we had better check whether we will overflow
        // the playlistCapacity after insertion. if we exceed we retrun
        //false to let MuzStream know.

        if (this.size() == this.playlistCapacity) return false;
        this.tokenArray.add(this.playlist.insert(0, track));
        return true;
    }

    public Song 


    
}
