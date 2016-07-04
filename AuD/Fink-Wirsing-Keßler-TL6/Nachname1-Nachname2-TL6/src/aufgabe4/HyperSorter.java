package aufgabe4;

public class HyperSorter {
	
	/**
	 * The maximum partial array size where quicksort is still applied. When the
	 * array size drops below this limit, selection sort is used to sort the
	 * partial array.
	 */
	private static final int arraySizeLimit = 5;
	
	/**
	 * The starting point of the sorting algorithm.
	 * 
	 * @param S The array to be sorted
	 */
	public static void sort(int[] S) {
		recQuickSort(0, S.length - 1, S);
	}
	
	/**
	 * The recursive method to apply quicksort to the whole array or to parts of
	 * it. When the partial array length drops below the limit given above you
	 * should use selectionSort.
	 * 
	 * @param left
	 *            The left boundary of the (partial-) array given as an array index.
	 * 
	 * @param right
	 *            The right boundary of the (partial-) array given as an array index.
	 *            
	 * @param S
	 * 			  The array to be sorted.
	 */
	private static void recQuickSort(int left, int right, int[] S) {
		// TODO implement
	}
	
	/**
	 * The method for finding the element at which you split the (partial-)
	 * array into two new arrays.
	 * 
	 * @param left
	 *            The left boundary of the (partial-) array given as an array index.
	 * 
	 * @param right
	 *            The right boundary of the (partial-) array given as an array index.
	 * 
	 * @param S
	 * 			  The array.
	 * 
	 * @return Returns the partition position as an array index.
	 */
	private static int partitionIt(int left, int right, int[] S){
		// TODO implement
		
		return Integer.MIN_VALUE;
	}
	
	/**
	 * A method that represents the selection sort algorithm.
	 * 
	 * @param left
	 *            The left boundary of the (partial-) array given as an array index.
	 * 
	 * @param right
	 *            The right boundary of the (partial-) array given as an array index.
	 * 
	 * @param S
	 * 			  The array.
	 */
	private static void selectionSort(int left, int right, int[] S) {
		// TODO implement
	}
	
	/**
	 * A helper method to swap two values in an array.
	 */
	private static void swap(int index1, int index2, int[] S) {
		int tmp = S[index1];
		S[index1] = S[index2];
		S[index2] = tmp;
	}
}