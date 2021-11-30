package ca.umontreal.introduction.muzik;

import java.time.Duration;

/**
* Track is the base class for Muzik tracks
* which allow an application to manipulate Muzik instances.
* A Track object encapsulates the instance information needed
* for the various Muzik applications. This
* information includes:
* <ul>
* <li>The album from where this Track is from
* <li>The artist interpreting this Track
* <li>The tempo of this Track (also referred sometimes by BPM)
* <li>A generic comment for this Track
* <li>The composer of this Track
* <li>The energy of this Track as defined by Mixed In Key
* <li>The genre of this Track defined by one of the Genre enum type in this class
* <li>The key of this Track defined by Mixed In Key's Camelot notation (to make harmonic mixing easy)
* <li>The number of this Track on the album
* <li>The duration of this Track in java.time.Duration format
* <li>The time of this Track in a String format hh:mm:ss
* <li>The title of this Track
* <li>The year this Track came out
* <li>The number of this Track on the album
* </ul>
* <p>
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class Track implements Comparable<Track> {

    // Muzik Track genres
    public enum Genre {
	Acoustic,
	African, AfroBeat, AfroHouse,
	Alternative,
	Ambient,
	Blues, ChicagoBlues, ElectricBlues,
	BlueGrass,
	Brazilian, BrazilianPop, Sertanejo, Samba, BossaNova,
	Breaks, BreakBeat,
	Christmas,
	Classical,
	Country,
	Dance, Disco, NuDisco,
	DrumNBass,
	Dubstep,
	Electro, Electronic, Electronica,
	Fado,
	Folk,
	French, FrenchPop,
	Funk,
	Garage, UKGarage,
	Gothic, GothicRock,
	Grunge,
	Hollydays,
	House, FrenchHouse, FutureHouse, DeepHouse, FunkyHouse, ProgressiveHouse, JackinHouse,
	Indie,
	Industrial,
	Jazz, AcidJazz,
	Latino, Salsa,
	Lounge,
	Metal,
	Other,
	Pop, ElectroPop, HipHop, Rap,
	Punk,
	Quebecois,
	RNB,
	Reggae, Reggaeton,
	Rock, ClassicRock, RockNRoll, DooWop,
	Slow,
	Soul,
	Smooth,
	Soundtrack,
	Techno, 
	Trance, PsyTrance, Tribal,
	Zydeco, Cajun
    }

    /**
     * Returns the Genre corresponding to a String.
     * if the String does not match any Genre, then the Genre Other is returned.
     *
     * @param genre String possibly matching on the Genre
     * @return      Genre
     * @see         Track
     */
    private Genre toGenre( String genre ) {
	for( Genre g : Genre.values() )
	    if( genre.contains( g.name() ) )
		return g;
	return Genre.Other;
    }

    // attributes
    private String   album;
    private String   artist;
    private int      tempo;
    private String   comment;
    private String   composer;
    private int      energy;
    private Genre    genre;
    private String   key;
    private int      number;
    private Duration duration;
    private String   time;
    private String   title;
    private int      year;

    /**
     * Modifies the String this.time (HH:MM:SS) to match this.duration.
     *
     * @see         Track
     */
    private void timeFromDuration() {
    	// create time String from Duration: "hh:mm:ss"
	long time = this.duration.getSeconds();
	long hh = time / 3600; time -= hh * 3600; // time in hours
	long mm = time / 60; time -= mm * 60; // time in minutes
	long ss = time;
	this.time = "";
	if( hh > 0 ) this.time += hh + ":";
	if( hh > 0 && mm < 10 ) this.time += "0" + mm + ":";
	else this.time += mm + ":";
	if( mm > 0 && ss < 10 ) this.time += "0" + ss;
	else this.time += ss;
    }

    /**
     * Modifies the Duration (PThHmMsS) this.duration to match this.time (HH:MM:SS).
     *
     * @see         Track
     */
    private void durationFromTime() {
	// create Duration from time
	// time is formatted as hh:mm:ss; mm:ss if hh is 0; ss if mm is 0
	String[] time = this.time.split( ":" );
	// case hh:mm:ss
	String parsedTime = "";
	if( time.length == 3 ) parsedTime = "PT" + time[0] + "H" + removeLeadingZeroes( time[1] ) + "M" + removeLeadingZeroes( time[2] ) + "S";
	if( time.length == 2 ) parsedTime = "PT" + time[0] + "M" + removeLeadingZeroes( time[1] ) + "S";
	if( time.length == 1 ) parsedTime = "PT" + time[0] + "S";
	this.duration = Duration.parse( parsedTime );
    }

    /**
     * Returns a String of digits freed from the leading zeroes.
     * ex) "001234" -> "1234"
     *
     * @param str   String to be freed of its zeroes
     * @return      String
     */
    public static String removeLeadingZeroes( String digitString ) {
	String pattern = "^0+(?!$)";
	digitString = digitString.replaceAll( pattern, "" );
	return digitString;
    }

    // getters
    public String   getAlbum()    { return this.album; }
    public String   getArtist()   { return this.artist; }
    public int      getTempo()    { return this.tempo; }
    public String   getComment()  { return this.comment; }
    public String   getComposer() { return this.composer; }
    public int      getEnergy()   { return this.energy; }
    public Genre    getGenre()    { return this.genre; }
    public String   getKey()      { return this.key; }
    public int      getNumber()   { return this.number; }
    public Duration getDuration() { return this.duration; }
    public String   getTime()     { return this.time; }
    public String   getTitle()    { return this.title; }
    public int      getYear()     { return this.year; }

    // setters (all with String arguments)
    public void setAlbum( String album )       { this.album = album; }
    public void setArtist( String artist )     { this.artist = artist; }
    public void setTempo( String tempo )       { if( !tempo.equals( "" ) ) this.tempo = Integer.parseInt( tempo ); else this.tempo = 0; }
    public void setComment( String comment )   { this.comment = comment; }
    public void setComposer( String composer ) { this.composer = composer; }
    public void setEnergy( String energy )     { this.energy = Integer.parseInt( energy ); }
    public void setGenre( String genre )       { this.genre = toGenre( genre ); }
    public void setKey( String key )           { this.key = key; }
    public void setNumber( String number )     { if( !number.equals( "" ) ) this.number = Integer.parseInt( number ); else this.number = 0; }
    public void setDuration( String duration ) { this.duration = Duration.parse( duration ); timeFromDuration(); }
    public void setTime( String time )         { this.time = time; durationFromTime(); }
    public void setTitle( String title )       { this.title = title; }
    public void setYear( String year )         { if( !year.equals( "" ) )this.year = Integer.parseInt( year ); else this.year = 0; }

    // default constructor
    public Track() { }
    
    // constructor by arguments
    public Track( String album,
		  String artist,
		  int tempo,
		  String comment,
		  String composer,
		  int energy,
		  Genre genre,
		  String key,
		  int number,
		  Duration duration,
		  String title,
		  int year ) {
	this.album = album;
	this.artist = artist;
	this.tempo = tempo;
	this.comment = comment;
	this.composer = composer;
	this.energy = energy;
	this.genre = genre;
	this.key = key;
	this.number = number;
	this.duration = duration;
	this.timeFromDuration();
	this.title = title;
	this.year = year;
    }

    // constructor by TSV String line
    public Track( String line ) {
	// parse a Track CSV line:
	// Deeper / Disco Stew	Anil Chawla, Dale Anderson	126	Purchased at Beatport.com		6	House	5A	0	9:06	Disco Stew (Original Mix)	2006
	// 12 data pieces seperated by tabs: album, artist, tempo, comment, composer, energy, genre, key, number, time, title, year
	String[] data = line.split( "\t", 12 );
	this.album = data[0];
	this.artist = data[1];
	this.setTempo( data[2] );
	this.comment = data[3];
	this.composer = data[4];
	this.setEnergy( data[5] );
	this.setGenre( data[6] );
	this.key = data[7];
	this.setNumber( data[8] );
	this.setTime( data[9] );
	this.durationFromTime();
	this.title = data[10];
	this.setYear( data[11] );
    }

    /**
     * Returns the result of compareTo between the title of this Track
     *   and that of the argument.
     *
     * @param   t   other Track instance
     * @return      0 if title of this Track equals that of the argument
     *              positive int if it is greater
     *              negative int if it is smaller
     * @see         Track
     */
    public int compareTo( Track t ) {
	return this.title.compareTo( t.getTitle() );
    }

    /**
     * Returns true if the title of this Track equals that of the argument,
     *   false otherwise
     *
     * @param   s   String to match the title
     * @return      title of this Track equals that of the argument
     * @see         Track
     */
    public boolean equals( Track t ) {
	return this.title.equals( t.getTitle() );
    }

    /**
     * Returns a String for this Track's information to be both human readable and CSV formatted.
     *
     * @return      a String with the info of this Track
     * @see         Track
     */
    public String toString() {
	return
	    this.album + "\t" +
	    this.artist + "\t" +
	    this.tempo + "\t" +
	    this.comment + "\t" +
	    this.composer + "\t" +
	    this.energy + "\t" +
	    this.genre + "\t" +
	    this.key + "\t" +
	    this.number + "\t" +
	    this.time + "\t" +
	    this.title + "\t" +
	    this.year;
    }
}
