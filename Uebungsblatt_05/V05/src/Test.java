import kareltherobot.Directions;
import kareltherobot.World;

public class Test {

    public static void main(String[] args) {

        World.setSize(20, 20);
        World.setDelay(30);
        World.setVisible(true);

        TeamRobot teamRobot = new TeamRobot(1, 5, Directions.North, 5, 2, 2);

        teamRobot.move();
        teamRobot.move();

        teamRobot.putBeeper();

        teamRobot.move();

        teamRobot.turnLeft();

        teamRobot.move();

        teamRobot.turnLeft();
        teamRobot.turnLeft();

        teamRobot.move();

        teamRobot.turnLeft();
        teamRobot.turnLeft();
        teamRobot.turnLeft();

        teamRobot.move();

        teamRobot.pickBeeper();

        teamRobot.move();

        teamRobot.putBeeper();
        teamRobot.putBeeper();
        teamRobot.putBeeper();
        teamRobot.putBeeper();
        teamRobot.putBeeper();

        teamRobot.move();

        teamRobot.putBeeper();
    }
}
