package ca.umontreal.maps;

/*
author = "Francois Major"
copyright = "2021"
credits = "Francois Major"
license = "MIT - Major Lab, Université de Montréal"
version = "1.0"
maintainer = "Francois Major"
email = "francois.major@umontreal.ca"
status = "v1.0"
date = "November 4, 2021"
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Simplified Fasta DNA/RNA sequence reader
//   applies to files with a single sequence
//   no parsing applied to the header line
//
// Fasta format starts with header line followed
//   by multiple line that constitute the sequence
//
// example)
// > seq_name
// ACACGCTAGCTAGCTAGCTAGCTAGCTA
// CACTATCTAACGTAGCTAGCTACTAGAA
// ...
//
public class SimpleFastaReader {

    // attributes
    private String header = "";
    private String sequence = "";

    // getters
    public String getHeader() { return this.header; }
    public String getSequence() { return this.sequence; }

    // constructor based on a filename with full path
    public SimpleFastaReader( String fileName ) {
	try {
	    File input = new File( fileName );
	    Scanner reader = new Scanner( input );
	    this.header = reader.nextLine(); // read header
	    System.out.println( "header: " + this.header );
	    // read the sequence
	    String line = reader.nextLine();
	    while( reader.hasNextLine() ) {
		this.sequence += line;
		line = reader.nextLine();
	    }
	    // line contains last sequence line
	    this.sequence += line;
	} catch( FileNotFoundException e ) {
	    System.out.println( "Something's wrong, file not found!" );
	    e.printStackTrace();
	}
    }
}
