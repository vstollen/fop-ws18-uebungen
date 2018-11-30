public class Car {

    private String name;
    private double mileage;

    /**
     * @param name name of the car
     */
    public Car(String name) {
        this.name = name;
        mileage = 0;
    }

    /**
     * @return name of the car
     */
    public String getName() {
        return name;
    }

    /**
     * @return mileage of the car
     */
    public double getMileage() {
        return mileage;
    }

    /**
     * Adds the given distance to the cars mileage
     * @param distance driven distance
     */
    public void drive(double distance) {
        mileage += distance;
    }
}
