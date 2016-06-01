package aufgabe_4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MyHashTable implements HashTable4Dict {

	// repraesentiert die Hashtabelle; eine Liste repraesentiert einen Bucket
	private List<Object>[] data = null;
	// welches Verfahren soll zur Bestimmung des Buckets fuer ein Wort verwendet
	// werden?
	private HashOption hashoption;

	// Konstruktor
	// TODO Nachfragen wegen initialisierung der Liste
	public MyHashTable(int numBuckets, HashOption hashoption) {
		if (numBuckets < 0 ) {
			System.err
					.println("Buckets müssen größer gleich null sein und die Hashoptions BaezaYates oder Hashcode müssen gewählt werden!");
		} else {
			// TODO können wir die Meldung umgehen? Verstehst du das aus dem VC
			// Forum?
			data = new ArrayList[numBuckets];
			for (int i = 0; i < numBuckets; i++) {
				data[i] = new ArrayList<Object>();
			}
			this.hashoption = hashoption;
		}
	}

	/**
	 * Fuegt ein Objekt in die Hashtabelle ein. <br/>
	 * <br/>
	 * 
	 * Entscheidend fuer das Einordnen in einen Bucket ist der Betrag des Wertes
	 * der Methode <code>getHashCode</code> (falls die <code>HashOption</code>
	 * <code>HASHCODE</code> gesetzt wurde) bzw. <code>getBaezaYates</code>
	 * (falls die <code>HashOption</code> <code>BAEZAYATES</code> gesetzt
	 * wurde).
	 * 
	 * @param object
	 *            das einzufuegende Objekt.
	 * 
	 * @throws RuntimeException
	 *             Wenn das uebergebene Objekt kein String ist.
	 */
	public void insert(Object object) throws RuntimeException {
		if (object instanceof String) {
			if (hashoption == HashOption.BAEZAYATES) {
				data[getBaezaYates(object)].add(object);
			} else {
				data[getHashCode(object)].add(object);
			}
		} else {
			throw new RuntimeException("The given object is no String!");
		}
	}

	/**
	 * Prueft, ob ein Objekt in der Hashtabelle verwaltet wird. Fuer die
	 * Bestimmung des buckets gilt das gleiche wie fuer die <code>insert</code>
	 * -Methode.
	 * 
	 * @param object
	 *            das zu pruefende Objekt
	 * @return ob <code>object</code> in der Hashtabelle verwaltet wird
	 * 
	 * @throws RuntimeException
	 *             Wenn das uebergebene Objekt kein String ist.
	 */
	public boolean contains(Object object) throws RuntimeException {
		if (!(object instanceof String)) {
			throw new RuntimeException("Object is not a String!");
		}
		// Wir sollen hier genauso vorgehen wie in insert --> Hashcode berechnen

		int hashcode;
		if (hashoption == HashOption.BAEZAYATES) {
			hashcode = getBaezaYates(object);
		} else {
			hashcode = getHashCode(object);
		}
		return data[hashcode].contains(object);
	}

	/**
	 * Liefert die maximale Anzahl an Elementen pro Bucket.
	 * 
	 * @return maximale Anzahl an Elementen pro Bucket
	 */
	public int getMaxChainLength() {
		int maximum = 0;

		for (List<Object> list : data) {
			if (list.size() > maximum) {
				maximum = list.size();
			}
		}

		return maximum;
	}

	/**
	 * Liefert die minimale Anzahl an Elementen pro Bucket, die von 0
	 * verschieden ist.
	 * 
	 * @return minimale Anzahl an Elementen pro Bucket (verschieden von 0)
	 * 
	 * @throws RuntimeException
	 *             Wenn alle Buckets leer sind (= die Groesse 0 haben).
	 */
	public int getMinNonZeroChainLength() throws RuntimeException {

		int minimum = getMaxChainLength();

		if (minimum == 0) {
			throw new RuntimeException("Buckets are empty!");
		}
		for (List<Object> list : data) {
			int size = list.size();
			if (size > 0 && size < minimum) {
				minimum = size;
			}
		}
		return minimum;
	}

	/**
	 * Liefert die Anzahl an Kollisionen in der Hashtabelle.
	 * 
	 * @return Anzahl an Kollisionen in der Hashtabelle.
	 */
	public int countCollisions() {
		// TODO stimmt das?
		int collisions = 0;
		for (List<Object> list : data) {
			int size = list.size();
			if (size > 0) {
				collisions++;
			}
		}

		return collisions;
	}

	/**
	 * Liefert das Array, das die Hashtabelle repraesentiert.
	 * 
	 * @return Array, das die Hashtabelle repraesentiert.
	 */
	public List<Object>[] getData() {
		return data;
	}

	/**
	 * Berechnet den Bucket fuer das uebergebene Objekt <code>object</code>
	 * gemaess der Beschreibung in der Aufgabenstellung (basierend auf der
	 * <code>hashCode()</code>-Methode).
	 * 
	 * Ist zur Bestimmung des Buckets zu verwenden, wenn <code>hashoption</code>
	 * den Wert <code>HashOption.HASHCODE</code> besitzt.
	 * 
	 * @param object
	 *            Objekt, dessen Bucket bestimmt werden soll.
	 * @return Index des Buckets, in den <code>object</code> eingefuegt werden
	 *         muss.
	 */
	private int getHashCode(Object object) {
		
		return (Math.abs(object.hashCode()) % data.length);
	}

	/**
	 * Berechnet den Bucket fuer das uebergebene Objekt <code>object</code>
	 * gemaess des Algorithmus von Gonnet & Baeza-Yates (Kap.3, S.13 im Skript).
	 * 
	 * Ist zur Bestimmung des Buckets zu verwenden, wenn <code>hashoption</code>
	 * den Wert <code>HashOption.BAEZAYATES</code> besitzt.
	 * 
	 * @param object
	 *            Objekt, dessen Bucket bestimmt werden soll.
	 * @return Index des Buckets, in den <code>object</code> eingefuegt werden
	 *         muss.
	 */
	private int getBaezaYates(Object object) {
		BigInteger b = BigInteger.valueOf(131);
		String stringObject = object.toString();
		BigInteger sum = BigInteger.ZERO;

		// Durchlaufen des String-Objects Buchstabe für Buchstabe
		for (int i = 0; i < stringObject.length(); i++) {
			// Auslesen des aktuellen Buchstabens
			String actualChar = stringObject.substring(stringObject.length()
					- i, stringObject.length() - i);
			// HashCode des aktuellen Buchstabens
			BigInteger hash = BigInteger.valueOf(actualChar.hashCode());
			// Addieren von B^i * den Hashwert
			sum = sum.add(hash.multiply((b.pow(i))));
		}

		// Summe modulo 2^w
		sum = (sum.mod((BigInteger.valueOf(2).pow(32))));
		// Summe modulo m
		int baeza = sum.intValue() % (data.length + 1);

		return baeza;
	}

}
