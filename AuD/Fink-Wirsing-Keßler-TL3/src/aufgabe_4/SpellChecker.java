package aufgabe_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpellChecker {

	// die Hashtabelle
	private MyHashTable hashtable = null;
	
	/**
	 * Konstruktor. 
	 * 
	 * Initialisieren Sie zunaechst die Hashtabelle <code>hashtable</code> zur Ablage der Woerter. 
	 * 
	 * Die Woerter sind aus der Woerterbuchdatei unter dem Pfad <code>dictionaryFile</code> einzulesen 
	 * und anschliessend in <code>hashtable</code> abzulegen.
	 * 
	 * @param dictionaryFile Pfad zur Woerterbuchdatei. Zum Einlesen der Daten koennen Sie einen 
	 * <code>Scanner</code> oder einen <code>Stream</code> verwenden.
	 * @param numBuckets Anzahl an Buckets von <code>hashtable</code>.
	 * 
	 * @throws IOException 
	 */
    public SpellChecker (String dictionaryFile, int numBuckets, HashOption hashoption) throws IOException{
    	
    	// TODO implementieren Sie den Konstruktor
    }

    /**
     * Fuehrt eine (sehr einfache) Rechtschreibpruefung durch.
     * 
     * Liest die in der Datei mit dem Pfad <code>documentFile</code> enthaltenen Woerter aus und 
     * prueft, ob diese in <code>hashtable</code> enthalten sind. 
     * 
     * @param documentFile Pfad zur Datei mit den Woertern, fuer die eine Rechtschreibpruefung 
     * durchgefuehrt werden soll. Zum Einlesen der Daten koennen Sie einen <code>Scanner</code> oder 
     * einen <code>Stream</code> verwenden.
     * 
     * @throws FileNotFoundException
     */
    public void spellCheck (String documentFile) throws FileNotFoundException{
    	
		// TODO implementieren Sie die Methode
		
    }

    
	public static void main(String[] args) throws IOException{
		// TODO Messen & Testen
		String dictionaryFile = "resources\\dict.txt";
		SpellChecker checker = null;
		
		System.out.print("numBuckets");
		System.out.print("\t");
		//Average-Non-Zero-Length: 
		System.out.print("# collisions");
		System.out.print("\t");
		//Max-Length:
		System.out.print("MaxChainLength");
		System.out.print("\t");
		//Min-Non-Zero-Length:
		System.out.print("MinNonZeroChainLength");
		System.out.print("\t");
		System.out.println();
		
		HashOption ho = HashOption.BAEZAYATES; // TODO anpassen

		for (int i = 1000; i <= 26000; i = i+5000) {
			int numBuckets = i;
			checker = new SpellChecker(dictionaryFile,numBuckets, ho);
			
			System.out.print(numBuckets);
			System.out.print("\t\t");
			//Average-Non-Zero-Length: 
			System.out.print(checker.hashtable.countCollisions());
			System.out.print("\t\t\t");
			//Max-Length:
			System.out.print(checker.hashtable.getMaxChainLength());
			System.out.print("\t\t");
			//Min-Non-Zero-Length:
			System.out.print(checker.hashtable.getMinNonZeroChainLength());
			System.out.print("\t");
			System.out.println();
	
		}
		
		System.out.println("SIZE: "+checker.hashtable.getData().length);
		System.out.println();
		System.out.println();
		checker.spellCheck("resources/test.txt");
		
	}
}
