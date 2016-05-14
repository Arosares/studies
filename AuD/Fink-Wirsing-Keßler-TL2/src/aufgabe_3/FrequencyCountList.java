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

		switch (type) {
		case ARRAYLIST:
			list = new ArrayList<ListElem>();

			break;
		case LINKEDLIST:
			
			list = new LinkedList<ListElem>();
			break;
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
			for (ListElem elem : list) {
				if (isElem(elem, id)) {
					return elem;
				}
			}
		} else if (loopType == LoopType.FORLOOP) {
			for (int i = 0; i < getSize(); i++) {
				try {
					ListElem elem = list.get(i);
					if (isElem(elem, i)) {
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
			adjustPosition(id, elem);
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
		for (int i = startIdx - 1; i > 0; i--) {
			try {
				ListElem prevElem = list.get(i);
				int prevFreq = prevElem.getCounter();
				if (prevFreq < freq) { // richtige Abfrage?
					list.add(i, elem);
					break;
				} else if (prevFreq == freq) {
					list.add(i - 1, elem);
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
		double timeArraySimple = Operation.effortMeter(arraySimple, 10, 20,
				LoopType.FOREACHLOOP);
		arraySimple.printList();
		System.out.println(timeArraySimple + " ms\n");

		FrequencyCountList linkedSimple = Operation.generateList(
				ListType.LINKEDLIST, 10);
		double timeLinkedSimple = Operation.effortMeter(linkedSimple, 10, 20,
				LoopType.FOREACHLOOP);
		arraySimple.printList();
		System.out.println(timeLinkedSimple + " ms\n");

		// //some bigger lists
		// arraylist
		FrequencyCountList arrayList = Operation.generateList(
				ListType.ARRAYLIST, 10000);
		double timeArray = Operation.effortMeter(arrayList, 1000, 20,
				LoopType.FOREACHLOOP);
		System.out.println(timeArray + " ms\n");
		arrayList = Operation.generateList(ListType.ARRAYLIST, 10000);
		timeArray = Operation
				.effortMeter(arrayList, 1000, 20, LoopType.FORLOOP);
		System.out.println(timeArray + " ms\n");

		// linkedlist
		FrequencyCountList linkedList = Operation.generateList(
				ListType.LINKEDLIST, 10000);
		double timeLinked = Operation.effortMeter(linkedList, 1000, 20,
				LoopType.FOREACHLOOP);
		System.out.println(timeLinked + " ms\n");
		linkedList = Operation.generateList(ListType.LINKEDLIST, 10000);
		timeLinked = Operation.effortMeter(linkedList, 1000, 20,
				LoopType.FORLOOP);
		System.out.println(timeLinked + " ms\n");

	}

}