package H2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

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
					splittedColorChannels[row][column][2] = -2;
				// Red
				} else if (row % 2 == 0 && column % 2 == 1) {
					splittedColorChannels[row][column][0] = data[row][column];

					splittedColorChannels[row][column][1] = -1;
					splittedColorChannels[row][column][2] = -2;
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

		int[][][] interpolated = new int[1][2][3];


		return interpolated;
	}

}
