/*
 * Name: Illia Lotfalian 
 * Date apr 10 2024
 * Class Maze runs the game 
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Maze {

//Intance variables 
private Graph graph;
private GraphNode startNode;
private GraphNode endNode;
private Stack<GraphNode> currentPath;
private int numCoins;
	/*
	 * Constructor for the Maze class
	 * @param inputFile the file that contains the maze
	 * @throws MazeException if there is an error reading the input file
	 * @throws GraphException if there is an error with the graph
	 */
	public Maze(String inputFile) throws MazeException {

		//initialize the instance variables
		this.currentPath = new Stack<GraphNode>();
		this.numCoins = 0;

		String filePath = inputFile; 
		try {
			
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			readInput(in);
			in.close();
		} catch (Exception e) {
			throw new MazeException("Error reading the input file");
		}
	}
	/*
	 * Getter method for the graph
	 * @return the graph
	 */
	public Graph getGraph() {
//		return your graph
		return this.graph;
	}
	/*
	 * Solve method that runs the DFS algorithm
	 */
	public Iterator<GraphNode> solve() {

		try {
			if(DFS(numCoins, startNode) == true) {
				return currentPath.iterator();
			}
		} catch (GraphException e) {
			System.out.println("Error in DFS");
		}
		return null;
	}
	/*
	 * Private helper method that performs the DFS algorithm
	 * @param k the number of coins
	 * @param go the current node
	 * @return true if the path is found, false otherwise
	 * @throws GraphException if there is an error with the graph
	 */
	private boolean DFS(int k, GraphNode go) throws GraphException {
		currentPath.push(go);
		//Base Case 		
		//mark the node as visited
		go.mark(true);
		//second base case 
		if(go.getName() == endNode.getName()) {
			return true;
		//recursive case
		}else{
			Iterator<GraphEdge> edges = graph.incidentEdges(go);
			
			while(edges.hasNext()) {

				GraphEdge edge = edges.next();
				if(!edge.secondEndpoint().isMarked() && k - edge.getType() >= 0){
					if(DFS(k - edge.getType(), edge.secondEndpoint())) {
						return true;
				}
			}
		}
		currentPath.pop();
		go.mark(false);
		return false;
	}
}
	/*
	 * Private helper method that reads the input file
	 * @param inputReader the BufferedReader object
	 * @throws IOException if there is an error reading the file
	 * @throws GraphException if there is an error with the graph
	 */
	private void readInput(BufferedReader inputReader) throws IOException, GraphException {
		//This is for S 
		inputReader.readLine();
		//This is For A
		int width = Integer.parseInt(inputReader.readLine());


		//This is for L
		int length = Integer.parseInt(inputReader.readLine());
		//This is for k
		numCoins = Integer.parseInt(inputReader.readLine());
		

		//initialize the graph
		graph = new Graph(width*length);
		String line;
		int roomcount = 0;
		//This is for the graph
		while((line = inputReader.readLine()) != null ) {

			//This is for when the line is at a row 
			if(line.contains("s") || line.contains("x") || line.contains("o")){
				for (int i = 0; i < line.length(); i++) {
					char currentVal = line.charAt(i);
					switch (currentVal) {
						case 's':

								startNode = graph.getNode(roomcount);
								roomcount++;
							break;
						case 'c':
								graph.insertEdge(graph.getNode(roomcount-1),graph.getNode(roomcount),0,"corridor");
							break;
						case 'x':
							endNode = graph.getNode(roomcount);
							roomcount++;
							break;
						case 'o':
							roomcount++;
							break;
						case 'w':

							break;
							
						default:
							graph.insertEdge(graph.getNode(roomcount-1),graph.getNode(roomcount) ,Character.getNumericValue(currentVal),"door");
							break;
						}	
					}
			}else{
				//This is for when the line is at a column
				int colcount = 0;
				for (int i = 0; i < line.length(); i++) {
					char currentVal = line.charAt(i);
					switch (currentVal) {
						case 'c':
							graph.insertEdge(graph.getNode(roomcount-width),graph.getNode(roomcount),0,"corridor");
							break;
						case 'w':
							break;
						default:
							graph.insertEdge(graph.getNode(roomcount-width),graph.getNode(roomcount) ,Character.getNumericValue(currentVal),"door");
							break;
						}
						if(i % 2 == 0) {
							roomcount++;
							colcount++;
						}
					}
					roomcount -= colcount;
					
				}
			}
		}
	}

	