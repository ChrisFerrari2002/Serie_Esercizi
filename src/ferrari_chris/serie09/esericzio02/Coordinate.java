package ferrari_chris.serie09.esericzio02;

import java.util.Objects;

@Added(version = "1.0.0", text = "first implementation")
@Removed(version = "1.0.2", text = "compareTo implementation")
public class Coordinate {
    @Changed(version = "1.0.2", text = " added changed precision from double to float")
    private double lat;
    @Changed(version = "1.0.2", text = " added changed precision from double to float")
    private double lon;
    @Fixed(version = "1.0.1", text = "Wrong approximation")
    private void distance(){};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(lat, that.lat) == 0 && Double.compare(lon, that.lon) == 0;
    }
}
