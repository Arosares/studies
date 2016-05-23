package de.uniba.wiai.dsg.ajp.assignment3;

/**
 * The abstract class Price provides methods for calculating final rental price
 * based on days rented
 * 
 *
 */
public abstract class Price {

	abstract double getCharge(int daysRented);

	/**
	 * {@code getFrequentRenterPoints} returns 1 on every movie rented,
	 * unless specifically overwritten by one of the pricecode classes.
	 * An Example would be {@code LowBudgetPrice}, which returns 0.
	 */
	int getFrequentRenterPoints(int daysRented) {
		return 1;
	}

	abstract int getPriceCode();

}
