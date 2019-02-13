package H2;

public class DragonCurve {

	/**
	 * Calculates the DragonSequence for a given n
	 * @param n
	 * @return DragonSequence of n'th order
	 */
	public String getSequence(int n) {

		if (n < 1) {
			return "";
		}

		if (n == 1) {
			return "R";
		}

		String leadingSequence = getSequence(n -1);

		StringBuilder newSequence = new StringBuilder(leadingSequence);

		newSequence.setCharAt(leadingSequence.length() / 2, 'L');

		return leadingSequence + "R" + newSequence.toString();
	}

}
