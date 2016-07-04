package aufgabe4;

import java.util.List;

public class BottomUpHeapsort {

	/**
     * The method to let an element sink down the heap. This has to be implemented in a bottom-up way just as described in the lecture slides.
     * 
     * @param i Left boundary of the heap
     * @param m Right boundary of the heap
     * @param S The array in question
     */
    private static void heapdown(int i, int m, int[] S) {
		// TODO implement
    }
     
    /**
     * Finds the sinking path for the element in the root of the (logical) heap and returns it.
     * <br/><br/>
     * 
     * The root/left boundary of the (logical) heap is at index position <code>i</code> of the array <code>S</code>, 
     * the last element/right boundary of the (logical) heap is at index position <code>m</code> of the array <code>S</code>.
     * 
     * @param i index of the root element/left boundary of the heap structure
     * @param m index of the last element/right boundary of the heap structure
     * @param S the array in which logically a heap is depicted - ranging from index <code>i</code> to index <code>m</code>
     * @return the sinking path for the root element, represented by an integer list which is comprised of the array indices
     * of the path
     */
    private static List<Integer> getSinkingPath(int i, int m, int[] S){
        // TODO implement
         
        return null;
    }
     
    /**
     * Re-establishes the heap structure, i.e. sinks the element at the root of the 
     * (logical) heap into its correct position inside the heap.
     * <br/><br/>
     * 
     * In order to achieve this, the steps 2 and 3 of the lecture notes (chapter 6, page 45) 
     * must be performed.
     * <br/><br/>
     * 
     * Pursue the <code>sinkingPath</code> from bottom-up until you find an element bigger than
     * the element at the root of the (logical) heap (= the element at index position <code>i</code> 
     * of the array <code>S</code>).
     * <br/><br/>
     * 
     * Afterwards, the remaining elements of the path must be pushed one level up towards the root 
     * of the heap.
     * 
     * @param i index of the root element/left boundary of the heap structure
     * @param S the array in which logically a heap is depicted - with its root being at index <code>i</code>
     * @param sinkingPath the sinking path for the root element, represented by an integer list which is comprised of the array indices
     * of the path
     */
    private static void reHeapBottomUp(int i, int[] S, List<Integer> sinkingPath){
        // TODO implement
    }
 
    /**
     * The starting point of the sorting algorithm. Make sure that the heap structure is always maintained.
     * 
     * @param S The array to be sorted
     */
    public static void sort(int[] S) {
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