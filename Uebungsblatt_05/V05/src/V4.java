import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

public class V4 {

    public static void main(String[] args) {

        World.setSize(20, 20);
        World.setDelay(20);
        World.setVisible(true);

        Robot rabbit = new Robot(1, 1, Directions.East, 0);
        Robot turtle = new Robot(1, 1, Directions.East, 0);

        for (int i = 0; i < 10; i++) {

            if (i%2 == 0) {
                rabbit.move();
            }

            rabbit.move();
            turtle.move();
        }
    }
}
