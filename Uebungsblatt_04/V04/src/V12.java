import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;

public class V12 {

    public static void main(String[] args) {
        World.setDelay(15);
        World.setSize(20, 20);
        World.setVisible(true);

        int startStreet = 12;
        int startAvenue = 5;

        int destinationStreet = 10;
        int destinationAvenue = 3;

        Robot karel = new Robot(startStreet, startAvenue, Directions.North, 0);

        for (int currentStreet = karel.street(); currentStreet < destinationStreet; currentStreet++) {
            karel.move();
        }

        karel.turnLeft();

        for (int currentAvenue = karel.avenue(); currentAvenue > destinationAvenue; currentAvenue--) {
            karel.move();
        }

        karel.turnLeft();

        for (int currentStreet = karel.street(); currentStreet > destinationStreet; currentStreet--) {
            karel.move();
        }

        karel.turnLeft();

        for (int currentAvenue = karel.avenue(); currentAvenue < destinationAvenue; currentAvenue++) {
            karel.move();
        }
    }
}
