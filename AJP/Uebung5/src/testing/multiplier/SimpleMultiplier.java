package testing.multiplier;

public class SimpleMultiplier implements Multiplier {

	private int product = 0;

	/**
	 * Default constructor.
	 * 
	 * Postcondition:
	 * <ul>
	 * <li>getResult() == 1</li>
	 * </ul>
	 */
	public SimpleMultiplier() {
		this.reset(1);
	}

	/**
	 * Constructor that initializes the multiplier.
	 * 
	 * Postcondition:
	 * <ul>
	 * <li>getResult() == n</li>
	 * </ul>
	 * 
	 * @param n
	 */
	public SimpleMultiplier(int n) {
		this.reset(n);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniba.wiai.lspi.ss11.ajp.testing.multiplier.Multiplier#multiplyWith
	 * (int)
	 */
	@Override
	public void multiplyWith(int n) {
		product = product * n;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.uniba.wiai.lspi.ss11.ajp.testing.multiplier.Multiplier#getResult()
	 */
	@Override
	public int getResult() {
		return product;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uniba.wiai.lspi.ss11.ajp.testing.multiplier.Multiplier#reset()
	 */
	@Override
	public void reset() {
		this.reset(1);
	}

	@Override
	public void reset(int n) {
		this.product = n;
	}

}
