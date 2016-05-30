package aufgabe_4;

/**
 * Interface, das eine Hashtabelle definiert, die wir anschliessend fuer die
 * Realisierung eines Woerterbuches verwenden wollen.
 */
public interface HashTable4Dict {

	/**
	 * Fuegt ein neues Element in die Hashtabelle ein.
	 * 
	 * Der Bucket, in den eingefuegt werden soll, wird durch das Ergebnis der 
	 * Methoden <code>getHashCode</code> bzw. <code>getBaezaYates</code> bestimmt, 
	 * welche in der Klasse <code>MyHashTable</code> zu implementieren sind.
	 * 
	 * @param object
	 *            das einzufuegende Element
	 */
	public void insert(Object object);

	/**
	 * Prueft, ob ein bestimmtes Element in der Hashtabelle enthalten ist. 
	 * Fuer die Bestimmung des buckets gilt das gleiche wie fuer die <code>insert</code>-Methode.
	 * 
	 * @param object
	 *            das auf Enthaltensein zu pruefende Element
	 * @return ob <code>object</code> enthalten ist
	 */
	public boolean contains(Object object);

	/**
	 * Liefert die maximale Anzahl an Elementen pro Bucket in der Hashtabelle.
	 * 
	 * @return die maximale Anzahl an Elementen pro Bucket
	 */
	public int getMaxChainLength();

	/**
	 * Liefert die minimale Anzahl an Elementen pro Bucket, die von 0
	 * verschieden ist.
	 * 
	 * @return minimale Anzahl an Elementen pro Bucket (verschieden 0)
	 */
	public int getMinNonZeroChainLength();

	/**
	 * Liefert die Anzahl an Kollisionen in der Hashtabelle.
	 * 
	 * @return Anzahl an Kollisionen in der Hashtabelle
	 */
	public int countCollisions();
}
