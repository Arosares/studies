package aufgabe4;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO einen Graphen erstellen und die Knoten des Graphen topologisch sortieren
		// Zuletzt die Knoten auf der Konsole sortiert ausgeben
		IGraph iGraph = new GraphImpl();
		List<Node> topArray = iGraph.topSortGraph();
		
		System.out.println("The Topologic Sorted Graph is: \n" + topArray);
	}
}
