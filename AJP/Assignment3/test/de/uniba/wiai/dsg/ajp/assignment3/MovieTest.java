package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovieTest {

	private Movie movie;
	private Price price;
	private Price regularPrice;
	private Price newReleasePrice;
	private Price childrensPrice;
	private Price lowBudgetPrice;
	
	@Before
	public void setUp() throws Exception {
		movie = new Movie();
		price = mock(Price.class);
		regularPrice = mock(RegularPrice.class);
		newReleasePrice = mock(NewReleasePrice.class);
		childrensPrice = mock(ChildrensPrice.class);
		lowBudgetPrice = mock(LowBudgetPrice.class);
	}

	@After
	public void tearDown() throws Exception {
		movie = null;
	}

	//	@Test
	//	public void testMovie() {
	//		fail("Not yet implemented");
	//	}
	//
		@Test
		public void testMovieConstructorWithParametersStringZero() {
			
			movie.setPrice(price);
		
			movie = new Movie("The Day After Tomorrow",0);
		
			when(price.getPriceCode()).thenReturn(0);
//			when(price.getPriceCode()).thenReturn(0);
//			movie.setPrice(price);
			assertEquals("Film should be called The Day After Tomorow", "The Day After Tomorrow",movie.getTitle());
			assertEquals("PriceCode should be Zero(RegularPriceCode)",0,movie.getPriceCode() );
		}
		
		
		@Test(expected = IllegalArgumentException.class)
		public void testMovieConstructorWithParameterPriceCodeInvalidFour() {
			movie = new Movie("The Day After Tomorrow",4);
			assertEquals("Film should be called The Day After Tomorow", "The Day After Tomorrow",movie.getTitle());
			movie.getPriceCode();
		}
		@Test(expected = IllegalArgumentException.class)
		public void testMovieConstructorWithParameterPriceCodeInvalidMinusOne() {
			movie = new Movie("The Day After Tomorrow",-1);
			assertEquals("Film should be called The Day After Tomorow", "The Day After Tomorrow",movie.getTitle());
			movie.getPriceCode();
		}
	
	@Test
	public void testSetTitle(){
		movie.setTitle("asdf");
		assertEquals("Title should be asdf","asdf",movie.getTitle());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testSetTitleWithEmpryString(){
		movie.setTitle("");
	}
	@Test
	public void testGetChargeWithRegularPriceCodeAndThreeDaysRented() {
		when(regularPrice.getCharge(3)).thenReturn(3.5);
		movie.setPrice(regularPrice);
		assertEquals("Charge for 3 days on a RegularPrice should be 3.5", 3.5, movie.getCharge(3), 0.0);
		
	}
	@Test
	public void testGetChargeWithChildrensPriceCodeAndThreeDaysRented() {
		when(childrensPrice.getCharge(3)).thenReturn(1.5);		
		movie.setPrice(childrensPrice);
		assertEquals("Charge for 3 days on a RegularPrice should be 1.5", 1.5, movie.getCharge(3), 0.0);
		
	}
	//Low Budget
	@Test
	public void testGetChargeWithLowBudgetPriceCodeAndThreeDaysRented() {
		when(lowBudgetPrice.getCharge(3)).thenReturn(2.0);		
		movie.setPrice(lowBudgetPrice);
		assertEquals("Charge for 3 days on a RegularPrice should be 2", 2.0, movie.getCharge(3), 0.0);
		
	}
	
	@Test
	public void testGetChargeWithRegularPriceCodeAndZeroDaysRented() {
		when(regularPrice.getCharge(0)).thenReturn(2.0);
		movie.setPrice(regularPrice);
		assertEquals("Charge for 0 days on a RegularPrice should be 1", 2.0, movie.getCharge(0),0.1);
		
	}
	
	
	//
	//	@Test
	//	public void testGetPriceCode() {
	//		fail("Not yet implemented");
	//	}
	@Test
	public void testSetPriceCodeNewRegularZero() {
		when(regularPrice.getPriceCode()).thenReturn(0);
		movie.setPriceCode(0);
		assertEquals("The Price should be REGULAR/0",0,regularPrice.getPriceCode());
	}
	@Test
	public void testSetPriceCodeNewReleasePriceOne() {
		when(newReleasePrice.getPriceCode()).thenReturn(1);
		movie.setPriceCode(1);
		assertEquals("The Price should be New Release/1",1,newReleasePrice.getPriceCode());
//		movie.setPriceCode(1);
	}
	@Test
	public void testSetPriceCodeChildrensPriceTwo() {
		when(childrensPrice.getPriceCode()).thenReturn(2);
		movie.setPriceCode(2);
		assertEquals("The Price should be CHILDRENS/2",2,childrensPrice.getPriceCode());
		
	}
	@Test
	public void testSetPriceCodeLowBudgetThree() {
		when(lowBudgetPrice.getPriceCode()).thenReturn(3);
		movie.setPriceCode(3);
		assertEquals("The Price should be LOW BUDGET/3",3,lowBudgetPrice.getPriceCode());
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void testSetPriceCodeWithIllegalArgumentFour() {
		movie.setPriceCode(4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPriceCodeWithIllegalArgumentMinusOne() {
		movie.setPriceCode(-1);
	}

	@Test
	public void testGetFrequentRenterPointsWithPriceCodeOne() {
		when(price.getFrequentRenterPoints(4)).thenReturn(2);
		when(newReleasePrice.getFrequentRenterPoints(4)).thenReturn(2);
		movie.setPrice(newReleasePrice);
		assertEquals("Method should return 2", 2 ,movie.getFrequentRenterPoints(4));
	}

	@Test
	public void testGetFrequentRenterPointsWithPriceCodeZero() {
		when(regularPrice.getFrequentRenterPoints(4)).thenReturn(1);
		movie.setPrice(regularPrice);
		assertEquals("Method should return 1", 1 ,movie.getFrequentRenterPoints(4));
	}


}
