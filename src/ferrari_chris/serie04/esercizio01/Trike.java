package ferrari_chris.serie04.esercizio01;

public class Trike extends Vehicle {

    public Trike(String brand) {
        super(brand, 3);
    }

    @Override
    public String toString() {
        return "Trike{} " + super.toString();
    }
}
