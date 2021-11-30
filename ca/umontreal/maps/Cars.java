package ca.umontreal.maps;

import java.util.concurrent.TimeUnit;

public class Cars {

    public static void main( String[] args ) {

	CostPerformanceDatabase myCars = new CostPerformanceDatabase();
	System.out.println( myCars );
	myCars.add( 2500,  90 ); // a
	System.out.println( myCars );
	myCars.add( 3000, 100 ); // b
	System.out.println( myCars );
	myCars.add( 3500, 120 ); // c
	System.out.println( myCars );
	myCars.add( 3550, 130 ); // d
	System.out.println( myCars );
	myCars.add( 3560, 140 ); // e
	System.out.println( myCars );
	myCars.add( 3600, 150 ); // f
	System.out.println( myCars );
	myCars.add( 3700, 155 ); // g
	System.out.println( myCars );
	myCars.add( 3900, 160 ); // h
	System.out.println( myCars );
	myCars.add( 3200, 145 ); // p
	System.out.println( myCars );

	long maxCars = 500000000; // consider up to 20M cars
	int minPrice = 2000;
	int maxPrice = 250000;
	int performanceMax = 300;
	myCars = new CostPerformanceDatabase();

	long start = System.currentTimeMillis();
	for( long length = 0; length <= maxCars; length++ )
	    myCars.add( (int)(Math.random() * maxPrice + minPrice  + 1 ),
			(int)(Math.random() * performanceMax + 1 ) );
	System.out.println( "Proposed car: " + myCars.best( 5000 ) );
	System.out.println( "Time to filter " + myCars.size() + " dominant cars over " + maxCars + " is " + (System.currentTimeMillis() - start) + " milliseconds." );
	System.out.println( myCars );
    }
}
