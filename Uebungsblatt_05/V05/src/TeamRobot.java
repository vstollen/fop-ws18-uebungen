import kareltherobot.Robot;

import java.util.HashSet;

public class TeamRobot extends Robot {

    private HashSet<Robot> teamMembers = new HashSet<>();

    public TeamRobot(int i, int i1, Direction direction, int beepers, int left, int right) {
        super(i, i1, direction, beepers);

        for (int relativeRobotPosition = -left; relativeRobotPosition < 0; relativeRobotPosition++) {
            teamMembers.add(new Robot(i, i1+relativeRobotPosition, direction, beepers));
        }

        for (int relativeRobotPosition = right; relativeRobotPosition > 0; relativeRobotPosition--) {
            teamMembers.add(new Robot(i, i1+relativeRobotPosition, direction, beepers));
        }
    }

    @Override
    public void move() {
        super.move();

        for (Robot robot : teamMembers) {
            robot.move();
        }
    }

    @Override
    public void turnLeft() {
        super.turnLeft();

        for (Robot robot : teamMembers) {
            robot.turnLeft();
        }
    }

    @Override
    public void pickBeeper() {
        super.pickBeeper();

        for (Robot robot : teamMembers) {
            robot.pickBeeper();
        }
    }

    @Override
    public void putBeeper() {
        super.putBeeper();

        for (Robot robot : teamMembers) {
            robot.putBeeper();
        }
    }
}
