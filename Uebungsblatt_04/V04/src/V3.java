import com.sun.istack.internal.NotNull;
import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

public class V3 {

	public static void main(String[] args) {
		World.setDelay(10);
		World.setSize(10,10);
		World.setVisible(true);

		final int height = 5;
		final int width = 3;

		Robot karel = new Robot(1, 1, Directions.East, Directions.infinity);
		Robot larel = new Robot(1, 1, Directions.East, Directions.infinity);

		boolean hasFinished = false;

		while (!hasFinished) {
			if (karel.facingEast() && karel.avenue()  < width) {
				movePutBeeper(karel);
			} else if (karel.facingEast() && karel.avenue() >= width) {
				karel.turnLeft();
			} else if (karel.facingNorth() && karel.street()< height) {
				movePutBeeper(karel);
			} else if (karel.facingNorth() && karel.street() >= height) {
				karel.turnLeft();
			} else if (karel.facingWest() && karel.avenue() > 1) {
				movePutBeeper(karel);
			} else if (karel.facingWest() && karel.avenue() <= 1) {
				karel.turnLeft();
			} else if (karel.facingSouth() && !(karel.street() == 1 && karel.avenue() == 1)) {
				movePutBeeper(karel);
			} else {
				hasFinished = true;
			}
		}

		hasFinished = false;

		while (!hasFinished) {
			if (larel.facingEast() && larel.avenue()  < width) {
				movePickBeeper(larel);
			} else if (larel.facingEast() && larel.avenue() >= width) {
				larel.turnLeft();
			} else if (larel.facingNorth() && larel.street()< height) {
				movePickBeeper(larel);
			} else if (larel.facingNorth() && larel.street() >= height) {
				larel.turnLeft();
			} else if (larel.facingWest() && larel.avenue() > 1) {
				movePickBeeper(larel);
			} else if (larel.facingWest() && larel.avenue() <= 1) {
				larel.turnLeft();
			} else if (larel.facingSouth() && !(larel.street() == 1 && larel.avenue() == 1)) {
				movePickBeeper(larel);
			} else {
				hasFinished = true;
			}
		}
	}

	private static void movePutBeeper(@NotNull Robot karel) {
		karel.putBeeper();
		karel.move();
	}

	private static void movePickBeeper(@NotNull Robot karel) {
		karel.pickBeeper();
		karel.move();
	}

}
