package ca.umontreal.util;

/**
* Error is a class for ca.umontreal packages error handling.
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class Error {

    /**
     * Print an error message and exit the program with output value 1.
     *
     * @param  message   the message to be printed
     */
    public static void generalError( String message ) {
	System.out.println( message );
	System.exit( 1 );
    }
}

