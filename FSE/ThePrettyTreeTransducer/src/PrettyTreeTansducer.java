import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


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
		LinkedList<Integer> ids = new LinkedList<>();
		LinkedList<Node> nodes = new LinkedList<>(); 
//		LinkedList<Node> children = new LinkedList<>(); 

		
//		System.out.println("Print the inputFile: \n" + input);
		char[] chars;
		Collections.sort(input);
		for (String line : input) {
			
			
			line = line.replaceAll(",", "");
			chars = line.toCharArray();
			
			
			//create Node
			Node node = new Node(Character.getNumericValue(chars[0]));
			
			addChildren(nodes, chars, node);
			
			nodes.add(node);
			
			//adds values/operator to node
			for (int i = 2; i < chars.length; i++) {
				switch (chars[i]) {
				case 'U':
					node.setOperator('U');
					break;
				case 'I':
					node.setOperator('I');
					break;
					
				default:
					node.addValue(Character.getNumericValue(chars[i]));
					break;
				}
				
			}
			
//		ids.add(Character.getNumericValue(chars[0]));
			
		nodeCounter++;
		}
		
		//computing values
		LinkedList<Integer> childValuesU = new LinkedList<>();
		LinkedList<Integer> childValuesI = new LinkedList<>();
		LinkedList<Integer> valueHelper = new LinkedList<>();
		LinkedList<Integer> childValues = new LinkedList<>();
		
		for (Node node : nodes) {
			


			switch (node.getOperator()) {
			case 'U':
				for (Node child : node.getChildren()) {
					childValuesU.addAll(child.getValues());
				}
				break;
			case 'I':
				
				LinkedList<Node> children = node.getChildren();
				
				valueHelper = children.getFirst().getValues();
				children.removeFirst();
				
				
				for (Node child : children) {
					 LinkedList<Integer> values = child.getValues();
					 for (Integer value : values) {
						valueHelper.stream().filter(e -> (e == value)).forEach(e -> childValuesI.add(e));
					}
					
				}
				
				break;
			case 'v':
				
				childValues = node.getValues();
				
				break;
			}
		}
		
		Collections.sort(childValuesU);
		
		
		//creating string
		
		String output = "";
		switch (nodes.getFirst().getOperator()) {
		case 'U':
			output = nodes.getFirst().getID() + " : " +  'U' + " -> " + childValuesU + "\n";
			nodes.removeFirst();
			break;
		case 'I':
			output = nodes.getFirst().getID() + " : " +  'I' + " -> " + childValuesI + "\n";
			nodes.removeFirst();
			break;
		default:
			break;
		}
		
		
		String lastNode =  "+- " + nodes.getLast().getID() + " : " +  nodes.getLast().getValues() + " -> " + "computed values\n";
		nodes.removeLast();
		
		for (Node node : nodes) {
			if(node.isHasChildren()){
				String spaces =  " ";
				output += "+- " + node.getID() + " : " +  node.getValues() + " -> " + "computed values\n" + "|\n" + "   " + "|\n";
			} else {
				output += "+- " + node.getID() + " : " +  node.getValues() + " -> " + "computed values\n" + "|\n";

			}
		}
		
		output += lastNode;
		return output;
	}


	private void addChildren(LinkedList<Node> nodes, char[] chars, Node node) {
		for (Node n : nodes) {

			//gets the parent from the string
			int parent = Character.getNumericValue(chars[1]);
			if (parent == n.getID()){
				//adds the node as child to an existing node
				n.addChild(node);
				n.setHasChildren(true);
			}
		}
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
