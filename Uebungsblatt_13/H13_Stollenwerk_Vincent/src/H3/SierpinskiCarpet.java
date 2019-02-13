package H3;

import java.awt.*;

import javax.swing.JFrame;

/**
 * a class to draw sierpinski carpets
 * @author Florian Kadner
 *
 */
@SuppressWarnings("serial")
public class SierpinskiCarpet extends JFrame {

	private int len = 729;
	private int n = 3;

	public SierpinskiCarpet() {
		super("Sierpinski Carpet");
		setBounds(100, 100, 100 + len, 100 + len);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void paint(Graphics g) {
		// ContantPane.getSize() returns the actual size of the canvas
		// while getWidth() and getHeight() include the title and the borders.
		Dimension dimens = getContentPane().getSize();

		// Starts to paint at the correct offset from the title bar
		paintAt(g, (int) (0.5 * (getWidth() - dimens.getWidth())), (int) (getHeight() - dimens.getHeight()), len, n);
	}

	private void paintAt(Graphics g ,int x, int y, int size, int n) {

		if (n == 0) {
			return;
		}

		int tileSize = size / 3;

		g.fillRect(x + tileSize, y + tileSize, tileSize, tileSize);

		if (n > 1) {
			paintAt(g, x, y, tileSize, n-1);
			paintAt(g, x + tileSize, y, tileSize, n-1);
			paintAt(g, x + 2 * tileSize, y, tileSize, n-1);

			paintAt(g, x, y + tileSize, tileSize, n-1);
			paintAt(g, x + 2 * tileSize, y + tileSize, tileSize, n-1);

			paintAt(g, x, y + 2 * tileSize, tileSize, n-1);
			paintAt(g, x + tileSize, y + 2 * tileSize, tileSize, n-1);
			paintAt(g, x + 2 * tileSize, y + 2 * tileSize, tileSize, n-1);
		}
	}

	
	public static void main(String[] args) {
		new SierpinskiCarpet().setVisible(true);
	}
}