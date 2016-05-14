package aufgabe_3;

/**
 * Diese Klasse repraesentiert ein Listenelement der <code>FrequencyCountList</code>.
 *
 */
public class ListElem {
	
	// ID des Listenelementes
	private int id;
	// Haeufigkeitszaehler des Listenelementes
	private int counter;

	public ListElem(int id){
		this.id = id;
		this.counter = 0;
	}
	
	public int getId(){
		return id;
	}

	public int getCounter(){
		return counter;
	}
	
	/**
	 * Erhoeht den Haufigkeitszaehler <code>counter</code> um 1 .
	 */
	public void incrementCounter(){
		counter++;
	}
	
}