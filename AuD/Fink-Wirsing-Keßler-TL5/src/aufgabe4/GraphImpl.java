package aufgabe4;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import aufgabe4.Node;

public class GraphImpl implements IGraph{

	private int adjacencyMatrix[][];
	private int numberOfNodes;
	
	/*
	 * gives you a list of the graph's nodes that is sorted topologically
	 */
	@Override
	public List<Node> topSortGraph() {
		// TODO implementieren und Rückgabewert anpassen
		
		
		List<Node> nodes = generateNodesFromMatrix(getMatrixFromUserInput());
		
		if (!isTopSortPossible(nodes)) {
			throw new RuntimeException();
		}
		
		sortNodesWithIndegree(nodes);
		return nodes;
	}
	
	/**
	 * Generates nodes that correspond to the given adjacency matrix and assign
	 * indegree and successors to them
	 */
	private List<Node> generateNodesFromMatrix(int[][] matrix) {
		// TODO implementieren und Rückgabewert anpassen
		List<Node> nodes = new LinkedList<>();
		
		
		for (int i = 0; i < matrix.length; i++) {
			List<Node> successors = new LinkedList<>();
			
			//create Node (starting from 1 ..)
			Node node = new Node(i+1);
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] != 0 && matrix[i][j] != -1) {
					//add succesor
					successors.add(new Node(j+1));
				}
				node.setSuccessors(successors);
			}
			nodes.add(node);
		}
		//set Indegree
		for (Node node : nodes) {
			
			for (Node suc : node.getSuccessors()) {
				int indegreeIncrement = suc.getIndegree()+1;
				int id = suc.getId();
				Node toChange = nodes.get(id-1);
				toChange.setIndegree(toChange.getIndegree() + indegreeIncrement);
			}
			
			
		}
		return nodes;
		
	}
	
	/**
	 * Sorts the given list according to their indegree (from lowest to highest)
	 */
	private void sortNodesWithIndegree(List<Node> nodes) {
		// TODO implementieren
		Comparator<Node> comp = new IndegreeComparator();
		nodes.sort(comp);
		
	}
	
	/**
	 * Tests if there is a node with indegree zero in nodes
	 */
	private boolean isTopSortPossible(List<Node> nodes) {
		// TODO implementieren und Rückgabewert anpassen
		for (Node node : nodes) {
			if (node.getIndegree() == 0) {
				return true;
			}
		}
		return false;
		
	}

	/**
	 * adjacency matrix is generated with given user input and returns it</br>
	 * </br>
	 * EXAMPLE-INPUT: </br>
	 * Enter the number of nodes</br>
	 * 6</br>
	 * </br>
	 * Enter the Weighted Matrix for the directed graph</br>
	 * 0 0 3 0 0 0</br>
	 * 0 0 0 0 0 0</br>
	 * 0 0 0 1 5 0</br>
	 * 0 2 0 0 0 0</br>
	 * 0 3 0 0 0 10</br>
	 * 0 0 0 0 0 0 </br>
	 * 
	 * @returns matrix of integers
	 */
	private int[][] getMatrixFromUserInput() {

		Scanner scan = new Scanner(System.in);

		// Eingabe der Knotenanzahl
		System.out.println("Enter the number of nodes");
		numberOfNodes = scan.nextInt();
		if(numberOfNodes < 1){
			System.err.println("Number of nodes shall at least be 1! Please try again!");
			getMatrixFromUserInput();
		}

		
		adjacencyMatrix = new int[numberOfNodes][numberOfNodes];
		// Angabe einer gewichteten Adjazenz-Matrix
		System.out.println("Enter the Weighted Matrix for the directed graph");
		for (int i = 0; i < numberOfNodes; i++) {

			for (int j = 0; j < numberOfNodes; j++) {
				adjacencyMatrix[i][j] = scan.nextInt();

				// Gewicht ist bei der "Kante zu sich selbst" 0
				if (i == j) {
					adjacencyMatrix[i][j] = 0;
					continue;
				}

				if (adjacencyMatrix[i][j] == 0) {
					adjacencyMatrix[i][j] = -1;
				}
			}
		}
		scan.close();
		return adjacencyMatrix;
	}

}
