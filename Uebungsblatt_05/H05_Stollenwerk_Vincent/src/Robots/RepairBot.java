package Robots;

import Main.MainController;
import Main.RepairInstruction;
import Parts.Battery;
import Parts.Part;
import kareltherobot.Robot;

public class RepairBot extends Bot {

    private Part[] spareParts;
    RepairInstruction currentJob;

    public RepairBot(int street, int avenue, Direction direction, int beepers) {
        super(street, avenue, direction, beepers);

        spareParts = new Part[20];

        for (int i = 0; i <= 4; i++) {
            spareParts[i] = new Battery(Part.conditionNew, 100);
        }

        for (int i = 5; i <= 9; i++) {
            spareParts[i] = new Part("Camera", Part.conditionNew);
        }

        for (int i = 10; i <= 14; i++) {
            spareParts[i] = new Part("Legs", Part.conditionNew);
        }

        for (int i = 15; i <=19; i++) {
            spareParts[i] = new Part("Arms", Part.conditionNew);
        }
    }

    public int sparePartAvailable(String partName) {

        for (int i = 0; i < spareParts.length; i++) {
            Part sparePart = spareParts[i];

            if (sparePart.getName().equals(partName) && (sparePart.getCondition() == Part.conditionNew || sparePart.getCondition() == Part.conditionUsed)) {
                return i;
            }
        }

        return -1;
    }

    public void replacePart(Bot r, int sparePartIndex) {

        int robotPartIndex = r.getPartIndexByName(spareParts[sparePartIndex].getName());

        if (robotPartIndex != -1) {

            Part partHolder = r.getPart(robotPartIndex);
            r.setPart(robotPartIndex, spareParts[sparePartIndex]);
            spareParts[sparePartIndex] = partHolder;
        }
    }

    public void getCloserToRobot(Robot r) {
        if (street() < r.street()) {
            faceDirection(North);
            move();
        } else if (street() > r.street()) {
            faceDirection(South);
            move();
        } else if (avenue() < r.avenue()) {
            faceDirection(East);
            move();
        } else if (avenue() > r.avenue()) {
            faceDirection(West);
            move();
        }
    }

    public void doMove() {
        if (currentJob == null) {
            currentJob = MainController.getNextRepairInstruction();
        }

        if (currentJob == null) {
            randomMove();
        } else {
            int sparePartIndex = sparePartAvailable(currentJob.getPartName());

            if (sparePartIndex != -1) {
                getCloserToRobot(currentJob.getRobot());

                if (street() == currentJob.getRobot().street() && avenue() == currentJob.getRobot().avenue()) {
                    replacePart(currentJob.getRobot(), sparePartIndex);
                    System.out.println("Replaced " + currentJob.getPartName());

                    currentJob = null;

                }
            } else {
                System.out.println("No " + currentJob.getPartName() + " left - Abandoning current job");
                currentJob = null;
                randomMove();
            }
        }
    }

}
