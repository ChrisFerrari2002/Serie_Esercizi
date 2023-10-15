package ferrari_chris.serie04.esercizio01;

public class F1Car extends RacingCar {

    private int kersCapacity;

    public F1Car(String brand, int carNumber, String pilotName, int kersCapacity) {
        super(brand, carNumber, pilotName);
        this.kersCapacity = kersCapacity;
    }

    @Override
    public String toString() {
        return "F1Car{" +
                "kersCapacity=" + kersCapacity +
                "} " + super.toString();
    }
}
