package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LowBudgetPriceIntegrationTest {
	private LowBudgetPrice lPrice;

	@Before
	public void setUp() throws Exception {
		lPrice = new LowBudgetPrice();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetPriceCode() {
		assertEquals("LowBudget Price Code is 3", 3, lPrice.getPriceCode());
	}

}
