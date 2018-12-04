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

		System.out.println("Monte Carlo - " + pi);

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

	/**
	 * Approximates pi by approximating an equivalent integral using the trapezoidal rule
	 * @param n number of trapezes to use
	 * @return approximation of pi
	 */
	double integrationPi(int n) {

		// Prevents division by zero
		if (n < 1) {
			n = 1;
		}

		double sum = 0;
		for (double k = 1; k <= n; k++) {
			sum += piFunction((k - 1) / n);
			sum += piFunction(k / n);
		}

		double pi = sum / (2*n);

		System.out.println("Integration - " + pi);
		return pi;
	}

	/**
	 * Calculates the y value of f(x) = 4 / (1 + x^2)
	 * @param x value to be placed into the function
	 * @return functions y value
	 */
	double piFunction(double x) {
		return 4 / (1 + x*x);
	}


}
