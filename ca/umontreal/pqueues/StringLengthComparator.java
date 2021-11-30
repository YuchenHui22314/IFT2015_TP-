package ca.umontreal.pqueues;

import java.util.Comparator;

/**
* StringLengthComparator is class to compare the lengths of two Strings
* 
* Based on Goodrich, Tamassia & Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class StringLengthComparator implements Comparator<String> {
    // compare two strings according to their lenghts
    public int compare( String a, String b ) {
	if( a.length() < b.length() ) return -1;
	else if( a.length() == b.length() ) return 0;
	else return 1;
    }
}
