package aufgabe4;

import java.util.Random;

public class Main {

	/**
	 * Generates a random integer array (you don't say).
	 * 
	 * @param size
	 * 			The size of the generated array
	 * @return
	 * 			The random array
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
	 * 			The array to print out
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
		// TODO test your implementations. For measuring the time you can use System.currentTimeMillis()
	}
}