package de.uniba.wiai.dsg.ajp.assignment3;

/**
 * contains getters for new released Movies
 *
 */

public class NewReleasePrice extends Price {

	/**
	 * gets the charge Calculates the {@code result} based on the number of days
	 * the movie was rented. New releases cost 3 per day.
	 * 
	 * @param daysRented
	 *            amount of days the movie has been rented
	 */
	@Override
	double getCharge(int daysRented) {
		return daysRented * 3;
	}

	/**
	 * {@code getFrequentRenterPoints} returns the amount of frequent renter
	 * points depending on the {@code daysRented}
	 * 
	 * @param the
	 *            amount of days the movie has been rented
	 */
	@Override
	int getFrequentRenterPoints(int daysRented) {
		if (daysRented > 1) {
			return 2;
		} else {
			return 1;
		}
	}

	/**
	 * Overrides the {@code getPriceCode} method
	 *
	 * Returns the Pricecode. This method is normally called by {@code movie}
	 * over the abstract class {@code price}.
	 */
	@Override
	int getPriceCode() {
		return Movie.NEW_RELEASE;
	}

}
