package H2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BayerPattern {
	
	/** 
	 * Array containing the bayer data
	 * first index is the height
	 * second index is the width
	 */
	private int[][] data;

	/**
	 * @return the height of the image
	 */
	public int getHeight() {
		return data.length;
	}

	/**
	 * @return the width of the image
	 */
	public int getWidth() {
		return data[0].length;
	}

	/**
	 * @return the bayer data array
	 */
	public int[][] getData() {
		return data;
	}

	/**
	 * Constructs a new BayerPattern object by reading in a file in bayer data format
	 * @param filePath
	 */
	public BayerPattern(String filePath) {
		DataInputStream is;
		try {
			is = new DataInputStream(new FileInputStream(filePath));
			int height = is.readInt();
			int width = is.readInt();
			data = new int[height][width];
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					data[i][j] = (int) is.readShort();
				}
			}
			is.close();
		} catch (IOException e) {
			System.err
					.println("Error loading bayer data | " + filePath + " | " + e.toString() + " | " + e.getMessage());
		}
	}

	// TODO H2.2

	/**
	 * splits the data array into an array with 3 rgb like color channels and fills in unknown values with -1
	 * @return rgb like array from bayer pattern data
	 */
	public int[][][] splitColorChannels() {

		int[][][] splittedColorChannels = new int[getHeight()][getWidth()][3];

		for (int row = 0; row < getHeight(); row++) {
			for (int column = 0; column < getWidth(); column++) {
				// Green
				if ((column + row) % 2 == 0) {
					splittedColorChannels[row][column][1] = data[row][column];

					splittedColorChannels[row][column][0] = -1;
					splittedColorChannels[row][column][2] = -1;
				// Red
				} else if (row % 2 == 0 && column % 2 == 1) {
					splittedColorChannels[row][column][0] = data[row][column];

					splittedColorChannels[row][column][1] = -1;
					splittedColorChannels[row][column][2] = -1;
				//Blue
				} else if (row % 2 == 1 && column % 2 == 0) {
					splittedColorChannels[row][column][2] = data[row][column];

					splittedColorChannels[row][column][0] = -1;
					splittedColorChannels[row][column][1] = -1;
				}
			}
		}
		return splittedColorChannels;

	}

	
	// TODO H2.3
	public static int[][][] interpolateMissingValues(int[][][] splittedColorChannels) {

		int[][][] interpolated = new int[splittedColorChannels.length][splittedColorChannels[0].length][3];

		for (int row = 0; row < interpolated.length; row++) {
			for (int column = 0; column < interpolated[0].length; column++) {
				for (int color = 0; color < 3; color++) {
					if (splittedColorChannels[row][column][color] == -1) {

						double total = 0;

						Set<Integer> neightborColors = getNeighborColors(splittedColorChannels, row, column, color);

						for (int colorValue : neightborColors) {
							total += colorValue;
						}

						double average = total / neightborColors.size();
						interpolated[row][column][color] = (int) Math.round(average);
					} else {
						interpolated[row][column][color] = splittedColorChannels[row][column][color];
					}
				}
			}
		}
		return interpolated;
	}

	/**
	 *
	 * @param splittedColorChannels rgb like 3-dimensional int array
	 * @param row a pixels row
	 * @param column a pixels column
	 * @param color color looked for
	 * @return Set of all values of the searched color surrounding the pixel
	 */
	private static Set<Integer> getNeighborColors(int[][][] splittedColorChannels, int row, int column, int color) {
		HashSet<Integer> neighbors = new HashSet<>();

		for (int verticalOffset = -1; verticalOffset <= 1; verticalOffset++) {
			for (int horizontalOffset = -1; horizontalOffset <= 1; horizontalOffset++) {

				int offsetRow = row + verticalOffset;
				int offsetColumn = column + horizontalOffset;

				boolean isInVerticalBounds = offsetRow >= 0 && offsetRow < splittedColorChannels.length;
				boolean isInHorizontalBounds = offsetColumn >= 0 && offsetColumn < splittedColorChannels[0].length;

				if (isInVerticalBounds && isInHorizontalBounds && (verticalOffset != 0 || horizontalOffset != 0)) {

					if (splittedColorChannels[offsetRow][offsetColumn][color] != -1) {
						neighbors.add(splittedColorChannels[offsetRow][offsetColumn][color]);
					}
				}
			}
		}

		return neighbors;
	}

}
