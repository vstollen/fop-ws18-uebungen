import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

public class V7 {

    public static void main(String[] args) {
        World.setDelay(20);
        World.setSize(30, 10);
        World.setVisible(true);

        Robot karel = new Robot(1, 1, Directions.North, 1);
        for (int i = 5; i < 28; i++) {
            karel.move();
        }
    }
}
