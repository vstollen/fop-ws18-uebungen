import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

public class FirstStepsKarel implements Directions {

	public static void main(String[] args) {
		World.setDelay(77);
		World.setSize(10,10);
		World.setVisible(true);
		World.placeBeepers(2, 5, 1);
		
		// TODO

		Robot karel = new Robot(5, 5, Directions.East, 3);

		for (int i = 0; i < 2; i+=1) {
			karel.move();
		}

		while (!karel.facingSouth()) {
			karel.turnLeft();
		}

		karel.move();

		karel.putBeeper();

		for (int i = 0; i < 2; i+=1) {
			karel.move();
		}

		for (int i = 0; i < 2; i+=1) {
			karel.putBeeper();
		}

		while (!karel.facingWest()) {
			karel.turnLeft();
		}

		for (int i = 0; i < 2; i+=1) {
			karel.move();
		}

		karel.pickBeeper();

		karel.move();

	}

}
