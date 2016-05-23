package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LowBudgetPriceTest {
	private LowBudgetPrice lPrice;
	private Movie movie;
	
	@Before
	public void setUp() throws Exception {
		lPrice = new LowBudgetPrice();
		movie = mock(Movie.class);
	}

	@After
	public void tearDown() throws Exception {
	}
	// Three Days Test
	@Test
	public void testGetChargeWithThreeDaysRented() {
		assertEquals("Expected Result : 2", 2, lPrice.getCharge(3),0.0);
	}

	@Test
	public void testGetFrequentRenterPointsWithThreeDaysRented() {
		assertEquals("Expected Result : 0", 0, lPrice.getFrequentRenterPoints(3),0.0);
	}
	// Two Days Test
	@Test
	public void testGetChargeWithTwoDaysRented() {
		assertEquals("Expected Result : 1.5", 1.5, lPrice.getCharge(2),0.0);
	}
	// One Day Test
	@Test
	public void testGetChargeWithOneDayRented() {
		assertEquals("Expected Result : 0.5", 0.5, lPrice.getCharge(1),0.0);
	}
	//Check Pricecode (IntegrationTest?)
	@Test
	public void testGetPriceCode() {
		lPrice = mock(LowBudgetPrice.class);
		when(lPrice.getPriceCode()).thenReturn(3);
	assertEquals("LowBudget Price Code is 3",3,lPrice.getPriceCode(),0.0);
	}

}
