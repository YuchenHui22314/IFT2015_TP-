package ca.umontreal.introduction.muzik;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import ca.umontreal.util.Error;

/**
* TSVTrackReader is a class to interface TSV files of Muzik tracks.
* An instance of TSVTrackReader is constructed using a file name.
* The file must be TSV format with tabs between fields.
* The instances of the file are accessed via an Iterator,
* ie using hasNext and next.
* Function next returns the next track while there are tracks in the file.
* Function hasNext returns false when there are no more tracks in the file.
* When hasNext becomes false, the file is closed.
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class TSVTrackReader implements Iterator<Track> {

    // attributes
    private String fileName;
    private FileReader reader;
    private BufferedReader bufferReader;
    private String line;

    // getter for the file name
    public String getFileName() { return this.fileName; }

    /**
     * Constructor with a file name argument.
     * The file name is kept in the instance, which also
     * uses a FileReader, a BufferedReader, and a String line as a line buffer.
     * <p>
     * This constructor assumes a TSV file with a header in the first line,
     * which is skipped. The line is set to the first line of data.
     *
     * @param  fileName   the file name with complete path
     * @see               Track
     */
    public TSVTrackReader( String fileName ) {
	this.fileName = fileName;
	try {
	    this.reader = new FileReader( this.fileName );
	} catch( FileNotFoundException e ) {
	    Error.generalError( "FileNotFound " + this.fileName );
	}
	try {
	    this.bufferReader = new BufferedReader( this.reader );
	    // read the header of the csv file (title bar)
	    this.line = this.bufferReader.readLine();
	    // position to first line of data
	    this.line = this.bufferReader.readLine();
	} catch( IOException e ) {
	    Error.generalError( "IO Exception caught" );
	}
    }

    /**
     * Constructor with file name and trackList arguments
     * Uses the Track constructor with data line.
     * @param  fileName   the file name with complete path
     * @param  trackList  an ArrayList of Tracks
     * @see               Track
     */
    public TSVTrackReader( String fileName, ArrayList<Track> trackList ) {
	this.fileName = fileName;
	try {
	    this.reader = new FileReader( this.fileName );
	} catch( FileNotFoundException e ) {
	    Error.generalError( "FileNotFound " + this.fileName );
	}
	try {
	    this.bufferReader = new BufferedReader( this.reader );
	    // read the header of the csv file (title bar)
	    this.line = this.bufferReader.readLine();
	    // read first data line
	    this.line = this.bufferReader.readLine();
	    // read all tracks in trackList
	    while( this.line != null ) {
		// parse line and add to trackList
		trackList.add( new Track( this.line ) );
		// read next line
		this.line = this.bufferReader.readLine();
	    }
	    this.bufferReader.close();
	} catch( IOException e ) {
	    Error.generalError( "IO Exception caught" );
        }
    }

    /**
     * Create and return an instance of Track that matches the current this.line.
     * Then, it modifies this.line to match the following line of data.
     *
     * @return      the Track instance corresponding to this.line
     * @see         Track
     */
    public Track next() {
	// next data are in this.line
	// assume no error in the data; empty "" strings are tolerated.
	// use the Track constructor with line argument
	Track track = new Track( this.line );
	// position the reader to the next line
	try {
	    this.line = this.bufferReader.readLine();
	} catch( IOException e ) {
	    Error.generalError( "IO Exception caught" );
	}
	// return the new Track
	return track;
    }

    /**
     * Returns true if this.line is not null, false otherwise.
     * Closes this.bufferReader if this.line is null, indicating the end of file.
     *
     * @return      true is this.line contains data, false otherwise
     */
    public boolean hasNext() {
	if( this.line != null ) return true;
	try {
	    this.bufferReader.close();
	} catch( IOException e ) {
	    Error.generalError( "Something wrong happened at closing the bufferReader" );
	}
	return false;
    }
}
