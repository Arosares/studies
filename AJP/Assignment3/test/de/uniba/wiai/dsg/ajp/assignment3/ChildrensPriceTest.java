package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChildrensPriceTest {
	private ChildrensPrice cPrice;
	private Movie movie;
	
	@Before
	public void setUp() throws Exception {
		cPrice = new ChildrensPrice();
		movie = mock(Movie.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetChargeWithThreeDaysRented() {
		assertEquals("Expected Result : 1.5", 1.5, cPrice.getCharge(3),0.1);
	}
	@Test
	public void testGetChargeWithFiveDaysRented() {
		assertEquals("Expected Result : 4.5", 4.5, cPrice.getCharge(5),0.1);
	}
	
	@Test
	public void testGetPriceCode() {
		cPrice = mock(ChildrensPrice.class);
		when(cPrice.getPriceCode()).thenReturn(2);
		assertEquals("ChildrensPrice Code is 2",2,cPrice.getPriceCode());
	}


}
