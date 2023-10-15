package ferrari_chris.serie04.esercizio01;

public abstract class Bike extends Vehicle {

    public Bike(String brand) {
        super(brand, 2);
    }

    @Override
    public String toString() {
        return "Bike{} " + super.toString();
    }
}
