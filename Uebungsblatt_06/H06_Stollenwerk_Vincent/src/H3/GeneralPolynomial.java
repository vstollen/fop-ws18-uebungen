package H3;

abstract public class GeneralPolynomial {

    private double[] coefficients;
    private int degree;

    /**
     * Constructs a new GeneralPolynomial object, given an array with coefficients
     * @param coef coefficients in degree matching descending order
     */
    public GeneralPolynomial(double[] coef) {
        degree = coef.length - 1;
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

}
