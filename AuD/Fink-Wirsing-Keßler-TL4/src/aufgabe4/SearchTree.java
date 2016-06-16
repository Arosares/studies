package aufgabe4;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
		tail.setKey(x);
		return (root.search(x) != tail);
	}

	/**
	 * Inserts player x into the search tree.
	 * 
	 */
	public void insert(Player x) {
		// TODO implement
		if (x == null) {
			System.err.println("Player is null!");
		} else {
			tail.setKey(x);
			root = root.insertp(x, tail);
		}
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
		
		if (root!=tail) {
			depthFirst(players, root);
		}
		
		
		return players;
	}
	
	private void depthFirst(List<Player> players, BinTree node){
		
		players.add(node.getKey());
		
		if (node.getLeft() != tail) {
			depthFirst(players, node.getLeft());
		}
		if (node.getRight() != tail){
			depthFirst(players, node.getRight());
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
		
		if (root != tail) {
			Queue<BinTree> queue = new LinkedList<>();
			
			queue.add(root);
			
			while (!queue.isEmpty()) {
				BinTree n = (BinTree) queue.remove();
					players.add(n.getKey());	
				if (n.getLeft() != tail)
					queue.add(n.getLeft());
				if (n.getRight() != tail)
					queue.add(n.getRight());
			}		
		}
		
		return players;
	}
	
	public static void main(String[] args) {
		SearchTree t = new SearchTree();
		Player gomez = new Player(189, "Gomez", "Mario");
		Player neuer = new Player(193, "Neuer", "Manuel");
		Player khedira = new Player(189, "Khedira", "Sami");
		Player kroos = new Player(182, "Kroos", "Toni");
		Player mueller = new Player(186, "Müller", "Thomas");
		Player oezil = new Player(180, "Oezil", "Mesut");
		t.insert(gomez);
		t.insert(neuer);
		t.insert(khedira);
		t.insert(kroos);
		t.insert(mueller);
		t.insert(oezil);
		
		System.out.println("Is Member? " + t.member(mueller));
		System.out.println("Is Member? " + t.member(new Player(123, "test", "asd")));
		System.out.println("DepthFirstOrder\n" + t.getDepthFirstOrder());
		System.out.println("BreadthFirstOrder\n" + t.getBreadthFirstOrder() + "\n");
		BTreePrinter.print(t.root, t.tail);
	}
}