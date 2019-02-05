package Main;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph<T, W extends Comparable<W>> {
	
	private ArrayList<T> nodes = new ArrayList<>();
	private HashMap<String, W[][]> weights = new HashMap<>();
	
	private final W noEdge;
	
	public Graph(W noEdge) {
		this.noEdge = noEdge;
	}
}
