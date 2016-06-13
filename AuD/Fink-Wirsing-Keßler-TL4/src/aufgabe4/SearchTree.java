package aufgabe4;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of a binary search tree.
 * 
 */
public class SearchTree {

	private BinTree root;
	private BinTree tail;

	public SearchTree() {
		tail = new BinTree(null, new Player(0, "DUMMY", "DUMMY"), null);
		root = tail;
	}

	public BinTree getRoot() {
		return root;
	}

	public void setRoot(BinTree root) {
		this.root = root;
	}

	public BinTree getTail() {
		return tail;
	}

	public void setTail(BinTree tail) {
		this.tail = tail;
	}

	/**
	 * Checks if the search tree is empty.
	 * 
	 * @return Returns true if the tree is empty, false otherwise.
	 *
	 */
	public boolean isEmpty() {
		return root == tail;
	}

	/**
	 * Checks if player x is an element of the search tree.
	 * 
	 * @return Returns true if x is element of the tree, false otherwise
	 * 
	 */
	public boolean member(Player x) {
		// TODO implement
		if (root.getKey() == x) {
			return true;
		}
		if (root.getKey().compareTo(x) < 0){
			root.getLeft();
		}
		
		
		return false;
	}

	/**
	 * Inserts player x into the search tree.
	 * 
	 */
	public void insert(Player x) {
		// TODO implement
		tail.setKey(x);
		root = root.insertp(x, tail);
		
	}

	/**
	 * Gives a list of all elements of the search tree in depth-first order.
	 * 
	 * @return Returns the depth-first order list.
	 * 
	 */
	public List<Player> getDepthFirstOrder() {
		// TODO implement
		List<Player> players = new LinkedList<>();

		
		return players;
	}
	
	private void depthFirst(List<Player> players, BinTree root){
		players.add(root.getKey());
		
		if (root.getLeft() != null) {
				depthFirst(players, root.getLeft());
		}
		if (root.getRight() != null) {
				depthFirst(players, root.getRight());
		}
	}
	

	/**
	 * Gives a list of all elements of the search tree in breadth-first order.
	 * 
	 * @return Returns the breadth-first order list.
	 * 
	 */
	public List<Player> getBreadthFirstOrder() {
		// TODO implement
		List<Player> players = new LinkedList<>();
		
		players.add(root.getKey());
		players.add(root.getLeft().getKey());
		
		return players;
	}

	public static void main(String[] args) {
		SearchTree t = new SearchTree();
		Player gomez = new Player(189, "Gomez", "Mario");
		Player neuer = new Player(193, "Neuer", "Manuel");
		Player khedira = new Player(189, "Khedira", "Sami");
		Player kroos = new Player(182, "Kroos", "Toni");
		Player mueller = new Player(186, "Müller", "Thomas");
		t.insert(gomez);
		t.insert(neuer);
		t.insert(khedira);
		t.insert(kroos);
		t.insert(mueller);

		System.out.println(t.getDepthFirstOrder());
		System.out.println(t.getBreadthFirstOrder());
		BTreePrinter.print(t.root, t.tail);
	}
}