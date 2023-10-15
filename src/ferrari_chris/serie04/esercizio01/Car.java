package ferrari_chris.serie04.esercizio01;
public abstract class Car extends Vehicle {

    public Car(String brand) {
        super(brand, 4);
    }

    @Override
    public String toString() {
        return "Car{} " + super.toString();
    }
}
