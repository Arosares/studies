package aufgabe4;
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
		return null;
	}
	
	/**
	 * Generates nodes that correspond to the given adjacency matrix and assign
	 * indegree and successors to them
	 */
	private List<Node> generateNodesFromMatrix(int[][] matrix) {
		// TODO implementieren und Rückgabewert anpassen
		return null;
		
	}
	
	/**
	 * Sorts the given list according to their indegree (from lowest to highest)
	 */
	private void sortNodesWithIndegree(List<Node> nodes) {
		// TODO implementieren
		
	}
	
	/**
	 * Tests if there is a node with indegree zero in nodes
	 */
	private boolean isTopSortPossible(List<Node> nodes) {
		// TODO implementieren und Rückgabewert anpassen
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
