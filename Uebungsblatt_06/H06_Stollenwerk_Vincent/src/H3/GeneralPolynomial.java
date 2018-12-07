package H3;

import java.util.Arrays;

abstract public class GeneralPolynomial {

    private double[] coefficients;
    private int degree;

    /**
     * Constructs a new GeneralPolynomial object, given an array with coefficients
     * @param coef coefficients in degree matching descending order
     */
    public GeneralPolynomial(double[] coef) {

        coef = removeLeadingZeros(coef);

        if (coef.length > 0) {
            degree = coef.length - 1;
        } else {
            degree = 0;
        }
        coefficients = coef;
    }

    /**
     *
     * @return coefficients in degree matching descending order
     */
    public double[] getCoefficients() {
        return coefficients;
    }

    /**
     *
     * @return degree of the polynomial
     */
    public int getDegree() {
        return degree;
    }

    /**
     * Sets the coefficients
     * @param coefficients coefficients in degree matching descending order
     */
    private void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

    /**
     * Sets the degree
     * @param degree
     */
    private void setDegree(int degree) {
        this.degree = degree;
    }

    /**
     *
     * @return first derivative of the function
     */
    abstract public GeneralPolynomial firstDeriv();

    /**
     *
     * @return anti derivative of the function
     */
    abstract public GeneralPolynomial antiDeriv();

    /**
     *
     * @param array array to remove leading zeros from
     * @return new Array containing the values of the given array after cutting leading zeros
     */
    private static double[] removeLeadingZeros(double[] array) {

        int leadingZeros = 0;

        for (double entry : array) {
            if (entry != 0) {
                break;
            }

            leadingZeros++;
        }

        double[] cleanArray = new double[array.length - leadingZeros];

        System.arraycopy(array, leadingZeros, cleanArray, 0, array.length - leadingZeros);

        return cleanArray;
    }

}
