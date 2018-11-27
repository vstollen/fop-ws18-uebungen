import kareltherobot.Robot;
import kareltherobot.World;

public class DirectionTurner extends Robot {

    public DirectionTurner(int i, int i1, Direction direction, int i2) {
        super(i, i1, direction, i2);
    }

    public void turnNorth() {
        while (!facingNorth()) {
            turnLeft();
        }
    }

    public void turnEast() {
        while (!facingEast()) {
            turnLeft();
        }
    }

    public void turnSouth() {
        while (!facingSouth()) {
            turnLeft();
        }
    }

    public void turnWest() {
        while (!facingWest()) {
            turnLeft();
        }
    }
}
