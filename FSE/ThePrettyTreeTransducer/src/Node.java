import java.util.LinkedList;

public class Node {
	private int id = 0;
	private int parent = 0;
	private LinkedList<Node> children = new LinkedList<>();
	
	private boolean hasChildren;
	private boolean isRoot = false;
	private LinkedList<Integer> values = new LinkedList<>();
	private LinkedList<Integer> computedValues = new LinkedList<>();
	
	//v means just print out the values
	private char operator = 'v';
	
	
	
	public Node(int id, LinkedList<Node> children){
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
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public void setID(int id) {
		this.id = id;
	}
	public char getOperator() {
		return operator;
	}
	public void setOperator(char operator) {
		this.operator = operator;
	}
	public LinkedList<Node> getChildren() {
		return children;
	}
	public void setChildren(LinkedList<Node> children) {
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
	public void addChild (Node child){
		children.add(child);
	}
	public void addValue (int value){
		values.add(value);
	}
	public LinkedList<Integer> getValues() {
		return values;
	}
	
	public LinkedList<Integer> getComputedValues() {
		return computedValues;
	}
	public void setComputedValues(LinkedList<Integer> computedValues) {
		this.computedValues = computedValues;
	}
	@Override
	public String toString() {
		return "Node [id=" + id + ", Children: " + hasChildren + ", Operator: " + operator + ", values=" + values +  ", computedValues: " + computedValues + ", children=" + children +"]";
	}
	
}
