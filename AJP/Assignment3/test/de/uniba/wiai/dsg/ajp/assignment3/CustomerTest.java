package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	private Customer customer;
	private Movie movie;
	private List<Rental> rentals = new LinkedList<Rental>();
	private Rental rental;

	@Before
	public void setUp() throws Exception {
		customer = new Customer();
		movie = mock(Movie.class);
		rental = mock(Rental.class);
	}

	@After
	public void tearDown() throws Exception {
		customer = null;
	}

		@Test
		public void testCustomer() {
			customer = new Customer("Peter Lustig");		
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testCustomerWithIllegalArgument() {
			customer = new Customer("   ");		
		}
		
	//
	//	@Test
	//	public void testCustomerString() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	public void testGetName() {
	//		fail("Not yet implemented");
	//	}
	//
		@Test(expected = IllegalArgumentException.class)
		public void testSetNameWithIllegalArgument() {
			customer.setName("    ");
		}
	//
	//	@Test
	//	public void testGetRentals() {
	//		fail("Not yet implemented");
	//	}
	//
	//	@Test
	//	public void testSetRentals() {
	//		fail("Not yet implemented");
	//	}

	@Test(expected = NullPointerException.class)
	public void testStatementWithRentalsNull() {
		customer.setRentals(null);
		customer.statement();
	}

	@Test
	public void testStatement() {
		double vat = customer.getVat();
		double totalvat = customer.getTotalVat();
		customer.setName("Peter");
		rental.setDaysRented(3);
		rental.setMovie(movie);
//		rental.setDiscount(0.10);
		rentals.add(rental);
		customer.setRentals(rentals);
		
		
		
		when(rental.getFrequentRenterPoints()).thenReturn(1);
		when(rental.getCharge()).thenReturn(3.15);
		when(rental.getDiscount()).thenReturn(0.10);
		when(rental.getMovie()).thenReturn(movie);
		when(movie.getTitle()).thenReturn("A Movie");
		
		
		String expected = "Rental Record for Peter\n"
				+"\t"+"A Movie"+"\t"+"3.15\t"+"incl. "+0.79+" VAT\t"+"Your savings: 0.35\n\n"
				+"Total Amount owed is 3.15\n"
				+"This includes 0.79 Value Added Tax.\n"
				+"You earned 1 frequent renter points";

		assertEquals(
				"Should return one String which contains all information about the customer",
				expected, customer.statement());

	}


		@Test
		public void testHtmlStatement() {
			customer.setName("Peter");
			rental.setDaysRented(3);
			rental.setMovie(movie);
			rentals.add(rental);
			customer.setRentals(rentals);
			
			
			when(rental.getFrequentRenterPoints()).thenReturn(1);
			when(rental.getCharge()).thenReturn(3.15);
			when(rental.getDiscount()).thenReturn(0.10);
			when(rental.getMovie()).thenReturn(movie);
			when(movie.getTitle()).thenReturn("A Movie");
			
			
			String expected = "<H1>Rentals for <EM>Peter</EM></H1><P>\n"
					+"A Movie"+": "+"3.15, including 0.79 VAT <BR>\n"
					+"You save: 0.35<BR>\n\n"
					+"<P>You owe <EM>3.15</EM>, including 0.79 Value Added Tax.<BR>\n"
					+"You earned <EM>1</EM> frequent renter points</P>";
			
			assertEquals(
					"Should return one String which contains all information about the customer",
					expected, customer.htmlStatement());
		}
	
		@Test
		public void testGetTotalCharge() {
			when(rental.getCharge()).thenReturn(3.5);
			rental.setDaysRented(3);
			rentals.add(rental);
			customer.setRentals(rentals);
			assertEquals("should be 3.5 for one Movie",3.5,customer.getTotalCharge(),0.0);
		}
	
		@Test
		public void testGetTotalFrequentRenterPoints() {
			when(rental.getFrequentRenterPoints()).thenReturn(1);
			rental.setDaysRented(3);
			rentals.add(rental);
			customer.setRentals(rentals);
			assertEquals("should be 1 for one Movie",1,customer.getTotalFrequentRenterPoints());	
	
		}

}
