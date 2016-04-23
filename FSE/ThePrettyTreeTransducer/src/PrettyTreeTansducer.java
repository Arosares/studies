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
		
		Node rootNode;
		LinkedList<Node> nodes = new LinkedList<>(); 
		
		
		createNodes(input, nodes);

		
		//computing values
		computeValues(nodes);

		
		//creating string
		return createString(nodes);
	}


	private String createString(LinkedList<Node> nodes) {
		String output = "";
		switch (nodes.getFirst().getOperator()) {
		case 'U':
			output = nodes.getFirst().getID() + " : " +  'U' + " -> " + nodes.getFirst().getComputedValues() + "\n";
			nodes.removeFirst();
			break;
		case 'I':
			output = nodes.getFirst().getID() + " : " +  'I' + " -> " + nodes.getFirst().getComputedValues() + "\n";
			nodes.removeFirst();
			break;
		default:
			break;
		}
		
		
		String lastNode =  "+- " + nodes.getLast().getID() + " : " +  nodes.getLast().getValues() + " -> " + nodes.getLast().getComputedValues() +"\n";
		nodes.removeLast();
		
		for (Node node : nodes) {
			if(node.isHasChildren()){
				computeValues(node.getChildren());
				output += "+- " + node.getID() + " : " +  node.getValues() + " -> " + node.getComputedValues() + "\n" + "|\n" + "   " + "|\n";
			} else {
				output += "+- " + node.getID() + " : " +  node.getValues() + " -> " +  node.getComputedValues() + "\n" + "|\n";

			}
		}
		
		output += lastNode;
		return output;
	}


	private void createNodes(ArrayList<String> input, LinkedList<Node> nodes) {
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
//					node.setHasChildren(true);
					break;
				case 'I':
					node.setOperator('I');
//					node.setHasChildren(true);
					break;
					
				default:
					node.addValue(Character.getNumericValue(chars[i]));
					break;
				}
			}
		}
	}


	private void computeValues(LinkedList<Node> nodes) {
		LinkedList<Integer> childValues = new LinkedList<>();
		LinkedList<Integer> valueHelper = new LinkedList<>();

		for (Node node : nodes) {
			


			switch (node.getOperator()) {
			case 'U':
				for (Node child : node.getChildren()) {
					if (child.isHasChildren()) {
						computeValues(child.getChildren());
					} else {
					childValues.addAll(child.getValues());
					}
				}
				Collections.sort(childValues);
				node.setComputedValues(childValues);
				break;
			case 'I':
				
				LinkedList<Node> children = node.getChildren();
				
				valueHelper = children.getFirst().getValues();
				children.removeFirst();
				
				
				for (Node child : children) {
					 LinkedList<Integer> values = child.getValues();
					 for (Integer value : values) {
						valueHelper.stream().filter(e -> (e == value)).forEach(e -> childValues.add(e));
					}
				}
				Collections.sort(childValues);
				node.setComputedValues(childValues);
				break;
				
			default:
				node.setComputedValues(node.getValues());
				break;
			}
			
			
			
		}
		
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
