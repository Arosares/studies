package aufgabe4;


import java.util.ArrayList;
import java.util.List;

public class Node {

	private int id;

	private int indegree;

	private List<Node> successors = new ArrayList<Node>();

	public Node(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndegree() {
		return indegree;
	}

	public void setIndegree(int indegree) {
		this.indegree = indegree;
	}

	public List<Node> getSuccessors() {
		return successors;
	}

	public void setSuccessors(List<Node> successors) {
		this.successors = successors;
	}

	public String toString() {
		return "Node with id: " + id;
	}

}
