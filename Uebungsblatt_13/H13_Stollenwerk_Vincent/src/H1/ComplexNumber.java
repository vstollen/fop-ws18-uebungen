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


	// TODO H1.1
	public ComplexNumber add(ComplexNumber cn) {
		return null;
	}

	// TODO H1.1
	public ComplexNumber mult(ComplexNumber cn) {
		return null;
	}

	// TODO H1.1
	public double abs() {
		return 0;
	}

	public String toString() {
		return this.imaginary + "i+" + this.real;
	}

}
