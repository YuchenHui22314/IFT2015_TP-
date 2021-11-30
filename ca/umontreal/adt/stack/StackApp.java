package ca.umontreal.adt.stack;
import java.util.Arrays;

/**
*
* From Goodrich, Tamassia & Goldsasser
*/

public class StackApp {

    public static <E> void reverse( E[] a ) {
	Stack<E> buffer = new ArrayStack<>( a.length );
	for( int i = 0; i< a.length; i++ )
	    buffer.push( a[i] );
	for( int i = 0; i< a.length; i++ )
	    a[i] = buffer.pop();
    }

    // test if delimiters in the expression are properly balanced
    public static boolean isBalanced( String expression ) {
	final String opening = "({["; // opening delimiters
	final String closing = ")}]"; // closing delimiters
	Stack<Character> buffer = new LinkedStack<>();
	for( char c : expression.toCharArray() ) {
	    if( opening.indexOf( c ) != -1 ) // this is an opening delimiter
		buffer.push( c );
	    else if( closing.indexOf( c ) != -1 ) { // closing delimiter
		if( buffer.isEmpty() ) // no match
		    return false;
		if( closing.indexOf( c ) != opening.indexOf( buffer.pop() ) ) // mismatch
		    return false;
	    }
	}
	return buffer.isEmpty(); // were all delimiters matched?
    }

    // test if every opening tag has a matching closing tag in html String
    public static boolean isHTMLBalanced( String html ) {
	Stack<String> buffer = new LinkedStack<>();
	int j = html.indexOf( '<' ); // first < character (if any)
	while( j != -1 ) {
	    int k = html.indexOf( '>', j+1 ); // find next >
	    if( k == -1 )
		return false;
	    String tag = html.substring( j+1, k ); // strip away <>
	    if( !tag.startsWith( "/" ) )
		buffer.push( tag );
	    else {
		if( buffer.isEmpty() )
		    return false; // no tag to match
		if( !tag.substring( 1 ).equals( buffer.pop() ) )
		    return false; // mismatched tag
	    }
	    j = html.indexOf( '<', k+1 ); // find next < (if any)
	}
	return buffer.isEmpty(); // were all opening tags matched?
    }
	
    public static void main( String[] args ) {

	// test the ArrayStack implementation
	Stack<Integer> AS = new ArrayStack<>();
	AS.push(5);
	AS.push(3);
	System.out.println(AS.size( ));
	System.out.println(AS.pop( ));
	System.out.println(AS.isEmpty( ));
	System.out.println(AS.pop( ));
	System.out.println(AS.isEmpty( ));
	System.out.println(AS.pop( ));
	AS.push(7);
	AS.push(9);
	System.out.println(AS.top( ));
	AS.push(4);
	System.out.println(AS.size( ));
	System.out.println(AS.pop( ));
	AS.push(6);
	AS.push(8);
	System.out.println(AS.pop( ));

	// test the LinkedStack implementation
	Stack<Integer> LS = new LinkedStack<>();
	LS.push(5);
	LS.push(3);
	System.out.println(LS.size( ));
	System.out.println(LS.pop( ));
	System.out.println(LS.isEmpty( ));
	System.out.println(LS.pop( ));
	System.out.println(LS.isEmpty( ));
	System.out.println(LS.pop( ));
	LS.push(7);
	LS.push(9);
	System.out.println(LS.top( ));
	LS.push(4);
	System.out.println(LS.size( ));
	System.out.println(LS.pop( ));
	LS.push(6);
	LS.push(8);
	System.out.println(LS.pop( ));

	// test reverse array
	Integer[] a = {4, 8, 15, 26, 33, 52 }; // autoboxing
	String[] s = {"Jacques", "Kate", "Elios", "Jin" };
	System.out.println( "a = " + Arrays.toString( a ) );
	System.out.println( "s = " + Arrays.toString( s ) );
	reverse( a );
	reverse( s );
	System.out.println( "a = " + Arrays.toString( a ) );
	System.out.println( "s = " + Arrays.toString( s ) );

	System.out.println(  );
	System.out.println( "isBalanced( \"" + "(((..)(()){([()])}))" + "\" ): " + isBalanced( "(((..)(()){([()])}))" ) );
	System.out.println( "isBalanced( \"" + "({[])}" + "\" ): " + isBalanced( "({[])}" ) );

	String littleBoat = "\n<body>\n<center>\n<h1> The Little Boat </h1>\n</center>\n<p> The storm tossed the little\nboat like a cheap sneaker in an\nold washing machine. The three\ndrunken fishermen were used to\nsuch treatment, of course, but\nnot the tree salesman, who even as\na stowaway now felt that he\nhad overpaid for the voyage. </p>\n<ol>\n<li> Will the salesman die? </li>\n<li> What color is the boat? </li>\n<li> And what about Naomi? </li>\n</ol>\n</body>\n";
	System.out.println( "isHTMLBalanced( \"" + littleBoat + "\" ): " + isHTMLBalanced( littleBoat ) );
    }
}
