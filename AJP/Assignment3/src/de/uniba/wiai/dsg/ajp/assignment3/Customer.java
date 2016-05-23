package de.uniba.wiai.dsg.ajp.assignment3;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Customer. Describes a customer renting movies.
 * 
 */
public class Customer {
	private double vat = 0.25;
	/** The name. */
	private String name;

	/** The rentals. */
	private List<Rental> rentals = new LinkedList<Rental>();

	/**
	 * Instantiates a new customer.
	 */
	public Customer() {

	}

	/**
	 * Instantiates a new customer using the Parameter String {code name}.
	 *
	 * @param name
	 *            the Customer's name. Must not be null or empty. Uses
	 *            {@code setTitle(String name)} method
	 */
	public Customer(String name) {
		super();
		setName(name);
	}

	/**
	 * Gets the Customer's name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Customer's name.
	 *
	 * @param name
	 *            Must not be null or empty.
	 */
	public void setName(String name) {
		if (!name.trim().isEmpty()) { // checks for empty strings or String and
										// deletes unnecessary whitespaces
			this.name = name;
		} else {
			throw new IllegalArgumentException(
					"An empty String for Name is not allowed.");
		}

	}

	// Getters and Setters for VAT(Mehrwertsteuer)
	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	/**
	 * Gets the rentals.
	 *
	 * @return A List containing several Rentals
	 */
	public List<Rental> getRentals() {
		return rentals;
	}

	/**
	 * Sets the rentals.
	 *
	 * @param rentals
	 *            the new rentals Must not be null
	 */
	public void setRentals(List<Rental> rentals) {

		this.rentals = rentals;

	}

	/**
	 * Prints a {@code statement} which calculates the customers charge.
	 *
	 * @return the final bill for all rented movies as a string {@code result}
	 *         including Vat and savings rounding by two decimals displays the
	 *         amount of frequent renter points
	 * 
	 */
	public String statement() {
		String result = "Rental Record for " + getName() + "\n";

		int frequentRenterPoints = 0;
		for (Rental each : this.rentals) {
			frequentRenterPoints += each.getFrequentRenterPoints();

			// show figures for this rental
			result += "\t"
					+ each.getMovie().getTitle()
					+ "\t"
					+ String.valueOf((double) Math.round(each.getCharge() * 100) / 100)
					+ "\t"
					+ "incl. "
					+ String.valueOf(((double) Math.round(vat
							* each.getCharge() * 100) / 100))
					+ " VAT\t"
					+ "Your savings: "
					+ String.valueOf((double) Math.round((((each.getCharge() / (1 - each
							.getDiscount())) - each.getCharge())) * 100) / 100)
					+ "\n\n";
		}

		// add footer lines
		result += "Total Amount owed is " + String.valueOf((getTotalCharge()))
				+ "\n";
		result += "This includes " + String.valueOf(getTotalVat())
				+ " Value Added Tax.\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	/**
	 * Prints a {@code htmlStatement} which calculates the customers charge.
	 *
	 * @return the final bill for all rented movies as a string in html code
	 *         {@code result} including Vat and savings rounding by two decimals
	 *         displays the amount of frequent renter points
	 * 
	 */
	public String htmlStatement() {
		String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";

		for (Rental each : rentals) {
			// show figures for each rental
			result += each.getMovie().getTitle()
					+ ": "
					+ String.valueOf((double) Math.round(each.getCharge() * 100) / 100)
					+ ", including "
					+ String.valueOf(((double) Math.round(vat
							* each.getCharge() * 100) / 100)) + " VAT <BR>\n";
			result += "You save: "
					+ String.valueOf((double) Math.round((((each.getCharge() / (1 - each
							.getDiscount())) - each.getCharge())) * 100) / 100)
					+ "<BR>\n";
		}

		// add footer lines
		result += "\n<P>You owe <EM>" + String.valueOf(getTotalCharge())
				+ "</EM>, including " + String.valueOf(getTotalVat())
				+ " Value Added Tax.<BR>\n";
		result += "You earned <EM>"
				+ String.valueOf(getTotalFrequentRenterPoints())
				+ "</EM> frequent renter points</P>";
		return result;
	}

	/**
	 * Gets the total charge.
	 *
	 * @return the total charge iterates through the {@code rentals} List and
	 *         summarizes the charges for all rentals rounding every rental by
	 *         two decimals
	 * 
	 */
	double getTotalCharge() {
		double result = 0.0;

		for (Rental each : rentals) {
			result += ((double) Math.round(each.getCharge() * 100) / 100);
		}
		result = (double) Math.round(result * 100) / 100;
		return result;
	}

	/**
	 * Gets the total vat.
	 *
	 * @return the total vat iterates through the {@code rentals} List and
	 *         summarizes the vat rounding the {@code result} by two decimals
	 */
	double getTotalVat() {
		double result = 0.0;

		for (Rental each : rentals) {
			result += vat * each.getCharge();
		}

		// final result rounded, due inaccuracy of rounding algorithm for double
		result = ((double) Math.round(result * 100) / 100);
		return result;
	}

	/**
	 * Gets the total frequent renter points.
	 *
	 * @return the total frequent renter points iterates through the
	 *         {@code rentals} List and summarizes the frequent renter points of
	 *         all rentals
	 */
	int getTotalFrequentRenterPoints() {
		int result = 0;

		for (Rental each : rentals) {
			result += each.getFrequentRenterPoints();
		}

		return result;
	}

}
