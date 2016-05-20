package aufgabe_3;

public class Arrays {

	/**
	 * Nimmt das Array <code>x</code> entgegen und vertauscht die Elemente an
	 * den Positionen <code>i</code> und <code>j</code>.
	 * 
	 * @param x
	 *            Das Array, in dem die Positionen von zwei Elementen vertauscht
	 *            werden sollen.
	 * @param i
	 *            Position des einen Elementes im Array <code>x</code>.
	 * @param j
	 *            Position des anderen Elementes im Array <code>x</code>.
	 * 
	 * @return Das Array <code>x</code>, nachdem die sich an Position
	 *         <code>i</code> und Position <code>j</code> befindlichen Elemente
	 *         vertauscht wurden.
	 */
	public static int[] swap(int[] x, int i, int j) {
		int ii = x[i];
		int jj = x[j];

		x[i] = jj;
		x[j] = ii;

		return x;
	}

	/**
	 * Nimmt das Array <code>x</code> entgegen und sortiert die darin
	 * enthaltenen Elemente absteigend nach ihrer Groesse.
	 * 
	 * @param x
	 *            Das Array, dessen Elemente absteigend sortiert werden sollen.
	 * 
	 * @return Das Array <code>x</code>, nachdem dessen Elemente absteigend
	 *         sortiert wurden.
	 */
	public static int[] reverseSort(int[] x) {
		for (int e = 0; e < x.length; e++) {
			for (int i = 0; i < e; i++) {
				int elem = x[e];
				int prev = x[i];
				if (prev < elem) {
					swap(x, e, i);
				}
			}
		}

		return x;
	}

	public static void main(String[] args) {

		// TODO: testen Sie Ihre Implementierung hinreichend

		// - Erstellung eines Arrays
		// - Tausch der Positionen zweier Elemente des Arrays
		// - Sortierung des Arrays
		int i = 2;
		int j = 4;
		int[] list = { 1, 2, 3, 4, 5, 6 };
		int listi = list[i];
		int listj = list[j];
		int[] listswap = swap(list, i, j);
		int swapi = listswap[i];
		int swapj = listswap[j];
		System.out.println("Swap: \nUrsprüngliches i: " + listi + ". Neues i: "
				+ swapi + ". Ursprüngliches j: " + listj + ". Neues j: "
				+ swapj);

		System.out.println("\nReversesort: \nOld list: ");
		printList(listswap);
		int[] reverse = reverseSort(listswap);
		System.out.println("New reverseSorted list: ");
		printList(reverse);
	}

	private static void printList(int list[]) {
		for (int i = 0; i < list.length; i++) {
			int elem = list[i];
			System.out.println(elem);
		}
	}
}
