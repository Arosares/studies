package de.uniba.wiai.dsg.ajp.assignment3;

/**
 * contains getters for Childrens Movies
 *
 */

public class ChildrensPrice extends Price {

	/**
	 * gets the charge
	 *
	 * Calculates the {@code result} based on the number of days the movie was
	 * rented. For Children, the first three days cost 1.5 and all additional
	 * days after that cost 1.5 extra.
	 * 
	 * @param daysRented
	 *            amount of days the movie has been rented
	 */
	@Override
	double getCharge(int daysRented) {
		double result = 1.5;
		if (daysRented > 3) {
			result += (daysRented - 3) * 1.5;
		}
		return result;
	}

	/**
	 * gets the {@code PriceCode} Returns the Pricecode. This method is normally
	 * called by {@code movie} over the abstract class {@code price}.
	 */
	@Override
	int getPriceCode() {
		return Movie.CHILDRENS;
	}

}
