package ferrari_chris.serie04.esercizio01;

public class Bycicle extends Bike {

    enum Type {
        MOUNTAIN_BIKE, ROAD, TOURING, BMX, FOLDING
    }

    private Type type;

    public Bycicle(String brand, Type type) {
        super(brand);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Bycicle{" +
                "type=" + type +
                "} " + super.toString();
    }
}
