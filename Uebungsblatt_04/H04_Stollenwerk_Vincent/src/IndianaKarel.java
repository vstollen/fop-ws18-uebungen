import kareltherobot.Robot;
import kareltherobot.World;

public class IndianaKarel extends Robot {

	public static final int RIGHT = -1;
	public static final int LEFT = 1;

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
		while (!nextToABeeper()) {
			turnLeft();
			while (!frontIsClear()) {
				turnRight();
			}

			move();
		}
		pickBeeper();
		turnOff();
	}
	

	// H3
	public void collectAll() {
		moveToStartPosition();

		int nextTurn = 0;

		do {

			movePickAllBeepers();

			if (nextTurn == RIGHT) {
				turnRight();
			} else {
				turnLeft();
			}

			if (nextTurn == LEFT) {
				nextTurn = RIGHT;
			} else {
				nextTurn = LEFT;
			}

			while (frontIsClear()) {
				movePickAllBeepers();
			}

			if (nextTurn == LEFT) {
				turnLeft();
			} else {
				turnRight();
			}
		} while (frontIsClear());
		turnOff();
	}

	public void movePickAllBeepers() {
		while (nextToABeeper()) {
			pickBeeper();
		}

		move();
	}

	public void moveToStartPosition() {
		while (frontIsClear()) {
			move();
		}

		turnLeft();

		while (frontIsClear()) {
			move();
		}

		turnLeft();
		turnLeft();

		move();

		turnLeft();
		turnLeft();
	}

}
