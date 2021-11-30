package ca.umontreal.introduction.muzik;

/**
* iTunesTrack is a class to interface iTunes tracks.
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class iTunesTrack {

    private String name;
    private String artist;
    private String composer;
    private String album;
    private String grouping;
    private String work;
    private String movementNumber;
    private String movementCount;
    private String movementName;
    private String genre;
    private String size;
    private String time;
    private String discNumber;
    private String discCount;
    private String trackNumber;
    private String trackCount;
    private String year;
    private String dateModified;
    private String dateAdded;
    private String bitRate;
    private String sampleRate;
    private String volumeAdjustment;
    private String kind;
    private String equalizer;
    private String comments;
    private String plays;
    private String lastPlayed;
    private String skips;
    private String lastSkipped;
    private String myRating;
    private String location;

    // getters
    public String getName() { return this.name; }
    public String getArtist() { return this.artist; }
    public String getComposer() { return this.composer; }
    public String getAlbum() { return this.album; }
    public String getGrouping() { return this.grouping; }
    public String getWork() { return this.work; }
    public String getMovementNumber() { return this.movementNumber; }
    public String getMovementCount() { return this.movementCount; }
    public String getMovementName() { return this.movementName; }
    public String getGenre() { return this.genre; }
    public String getSize() { return this.size; }
    public String getTime() { return this.time; }
    public String getDiscNumber() { return this.discNumber; }
    public String getDiscCount() { return this.discCount; }
    public String getTrackNumber() { return this.trackNumber; }
    public String getTrackCount() { return this.trackCount; }
    public String getYear() { return this.year; }
    public String getDateModified() { return this.dateModified; }
    public String getDateAdded() { return this.dateAdded; }
    public String getBitRate() { return this.bitRate; }
    public String getSampleRate() { return this.sampleRate; }
    public String getVolumeAdjustment() { return this.volumeAdjustment; }
    public String getKind() { return this.kind; }
    public String getEqualizer() { return this.equalizer; }
    public String getComments() { return this.comments; }
    public String getPlays() { return this.plays; }
    public String getLastPlayed() { return this.lastPlayed; }
    public String getSkips() { return this.skips; }
    public String getLastSkipped() { return this.lastSkipped; }
    public String getMyRating() { return this.myRating; }
    public String getLocation() { return this.location; }

    // setters (all with String arguments)
    public void setName( String s ) { this.name = s; }
    public void setArtist( String s ) { this.artist = s; }
    public void setComposer( String s ) { this.composer = s; }
    public void setAlbum( String s ) { this.album = s; }
    public void setGrouping( String s ) { this.grouping = s; }
    public void setWork( String s ) { this.work = s; }
    public void setMovementNumber( String s ) { this.movementNumber = s; }
    public void setMovementCount( String s ) { this.movementCount = s; }
    public void setMovementName( String s ) { this.movementName = s; }
    public void setGenre( String s ) { this.genre = s; }
    public void setSize( String s ) { this.size = s; }
    public void setTime( String s ) { this.time = s; }
    public void setDiscNumber( String s ) { this.discNumber = s; }
    public void setDiscCount( String s ) { this.discCount = s; }
    public void setTrackNumber( String s ) { this.trackNumber = s; }
    public void setTrackCount( String s ) { this.trackCount = s; }
    public void setYear( String s ) { this.year = s; }
    public void setDateModified( String s ) { this.dateModified = s; }
    public void setDateAdded( String s ) { this.dateAdded = s; }
    public void setBitRate( String s ) { this.bitRate = s; }
    public void setSampleRate( String s ) { this.sampleRate = s; }
    public void setVolumeAdjustment( String s ) { this.volumeAdjustment = s; }
    public void setKind( String s ) { this.kind = s; }
    public void setEqualizer( String s ) { this.equalizer = s; }
    public void setComments( String s ) { this.comments = s; }
    public void setPlays( String s ) { this.plays = s; }
    public void setLastPlayed( String s ) { this.lastPlayed = s; }
    public void setSkips( String s ) { this.skips = s; }
    public void setLastSkipped( String s ) { this.lastSkipped = s; }
    public void setMyRating( String s ) { this.myRating = s; }
    public void setLocation( String s ) { this.location = s; }

    // default constructor
    public iTunesTrack() { }

    // convert to Track
    public Track toTrack() {
	Track t = new Track();
	t.setAlbum( this.album );
	t.setArtist( this.artist );
	t.setTempo( "0" );
	t.setComment( this.comments );
	t.setComposer( this.composer );
	t.setEnergy( "0" );
	t.setGenre( this.genre );
	t.setKey( "0" );
	t.setNumber( this.trackNumber );
	t.setDuration( "PT" + this.time + "S" );
	t.setTitle( this.name );
	t.setYear( this.year );
	return t;
    }

    // toString formats the two first fields only
    public String toString() { return this.name + "\t" + this.artist; }
}
