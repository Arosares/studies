package aufgabe_4;

import java.math.BigInteger;

public class ChessboardProgram {

	/**
	 * Erstellt eine das Schachbrett repraesentierende Matrix, die in ihren
	 * einzelnen Feldern die jeweiligen Belohnungen der repraesentierten
	 * Schachfelder enthaelt.
	 * 
	 * @param n
	 *            Anzahl der Felder in einer Dimension (Breite/Laenge des
	 *            Schachbretts).
	 * @param amount
	 *            Grundbetrag, der in das erste Feld eingefuegt wird.
	 * @param multiplier
	 *            Multiplikator, mit dem der Betrag des vorherigen Feldes
	 *            multipliziert wird, um den Betrag des aktuellen Feldes zu
	 *            erhalten.
	 * 
	 * @return Gefuellte Matrix, deren einzelne Felder die jeweiligen Belohungen
	 *         enthalten.
	 */
	public static BigInteger[][] getChessboard(int n, String amount, String multiplier) {

		// TODO: implementieren Sie hier die Methode und passen Sie den
		// Rueckgabewert an

		BigInteger[][] chessBoard;

		// Checking for valid dimension of the array
		if (n <= 0) {
			System.out.println("You chose a dimension smaller than zero.\n This is invalid.\n n is set to 1");
			chessBoard = new BigInteger[1][1];
		} else {
			chessBoard = new BigInteger[n][n];
		}

		BigInteger am;
		BigInteger mult;

		try {
			am = BigInteger.valueOf(Integer.parseInt(amount));
			mult = BigInteger.valueOf(Integer.parseInt(multiplier));

			for (int row = 0; row < chessBoard.length; row++) {
				// iterate through each column in a row
				for (int column = 0; column < chessBoard[row].length; column++) {
					chessBoard[row][column] = am;
					am = am.multiply(mult);
				}
			}
		} catch (NumberFormatException e) {
			System.err.println("Must be a number! " + e.getMessage());
			return null;
		}

		return chessBoard;
	}

	/**
	 * Nimmt die das Schachbrett repraesentierende Matrix
	 * <code>chessboard</code> entgegen und berechnet die Gesamtsumme der in den
	 * einzelnen Felder enthaltenen Betraege.
	 * 
	 * @param chessboard
	 *            Gefuellte Matrix, die das Schachbrett mitsamt der Belohnungen
	 *            repraesentiert.
	 * 
	 * @return Gesamtsumme der in den einzelnen Feldern enthaltenen Betraege.
	 */
	public static BigInteger getChessboardSum(BigInteger[][] chessboard) {

		// TODO: implementieren Sie hier die Methode und passen Sie den
		// Rueckgabewert an

		BigInteger chessSum = BigInteger.valueOf(0);

		for (int row = 0; row < chessboard.length; row++) {
			for (int column = 0; column < chessboard[row].length; column++) {
				// adding the value in each cell to chessSum
				chessSum = chessSum.add(chessboard[row][column]);
			}
		}

		return chessSum;
	}

	public static void main(String[] args) {

		// TODO: testen Sie Ihre Implementierung hinreichend
		BigInteger[][] chessBoard = getChessboard(4, "1", "2");

		if (chessBoard != null) {
			BigInteger chessSum = getChessboardSum(chessBoard);

			for (int i = 0; i < chessBoard.length; i++) {
				for (int j = 0; j < chessBoard[i].length; j++) {
					System.out.println("Row " + (i + 1) + " Column " + (j + 1) + " : " + chessBoard[i][j]);
				}
			}

			System.out.println("Sum of all cells: " + chessSum);
		}

	}

}
