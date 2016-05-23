package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegularPriceIntegrationTest {
	private RegularPrice rPrice;

	@Before
	public void setUp() throws Exception {
		rPrice = new RegularPrice();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetPriceCode() {
		assertEquals("Regular Price Code is 0",0,rPrice.getPriceCode());
	}
}
