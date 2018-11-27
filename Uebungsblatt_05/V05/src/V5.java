import kareltherobot.Robot;
import kareltherobot.World;

public class V5 extends Robot {

    public V5(int i, int i1, Direction direction, int i2) {
        super(i, i1, direction, i2);
    }

    public static void main(String[] args) {

        World.setSize(20, 20);
        World.setDelay(20);
        World.setVisible(true);

        V5 robot = new V5(1, 1, East, 15);
        V5 lowbot = new V5(1, 1, North, 2);

        robot.beeperMove(10);
        lowbot.beeperMove(10);

    }

    public void beeperMove(int numberOfSteps) {
        if (numberOfSteps > beepers()) {
            turnOff();
        } else {
            for (int i = 0; i < numberOfSteps; i++) {
                move();
                putBeeper();
            }

            while (anyBeepersInBeeperBag()) {
                putBeeper();
            }
        }
    }
}
