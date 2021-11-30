package ca.umontreal.maps;

import java.util.concurrent.TimeUnit;
import ca.umontreal.adt.list.Position;

public class SkipApp {

    public static void main( String[] args ) {

	Integer maxValue = Integer.MAX_VALUE;
	Integer minValue = Integer.MIN_VALUE;
	SkipListMap<Integer,Integer> sli = new SkipListMap<>( minValue, maxValue );
	System.out.println( sli );
	sli.put( 12, 12 );
	System.out.println( sli );
	sli.put( 17, 17 );
	System.out.println( sli );
	sli.put( 55, 55 );
	System.out.println( sli );
	sli.put( 50, 50 );
	System.out.println( sli );
	sli.put( 20, 20 );
	System.out.println( sli );
	sli.put( 25, 25 );
	System.out.println( sli );
	sli.put( 44, 44 );
	System.out.println( sli );
	sli.put( 39, 39 );
	System.out.println( sli );
	sli.put( 31, 31 );
	System.out.println( sli );
	sli.put( 38, 38 );
	System.out.println( sli );
	sli.put( 42, 42 );
	System.out.println( sli );

	// test SkipList with AbstractSortedMap
	SortedMap<Integer,Integer> map = new SkipListMap<>( Integer.MIN_VALUE, Integer.MAX_VALUE );
	System.out.println( "map: " + map );
    }
}
