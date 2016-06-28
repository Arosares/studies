package aufgabe4;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO einen Graphen erstellen und die Knoten des Graphen topologisch sortieren
		// Zuletzt die Knoten auf der Konsole sortiert ausgeben
		IGraph iGraph = new GraphImpl();
		
		//for debugging
		
		List<Node> nodes = iGraph.topSortGraph();
		
		while (!nodes.isEmpty()) {
			print(nodes);
			
			nodes.remove(0);
		}
	}

	private static void print(List<Node> nodes) {
		System.out.println("Topologic Sorting: \n");
		for (Node node : nodes) {
			System.out.println("Node: " + node + " Succ: " + node.getSuccessors() + " indegree: " + node.getIndegree());
		}
		System.out.println();
	}
	
}
