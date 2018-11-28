package Robots;

import Main.MainController;
import Main.RepairInstruction;
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

    public Part checkForDamagedParts() {

        for (Part part : parts) {
            if (part.getCondition().equals(Part.conditionDamaged)) {
                return part;
            }
        }

        return null;
    }

    public void wearOutParts() {

        int batteryIndex = getPartIndexByName("Battery");

        Battery battery = new Battery(Part.conditionNew, 100);

        if (batteryIndex != -1) {
            battery = (Battery) getPart(batteryIndex);
            battery.setLevel(battery.getLevel() - 1);
        }

        if (!battery.getCondition().equals(Part.conditionDamaged)) {

            // 200, da Arms bei 12.5% kaputt gehen
            int randomNumber = MainController.getRandomNumber(1, 200);

            if (0 < randomNumber && randomNumber <= 20) {
                damagePart("Camera");
            } else if (20 < randomNumber && randomNumber <= 64) {
                damagePart("Legs");
            } else if (64 < randomNumber && randomNumber <= 89) {
                damagePart("Arms");
            }
        }

        for (Part part : parts) {
            if (part.getCondition().equals(Part.conditionNew)) {
                part.setCondition(Part.conditionUsed);
            }
        }
    }

    public void doMove() {

        Part damagedPart = checkForDamagedParts();

        if (damagedPart == null) {
            randomMove();
            wearOutParts();

            if (waitingForRepair) {
                waitingForRepair = false;
            }
        } else if (!waitingForRepair) {
            waitingForRepair = true;

            RepairInstruction repairInstruction = new RepairInstruction(this, damagedPart.getName());
            MainController.orderRepairInstruction(repairInstruction);
        }
    }


    private void damagePart(String partName) {

        int partIndex = getPartIndexByName(partName);

        if (partIndex != -1) {
            Part part = getPart(partIndex);
            part.setCondition(Part.conditionDamaged);
        }
    }
}
