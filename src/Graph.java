/*
 * Illia lotfalian 
 * Date apr 10 2024
 * Class Graph implements the GraphADT interface
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Graph implements GraphADT {
	
//	Instance variables
	private Map<GraphNode, List<GraphEdge>> adjacencyList; 
	
	/*
	 * Constructor for the Graph class
	 * @param n the number of nodes in the graph
	 */
	public Graph(int n) {
		adjacencyList = new HashMap<>();
		for (int i = 0; i < n; i++) {
			GraphNode node = new GraphNode(i);
			adjacencyList.put(node, new ArrayList<GraphEdge>());
		}
	}
	/*
	 * InsertEdge
	 * @param nodeu the first node
	 * @param nodev the second node
	 * @param type the type of the edge
	 * @param label the label of the edge
	 * @throws GraphException if the edge already exists or the nodes do not exist
	 */
	@Override
	public void insertEdge(GraphNode nodeu, GraphNode nodev, int type, String label) throws GraphException {
		// Check if the nodes exist by retrieving the exact node instance from the map
		GraphNode actualNodeU = getNode(nodeu.getName());
		GraphNode actualNodeV = getNode(nodev.getName());
	
		if (actualNodeU == null || actualNodeV == null) {
			throw new GraphException("Node does not exist");
		}
	
		// Check if the edge already exists
		if (getEdge(actualNodeU, actualNodeV) != null) {
			throw new GraphException("Edge already exists");
		}
	
		// Create the edge and add it to both nodes' lists
		GraphEdge edge = new GraphEdge(actualNodeU, actualNodeV, type, label);
		GraphEdge edge1 = new GraphEdge(actualNodeV, actualNodeU, type, label);

		adjacencyList.get(actualNodeU).add(edge);
		adjacencyList.get(actualNodeV).add(edge1);
		
	
		//System.out.println("Edge successfully added between " + nodeu.getName() + " and " + nodev.getName());
	}
	
	/*
	 * getNode
	 * @param u the identifier of the node
	 * @return the node with the identifier u
	 * @throws GraphException if the node does not exist
	 */
	@Override
	public GraphNode getNode(int u) throws GraphException {
		// Iterate over each node in the adjacency list
		for (GraphNode node : adjacencyList.keySet()) {
			// Check if the current node's identifier matches 'u'
			if (node.getName() == u) {
				return node; // Return the found node
			}
		}
		// If no node was found with the given identifier, throw an exception
		throw new GraphException("Node with specified identifier does not exist");
	}

	/*
	 * incidentEdges
	 * @param u the node
	 * @return an iterator of the edges incident on node u
	 */
	@Override
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {
//		Select from your adjacency list the appropriate Node and return an iterator over the collection.
//		Usually a call to .iterator() should work, unless you do something really exotic
		if(getNode(u.getName()) == null) {
			throw new GraphException("Node does not exist");
		}else {
			//This should return a java iterator sotring all the edges incident on node u 
			return adjacencyList.get(u).iterator();
		}

	}
	/*
	 * getEdge
	 * @param u the first node
	 * @param v the second node
	 * @return the edge between u and v
	 * @throws GraphException if the edge does not exist or the nodes do not exist
	 */
	@Override
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
		if (getNode(u.getName()) == null || getNode(v.getName()) == null) {
			throw new GraphException("One or both nodes do not exist");
		}
		
		// Check edges from u to v and v to u
		List<GraphEdge> edgesFromU = adjacencyList.get(u);
		if (edgesFromU != null) {
			for (GraphEdge edge : edgesFromU) {
				if ((edge.firstEndpoint().equals(u) && edge.secondEndpoint().equals(v)) ||
					(edge.firstEndpoint().equals(v) && edge.secondEndpoint().equals(u))) {
					return edge;  // Edge found
				}
			}
		}
	
		// If no edge found, return null instead of throwing an exception
		return null;
	}
	
	
	
	/*
	 * areAdjacent
	 * @param u the first node
	 * @param v the second node
	 * @return true if u and v are adjacent, false otherwise
	 * @throws GraphException if the nodes do not exist
	 */
	@Override
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
//		maybe you could use a previously written method to solve this one quickly...
		if(getEdge(u, v) != null) {
			return true;
		}
		return false;
	}
}