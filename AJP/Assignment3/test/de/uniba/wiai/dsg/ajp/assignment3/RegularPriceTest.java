package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegularPriceTest {
	private RegularPrice rPrice;
	private Movie movie;

	@Before
	public void setUp() throws Exception {
		rPrice = new RegularPrice();
		movie = mock(Movie.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetChargeWithThreeDaysRented() {
		assertEquals("Expected Result : 3.5", 3.5, rPrice.getCharge(3),0.1);
	}
	@Test
	public void testGetChargeOneDayRented() {
		assertEquals("Expected Result : 2", 2, rPrice.getCharge(1),0.1);
	}
	@Test
	public void testGetPriceCode() {
		rPrice = mock(RegularPrice.class);
		when(rPrice.getPriceCode()).thenReturn(0);
		assertEquals("Regular Price Code is 0",0,rPrice.getPriceCode(),0.0);
	}

//	@Test
//	public void testGetFrequentRenterPoints() {
//		fail("Not yet implemented");
//	}

}
