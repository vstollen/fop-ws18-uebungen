package H3;

import java.util.Arrays;

public class Main {

	// To test your implementation
	public static void main(String[] args) {

		double[] coeffs = {0, 0, 3, 0.333, 0, 5};
		Polynomial s = new Polynomial(coeffs);
		Polynomial sD = s.firstDeriv();
		Polynomial sAD = s.antiDeriv();
		System.out.println(s.getDegree());
		System.out.println(Arrays.toString(s.getCoefficients()));
		System.out.println(Arrays.toString(sD.getCoefficients()));
		System.out.println(Arrays.toString(sAD.getCoefficients()));
		System.out.println(s.getValue(4));
	}

}
