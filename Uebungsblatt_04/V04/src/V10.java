import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

public class V10 {

    public static void main(String[] args) {
        World.setDelay(15);
        World.setSize(20, 30);
        World.setVisible(true);

        World.placeNSWall(1, 20, 3);

        Robot karel = new Robot(1, 1, Directions.East, 1);

        while (karel.frontIsClear()) {
            karel.move();
        }

        karel.putBeeper();

        for (int i = 0; i < 2; i++) {
            karel.turnLeft();
        }

        while (karel.avenue() != 1) {
            karel.move();
        }
    }
}
