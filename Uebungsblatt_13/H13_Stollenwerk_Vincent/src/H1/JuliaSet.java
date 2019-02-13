package H1;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class JuliaSet extends Set {

	public JuliaSet() {
		this.name = "Julia";
	}

	@Override
	public BufferedImage generate(int width, int height, int maxIter, int zoom, int rMax) {

		I = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				I.setRGB(column, row, generatePixel(column, row, width, height, maxIter, zoom, rMax).getRGB());
			}
		}

		return I;
	}

	/**
	 * Generates a pixels color for Mandelbrot visualization
	 * @param x pixel's x coordinate
	 * @param y pixel's y coordinate
	 * @param width resulting image's width
	 * @param height resulting image's height
	 * @param maxIter maximum iterations for the algorithm
	 * @param zoom zoom factor
	 * @param rMax maximum radius for the algorithm
	 * @return Color of the pixel at that position
	 */
	private Color generatePixel(int x, int y, int width, int height, int maxIter, int zoom, int rMax) {
		ComplexNumber z = new ComplexNumber(0.156, -0.8);
		ComplexNumber c = getComplexValueForPixel(x, y, width, height, zoom);

		int i = 0;

		while (c.abs() < rMax && i < maxIter) {
			double newReal = Math.pow(c.getRe(), 2) - Math.pow(c.getIm(), 2);

			c.setIm(2 * c.getRe() * c.getIm());
			c.setRe(newReal);

			c = c.add(z);

			i++;
		}

		return colorMap(i, maxIter);
	}

	/**
	 * Calculates the complex number for a pixel at the position
	 * @param x pixel's x coordinate
	 * @param y pixel's y coordinate
	 * @param width resulting images's width
	 * @param height resulting image's height
	 * @param zoom zoom factor
	 * @return Complex number for the coordinates
	 */
	private ComplexNumber getComplexValueForPixel(int x, int y, int width, int height, int zoom) {

		double real = (x - 0.5 * width) / zoom;
		double imaginary = (y - 0.5 * height) / zoom;

		return new ComplexNumber(imaginary, real);
	}
	
	/**
	 * Maps the algorithms iterations needed to exceed rMax to a color
	 * @param i needed iterations
	 * @param maxIter maximum iterations
	 * @return Color matching the iterations needed
	 */
	private Color colorMap(int i, int maxIter) {
		return Color.getHSBColor( (i % 256) / 255f, 0.6f, i < maxIter ? 1 : 0);
	}

}
