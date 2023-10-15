package ferrari_chris.serie04.esercizio01;

public class Motorbike extends Bike {

    enum Type {
        STREET, CROSS, NAKED, TOURING, SCOOTER
    }

    private Type type;

    public Motorbike(String brand, Type type) {
        super(brand);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Motorbike{" +
                "type=" + type +
                "} " + super.toString();
    }
}
