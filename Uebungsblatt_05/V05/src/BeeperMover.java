import kareltherobot.Robot;

public class BeeperMover extends Robot {

    public BeeperMover(int i, int i1, Direction direction, int i2) {
        super(i, i1, direction, i2);
    }

    public void move(int numberOfSteps) {

        if (numberOfSteps > beepers()) {
            turnOff();
        } else {
            for (int i = 0; i < numberOfSteps; i++) {
                move();
                putBeeper();
            }

            putAllBeepers();
        }
    }

    @Override
    public void move() {
        move(1);
    }

    public void putAllBeepers() {
        while (anyBeepersInBeeperBag()) {
            putBeeper();
        }
    }
}
