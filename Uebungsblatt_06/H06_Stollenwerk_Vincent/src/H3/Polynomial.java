package H3;

public class Polynomial extends GeneralPolynomial implements Function {

    /**
     * Constructs new polynomial object
     * @param coef coefficients in degree matching descending order
     */
    public Polynomial(double[] coef) {
        super(coef);
    }

    @Override
    public double getValue(double x) {

        double sum = 0;

        for (int i = 0; i < getCoefficients().length; i++) {
            sum += getCoefficients()[i] * Math.pow(x, getDegree() - i);
        }

        return sum;
    }

    @Override
    public Polynomial firstDeriv() {

        double[] newCoeff = new double[getCoefficients().length - 1];

        for (int i = 0; i < newCoeff.length; i++) {
            newCoeff[i] = getCoefficients()[i] * (getDegree() - i);
        }

        return new Polynomial(newCoeff);
    }

    @Override
    public Polynomial antiDeriv() {

        double[] newCoeff = new double[getCoefficients().length + 1];

        for (int i = 0; i < getCoefficients().length; i++) {
            newCoeff[i] = getCoefficients()[i] / (getDegree() + 1 - i);
        }

        return new Polynomial(newCoeff);
    }
}
