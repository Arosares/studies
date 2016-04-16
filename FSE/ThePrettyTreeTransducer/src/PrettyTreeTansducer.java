import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


/**
 * This is the main PrettyTreeTransducer class. It is used to transduce a tree.
 * @author Johannes Gareis
 *
 */
public class PrettyTreeTansducer {
	
	/**
	 * Function to read a input file in and return the corresponding ASCII-style
	 * string of the tree
	 * 
	 * @param input
	 *            the tree as an string-array list
	 * @return an ASCII-formated, well evaluated tree as an string
	 */
	public String printTreePretty(ArrayList<String> input) {
		// TODO ADD YOUR CODE HERE !!!
		
		int nodeCounter = 0;
		String output;
		LinkedList<Integer> ids = new LinkedList<>();
		LinkedList<Node> nodes = new LinkedList<>(); 
		LinkedList<Node> children = new LinkedList<>(); 

		
//		System.out.println("Print the inputFile: \n" + input);
		char[] chars;
		Collections.sort(input);
		
		for (String line : input) {
			
			
			line = line.replaceAll(",", "");
			chars = line.toCharArray();
			
			if (nodeCounter == 0){
				Node node = new Node(1);
				node.setRoot(true);
				nodes.add(node);
			} else {
				Node node = new Node(Character.getNumericValue(chars[0]));
				
				for (Node n : nodes) {
					if (Character.getNumericValue(chars[1]) == n.getID()){
						n.addChild(Character.getNumericValue(chars[1]));
					}	
				}
			}
			
			
			ids.add(Character.getNumericValue(chars[0]));
			for (int i = 3; i < chars.length; i++) {
				
			}
			nodeCounter++;
		}
		 
		return null;
	}
	
	
	public static void main(String[] args) {
		FileReaderImpl myFileReader = new FileReaderImpl();
		ArrayList<String> inputList = new ArrayList<String>();
		PrettyTreeTansducer treeTransducer = new PrettyTreeTansducer();

		// iterate over the arguments and print for each file the corresponding tree
		for (int i = 0; i < args.length; i++) {
			System.out.println("Printing Tree for File " + args[i]);
			// read the file in
			inputList = myFileReader.readFileToStringList(args[i]);
			// get the tree as an ASCII string
			String result = treeTransducer.printTreePretty(inputList);
			// print the tree to stout
			System.out.println(result);
		}

	}

}
