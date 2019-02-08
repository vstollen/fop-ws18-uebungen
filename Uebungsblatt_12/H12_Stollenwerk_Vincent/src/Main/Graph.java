package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class Graph<T, W extends Comparable<W>> {
	
	private ArrayList<T> nodes = new ArrayList<>();
	private ScalingMatrix<W> weights;
	
	/**
	 * @param defaultEdgeValue value for no edge value
	 */
	public Graph(W defaultEdgeValue) {
		weights = new ScalingMatrix<W>(defaultEdgeValue);
	}
	
	/**
	 * Adds node to the graph
	 * @param node
	 */
	public void addNode(T node) {
		nodes.add(node);
		weights.assureSize(nodes.size());
	}
}
