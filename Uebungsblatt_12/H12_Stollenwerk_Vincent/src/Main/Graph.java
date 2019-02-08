package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class Graph<T, W extends Comparable<W>> {
	
	private ArrayList<T> nodes = new ArrayList<>();
	private HashMap<String, ScalingMatrix<W>> weights = new HashMap<>();
	
	private final W defaultEdgeValue;
	/**
	 * @param defaultEdgeValue value for no edge value
	 */
	public Graph(W defaultEdgeValue) {
		this.defaultEdgeValue = defaultEdgeValue;
	}
	
	/**
	 * Adds node to the graph
	 * @param node
	 */
	public void addNode(T node) {
		nodes.add(node);
		
		int newSize = nodes.size();
		
		for (ScalingMatrix<W> matrix : weights.values()) {
			matrix.assureSize(newSize);
		}
	}
	
	/**
	 * Sets the weight of the matrix named matrixName at position row column to weight
	 * If there is no matrix named matrixName a new one will be created
	 * @param matrixName name of the matrix
	 * @param row row in the matrix
	 * @param column column in the matrix
	 * @param weight new weight to be set
	 */
	public void setWeight(String matrixName, int row, int column, W weight) {
		if (!weights.containsKey(matrixName)) {
			weights.put(matrixName, new ScalingMatrix<W>(defaultEdgeValue, nodes.size()));
		}
		
		weights.get(matrixName).set(row, column, weight);
	}
	
	/**
	 * @param matrixName name of the Matrix
	 * @param row row in the matrix
	 * @param column column in the matrix
	 * @return Weight at position row column of the matrix named matrixName
	 */
	public W getWeight(String matrixName, int row, int column) {
		return weights.get(matrixName).get(row, column);
	}
	
	/**
	 * @return current number of nodes
	 */
	public int getNumberOfNodes() {
		return nodes.size();
	}
}
