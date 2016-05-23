package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NewReleasePriceTest {
	private NewReleasePrice nPrice;
	
	@Before
	public void setUp() throws Exception {
		nPrice = new NewReleasePrice();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetChargeWithThreeDaysRented() {
		assertEquals("Expected Result : 9", 9, nPrice.getCharge(3),0.1);
	}


	@Test
	public void testGetFrequentRenterPointsWithThreeDaysRented() {
		assertEquals("Expected Result : 2", 2, nPrice.getFrequentRenterPoints(3),0.1);
	}
	@Test
	public void testGetFrequentRenterPointsWithOneDayRented() {
		assertEquals("Expected Result : 1", 1, nPrice.getFrequentRenterPoints(1),0.1);
	}
	//Integration Test?
	@Test
	public void testGetPriceCode() {
		nPrice = mock(NewReleasePrice.class);
		when(nPrice.getPriceCode()).thenReturn(1);
		assertEquals("NewRelease Price Code is 1",1,nPrice.getPriceCode(),0.0);
	}

}
