package H1;

public class ApproximatePi {

	// TODO H1.1

	/**
	 * Approximates pi by generating random points and calculating the ratio of points inside and outside a circle
	 * @param n number of points to generate
	 * @return approximation of pi using a Monte-Carlo-Simulation
	 */
	double monteCarloPi(int n) {

		// Prevents division by zero
		if (n < 1) {
			n = 1;
		}

		double pointsInsideCircle = 0;

		for (int i = 0; i < n; i++) {

			double xCoordinate = Math.random();
			double yCoordinate = Math.random();

			if (getDistanceToCenter(xCoordinate, yCoordinate) < 1) {
				pointsInsideCircle++;
			}
		}

		double pi = (pointsInsideCircle / n) * 4;

		System.out.println(pi);

		return pi;
	}

	/**
	 *
	 * @param x coordinate of a two dimensional point
	 * @param y coordinate of a two dimensional point
	 * @return The distance of a two dimensional point to (0,0)
	 */
	private double getDistanceToCenter(double x, double y) {
		return Math.sqrt(x*x + y*y);
	}

	// TODO H1.2
	double integrationPi(int n) {
		return 3;
	}


}
