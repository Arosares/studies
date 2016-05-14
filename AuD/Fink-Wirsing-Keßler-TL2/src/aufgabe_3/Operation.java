package aufgabe_3;

import java.util.Random;

/**
 * 
 * This class represents a bunch of operations that 
 * could be applied to a instance of FrequencyCountList class.
 *
 */
public class Operation {
	
	/**
	 * Generates a specific list. 
	 * @param TypeOfList: ARRAYLIST or LINKEDLIST
	 * @param numberOfElements: amount of the list
	 */
	public static FrequencyCountList generateList(ListType type, int numberOfElements){
		FrequencyCountList list = new FrequencyCountList(type);
		for (int i = 0; i < numberOfElements ; i++) 
			list.add(new ListElem(i));
		return list;
	}

	
	/**
	 * This method should measure the time of searching in a list.
	 * Firstly, a random number (id) is generated. Then an element
	 * with this id should be searched. The process has to be repeated 
	 * according to the number of iterations.
	 * 
	 * @param list
	 * @param iterations
	 * @param seed
	 * @param loopType type of loop which should be used when the <code>find</code>
	 * method of the <code>FrequencyCodeList</code> is called.
	 */
	public static double effortMeter(FrequencyCountList list, int iterations, long seed, 
			LoopType loopType) {
		
		double start = System.currentTimeMillis();
		
		Random random = new Random(seed);
		int id = 0;
		for (int i = 0; i < iterations; i++) {
			id = random.nextInt(list.getSize());
			list.find(id, loopType);
		}
		
		double end = System.currentTimeMillis();
		return end - start;
	}

}
