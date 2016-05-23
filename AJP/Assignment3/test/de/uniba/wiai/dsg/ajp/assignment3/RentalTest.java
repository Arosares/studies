package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RentalTest {

	private Rental rental;
	private Movie movie;

	@Before
	public void setUp() throws Exception {
		rental = new Rental();
		movie = mock(Movie.class);
	}

	@After
	public void tearDown() throws Exception {
		rental = null;
		movie = null;
	}

	//	@Test
	//	public void testGetMovie() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	public void testSetMovie() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	public void testGetDaysRented() {
	//		fail("Not yet implemented");
	//	}
	//
	@Test
	public void testSetDaysRentedWithFourDaysRented() {
		rental.setDaysRented(4);
		assertEquals("The Rental is over 4 days",4,rental.getDaysRented());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testSetDaysRentedWithMinusOneDaysRented() {
		rental.setDaysRented(-1);
//		assertEquals("The Rental is over -1 days",4,rental.getDaysRented());
	}
	

	@Test
	public void testGetCharge() {
		rental.setDaysRented(3);

		when(movie.getCharge(3)).thenReturn(3.5);
				rental.setMovie(movie);
		assertEquals("Charge should be 3.5 for a RegularMovie with 3 days rented",3.5 ,rental.getCharge(),0.1);
	}
	
	@Test
	public void testGetFrequentRenterPoints() {
		rental.setDaysRented(3);

		when(movie.getFrequentRenterPoints(3)).thenReturn(1);
				rental.setMovie(movie);
		assertEquals("Amount of Renterpoints should be 1 for a RegularMovie with 3 days rented",1,rental.getFrequentRenterPoints());
	}

}
