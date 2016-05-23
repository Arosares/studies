package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NewReleasePriceIntegrationTest {
	private NewReleasePrice nPrice;
	
	@Before
	public void setUp() throws Exception {
		nPrice = new NewReleasePrice();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetPriceCode() {
	assertEquals("NewRelease Price Code is 1",1,nPrice.getPriceCode());
	}

}
