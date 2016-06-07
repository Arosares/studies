package aufgabe4;


public class BinTree {
	
	private Player key;
	private BinTree left, right;
	
	BinTree(BinTree l, Player x, BinTree r) {
		this.left = l;
		this.key = x;
		this.right = r;
	}

	public Player getKey() {
		return key;
	}

	public void setKey(Player key) {
		this.key = key;
	}

	public BinTree getLeft() {
		return left;
	}

	public void setLeft(BinTree left) {
		this.left = left;
	}

	public BinTree getRight() {
		return right;
	}

	public void setRight(BinTree right) {
		this.right = right;
	}
	
	/**
	 * Searches for <code>Player x</code> in the binary tree.<br/><br/>
	 * 
	 * Returns the tree node/subtree containing <code>Player x</code> in its root if <code>Player x</code>
	 * is contained in the tree. Otherwise, it returns the stopping node <code>tail</code>.
	 * 
	 * @param x <code>Player</code> to be searched inside the tree
	 * @return tree node of <code>Player x</code> in case it is contained in the tree, <code>tail</code>
	 * otherwise
	 */
	public BinTree search(Player x) {
		// TODO implement
		return null;
	}

	/**
	 * Inserts <code>Player x</code> into the binary tree.
	 * 
	 * Returns the newly constructed subtree in case <code>Player x</code> is not in the tree yet.<br/>
	 * Otherwise, it returns the subtree containing <code>Player x</code> in its root.
	 * 
	 * @param x new <code>Player</code> to be inserted
	 * @param tail stopping node (end of tree)
	 * @return subtree with <code>Player x</code> in its root 
	 */
	public BinTree insertp(Player x, BinTree tail) {
		// TODO implement
		return null;
	}
}