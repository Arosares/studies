package de.uniba.wiai.dsg.ajp.assignment3;

/**
 * contains getters for Low Budget Movies
 *
 */

public class LowBudgetPrice extends Price {

	/**
	 * gets the charge Calculates the <code>result</code> based on the number of
	 * days the movie was rented. New releases cost 3 per day.
	 * 
	 * @param daysRented
	 *            amount of days the movie has been rented
	 */
	@Override
	double getCharge(int daysRented) {
		if (daysRented < 2) {
			return daysRented * 0.5;
		} else {
			return daysRented * 0.5 + 0.5;
		}
	}

	/**
	 * {@code getFrequentRenterPoints} returns the amount of frequent renter
	 * points
	 * 
	 * @param the
	 *            amount of days the movie has been rented
	 */
	@Override
	int getFrequentRenterPoints(int daysRented) {
		return 0;

	}

	/**
	 * Overrides the {@code getPriceCode} method
	 *
	 * Returns the Pricecode. This method is normally called by
	 * <code>movie</code> over the abstract class <code>Price</code>.
	 */
	@Override
	int getPriceCode() {
		return Movie.LOW_BUDGET;
	}

}
