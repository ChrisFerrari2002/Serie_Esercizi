package ferrari_chris.serie05.esercizio02;

import java.io.Serializable;

@MarkdownDoc
class Coordinate implements Comparable<Coordinate>, Serializable {
    private float lat;
    private float lon;

    @MarkdownDocIgnore
    public Coordinate() {
        this.lat = (float) (Math.random() * 180. -  90.); // between -90;+90
        this.lon = (float) (Math.random() * 360. - 180.);// between -180;+180
    }

    public Coordinate(float lat, float lon) {
        if (!isValidLat(lat))
            throw new IllegalArgumentException("Invalid latitude");
        if (!isValidLon(lat))
            throw new IllegalArgumentException("Invalid longitude");
        this.lat = lat;
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    static boolean isValidLat(float lat) {
        return (lat <= 90 && lat >= -90);
    }

    static boolean isValidLon(float lon) {
        return (lon <= 180 && lon >= -180);
    }

    public double distance(Coordinate other) {
        return Math.acos(Math.sin(lat)*Math.sin(other.lat)+Math.cos(lat)*Math.cos(other.lat)*Math.cos(other.lon-lon))*6371;
    }

    @Override
    public int compareTo(Coordinate o) {
        return Float.compare(this.lat, o.lat) +  + Float.compare(this.lon, o.lon);
    }

    @MarkdownDocIgnore
    @Override
    public String toString() {
        return String.format("Coordinate@%s (lat=%.5f, lon=%.5f)", hashCode(), getLat(), getLon());
    }
}
