package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PriceTest {
	private Price price;
	
	@Before
	public void setUp() throws Exception {
		price = Mockito.mock(Price.class, Mockito.CALLS_REAL_METHODS);

	}

	@After
	public void tearDown() throws Exception {
	}
	
	//Tests von Methoden, die in Subklassen abgehandelt werden, werden ebenfalls in die Tests dieser Subklassen verschoben
	
	@Test
	public void testGetFrequentRenterPoints() {
		assertEquals("Expected Result : 1", 1, price.getFrequentRenterPoints(1));
	}


}
