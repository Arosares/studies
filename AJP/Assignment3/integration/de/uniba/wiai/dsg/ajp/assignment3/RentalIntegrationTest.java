package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RentalIntegrationTest {
	
	private Rental rental;
	private Movie movie;
	
	@Before
	public void setUp() throws Exception {
		rental = new Rental();
		movie = new Movie();
	}

	@After
	public void tearDown() throws Exception {
		rental = null;
		movie = null;
	}

	@Test
	public void testGetChargeWithRegularPriceCodeAndThreeDaysRented() {
		movie.setPriceCode(0);
		
		rental.setDaysRented(3);
		rental.setMovie(movie);
		
		assertEquals("Charge should be 3.5 for a RegularMovie with 3 days rented",3.5 ,rental.getCharge(),0.1);	
	}
	@Test
	public void testGetChargeWithRegularPriceCodeAndOneDaysRented() {
		movie.setPriceCode(0);
		
		rental.setDaysRented(1);
		rental.setMovie(movie);
		
		assertEquals("Charge should be 2.0 for a RegularMovie with 3 days rented",2.0 ,rental.getCharge(),0.1);	
	}
	@Test
	public void testGetChargeWithNewReleasePriceCodeAndThreeDaysRented() {
		movie.setPriceCode(1);
		
		rental.setDaysRented(3);
		rental.setMovie(movie);
		
		assertEquals("Charge should be 9.0 for a RegularMovie with 3 days rented",9.0 ,rental.getCharge(),0.1);	
	}
	@Test
	public void testGetChargeWithChildrensPriceCodeAndFourDaysRented() {
		movie.setPriceCode(2);
		
		rental.setDaysRented(4);
		rental.setMovie(movie);
		
		assertEquals("Charge should be 3.0 for a RegularMovie with 4 days rented",3.0 ,rental.getCharge(),0.1);	
	}
	@Test
	public void testGetChargeWithChildrensPriceCodeAndTwoDaysRented() {
		movie.setPriceCode(2);
		
		rental.setDaysRented(2);
		rental.setMovie(movie);
		
		assertEquals("Charge should be 1.5 for a RegularMovie with 2 days rented",1.5 ,rental.getCharge(),0.1);	
	}
	@Test
	public void testGetFrequentRenterPointsWithPriceCodeZeroAndThreeDaysRented() {
		movie.setPriceCode(0);
		
		rental.setDaysRented(3);
		rental.setMovie(movie);
		
		assertEquals("Amount of Renterpoints should be 1 for a RegularMovie with 3 days rented",1,rental.getFrequentRenterPoints());	
	}
	@Test
	public void testGetFrequentRenterPointsWithPriceCodeOneAndThreeDaysRented() {
		movie.setPriceCode(1);
		
		rental.setDaysRented(3);
		rental.setMovie(movie);
		
		assertEquals("Amount of Renterpoints should be 2 for a New Release Movie with 3 days rented",2,rental.getFrequentRenterPoints());	
	}
	@Test(expected = IllegalArgumentException.class)
	public void testGetFrequentRenterPointsWithPriceCodeOneAndZeroDaysRented() {
		movie.setPriceCode(1);
		
		rental.setDaysRented(0);
		rental.setMovie(movie);
		
		assertEquals("Amount of Renterpoints should be 1 for a New Release Movie with 3 days rented",1,rental.getFrequentRenterPoints());	
	}

}
