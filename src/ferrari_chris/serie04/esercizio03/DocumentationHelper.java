package ferrari_chris.serie04.esercizio03;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

class Coordinate {
    private float lat;
    private float lon;

    public Coordinate() {
        this.lat = (float) (Math.random() * 180. - 90.); // between -90;+90
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

    private static boolean isValidLat(float lat) {
        return (lat <= 90 && lat >= -90);
    }

    private static boolean isValidLon(float lon) {
        return (lon <= 180 && lon >= -180);
    }

    public double distance(Coordinate other) {
        return Math.acos(Math.sin(lat) * Math.sin(other.lat) + Math.cos(lat) * Math.cos(other.lat) * Math.cos(other.lon - lon)) * 6371;
    }

    @Override
    public String toString() {
        return String.format("Coordinate@%s (lat=%.5f, lon=%.5f)", hashCode(), getLat(), getLon());
    }
}

public class DocumentationHelper {

    private static void toMarkdown(final Class<?> theClass) throws ClassNotFoundException {
        System.out.printf("# markdown.%s\n", getClassName(theClass));
        printConstructor(theClass);
        printMethod(theClass);
    }
    private static void printConstructor(final Class<?> theClass){
        Constructor<?>[] constructors = theClass.getConstructors();

        System.out.println("## Constructor(s)");
        Class<?>[] paramType;
        Parameter[] paramName;
        for (int i = 0; i < constructors.length; i++) {
            paramType = constructors[i].getParameterTypes();
            paramName = constructors[i].getParameters();
            System.out.printf("### markdown.%s(%s)\n",
                    getClassName(theClass), Arrays.toString(paramType));
            if (constructors[i].getParameterCount() > 0){
                System.out.println("####Parameters:");
                for (int j = 0; j < paramName.length; j++) {
                    System.out.printf("-%s : TODO describe parameter.\n", paramName[j]);
                }
                System.out.println("TODO describe constructor");
                continue;
            }
            System.out.println("TODO describe constructor");
        }

    }
    private static void printMethod(final Class<?> theClass) throws ClassNotFoundException {
        Method[] classMethods = Class.forName("ferrari_chris.serie04.esercizio03.Coordinate").getDeclaredMethods();
        for (int i = classMethods.length-1; i >= 0; i--) {
            String name = classMethods[i].getName();
            String type = classMethods[i].getReturnType().toString();
            int paramCount = classMethods[i].getParameterCount();
            Parameter[] paramNames = classMethods[i].getParameters();
            if (paramCount != 0){
                System.out.println("#### Parameters:");
                for (int j = paramCount-1; j >= 0; j--) {
                    System.out.printf("-markdown.%s %s: TODO describe parameter\n", getClassName(theClass), paramNames[j].getName());
                }
            }
            System.out.printf("### %s %s\n", type, name);
            System.out.println("TODO describe method");
        }
    }
    private static String getClassName(final Class<?> theClass){
        return theClass.getSimpleName();
    }


    public static void main(String[] args) {
        try {
            toMarkdown(Coordinate.class);
        }
        catch (ClassNotFoundException c){
            System.out.println("Errore");
        }
    }
}
