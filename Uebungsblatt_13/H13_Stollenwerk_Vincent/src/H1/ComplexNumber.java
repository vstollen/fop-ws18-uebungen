package H1;

public class ComplexNumber {

	private double imaginary;
	private double real;

	public ComplexNumber(double im, double re) {
		this.imaginary = im;
		this.real = re;
	}


	public double getIm() {
		return this.imaginary;
	}

	public void setIm(double im) {
		this.imaginary = im;
	}
	
	public double getRe() {
		return this.real;
	}
	
	public void setRe(double re) {
		this.real = re;
	}


	/**
	 * Adds given complex number to this and returns the result in a new ComplexNumber object
	 * @param cn Complex number to add
	 * @return sum of this and cn
	 */
	public ComplexNumber add(ComplexNumber cn) {
		double real = this.real + cn.real;
		double imaginary = this.imaginary + cn.imaginary;

		return new ComplexNumber(imaginary, real);
	}

	/**
	 * Multiplies given complex number to this and returns the result in a new ComplexNumber object
	 * @param cn Complex number multiply with
	 * @return product of this and cn
	 */
	public ComplexNumber mult(ComplexNumber cn) {
		double real = this.real * cn.real - this.imaginary * cn.imaginary;
		double imaginary = this.real * cn.imaginary + this.imaginary * cn.real;

		return new ComplexNumber(imaginary, real);
	}

	/**
	 * Calculates the absolute value of this
	 * @return Absolute value of this
	 */
	public double abs() {
		double realSquare = Math.pow(real, 2);
		double imaginarySquare = Math.pow(imaginary, 2);

		return Math.sqrt(realSquare + imaginarySquare);
	}

	public String toString() {
		return this.imaginary + "i+" + this.real;
	}

}
