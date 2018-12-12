package Math;

import Main.Utils;

public class DoubleStringMath implements StringMath{

	@Override
	public String add(String x, String y) {
		
		double result = Double.parseDouble(x) + Double.parseDouble(y);
		return Utils.doubleToString(result);
	}

	@Override
	public String sub(String x, String y) {
		double result = Double.parseDouble(x) - Double.parseDouble(y);
		return Utils.doubleToString(result);
	}

	@Override
	public String mul(String x, String y) {
		double result = Double.parseDouble(x) * Double.parseDouble(y);
		return Utils.doubleToString(result);
	}

	@Override
	public String div(String x, String y) {
		double result = Double.parseDouble(x) / Double.parseDouble(y);
		return Utils.doubleToString(result);
	}

}
