import kareltherobot.Robot;
import kareltherobot.World;

public class IndianaKarel extends Robot {

	public IndianaKarel(int street, int avenue, Direction direction, int beepers) {
		super(street, avenue, direction, beepers);
	}

	public void turnRight() {
		for (int i = 0; i < 3; i++)
			turnLeft();
	}

	public void putBeepers(int numberOfBeepers) {
		for (int i = 0; i < numberOfBeepers; i++) {
			putBeeper();
		}
	}

	// H1
	public static int pyramidBeepers = 140;

	public void buildPyramid() {
		move();
		turnLeft();

		for (int rowWidth = 13; rowWidth > 0; rowWidth = rowWidth - 2) {
			move();
			boolean karelOnEvenStreet = street() % 2 == 0;

			if (karelOnEvenStreet) {
				turnRight();
			} else {
				turnLeft();
			}

			for (int beepersToPlace = rowWidth; beepersToPlace > 0; beepersToPlace--) {
				move();
				putBeepers(street() - 3);
			}

			if (karelOnEvenStreet) {
				turnLeft();
			} else {
				turnRight();
			}
		}

		move();
		turnOff();
	}

	
	// H2
	public void solveMaze() {
		// TODO
		turnOff();
	}
	

	// H3
	public void collectAll() {
		// TODO
		turnOff();
	}

}
