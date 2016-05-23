package testing.multiplier;

/**
 * The multiplier allows to do multiplication in an object-oriented way.
 * 
 * @author Simon Harrer, Joerg Lenhard
 * @version 1.0
 */
public interface Multiplier {

	/**
	 * Multiplies the current result with the passed value n.
	 * 
	 * Precondition:
	 * <ul>
	 * <li>none</li>
	 * </ul>
	 * 
	 * Postcondition:
	 * <ul>
	 * <li>pre.getResult() * n == post.getResult()</li>
	 * </ul>
	 * 
	 * @param n
	 *            the number that is multiplied to the internal state of the
	 *            multiplier.
	 */
	void multiplyWith(int n);

	/**
	 * The current result.
	 * 
	 * Precondition:
	 * <ul>
	 * <li>none</li>
	 * </ul>
	 * 
	 * Postcondition:
	 * <ul>
	 * <li>the current internal product of the {@link Multiplier}</li>
	 * </ul>
	 * 
	 * @return the current internal product
	 */
	int getResult();

	/**
	 * Sets internal state to 1 again.
	 * 
	 * Precondition:
	 * <ul>
	 * <li>none</li>
	 * </ul>
	 * 
	 * Postcondition:
	 * <ul>
	 * <li>getResult() == 1</li>
	 * </ul>
	 */
	void reset();

	/**
	 * Sets internal state to n again.
	 * 
	 * Precondition:
	 * <ul>
	 * <li>none</li>
	 * </ul>
	 * 
	 * Postcondition:
	 * <ul>
	 * <li>getResult() == n</li>
	 * </ul>
	 */
	void reset(int n);

}