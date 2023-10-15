package ferrari_chris.serie04.esercizio01;

public class NascarCar extends RacingCar {

    public NascarCar(String brand, int carNumber, String pilotName) {
        super(brand, carNumber, pilotName);
    }

    @Override
    public String toString() {
        return "NascarCar{} " + super.toString();
    }
}
