package H1;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class MandelbrotSet extends Set {

	public MandelbrotSet() {
		this.name = "Mandelbrot";
	}

	// TODO H1.2
	@Override
	BufferedImage generate(int width, int height, int maxIter, int zoom, int rMax) {

		I = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


		return I;

	}

}
