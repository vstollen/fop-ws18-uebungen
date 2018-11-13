import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

public class Square implements Directions {

	public static void main(String[] args) {
		World.setDelay(10);
		World.setSize(10,10);
		World.setVisible(true);

		// TODO: V5 insert your code here

		Robot[] robots = new Robot[2];
		robots[0] = new Robot(1, 1, Directions.East, 20);
		robots[1] = new Robot(10, 10, Directions.West, 20);

		for (int i = 0; i < 2; i+=1) {
			for (int j = 0; j < 9; j+=1) {
				for (Robot robot : robots) {
					robot.move();
					robot.putBeeper();
				}
			}

			for (Robot robot : robots) {
				robot.turnLeft();
			}
		}
	}

}
