package de.uniba.wiai.dsg.ajp.assignment3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerIntegrationTest {

	private Customer customer;
	private Movie movie1;
	private Movie movie2;
	private Movie movie3;
	private Movie movie4;
	private Rental rental1;
	private Rental rental2;
	private Rental rental3;
	private Rental rental4;
	
	@Before
	public void setUp() throws Exception {
		customer = new Customer();
		movie1 = new Movie();
		movie2 = new Movie();
		movie3 = new Movie();
		movie4 = new Movie();
		rental1 = new Rental();
		rental2 = new Rental();
		rental3 = new Rental();
		rental4 = new Rental();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCustomerConstructorWithValidString(){
		customer = new Customer("Peter Lustig");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testCustomerConstructorWithIllegalArgument() {
		customer = new Customer("   ");		
	}
	
	@Test
	public void testSetName(){
		customer.setName("Peter Lustig");
		assertEquals("Name should be Peter Lustig","Peter Lustig",customer.getName());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testSetNameWithIllegalString(){
		customer.setName(" ");
	}
	@Test
	public void integrationTestStatement() {
		movie1.setTitle("asdf");
		movie1.setPriceCode(0);
		movie2.setTitle("The very last ride");
		movie2.setPriceCode(1);
		movie3.setTitle("Movie 3");
		movie3.setPriceCode(2);
		movie4.setTitle("Movie 4");
		movie4.setPriceCode(3);
		
		rental1.setDaysRented(3);
		rental1.setDiscount(0.2);
		rental1.setMovie(movie1);
		rental2.setDaysRented(6);
		rental2.setDiscount(0.1);
		rental2.setMovie(movie2);
		rental3.setDaysRented(4);
		rental3.setDiscount(0.3);
		rental3.setMovie(movie3);
		rental4.setDaysRented(2);
		rental4.setDiscount(0.4);
		rental4.setMovie(movie4);
		
		customer.setName("Peter");
		customer.setVat(0.25);
		customer.getRentals().add(rental1);
		customer.getRentals().add(rental2);
		customer.getRentals().add(rental3);
		customer.getRentals().add(rental4);
		
		String expected = "Rental Record for Peter\n"
				+"\t"+"asdf"+"\t"+"2.8\t"+"incl. "+0.7+" VAT\t"+"Your savings: 0.7\n\n"
				+"\t"+"The very last ride"+"\t"+"16.2\t"+"incl. "+4.05+" VAT\t"+"Your savings: 1.8\n\n"
				+"\t"+"Movie 3"+"\t"+"2.1\t"+"incl. "+0.52+" VAT\t"+"Your savings: 0.9\n\n"
				+"\t"+"Movie 4"+"\t"+"0.9\t"+"incl. "+0.22+" VAT\t"+"Your savings: 0.6\n\n"

				+"Total Amount owed is 22.0\n"
				+"This includes 5.5 Value Added Tax.\n"
				+"You earned 4 frequent renter points";
		
		assertEquals(
				"Should return one String which contains all information about the customer",
				expected, customer.statement());
		
	}
	@Test
	public void integrationTestHtmlStatement() {
		movie1.setTitle("asdf");
		movie1.setPriceCode(0);
		movie2.setTitle("The very last ride");
		movie2.setPriceCode(1);
		movie3.setTitle("Movie 3");
		movie3.setPriceCode(2);
		movie4.setTitle("Movie 4");
		movie4.setPriceCode(3);
		
		rental1.setDaysRented(2);
		rental1.setDiscount(0.2);
		rental1.setMovie(movie1);
		rental2.setDaysRented(1);
		rental2.setDiscount(0.1);
		rental2.setMovie(movie2);
		rental3.setDaysRented(2);
		rental3.setDiscount(0.3);
		rental3.setMovie(movie3);
		rental4.setDaysRented(1);
		rental4.setDiscount(0.4);
		rental4.setMovie(movie4);
		
		customer.setName("Peter");
		customer.setVat(0.25);
		customer.getRentals().add(rental1);
		customer.getRentals().add(rental2);
		customer.getRentals().add(rental3);
		customer.getRentals().add(rental4);
		
		String expected = "<H1>Rentals for <EM>Peter</EM></H1><P>\n"
				+"asdf"+": "+"1.6, including 0.4 VAT <BR>\n"
				+"You save: 0.4<BR>\n"
				+"The very last ride"+": "+"2.7, including 0.68 VAT <BR>\n"
				+"You save: 0.3<BR>\n"
				+"Movie 3"+": "+"1.05, including 0.26 VAT <BR>\n"
				+"You save: 0.45<BR>\n"
				+"Movie 4"+": "+"0.3, including 0.08 VAT <BR>\n"
				+"You save: 0.2<BR>\n\n"
				+"<P>You owe <EM>5.65</EM>, including 1.41 Value Added Tax.<BR>\n"
				+"You earned <EM>3</EM> frequent renter points</P>";
		
		assertEquals(
				"Should return one String which contains all information about the customer in Html Code",
				expected, customer.htmlStatement());
	}

	
	@Test
	public void testGetTotalCharge() {
		movie1.setPriceCode(0);
		movie2.setPriceCode(1);
		
		rental1.setMovie(movie1);
		rental1.setDaysRented(3);
		
		rental2.setMovie(movie2);
		rental2.setDaysRented(1);
		
		customer.getRentals().add(rental1);
		customer.getRentals().add(rental2);

		assertEquals("Should be 6.5 for two Movies with PriceCode Regular and NewRelease",6.5,customer.getTotalCharge(),0.0);
	}

	@Test
	public void testGetTotalFrequentRenterPoints() {
		movie1.setPriceCode(0);
		movie2.setPriceCode(1);
		
		rental1.setMovie(movie1);
		rental1.setDaysRented(3);
		
		rental2.setMovie(movie2);
		rental2.setDaysRented(1);
		
		customer.getRentals().add(rental1);
		customer.getRentals().add(rental2);
		assertEquals("Should be 2 for two Movies with PriceCode Regular and NewRelease",2,customer.getTotalFrequentRenterPoints());	

	}

}
