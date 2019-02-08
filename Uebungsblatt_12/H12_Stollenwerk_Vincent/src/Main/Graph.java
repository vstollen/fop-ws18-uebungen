package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class Graph<T, W extends Comparable<W>> {
	
	private ArrayList<T> nodes = new ArrayList<>();
	private HashMap<String, ScalingMatrix<W>> weights = new HashMap<>();
	
	private final W noEdgeValue;
	/**
	 * @param noEdgeValue value for no edge value
	 */
	public Graph(W noEdgeValue) {
		this.noEdgeValue = noEdgeValue;
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
			weights.put(matrixName, new ScalingMatrix<W>(noEdgeValue, nodes.size()));
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
	
	public String[] getAllPaths(String matrixName, int startNode, boolean useNodeIndizes) {
		
		ArrayList<LinkedList<String>> allPaths = getAllPaths(weights.get(matrixName), startNode, useNodeIndizes, new ArrayList<String>());
		ArrayList<String> pathStrings = new ArrayList<>(allPaths.size());
		
		final String edgeString = " -> ";
		
		for (LinkedList<String> path : allPaths) {
			StringBuilder pathString = new StringBuilder();
			
			for (String node : path) {
				
				if (pathString.length() > 0) {
					pathString.append(edgeString);
				}
				
				pathString.append(node);
			}
			
			pathStrings.add(pathString.toString());
		}
		
		return pathStrings.toArray(new String[pathStrings.size()]);
	}
	
	private ArrayList<LinkedList<String>> getAllPaths(ScalingMatrix<W> weightMatrix, int startNode, boolean useNodeIndizes, Collection<String> visitedNodes) {
		
		ArrayList<LinkedList<String>> paths = new ArrayList<>();
		
		if (useNodeIndizes) {
			visitedNodes.add(Integer.toString(startNode));
		} else {
			visitedNodes.add(nodes.get(startNode).toString());
		}
		
		for (int nextNode = 0; nextNode < weightMatrix.size(); nextNode++) {
			
			W nextWeight = weightMatrix.get(startNode, nextNode);
			
			if (nextWeight.equals(noEdgeValue)) {
				continue;
			}
			
			if (useNodeIndizes) {
				if (visitedNodes.contains(Integer.toString(nextNode))) {
					continue;
				}
			} else {
				if (visitedNodes.contains(nodes.get(nextNode).toString())) {
					continue;
				}
			}
			
			ArrayList<LinkedList<String>> pathsFromNextNode = getAllPaths(weightMatrix, nextNode, useNodeIndizes, new ArrayList<String>(visitedNodes));
			
			for (LinkedList<String> path : pathsFromNextNode) {
				if (useNodeIndizes) {
					path.addFirst(Integer.toString(startNode));
				} else {
					path.addFirst(nodes.get(startNode).toString());
				}
			}
			
			paths.addAll(pathsFromNextNode);
		}
		
		if (paths.isEmpty()) {
			
			LinkedList<String> pathToStartNode = new LinkedList<>();
			
			if (useNodeIndizes) {
				pathToStartNode.add(Integer.toString(startNode));
			} else {
				pathToStartNode.add(nodes.get(startNode).toString());
			}
			
			paths.add(pathToStartNode);
		}
		return paths;
	}
}
