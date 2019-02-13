package H2;

public class DragonCurve {

	// TODO H2.1
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
