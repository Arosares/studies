package aufgabe_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementierung der Frequency-Count-Liste.
 *
 */
public class FrequencyCountList {

	// die Liste selbst
	private List<ListElem> list;

	/**
	 * Konstruktor.
	 * 
	 * @param type
	 *            Als welcher Listentyp soll <code>list</code> instantiiert
	 *            werden?
	 */
	public FrequencyCountList(ListType type) {

		if (type == ListType.ARRAYLIST) {
			list = new ArrayList<ListElem>();
		} else if (type == ListType.LINKEDLIST) {
			list = new LinkedList<ListElem>();
		} else {
			System.out.println("Wrong List Type!");
		}
	}

	public int getSize() {
		if (list == null) {
			throw new RuntimeException("list has no been instantiated yet!");
		}

		return list.size();
	}

	/**
	 * Fuegt ein neues Element an <code>list</code> an.
	 * 
	 * @param elem
	 *            Neues Listenelement, das angefuegt werden soll.
	 * 
	 * @return <code>true</code>, falls das Anhaengen erfolgreich war; sonst
	 *         <code>false</code>
	 */
	public boolean add(ListElem elem) {
		try {
			list.add(getSize(), elem);
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Sucht das Element mit der uebergebenen <code>id</code> in
	 * <code>list</code>. Nutzt dabei zum Iterieren ueber die Liste den
	 * entsprechenden <code>loopType</code>. <br/>
	 * <br/>
	 * Sofern das Element mit der ID <code>id</code> gefunden wird, muss die
	 * Methode <code>adjustPosition(.)</code> aufgerufen werden.
	 * 
	 * @param id
	 *            ID des Elementes, das gesucht wird.
	 * @param loopType
	 *            Schleifentyp, mit dem ueber die Liste iteriert werden soll.
	 * @return Das Listenelement, dessen ID <code>id</code> ist. <br/>
	 *         <code>null</code>, falls kein Element mit der ID <code>id</code>
	 *         in der Liste vorhanden ist.
	 */
	public ListElem find(int id, LoopType loopType) {
		if (loopType == LoopType.FOREACHLOOP) {
			int counter = 0;
			for (ListElem elem : list) {
				if (isElem(elem, id)) {
					adjustPosition(counter, elem);
					return elem;
				}
				counter++;
			}
		} else if (loopType == LoopType.FORLOOP) {
			for (int i = 0; i < getSize(); i++) {
				try {
					ListElem elem = list.get(i);
					if (isElem(elem, i)) {
						adjustPosition(i, elem);
						return elem;
					}
				} catch (IndexOutOfBoundsException e) {
					continue;
				}
			}
		} else {
			System.out.println("Wrong loop!");
		}
		return null;
	}

	private boolean isElem(ListElem elem, int id) {
		if (elem.getId() == id) {
			elem.incrementCounter();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Passt die Position von <code>elem</code> in der Liste gemaess dessen
	 * Haeufigkeitszaehler an. <code>startIdx</code> ist die Position von
	 * <code>elem</code> in der Liste vor der Anpassung (d.h., Sie koennen von
	 * dort aus in der Liste nach vorne gehen und die neue Position fuer
	 * <code>elem</code> ermitteln).
	 * 
	 * @param startIdx
	 *            Position von <code>elem</code> vor der Anpassung.
	 * @param elem
	 *            Listenelement, dessen Position in der Liste angepasst werden
	 *            soll.
	 */
	private void adjustPosition(int startIdx, ListElem elem) {
		int freq = elem.getCounter();
		System.out.println("start adjust");
		printList();
		for (int i = startIdx - 1; i > 0; i--) {
			try {
				ListElem nextElem = list.get(i);
				int nextFreq = nextElem.getCounter();
				if (nextFreq == freq) { // richtige Abfrage?
					list.remove(startIdx);
					list.add(i + 1, elem);
					
					break;
				} else if (nextFreq < freq) {
					
					
					int counter = i-1;
					while(counter >= 0){
						nextElem = list.get(counter);
						nextFreq = nextElem.getCounter();
						if (counter == 0) {
							list.remove(startIdx);
							list.add(0, elem);
						}
						if (nextFreq > freq) {
							list.remove(startIdx);
							list.add(counter+1, elem);
							
							break;
						} else {
							counter--;
						}
					}
					break;
				}
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method prints the list in a specific format
	 */
	public void printList() {
		for (ListElem elem : list) {
			System.out.print("(ID " + elem.getId() + ", Count "
					+ elem.getCounter() + ") ");
		}
		System.out.println();
	}

	/**
	 * Main Method for Testing
	 * 
	 */
	public static void main(String[] args) {

		// simple test: 10 elements and 10 search iterations
		FrequencyCountList arraySimple = Operation.generateList(
				ListType.ARRAYLIST, 10);
		arraySimple.printList();
		double timeArraySimple = Operation.effortMeter(arraySimple, 10, 20,
				LoopType.FOREACHLOOP);
		arraySimple.printList();
		System.out
				.println("ArrayList - For-Each: " + timeArraySimple + " ms\n");

		FrequencyCountList linkedSimple = Operation.generateList(
				ListType.LINKEDLIST, 10);
		linkedSimple.printList();
		double timeLinkedSimple = Operation.effortMeter(linkedSimple, 10, 20,
				LoopType.FOREACHLOOP);
		linkedSimple.printList();
		System.out.println("LinkedList - For-Each: " + timeLinkedSimple
				+ " ms\n");

		/*
		 * // variables for the itertations and arraysize int iter = 10; int
		 * arrSize = 1000;
		 * 
		 * // arraylist FrequencyCountList arrayList = Operation.generateList(
		 * ListType.ARRAYLIST, arrSize); double timeArray =
		 * Operation.effortMeter(arrayList, iter, 20, LoopType.FOREACHLOOP);
		 * System.out.println("Array-List- For-Each: " + timeArray + " ms\n");
		 * arrayList = Operation.generateList(ListType.ARRAYLIST, arrSize);
		 * timeArray = Operation .effortMeter(arrayList, iter, 20,
		 * LoopType.FORLOOP); System.out.println("Array-List- For: " + timeArray
		 * + " ms\n");
		 * 
		 * // linkedlist FrequencyCountList linkedList = Operation.generateList(
		 * ListType.LINKEDLIST, arrSize); double timeLinked =
		 * Operation.effortMeter(linkedList, iter, 20, LoopType.FOREACHLOOP);
		 * System.out.println("Linked-List For-Each: " + timeLinked + " ms\n");
		 * linkedList = Operation.generateList(ListType.LINKEDLIST, arrSize);
		 * timeLinked = Operation.effortMeter(linkedList, iter, 20,
		 * LoopType.FORLOOP); System.out.println("Linked-List For: " +
		 * timeLinked + " ms\n");
		 */
	}

}