import java.util.LinkedList;

public class Node {
	private int id = 0;
	private LinkedList<Integer> children = new LinkedList<>();
	private boolean hasChildren;
	private boolean isRoot = false;
	private LinkedList<Integer> values = new LinkedList<>();
	
	public Node(int id, LinkedList<Integer> children){
		this.id = id;
		this.children = children;
		hasChildren = true;
	}
	public Node(int id){
		this.id = id;
		hasChildren = false;
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public LinkedList<Integer> getChildren() {
		return children;
	}
	public void setChildren(LinkedList<Integer> children) {
		this.children = children;
	}
	public boolean isHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public void addChild (int child){
		children.add(child);
	}
	public void addValue (int value){
		values.add(value);
	}
	
}
