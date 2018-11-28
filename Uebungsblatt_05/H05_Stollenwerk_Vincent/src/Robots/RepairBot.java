package Robots;

import Main.RepairInstruction;
import Parts.Battery;
import Parts.Part;

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

        for (int i = 19; i <=19; i++) {
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
}
