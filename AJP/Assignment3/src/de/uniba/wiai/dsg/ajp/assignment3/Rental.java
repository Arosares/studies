package de.uniba.wiai.dsg.ajp.assignment3;

/**
 * Contains getters and setters to supply other classes with data about a movie,
 * like how long it has been rented or what price code it has. Initializes
 * {@code discount}, which is used to modify the final price of the movie before
 * VAT.
 *
 */
public class Rental {

	private int daysRented;
	private Movie movie;
	private double discount = 0.0;

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	// Getters and Setters for Discount
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		if (discount > 0 && discount < 1){
			this.discount = discount;
		} else {
			System.err.println("Invalid Discount. Must be between Zero and One.");
		}
	}

	public int getDaysRented() {
		return daysRented;
	}

	public void setDaysRented(int daysRented) {
		if (daysRented < 1) {
			throw new IllegalArgumentException(
					"You can't rent movies for less than one day.");
		} else {
			this.daysRented = daysRented;
		}
	}

	public double getCharge() {
		return movie.getCharge(daysRented) * (1 - discount);
	}

	public int getFrequentRenterPoints() {
		return movie.getFrequentRenterPoints(daysRented);
	}

}
