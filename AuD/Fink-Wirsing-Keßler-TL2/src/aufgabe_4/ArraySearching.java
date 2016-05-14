package aufgabe_4;

import java.util.Arrays;
import java.util.Random;

public class ArraySearching {
	
	/**
	 * Ermittelt die Laufzeit fuer eine Menge von Aehnlichkeitssuchen.
	 * 
	 * @param n Groesse des Arrays S.
	 * @param runs Groesse des Arrays R (= Anzahl von Werten, fuer die im Array S der naechste Nachbar gefunden 
	 * werden soll).
	 * @param max Maximal zulaessiger Wert fuer die sich im Array S bzw. im Array R befindlichen double. 
	 * @param seed1 Seed fuer den Zufallszahlengenerator zur Erzeugung des Arrays S.
	 * @param seed2 Seed fuer den Zufallszahlengenerator zur Erzeugung der Werte des Arrays R (fuer die 
	 * jeweils der aehnlichste Wert im Array S gefunden werden soll).
	 * @param searchOption Lineare Suche oder Binaere Suche?
	 * @return
	 */
	public long testRuntime(int n, int runs, double max, long seed1, long seed2, SearchOption searchOption){
		
		// TODO: hier bitte das zu durchsuchende Array S erzeugen
		
		double[] arrayS = generateRandomSortedArray(n, max, seed1);
//		
//		// TODO: hier bitte das Array R erzeugen
//		
		double[] arrayR = generateRandomSortedArray(n, max, seed2);
		
		long totalTime= 0;
		// TODO Laufzeitmessungen durchfuehren
		
		return totalTime;
	}
	
	/**
	 * Erzeugt ein Array mit double-Werten zwischen 0 und <code>max</code>, welche aufsteigend sortiert 
	 * sind.
	 * 
	 * @param size Groesse des Arrays.
	 * @param max Maximaler Wert fuer ein double im Array.
	 * @param seed Seed fuer den Zufallszahlengenerator.
	 * 
	 * @return Array mit aufsteigend sortierten Fliesskommazahlen zwischen 0 und <code>max</code>.
	 */
	public double[] generateRandomSortedArray(int size, double max, long seed){
		
		// TODO Implementieren Sie hier bitte die Methode und passen Sie den Rueckgabewert an.
		double[] array = new double[size];
		Random rng = new Random(seed);

		for (int i = 0; i < array.length; i++) {
			
			array[i] = rng.nextDouble()*max; 
		}
		
		return array;
	}
	
	/**
	 * Findet den naechsten /aehnlichsten Wert zu <code>c</code> im Array <code>S</code> und gibt diesen zurueck. 
	 * Durchsucht das Array <code>S</code> dazu linear.
	 * 
	 * @param S Das zu durchsuchende Array.
	 * @param c Der Wert, zu dem im Array <code>S</code> der aehnlichste Wert (= geringste Differenz) gefunden 
	 * werden soll.
	 * 
	 * @return Den aehnlichsten Wert zu <code>c</code> aus dem Array <code>S</code>.
	 */
	public double getClosestLinearSearch(double[] S, double c){
		// TODO Implementieren Sie hier bitte die Methode und passen Sie den Rueckgabewert an.

		double diff = Math.abs(c - S[0]);
		double closest = S [0];
		
		for (int i = 1; i < S.length; i++) {
			double diffHelper = Math.abs(c - S[i]);
			
			if (diff > diffHelper){
				diff = diffHelper;
				closest = S[i];
				
			}
		}
		
		return closest;
	}
	
	/**
	 * Findet den naechsten /aehnlichsten Wert zu <code>c</code> im Array <code>S</code> und gibt diesen zurueck. 
	 * Durchsucht das Array <code>S</code> dazu per binaerer Suche.
	 * 
	 * @param S Das zu durchsuchende Array.
	 * @param c Der Wert, zu dem im Array <code>S</code> der aehnlichste Wert (= geringste Differenz) gefunden 
	 * werden soll.
	 * 
	 * @return Den aehnlichsten Wert zu <code>c</code> aus dem Array <code>S</code>.
	 */
	public double getClosestBinarySearch(double[] S, double c){
		
		// TODO Implementieren Sie hier bitte die Methode und passen Sie den Rueckgabewert an.
		bubbleSort(S);
		
		int low = 0; 
		int high = S.length-1;
		
		
		
		
		return closestBinary(S, low, high, c);
	}
	
	private double closestBinary(double[] S, int low, int high, double c){
		
			    if (high < low){
			        try {
						throw new Exception("not found");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			     int mid = (low + high)/2;
			    if (S[mid] == c)
			        return S[mid];
			    else if (S[mid] < c){
			        return closestBinary(S, low, mid+1, c);
			    } else {
			        return closestBinary(S, low, high-1, c);
			    }
//		if (S.length==1) {
//			return S[0];
//		}
//		
//		int mid = (low + high+1) / 2;
//		
//		double diff1 = Math.abs(S[mid] - c);
//		double diff2 = Math.abs(S[mid-1] - c);
//		double diff3 = Math.abs(S[mid] +1 - c);
//		double closest = 0;
//		double[] tmp; 
//		
//		System.out.println(Arrays.toString(S));
//		System.out.println("Diff1: "+diff1 +"\nDiff2: "+diff2+"\nDiff3: "+ diff3);
//		if (diff1 < diff2 && diff1 < diff3) {
//			System.out.println("Diff1");
//			closest = S[mid];
//		}
//		
//		if (diff2 < diff1 && diff2 < diff3) {
//			System.out.println("Diff2: " + low + ", " + mid + ", "+ c);
//			
//			tmp = new double[S.length-mid];
//			
//			for (int i = 0 + 1; i < tmp.length; i++) {
//				tmp[i] = S[mid - (mid+i)];
//			}
//			
//			closest = closestBinary(tmp, low, mid, c);
//		}
//		
//		if (diff3 < diff1 && diff3 < diff2) {
//			System.out.println("Diff3"+ " low " + low +" high " + high +"mid "+ mid );
//			tmp = new double[S.length-mid];
//			
//			for (int i = 0 + 1; i < tmp.length; i++) {
//				tmp[i] = S[i+mid-1];
//			}
//			
//			System.out.println(Arrays.toString(tmp));
//			closest = closestBinary(tmp, mid, high, c);
//		}
//		
//		return closest;
		
	}
	
	// Main-Methode zum Testen
	public static void main(String[] args) {
		
		// Initialisierung
		ArraySearching as = new ArraySearching();
		// Parameter
		int n = 8; double max = 50; long seed = 133;
		
		// erzeuge Array (4.1)
		double[] S = as.generateRandomSortedArray(n, max, seed);
		
		// finde aehnlichsten Wert per linearer Suche (4.2)
		double c = 0.1;
		double closest = as.getClosestLinearSearch(S, c);
		System.out.println("closest value to " + c + " in " + Arrays.toString(S) + ": " + closest);
		System.out.println();
		
		// finde aehnlichsten Wert per binaerer Suche (4.3)
		c = 20.0;
		closest = as.getClosestBinarySearch(S, c);
		System.out.println("closest value to " + c + " in " + Arrays.toString(S) + ": " + closest);
		System.out.println();
		
		/* L A U F Z E I T M E S S U N G E N (4.4) */
		n = 10; int runs = 100000; max = 10000; long seed1 = 4711; long seed2 = 322;
		long time = as.testRuntime(n, runs, max, seed1, seed2, SearchOption.LINEAR);
		System.out.println("time for linear search: " + time);
		System.out.println();
		
		time = as.testRuntime(n, runs, max, seed1, seed2, SearchOption.BINARY);
		System.out.println("time for binary search: " + time);
	}
	
	public void bubbleSort(double[] array) {
	    
		boolean swapped = true;
	    double j = 0;
	    double tmp;
	    while (swapped) {
	        swapped = false;
	        j++;
	        for (int i = 0; i < array.length - j; i++) {
	            if (array[i] > array[i + 1]) {
	                tmp = array[i];
	                array[i] = array[i + 1];
	                array[i + 1] = tmp;
	                swapped = true;
	            }
	        }
	    }
	}

	
//	private double[] sort(double[] array){
//		for (int e = 0; e < array.length; e++) {
//			for (int i = 0; i < e; i++) {
//				double elem = array[e];
//				double prev = array[i];
//				if (prev < elem) {
//					swap(array, e, i);
//				}
//			}
//		}
//
//		return array;
//	}
//	
//
//	public double[] swap(double[] x, double i, double j) {
//		double ii = x[i];
//		double jj = x[j];
//	
//		x[i] = jj;
//		x[j] = ii;
//	
//		return x;
//	}

}
