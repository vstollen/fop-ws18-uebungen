import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

public class FirstJava implements Directions {

	public static void main(String[] args) {
		World.setDelay(5);
		World.setSize(7, 5);
		World.setVisible(true);

		// TODO: H1 insert your code here

		Robot[] robots = new Robot[5];

		// Da Arrays bei 0 anfangen entspricht das i in der Aufgabenstellung i + 1 im Code
		for (int i = 0; i < 5; i+=1) {
			robots[i] = new Robot(1, i+1, Directions.North, (i+1)*(i+2));
		}

		for (int i = 0; i < robots.length; i+=1) {
			while (robots[i].anyBeepersInBeeperBag()) {
				for (int j = 0; j < i+1; j+=1) {
					robots[i].putBeeper();
				}
				robots[i].move();
			}
		}
	}

}
