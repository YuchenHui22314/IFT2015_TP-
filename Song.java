import javax.xml.namespace.QName;

public class Song implements Comparable<Song>{
    private String artist;
    private String songName;
    private int time;
    private int firstTime;
    
    public Song(String[] infos){
       if (infos.length != 3) throw new IllegalArgumentException("not a song"); 
       this.artist = infos[0];
       this.songName = infos[1];
       this.time = Integer.parseInt(infos[2]);
       this.firstTime = 0;

    }
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    public void setFirstTime(int firstTime) {
        this.firstTime = firstTime;
    }

    public int getFirstTime() {
        return firstTime;
    }

    @Override
    public boolean equals(Object obj) {
        Song s = (Song)obj;
        return (this.artist.equals(s.getArtist()) 
        && this.songName.equals(s.getSongName()) 
        && (this.time == s.getTime()));
    }
    
    //favoriteList require that song should be comparable,
    //although it is useless to be comparable.
    //So we just implement a useless method to cope with it.
    @Override
    public int compareTo(Song o) {
        return 0;
    }

    @Override
    public String toString(){
        return ("[" + this.artist + ", " 
        + this.songName + ", " + this.time + "]");
    }

    

}
