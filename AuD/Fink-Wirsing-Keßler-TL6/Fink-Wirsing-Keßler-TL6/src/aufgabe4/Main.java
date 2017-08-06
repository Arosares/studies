package aufgabe4;

import java.util.Arrays;
import java.util.Random;


public class Main {

	/**
	 * Generates a random integer array (you don't say).
	 * 
	 * @param size
	 *            The size of the generated array
	 * @return The random array
	 */
	private static int[] generateRandomArray(int size) {
		int[] output = new int[size];
		Random random = new Random(1337);
		for (int i = 0; i < size; i++) {
			output[i] = random.nextInt(size * 10);
		}

		return output;
	}

	/**
	 * Nicely prints out an array. Use for testing.
	 * 
	 * @param array
	 *            The array to print out
	 */
	private static void printArray(int array[]) {
		System.out.print("[");
		if (array.length != 0) {
			for (int i = 0; i < array.length - 1; i++) {
				System.out.print(array[i] + ", ");
			}
			System.out.print(array[array.length - 1]);
		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		//BottomUpHeapsort
		
		//disabled printing of the arrays for very big arrays because it takes forever
		//and the text overlaps on my eclipse-install
		
		System.out.println("Bottom-up Heap sorting");
		int[] arr = generateRandomArray(1000000);
//		System.out.println("Before BottomUp");
//		printArray(arr);
		
		long startTime = System.nanoTime();
		
		BottomUpHeapsort.sort(arr);
		
		long endTime = System.nanoTime();
		
		long totalTime = (endTime - startTime) / 1000000; //divide by 1000000 (from nanoseconds to milliseconds)

//		System.out.println("After BottomUp");
//		printArray(arr);
		System.out.println("ms: "+ totalTime);

		
		//HyperSort
		System.out.println("\nHyperSorting");
		int[] hyperSortArray = generateRandomArray(1000000);
		//printArray(hyperSortArray);
		startTime = System.nanoTime();
		HyperSorter.sort(hyperSortArray);
		
		endTime = System.nanoTime();
//		printArray(hyperSortArray);
		totalTime = (endTime - startTime) / 1000000; //divide by 1000000 (from nanoseconds to milliseconds)
		System.out.println("ms: "+ totalTime);
		
		System.out.println("\nArrays.Utils - Sorting");
		
		int[] utilSort = generateRandomArray(1000000);
		startTime = System.nanoTime();
		Arrays.sort(utilSort);
		endTime = System.nanoTime();
		totalTime = (endTime - startTime) / 1000000;
		
		System.out.println("ms: "+ totalTime);
	}
}