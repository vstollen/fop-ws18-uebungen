import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

public class V4 {

    public static void main(String[] args) {
        World.setDelay(100);
        World.setSize(7, 7);
        World.setVisible(true);
        World.placeBeepers(3, 4, 0);

        Robot karel = new Robot(2, 4, Directions.North, 0);
        karel.move();
        if (karel.nextToABeeper()) {
            karel.pickBeeper();
        } else if (karel.anyBeepersInBeeperBag()){
            karel.putBeeper();
        }
    }
}
