package H2;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * a class to visualize the dragon curve
 * @author Florian Kadner
 *
 */
@SuppressWarnings("serial")
public class Visualizer extends JFrame {

	private String turns;
	private double startingAngle, len;

	public Visualizer(int n) {
		super("Dragon Curve");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		DragonCurve dc = new DragonCurve();
		turns = dc.getSequence(n);
		startingAngle = -n * (Math.PI / 4);
		len = 400 / Math.pow(2, n / 2.);
	}

	@Override
	public void paint(Graphics g) {

		double currentAngle = startingAngle;
		int currentX = 250;
		int currentY = 375;

		for (int i = 0; i <= turns.length(); i++) {

			int newX = currentX + (int) Math.round((int) len * Math.cos(currentAngle));
			int newY = currentY + (int) Math.round((int) len * Math.sin(currentAngle));

			g.drawLine(currentX, currentY, newX, newY);

			if (i == turns.length()) {
				break;
			}

			if (turns.charAt(i) == 'R') {
				currentAngle = currentAngle + Math.PI / 2;
			} else {
				currentAngle = currentAngle - Math.PI / 2;
			}

			currentX = newX;
			currentY = newY;
		}
	}
	
	
}
