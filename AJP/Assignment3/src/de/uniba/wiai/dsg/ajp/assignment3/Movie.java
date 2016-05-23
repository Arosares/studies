package de.uniba.wiai.dsg.ajp.assignment3;

/**
 * The Class Movie.
 *
 * Contains Getters and Setters for {@code title} and {@code pricecode},
 * {@code daysrented}. Initializes the {@code pricecodes} from 0-3. Is called by
 * the class Rental and passes on calls to the abstract class Price, from where
 * the pricecode subclasses are called to calculate the actual price of a movie
 * rental based on {@code daysrented}.
 * 
 */
public class Movie {

	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int LOW_BUDGET = 3;

	private Price price;

	private String title;

	/**
	 * Instantiates a new movie.
	 */
	public Movie() {

	}

	/**
	 * Instantiates a new movie using the Parameters String title and int
	 * priceCode
	 *
	 * @param title
	 *            the {@code title}. Must not be null or empty.
	 * @param priceCode
	 *            the {@code pricecode}; 0<=priceCode<=3 .
	 */
	public Movie(String title, int priceCode) {
		setTitle(title);
		this.setPriceCode(priceCode);
	}

	/**
	 * Gets the Movie's title.
	 *
	 * @return the {@code title}
	 */
	public String getTitle() {
		return title;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	/**
	 * Sets the title based on given parameter.
	 *
	 * @param title
	 *            the new {@code title}. Must not be null or empty.
	 */
	public void setTitle(String title) {
		if (!title.trim().isEmpty()) {
			this.title = title;
		} else {
			throw new IllegalArgumentException("Invalid Movie Title");
		}
	}

	/**
	 * Gets the charge.
	 *
	 * @param daysRented
	 *            the started {@code daysRented}. Most not be null, negative or
	 *            zero. int daysRented >= 1;
	 * @return the charge the cost of renting the film for {@code daysRented}
	 */
	double getCharge(int daysRented) {
		return price.getCharge(daysRented);
	}

	/**
	 * Gets the price code.
	 *
	 * @return the price code is either REGULAR; CHILDRENS; LOW_BUDGET, or
	 *         NEW_RELEASE;
	 */
	public int getPriceCode() {
		return price.getPriceCode();
	}

	/**
	 * Sets the price code.
	 *
	 * @param priceCode
	 *            the new price code: Must be between 0 and 3. 0<=priceCode<=3 .
	 *            Must not be null or empty. --> IllegalArgumentException
	 */
	public void setPriceCode(int priceCode) {
		switch (priceCode) {
		case REGULAR:
			price = new RegularPrice();
			break;
		case CHILDRENS:
			price = new ChildrensPrice();
			break;
		case NEW_RELEASE:
			price = new NewReleasePrice();
			break;
		case LOW_BUDGET:
			price = new LowBudgetPrice();
			break;
		default:
			throw new IllegalArgumentException("Incorrect Price Code");
		}
	}

	/**
	 * Gets the frequent renter points.
	 *
	 * @param daysRented
	 *            the amount of days the movie has been rented.
	 * @return the frequent renter points for the amount of {@code daysRented)
	 */
	public int getFrequentRenterPoints(int daysRented) {

		return price.getFrequentRenterPoints(daysRented);
	}

}
