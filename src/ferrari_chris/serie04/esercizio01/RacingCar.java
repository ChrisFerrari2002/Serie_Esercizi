package ferrari_chris.serie04.esercizio01;

public abstract class RacingCar extends Car {

    private int carNumber;

    private String pilotName;

    public RacingCar(String brand, int carNumber, String pilotName) {
        super(brand);
        this.carNumber = carNumber;
        this.pilotName = pilotName;
    }

    @Override
    public String toString() {
        return "RacingCar{" +
                "carNumber=" + carNumber +
                ", pilotName='" + pilotName + '\'' +
                "} " + super.toString();
    }
}
