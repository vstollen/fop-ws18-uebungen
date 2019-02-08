package Main;

import java.util.Arrays;

public class ScalingMatrix<T> {

    private Object[][] matrix;
    private T defaultElement = null;

    /**
     * @param defaultElement element to fill not set matrix values
     */
    public ScalingMatrix(T defaultElement) {
        this.defaultElement = defaultElement;
        matrix = new Object[0][0];
    }

    /**
     * @param row
     * @param column
     * @return matrix element at position (row, column)
     */
    @SuppressWarnings("unchecked")
    public T get(int row, int column) {
        return (T) matrix[row][column];
    }
    
    /**
     * @return matrix size
     */
    public int getSize() {
    	return matrix.length;
    }
    
    /**
     * Sets the matrix element at position (row, column) to element
     * @param row
     * @param column
     * @param element
     */
    public void set(int row, int column, T element) {
    	matrix[row][column] = element;
    }

    /**
     * Assures that matrix has at minimum the given size. Grows the matrix if needed
     * @param size minimum size
     */
    public void assureSize(int size) {
        if (getSize() < size) {
            growToSize(size);
        }
    }

    /**
     * Grows the matrix to size x size, filling it up with defaultElements
     * @param size size to grow the matrix to
     */
    private void growToSize(int size) {

        matrix = Arrays.copyOf(matrix, size);

        for (int row = 0; row < size; row++) {
            matrix[row] = (matrix[row] == null) ? new Object[size] : Arrays.copyOf(matrix[row], size);

            for (int column = 0; column < size; column++) {
                if (matrix[row][column] == null) {
                    matrix[row][column] = defaultElement;
                }
            }
        }
    }
}
