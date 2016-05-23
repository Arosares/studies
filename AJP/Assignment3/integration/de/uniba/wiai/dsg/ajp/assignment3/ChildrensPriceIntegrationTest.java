package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChildrensPriceIntegrationTest {
	private ChildrensPrice cPrice;

	@Before
	public void setUp() throws Exception {
		cPrice = new ChildrensPrice();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetPriceCode() {
		assertEquals("ChildrensPrice Code is 2", 2, cPrice.getPriceCode());
	}

}
