package Main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class Graph<T, W extends Comparable<W>> {
	
	private int MIN_MATRIX_SIZE = 10;
	
	private ArrayList<T> nodes = new ArrayList<>();
	private HashMap<String, W[][]> weights = new HashMap<>();
	
	private final W noEdge;
	
	/**
	 * @param noEdge value for no edge value
	 */
	public Graph(W noEdge) {
		this.noEdge = noEdge;
	}
	
	/**
	 * Adds node to the graph
	 * @param node
	 */
	public void addNode(T node) {
		nodes.add(node);
		adjustWeightMatrices();
	}
	
	@SuppressWarnings("unchecked")
	public void setWeight(String weightMatrix, int row, int column, W weight) {
		if (!weights.containsKey(weightMatrix)) {
			weights.put(weightMatrix, (W[][]) new Object[MIN_MATRIX_SIZE][MIN_MATRIX_SIZE]);
			adjustWeightMatrices();
		}
		
		weights.get(weightMatrix)[row][column] = weight;
	}
	
	/**
	 * Checks if the weight matrices have the needed size and increses it if needed
	 */
	private void adjustWeightMatrices() {
		for (Entry<String, W[][]> matrix : weights.entrySet()) {
			W[][] data = matrix.getValue();
			
			if (data.length < nodes.size()) {
				matrix.setValue(copyToBiggerMatrix(data));
			}
		}
	}
	
	/**
	 * Copies oldMatrix into a bigger Matrix
	 * @param oldMatrix
	 * @return the bigger matrix
	 */
	private W[][] copyToBiggerMatrix(W[][] oldMatrix) {
		
		int newMatrixSize = oldMatrix.length + oldMatrix.length >> 1;
			
		for (int row = 0; row < oldMatrix.length; row++) {
			oldMatrix[row] = Arrays.copyOf(oldMatrix[row], newMatrixSize);
		}
		
		W[][] biggerMatrix = Arrays.copyOf(oldMatrix, newMatrixSize);
		
		return replaceNullValues(biggerMatrix);
	}
	
	/**
	 * Replaces all null values of matrix with the noEdge parameter
	 * @param matrix
	 * @return matrix with null vales replaced
	 */
	private W[][] replaceNullValues(W[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix[row].length; column++) {
				if (matrix[row][column] == null) {
					matrix[row][column] = noEdge;
				}
			}
		}
		
		return matrix;
	}
}
