package aufgabe4;


import java.util.List;

public interface IGraph {
		
	/**
	 * gives you a list of the graph's nodes that is sorted topologically
	 */
	List<Node> topSortGraph();

}