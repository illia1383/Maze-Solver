import org.w3c.dom.Node;

public class GraphNode {

	//instance variables
	private boolean mark;
	private int name;
	/*
	 * Constructor for the GraphNode class
	 * @param name the name of the node
	 */
	public GraphNode(int name) {

	//initialize the variables
	this.name = name;
	this.mark = false;		
	} 

	/*
	 * Method to mark the node
	 * @param mark the mark to set
	 */	
	public void mark(boolean mark) {
		this.mark = mark;
	}
	/*
	 * Method to check if the node is marked
	 * @return true if the node is marked, false otherwise
	 */
	public boolean isMarked() {
		return mark;
	}
	/*
	 * Method to get the name of the node
	 * @return the name of the node
	 */
	public int getName() {
		return name;
	}
	
}
