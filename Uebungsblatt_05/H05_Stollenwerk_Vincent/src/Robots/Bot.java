package Robots;

import Main.MainController;
import Parts.Battery;
import Parts.Part;
import kareltherobot.Robot;

public class Bot extends Robot {

    private Part[] parts;
    private boolean waitingForRepair;


    public Bot(int i, int i1, Direction direction, int i2) {
        super(i, i1, direction, i2);

        waitingForRepair = false;

        parts = new Part[4];
        parts[0] = new Battery(Part.conditionNew, 100);
        parts[1] = new Part("Camera", Part.conditionNew);
        parts[2] = new Part("Legs", Part.conditionNew);
        parts[3] = new Part("Arms", Part.conditionNew);
    }

    public Part getPart(int index) {
        return parts[index];
    }

    public void setPart(int index, Part part) {
        parts[index] = part;
    }

    public int getPartIndexByName(String name) {

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].getName().equals(name)) {
                return i;
            }
        }

        return -1;
    }

    public void faceDirection(Direction dir) {
        while (direction() != dir) {
            turnLeft();
        }
    }

    public void randomMove() {

        switch (MainController.getRandomNumber(1, 4)) {
            case (1):
                faceDirection(North);
                break;
            case (2):
                faceDirection(South);
                break;
            case (3):
                faceDirection(East);
                break;
            case (4):
                faceDirection(West);
                break;
        }

        while (!frontIsClear()) {
            turnLeft();
        }

        move();
    }
}
