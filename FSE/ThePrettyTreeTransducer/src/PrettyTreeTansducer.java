import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;



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
		
		LinkedList<Node> nodes = new LinkedList<>(); 

		createNodes(input, nodes);
		nodes.getFirst().setRoot(true);
		
		//marks last child of a node, needed for printing
		nodes.stream().filter(n -> n.isHasChildren()).forEach(n -> n.getChildren().getLast().setLast(true));
		
		//computing values
		computeChilds(nodes.getFirst());
		
		computeValues(nodes.getFirst());
		
		//creating string
		String prettyTree = createString(nodes.getFirst(), "", 0);
		
		//remove last 2 Strings, bc they are wrong at the end
		prettyTree = prettyTree.substring(0, prettyTree.length()-2);
		
		return prettyTree;
	}
	
	private void createNodes(ArrayList<String> input, LinkedList<Node> nodes) {
		Collections.sort(input);
		for (String line : input) {
			
			
			line = line.replaceAll(",", "");
			char[] chars = line.toCharArray();
			
			
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
		}
	}
	
	private void computeChilds(Node node) {
			switch (node.getOperator()) {
			case 'U':
				for (Node child : node.getChildren()) {
					computeChilds(child);
				}
				break;
			case 'I':
				for (Node child : node.getChildren()) {
					computeChilds(child);
				}
				
				break;
				
			default:
				node.setComputedValues(node.getValues());
				break;
			}
	}
	
	private void computeValues(Node node){
		
		switch (node.getOperator()) {
		case 'U':
			node.getChildren().stream().forEach(child -> {
				boolean hasChild = child.isHasChildren();
				if (hasChild) {
					computeValues(child);	
				} 
					
				LinkedList<Integer> testValues = node.getChildren().getFirst().getComputedValues();
				node.setComputedValues(union(testValues, child.getComputedValues()));
				
			});

			break;
		case 'I':
			
			node.getChildren().stream().forEach(child -> {
				boolean hasChild = child.isHasChildren();
				if (hasChild) {
					computeValues(child);	
				} 
				LinkedList<Integer> testValues = node.getChildren().getFirst().getComputedValues();
				node.setComputedValues(intersection(testValues, child.getComputedValues()));
			});
			
			break;
		}
	}

	private String createString(Node node, String output, int level) {
		String prettyTree = output;
		LinkedList<Node> children = node.getChildren();
		
		for (int i = 0; i < level; i++) {
			prettyTree += "|  ";
		}
		
		if(!node.isRoot()){
			level++;
		}
		
		if (node.isHasChildren()) {
			if (node.isRoot()) {
				prettyTree += node.getID() + " : " +  node.getOperator() + " -> " + printV(node.getComputedValues()) + "\n|\n";
			} else {
				prettyTree += "+- " + node.getID() + " : " +  node.getOperator() + " -> " + printV(node.getComputedValues()) + "\n|";

			}
			
			for (int i = 0; i < level; i++) {
				prettyTree += "  |";
			}
			if (level > 0) {
				prettyTree += "\n";
			}
			
			for (Node child : children) {
				prettyTree = createString(child, prettyTree, level);
				
			}
			
		} else {
			
			prettyTree += "+- " + node.getID() + " : " + printV(node.getValues()) + " -> " + printV(node.getComputedValues()) + "\n";
			if(!node.isLast()){
				for (int i = 0; i < level; i++) {
					prettyTree += "|  ";
				}
				prettyTree +=  "\n";
			} else {
				prettyTree += "|\n";
			}
		}
			
		return prettyTree;
	}
	
	private String printV (LinkedList<Integer> values){
		String value = "";
		if (values.isEmpty()) {
			return "{}";
		}
		for (Integer v : values) {
			if (values.getLast()==v) {
				value += v + "}";
			} else {
				value += v + ", ";
			}
		}
		return "{"+value;
	}
	
	private <T> LinkedList<T> union(LinkedList<T> list1, LinkedList<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new LinkedList<T>(set);
    }
	private <T> LinkedList<T> intersection(LinkedList<T> list1, LinkedList<T> list2) {
		LinkedList<T> list = new LinkedList<>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
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
