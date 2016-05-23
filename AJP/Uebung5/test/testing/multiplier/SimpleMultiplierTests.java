package testing.multiplier;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testing.multiplier.Multiplier;
import testing.multiplier.SimpleMultiplier;

public class SimpleMultiplierTests {

	private Multiplier multiplier;

	@Before
	public void setUp() throws Exception {
		multiplier = new SimpleMultiplier();
	}

	@After
	public void tearDown() throws Exception {
		multiplier = null;
	}

	@Test
	public void testDefaultConstructor() {
		//when
		int expected = 1;
		int actual = multiplier.getResult();
		assertEquals("product is defined to be 1 with default constructor", expected,
				actual);
	}

	@Test
	public void testConstructorWithParameterWith5() {
		multiplier = new SimpleMultiplier(5);
		assertEquals("product is defined to be 5 with default constructor", 5, multiplier.getResult());
	}

	@Test
	public void afterMultiplyFourWithThreeResultShouldBe12() {
		multiplier.reset(4);
		multiplier.multiplyWith(3);
		assertEquals("result has to be product*n", 12, multiplier.getResult());
	}
	
	@Test
	public void multiplyWithZeroShouldBeZero(){
		multiplier.multiplyWith(0);
		assertEquals("result has to be 0, since multiplied with 0", 0, multiplier.getResult());
	}
	
	public void multiplyWithMinusOne(){
		multiplier.multiplyWith(-1);
		assertEquals("result has to be -1", -1, multiplier.getResult());
	}

	@Test
	public void afterInitializationResultshouldBeOne() {
		assertEquals("result has to be initialized with 1", 1,
				multiplier.getResult());
	}

	@Test
	public void afterResetToOneResultShouldBeOne() {
		multiplier.reset();
		assertEquals("result has to be 1 after reset()", 1,
				multiplier.getResult());
	}

	@Test
	public void afterResetFourResultShouldBeFour() {
		multiplier.reset(4);
		assertEquals("result has to be 4 after reset(4)", 4,
				multiplier.getResult());
	}

}
