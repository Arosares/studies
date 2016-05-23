package aufgabe_4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import org.omg.CosNaming.IstringHelper;

public class ArraySearching {

	/**
	 * Ermittelt die Laufzeit fuer eine Menge von Aehnlichkeitssuchen.
	 * 
	 * @param n
	 *            Groesse des Arrays S.
	 * @param runs
	 *            Groesse des Arrays R (= Anzahl von Werten, fuer die im Array S
	 *            der naechste Nachbar gefunden werden soll).
	 * @param max
	 *            Maximal zulaessiger Wert fuer die sich im Array S bzw. im
	 *            Array R befindlichen double.
	 * @param seed1
	 *            Seed fuer den Zufallszahlengenerator zur Erzeugung des Arrays
	 *            S.
	 * @param seed2
	 *            Seed fuer den Zufallszahlengenerator zur Erzeugung der Werte
	 *            des Arrays R (fuer die jeweils der aehnlichste Wert im Array S
	 *            gefunden werden soll).
	 * @param searchOption
	 *            Lineare Suche oder Binaere Suche?
	 * @return
	 */
	public long testRuntime(int n, int runs, double max, long seed1, long seed2, SearchOption searchOption) {

		//  hier bitte das zu durchsuchende Array S erzeugen
		
		System.out.println("Generating S..");
		double[] arrayS = generateRandomSortedArray(n, max, seed1);
		
		//  hier bitte das Array R erzeugen
		System.out.println("Generating R..");
		double[] arrayR = generateRandomSortedArray(runs, max, seed2);

		// initiate time measurement
		long totalTime = 0;
		long startTime = 0;
		long endTime = 0;
		// TODO Laufzeitmessungen durchfuehren

		System.out.print("Start searching .. ");
		switch (searchOption) {
		case LINEAR:
			System.out.println("LINEAR");

			// get current system-time in nanoseconds
			startTime = System.nanoTime();

			for (int i = 0; i < arrayR.length; i++) {
				getClosestLinearSearch(arrayS, arrayR[i]);
			}

			// get current system-time in nanoseconds after Linear search
			// finished
			endTime = System.nanoTime();

			// Get the difference as duration
			totalTime = endTime - startTime;

			// divide by 1000000 from nano to milliseconds

			totalTime /= 1000000;

			break;
		case BINARY:
			System.out.println("BINARY");
			
			// get current system-time in nanoseconds
			startTime = System.nanoTime();

			for (int i = 0; i < arrayR.length; i++) {
				getClosestBinarySearch(arrayS, arrayR[i]);
			}
			
			// get current system-time in nanoseconds after Binary search
			// finished
			endTime = System.nanoTime();

			totalTime = endTime - startTime;

			// in milliseconds
			totalTime /= 1000000;
			break;
		}

		return totalTime;
	}

	/**
	 * Erzeugt ein Array mit double-Werten zwischen 0 und <code>max</code>,
	 * welche aufsteigend sortiert sind.
	 * 
	 * @param size
	 *            Groesse des Arrays.
	 * @param max
	 *            Maximaler Wert fuer ein double im Array.
	 * @param seed
	 *            Seed fuer den Zufallszahlengenerator.
	 * 
	 * @return Array mit aufsteigend sortierten Fliesskommazahlen zwischen 0 und
	 *         <code>max</code>.
	 */
	public double[] generateRandomSortedArray(int size, double max, long seed) {

		// TODO Implementieren Sie hier bitte die Methode und passen Sie den
		// Rueckgabewert an.
		Random rng = new Random(seed);

		//creating HashSet
		HashSet<Double> hashList = new HashSet<Double>(); 
		
		double randomNumber;
		
		//adding random numbers between zero(inclusiv) and max(exclusiv) to Hashset
		for (int i = 0; i < size; i++) {
			randomNumber = rng.nextDouble() * max; 													
			hashList.add(randomNumber);
		}
		
		//transform haslist to ObjectArray
		Object[] array = hashList.toArray();
		
		double newArray[] = new double[size];
		
		//copy from object to array with safecast
		for (int i = 0; i < size; i++) {
			if (array[i] instanceof Double) {
				newArray[i] = (double) array[i];
			}
		}

		Arrays.sort(newArray);
		return newArray;
	}

	/**
	 * Findet den naechsten /aehnlichsten Wert zu <code>c</code> im Array
	 * <code>S</code> und gibt diesen zurueck. Durchsucht das Array
	 * <code>S</code> dazu linear.
	 * 
	 * @param S
	 *            Das zu durchsuchende Array.
	 * @param c
	 *            Der Wert, zu dem im Array <code>S</code> der aehnlichste Wert
	 *            (= geringste Differenz) gefunden werden soll.
	 * 
	 * @return Den aehnlichsten Wert zu <code>c</code> aus dem Array
	 *         <code>S</code>.
	 */
	public double getClosestLinearSearch(double[] S, double c) {
		// TODO Implementieren Sie hier bitte die Methode und passen Sie den
		// Rueckgabewert an.
		
		//Get difference for first element in S and c
		double diff = Math.abs(c - S[0]);
		double closest = S[0];

		//compare difference of following elements with S[i-1]
		for (int i = 1; i < S.length; i++) {
			double diffHelper = Math.abs(c - S[i]);

			if (diffHelper < diff) {
				diff = diffHelper;
				closest = S[i];

			} else {
				// since array is sorted, we can stop searching when difference
				// is not getting smaller anymore
				break;
			}
		}

		return closest;
	}

	/**
	 * Findet den naechsten /aehnlichsten Wert zu <code>c</code> im Array
	 * <code>S</code> und gibt diesen zurueck. Durchsucht das Array
	 * <code>S</code> dazu per binaerer Suche.
	 * 
	 * @param S
	 *            Das zu durchsuchende Array.
	 * @param c
	 *            Der Wert, zu dem im Array <code>S</code> der aehnlichste Wert
	 *            (= geringste Differenz) gefunden werden soll.
	 * 
	 * @return Den aehnlichsten Wert zu <code>c</code> aus dem Array
	 *         <code>S</code>.
	 */
	public double getClosestBinarySearch(double[] S, double c) {

		// TODO Implementieren Sie hier bitte die Methode und passen Sie den
		// Rueckgabewert an.

		int low = 0;
		int high = S.length - 1;

		double closest = closestBinary(S, c, low, high);

		return closest;
	}

	private double closestBinary(double[] S, double c, int low, int high) {
		int mid = (low + high) / 2;

		// System.out.println("low " + low +" mid " + mid +" high "+ high);
		if (mid == high) {
			return S[mid];
		}

		// to avoid index out of bounds exception
		if (mid == 0) {
			mid++;
		}
		double diff1 = Math.abs(S[mid] - c);
		double diff2 = Math.abs(S[mid - 1] - c);
		double diff3 = Math.abs(S[mid + 1] - c);

		if (diff1 < diff2 && diff1 < diff3) {
			return S[mid];
		} else if (diff2 < diff1 && diff2 < diff3) {
			return closestBinary(S, c, 0, mid - 1);
		} else {
			return closestBinary(S, c, mid + 1, high);
		}
	}

	// Main-Methode zum Testen
	public static void main(String[] args) {

		// Initialisierung
		ArraySearching as = new ArraySearching();
		// Parameter
		int n = 10;
		double max = 100;
		long seed = 1337;

		// erzeuge Array (4.1)
		double[] S = as.generateRandomSortedArray(n, max, seed);

		// finde aehnlichsten Wert per linearer Suche (4.2)
		double c = 50.7;
		double closest = as.getClosestLinearSearch(S, c);
		System.out.println("closest value to " + c + " in " + Arrays.toString(S) + ": " + closest);
		System.out.println();

		// finde aehnlichsten Wert per binaerer Suche (4.3)
		c = 50.7;
		closest = as.getClosestBinarySearch(S, c);
		System.out.println("closest value to " + c + " in " + Arrays.toString(S) + ": " + closest);
		System.out.println();

		/* L A U F Z E I T M E S S U N G E N (4.4) */
		n = 1000;
		int runs = 10000;
		max = 10000;
		long seed1 = 4711;
		long seed2 = 322;
		long time = as.testRuntime(n, runs, max, seed1, seed2, SearchOption.LINEAR);
		System.out.println("time for linear search: " + time);
		System.out.println();

		time = as.testRuntime(n, runs, max, seed1, seed2, SearchOption.BINARY);
		System.out.println("time for binary search: " + time);
	}

}
