package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.uniba.wiai.dsg.ajp.assignment3.Movie;

public class MovieIntegrationTest {

	private Movie movie;
	private Movie movie2;
//	private Price rPrice, nPrice, cPrice;

	@Before
	public void setUp() throws Exception {
		movie = new Movie();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testMovieConstructorWithParametersStringZero() {
		movie = new Movie("The Day After Tomorrow",0);
		movie2 = new Movie("Up",1);

		assertEquals("Film should be called The Day After Tomorow", "The Day After Tomorrow",movie.getTitle());
		assertEquals("PriceCode should be Zero(RegularPriceCode)",0,movie.getPriceCode() );
		assertEquals("PriceCode should be One(ChildrensPriceCode)",1,movie2.getPriceCode() );
		
		movie = new Movie("Your Advert here",2);
		assertEquals("PriceCode should be Two(NewReleasePriceCode)",2,movie.getPriceCode() );
	}
	@Test(expected = IllegalArgumentException.class)
	public void testMovieConstructorWithParameterPriceCodeInvalidFour() {
		movie = new Movie("Exception Handling 4",4);
//		assertEquals("Film should be called 'Exception Handling 4'", "Exception Handling 4",movie.getTitle());
//		movie.getPriceCode();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testMovieConstructorWithParameterPriceCodeInvalidMinusOne() {
		movie = new Movie("Exception Handling 4",-1);
//		assertEquals("Film should be called 'Exception Handling 4'", "Exception Handling 4",movie.getTitle());
//		movie.getPriceCode();
	}
	
	
	@Test
	public void testSetPriceCodeNewReleasePriceZero() {
		movie.setPriceCode(0);
		assertEquals("The Price should be REGULAR/0",0,movie.getPriceCode());
	}
	@Test
	public void testSetPriceCodeNewReleasePriceOne() {
		movie.setPriceCode(1);
		assertEquals("The Price should be NEW RELEASE/1",1,movie.getPriceCode());
	}
	@Test
	public void testSetPriceCodeChildrensPriceTwo() {
		movie.setPriceCode(2);
		assertEquals("The Price should be CHILDRENS/2",2,movie.getPriceCode());
	}
	@Test
	public void testSetPriceCodeChildrensPriceThree() {
		movie.setPriceCode(3);
		assertEquals("The Price should be LOW BUDGET/3",3,movie.getPriceCode());
	}
	
	@Test
	public void testGetChargeWithRegularPriceCodeAndThreeDaysRented() {
				
		movie.setPriceCode(0);
		assertEquals("Charge for 3 days on a RegularPrice should be 2", 3.5, movie.getCharge(3), 0.0);
		
	}
	@Test
	public void testGetChargeWithRegularPriceCodeAndZeroDaysRented() {
		
		movie.setPriceCode(0);
		assertEquals("Charge for 0 days on a RegularPrice should be 2", 2, movie.getCharge(0),0.0);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetPriceCodeWithIllegalArgumentFour() {
		movie.setPriceCode(4);
	}
	
	@Test
	public void testGetFrequentRenterPointsOneDay() {
		movie.setPriceCode(0);
		assertEquals("Should be one",1, movie.getFrequentRenterPoints(1));
	}

}
