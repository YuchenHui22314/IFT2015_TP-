public class Song {
    private String artist;
    private String songName;
    private int time;

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

    @Override
    public boolean equals(Object obj) {
        Song s = (Song)obj;
        return (this.artist.equals(s.getArtist()) 
        && this.songName.equals(s.getSongName()) 
        && (this.time == s.getTime()));
    }

    

}
