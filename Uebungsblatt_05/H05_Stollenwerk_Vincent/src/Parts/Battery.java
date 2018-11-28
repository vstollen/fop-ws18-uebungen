package Parts;

public class Battery extends Part {

    private int level;

    public Battery(String condition, int level) {
        super("Battery", condition);

        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;

        if (level < 100) {
            if (level > 0) {
                setCondition(Part.conditionUsed);
            } else {
                setCondition(Part.conditionDamaged);
                System.out.println("Battery is empty");
            }
        }
    }
}
