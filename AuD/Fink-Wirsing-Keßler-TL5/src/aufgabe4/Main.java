package aufgabe4;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO einen Graphen erstellen und die Knoten des Graphen topologisch sortieren
		// Zuletzt die Knoten auf der Konsole sortiert ausgeben
		IGraph iGraph = new GraphImpl();
		
		//for debugging
		List<Node> nodes = iGraph.topSortGraph();
		for (Node node : nodes) {
			System.out.println("Node: " + node + " Succ: " + node.getSuccessors() + " indegree: " + node.getIndegree());
		}
	}
	
}
