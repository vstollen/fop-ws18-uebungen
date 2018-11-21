import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

public class V9 {

    public static void main(String args[]) {
        World.setDelay(10);
        World.setSize(20, 20);
        World.setVisible(true);

        World.placeBeepers(3, 10, 2);
        World.placeBeepers(3, 12, 1);
        World.placeBeepers(3, 5, 20);
        World.placeBeepers(3, 2, 1);

        int numberOfTurns = 0;
        Robot robot = new Robot(3, 15, Directions.West, 0);

        while (robot.avenue() > 1) {
            while (robot.nextToABeeper()) {
                robot.pickBeeper();
                numberOfTurns += 1;
            }
            robot.move();
        }

        for (int i = 0; i < numberOfTurns; i++) {
            robot.turnLeft();
        }
    }
}
