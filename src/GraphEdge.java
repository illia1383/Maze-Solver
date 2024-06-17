/*
 * Illia lotfalian
 * Date apr 10 2024
 * Class GraphEdge implements the GraphEdgeADT interface
 */



public class GraphEdge {

	//	Instance variables
	private GraphNode u, v;
	private int type;
	private String label;
	
	/*
	 * Constructor for the GraphEdge class
	 * @param u the first node
	 * @param v the second node
	 * @param type the type of the edge
	 * @param label the label of the edge
	 */
	public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
		this.u = u;
		this.v = v;
		this.type = type;
		this.label = label;
	}
	/*
	 * Getter method for the first endpoint
	 * @return the first endpoint
	 */
	public GraphNode firstEndpoint() {
		return u;
	}
	/*
	 * Getter method for the second endpoint
	 * @return the second endpoint
	 */
	public GraphNode secondEndpoint() {
		return v;
	}
	/*
	 * Getter method for the type
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/*
	 * Getter method for the label
	 * @return the label
	 */
	public void setype(int type) {
		this.type = type;
	}
	/*
	 * Setter method for the type
	 * @param type the type
	 */
	public String getLabel() {
		return label;
	}
	/*
	 * Setter method for the label
	 * @param label the label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
}
