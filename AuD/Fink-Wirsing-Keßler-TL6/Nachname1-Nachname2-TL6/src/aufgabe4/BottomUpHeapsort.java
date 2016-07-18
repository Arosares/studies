package aufgabe4;

import java.util.LinkedList;
import java.util.List;

public class BottomUpHeapsort {

	/**
	 * The method to let an element sink down the heap. This has to be
	 * implemented in a bottom-up way just as described in the lecture slides.
	 * 
	 * @param i
	 *            Left boundary of the heap
	 * @param m
	 *            Right boundary of the heap
	 * @param S
	 *            The array in question
	 */
	private static void heapdown(int i, int m, int[] S) {
		List<Integer> sinkingPath = getSinkingPath(i, m, S);
		System.out.println("Before");
		printArray(S);
		reHeapBottomUp(i, S, sinkingPath);
		System.out.println("After reHeap");
		printArray(S);
	}

	/**
	 * Finds the sinking path for the element in the root of the (logical) heap
	 * and returns it. <br/>
	 * <br/>
	 * 
	 * The root/left boundary of the (logical) heap is at index position
	 * <code>i</code> of the array <code>S</code>, the last element/right
	 * boundary of the (logical) heap is at index position <code>m</code> of the
	 * array <code>S</code>.
	 * 
	 * @param i
	 *            index of the root element/left boundary of the heap structure
	 * @param m
	 *            index of the last element/right boundary of the heap structure
	 * @param S
	 *            the array in which logically a heap is depicted - ranging from
	 *            index <code>i</code> to index <code>m</code>
	 * @return the sinking path for the root element, represented by an integer
	 *         list which is comprised of the array indices of the path
	 */
	private static List<Integer> getSinkingPath(int i, int m, int[] S) {
		List<Integer> path = new LinkedList<>();
		// add first elem
		path.add(i);
		int son;
		while (2 * i + 1 <= m) {
			son = 2 * i + 1;
			// if right son is bigger
			if ((son < m) && (S[son] < S[son + 1])) { // if right son is
														// bigger son =
														// rightSon

				son = son + 1;
			}
			if (S[i] < S[son]) {
				swap(i, son, S);
				i = son;
			} else {
				break;
			}
			path.add(i);

		}
		// System.out.println(path);
		return path;
	}

	/**
	 * Re-establishes the heap structure, i.e. sinks the element at the root of
	 * the (logical) heap into its correct position inside the heap. <br/>
	 * <br/>
	 * 
	 * In order to achieve this, the steps 2 and 3 of the lecture notes (chapter
	 * 6, page 45) must be performed. <br/>
	 * <br/>
	 * 
	 * Pursue the <code>sinkingPath</code> from bottom-up until you find an
	 * element bigger than the element at the root of the (logical) heap (= the
	 * element at index position <code>i</code> of the array <code>S</code>).
	 * <br/>
	 * <br/>
	 * 
	 * Afterwards, the remaining elements of the path must be pushed one level
	 * up towards the root of the heap.
	 * 
	 * @param i
	 *            index of the root element/left boundary of the heap structure
	 * @param S
	 *            the array in which logically a heap is depicted - with its
	 *            root being at index <code>i</code>
	 * @param sinkingPath
	 *            the sinking path for the root element, represented by an
	 *            integer list which is comprised of the array indices of the
	 *            path
	 */
	private static void reHeapBottomUp(int i, int[] S, List<Integer> sinkingPath) {
		// elem to be sinked
		int elem = S[i];
		System.out.println("SinkingPath: " + sinkingPath);
		int nextPosition = sinkingPath.size() - 1;
		while (nextPosition >= 0) {
			// if i is bigger than current elem, next is father
			if (elem > S[sinkingPath.get(nextPosition)]) {
				sinkingPath.remove(nextPosition);
				nextPosition--;
			}
			// current elem is bigger or equal than i
			else {
				// rebuild heap
				for (int up : sinkingPath) {
					swap(up, (up - 1) / 2, S);
				}
				break;
			}
		}
	}

	/**
	 * The starting point of the sorting algorithm. Make sure that the heap
	 * structure is always maintained.
	 * 
	 * @param S
	 *            The array to be sorted
	 */
	public static void sort(int[] S) {
		// heapstructure
		System.out.println("Creating Heapstructure");
		for (int i = (S.length / 2) - 1; i >= 0; i--) {
			heapdown(i, S.length - 1, S);
		}

		// sort array
		System.out.println("Sorting array");
		for (int i = S.length - 1; i > 0; i--) {
			swap(i, 0, S);
			heapdown(0, i - 1, S);
		}
	}

	/**
	 * A helper method to swap two values in an array.
	 */
	private static void swap(int index1, int index2, int[] S) {
		int tmp = S[index1];
		S[index1] = S[index2];
		S[index2] = tmp;
	}

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
}