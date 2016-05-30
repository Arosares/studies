package aufgabe_4;

import java.math.BigInteger;

public class Utility {

	/**
	 * Berechnet das Ergebnis von base^exponent und gibt dieses zurueck.
	 * 
	 * @param base
	 * @param exponent
	 * @return
	 */
	public static BigInteger pow(BigInteger base, BigInteger exponent) {
		BigInteger result = BigInteger.ONE;
		while (exponent.signum() > 0) {
			if (exponent.testBit(0))
				result = result.multiply(base);
			base = base.multiply(base);
			exponent = exponent.shiftRight(1);
		}
		return result;
	}

}
