package aufgabe_4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MyHashTable implements HashTable4Dict {

	// repraesentiert die Hashtabelle; eine Liste repraesentiert einen Bucket
	private List<Object>[] data = null;
	// welches Verfahren soll zur Bestimmung des Buckets fuer ein Wort verwendet werden?
	private HashOption hashoption;

	// Konstruktor
	public MyHashTable(int numBuckets, HashOption hashoption) {
		
		// TODO implementieren Sie den Konstruktor
		// TODO pruefen Sie die Parameter auf Korrektheit
		// TODO initialisieren Sie data
		// TODO setzen Sie die HashOption
		
		if(numBuckets <= 0 || hashoption != HashOption.BAEZAYATES || hashoption != HashOption.HASHCODE){
			System.out.println("Buckets müssen größer null sein und die Hashoption BaezaYates oder Hashcode!");
		}else {
			
			data = new ArrayList[numBuckets];
			for(int i = 0; i<numBuckets; i++){
				data[i] = new ArrayList<Object>();
			}
			this.hashoption = hashoption;
		}
		
	}
	
	/**
	 * Fuegt ein Objekt in die Hashtabelle ein. <br/><br/>
	 * 
	 * Entscheidend fuer das Einordnen in einen Bucket ist der Betrag des Wertes
	 * der Methode <code>getHashCode</code> (falls die <code>HashOption</code> 
	 * <code>HASHCODE</code> gesetzt wurde) bzw. <code>getBaezaYates</code> (falls 
	 * die <code>HashOption</code> <code>BAEZAYATES</code> gesetzt wurde).
	 * 
	 * @param object
	 *            das einzufuegende Objekt.
	 *            
	 * @throws RuntimeException Wenn das uebergebene Objekt kein String ist.
	 */
	public void insert(Object object) throws RuntimeException {
		
		// TODO implementieren Sie die Methode
		if (object instanceof String) {
			if (hashoption == HashOption.BAEZAYATES) {
				data[getHashCode(object)].add(object);
			} else {
				data[getBaezaYates(object)].add(object);
			}
		} else {
			throw new RuntimeException("The given object is no String!");
		}
	}

	/**
	 * Prueft, ob ein Objekt in der Hashtabelle verwaltet wird. Fuer die Bestimmung des
	 * buckets gilt das gleiche wie fuer die <code>insert</code>-Methode.
	 * 
	 * @param object
	 *            das zu pruefende Objekt
	 * @return ob <code>object</code> in der Hashtabelle verwaltet wird
	 * 
	 * @throws RuntimeException Wenn das uebergebene Objekt kein String ist.
	 */
	public boolean contains(Object object) throws RuntimeException {
		
		// TODO implementieren Sie die Methode und passen Sie den Rueckgabewert an
		if (!(object instanceof String)) {
			throw new RuntimeException("Object is not a String!");
		}
		
		for (List<Object> list : data) {
			for (Object obj : list) {
				if (object == obj) { // not sure if '==' or 'object.equals(obj)' is correct
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Liefert die maximale Anzahl an Elementen pro Bucket.
	 * 
	 * @return maximale Anzahl an Elementen pro Bucket
	 */
	public int getMaxChainLength() {
		
		// TODO implementieren Sie die Methode und passen Sie den Rueckgabewert an
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
	 * @throws RuntimeException Wenn alle Buckets leer sind (= die Groesse 0 haben).
	 */
	public int getMinNonZeroChainLength() throws RuntimeException {
		
		// TODO implementieren Sie die Methode und passen Sie den Rueckgabewert an
		
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
	public int countCollisions(){
		
		// TODO implementieren Sie die Methode und passen Sie den Rueckgabewert an
		
		return -1;
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
	 * Berechnet den Bucket fuer das uebergebene Objekt <code>object</code> gemaess 
	 * der Beschreibung in der Aufgabenstellung (basierend auf der <code>hashCode()</code>-Methode). 
	 * 
	 * Ist zur Bestimmung des Buckets zu verwenden, wenn <code>hashoption</code> den 
	 * Wert <code>HashOption.HASHCODE</code> besitzt.
	 * 
	 * @param object Objekt, dessen Bucket bestimmt werden soll.
	 * @return Index des Buckets, in den <code>object</code> eingefuegt werden muss.
	 */
	private int getHashCode(Object object){
		
		// TODO implementieren Sie die Methode und passen Sie den Rueckgabewert an
		
		return (object.hashCode() % data.length);
		
	}
	
	/**
	 * Berechnet den Bucket fuer das uebergebene Objekt <code>object</code> gemaess 
	 * des Algorithmus von Gonnet & Baeza-Yates (Kap.3, S.13 im Skript). 
	 * 
	 * Ist zur Bestimmung des Buckets zu verwenden, wenn <code>hashoption</code> den 
	 * Wert <code>HashOption.BAEZAYATES</code> besitzt.
	 * 
	 * @param object Objekt, dessen Bucket bestimmt werden soll.
	 * @return Index des Buckets, in den <code>object</code> eingefuegt werden muss.
	 */
	private int getBaezaYates(Object object){

		// TODO implementieren Sie die Methode und passen Sie den Rueckgabewert an
		BigInteger w = BigInteger.valueOf(32);
		BigInteger b = BigInteger.valueOf(131);
		String string = object.toString();
		BigInteger sum = BigInteger.ZERO;

		for (int i = 0; i < string.length(); i++) {
			String actualChar = string.substring(string.length() - i,
					string.length() - i);
			BigInteger hash = BigInteger.valueOf(actualChar.hashCode());
			sum = sum.add(hash.multiply((w.pow(i))));
		}

		sum = (sum.mod((BigInteger.valueOf(2).pow(32))));
		int baeza = sum.intValue() % data.length;

		return baeza;
	}

}
