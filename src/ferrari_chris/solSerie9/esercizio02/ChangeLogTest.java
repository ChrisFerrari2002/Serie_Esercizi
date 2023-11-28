package ferrari_chris.solSerie9.esercizio02;

import java.lang.annotation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

enum ChangeType {
    ADDED, CHANGED, FIXED, REMOVED;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.TYPE})
@Repeatable(ChangeLogContainer.class)
@interface ChangeLog {

    String version();

    ChangeType type() default ChangeType.CHANGED;

    String description();
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.TYPE})
@interface ChangeLogContainer {
    ChangeLog[] value();
}

@ChangeLog(version = "1.0.0", type = ChangeType.ADDED, description = "first implementation")
@ChangeLog(version = "1.0.2", type = ChangeType.REMOVED, description = "compareTo implementation")
class Coordinate {

    @ChangeLog(version = "1.0.2", description = "added changed precision from double to float")
    @ChangeLog(version = "1.0.3", description = "added final")
    private final float lat;

    @ChangeLog(version = "1.0.2", description = "added changed precision from double to float")
    @ChangeLog(version = "1.0.3", description = "added final")
    private final float lon;

    @ChangeLog(version = "1.0.4", description = "Initialized random values")
    public Coordinate() {
        this.lat = (float) (Math.random() * 180. - 90.); // between -90;+90
        this.lon = (float) (Math.random() * 360. - 180.);// between -180;+180
    }

    @ChangeLog(version = "1.0.4", type = ChangeType.FIXED, description = "Added validation on parameters")
    public Coordinate(float lat, float lon) {
        if (!isValidLat(lat)) throw new IllegalArgumentException("Invalid latitude");
        if (!isValidLon(lat)) throw new IllegalArgumentException("Invalid longitude");
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

    @ChangeLog(version = "1.0.1", type = ChangeType.FIXED, description = "Wrong approximation")
    public double distance(Coordinate other) {
        return Math.acos(Math.sin(lat) * Math.sin(other.lat) + Math.cos(lat) * Math.cos(other.lat) * Math.cos(other.lon - lon)) * 6371;
    }

    @Override
    public String toString() {
        return String.format("Coordinate@%s (lat=%.5f, lon=%.5f)", hashCode(), getLat(), getLon());
    }
}

public class ChangeLogTest {

    final static Map<String, Map<ChangeType, List<String>>> changesByVersion = new HashMap<>();

    private static void collectAnnotation(final String element, final ChangeLog annotation) {

        // Collect by version
        final String version = annotation.version();
        if (!changesByVersion.containsKey(version))
            changesByVersion.put(version, new HashMap<>());
        final Map<ChangeType, List<String>> changesByType = changesByVersion.get(version);

        // Collect by type for current version
        final ChangeType type = annotation.type();
        if (!changesByType.containsKey(type))
            changesByType.put(type, new ArrayList<>());

        changesByType.get(type).add(String.format("%s [%s]", annotation.description(), element));
    }

    private static void generateChangelog(Class<?> clazz) throws ClassNotFoundException {
        final String className = clazz.getName();

        if (clazz.isAnnotationPresent(ChangeLog.class) || clazz.isAnnotationPresent(ChangeLogContainer.class)) {
            final ChangeLog annotations[] = clazz.getAnnotationsByType(ChangeLog.class);
            for (ChangeLog annotation : annotations) {
                collectAnnotation(className, annotation);
            }
        }

        for (final Constructor constructor : clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(ChangeLog.class) || constructor.isAnnotationPresent(ChangeLogContainer.class)) {
                final String elementName = className + "." + constructor.getName();
                final ChangeLog annotations[] = constructor.getAnnotationsByType(ChangeLog.class);
                for (ChangeLog annotation : annotations) {
                    collectAnnotation(elementName, annotation);
                }
            }
        }

        for (final Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ChangeLog.class) || field.isAnnotationPresent(ChangeLogContainer.class)) {
                final String elementName = className + "." + field.getName();
                final ChangeLog annotations[] = field.getAnnotationsByType(ChangeLog.class);
                for (ChangeLog annotation : annotations) {
                    collectAnnotation(elementName, annotation);
                }
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ChangeLog.class) || method.isAnnotationPresent(ChangeLogContainer.class)) {
                final String elementName = className + "." + method.getName();
                final ChangeLog annotations[] = method.getAnnotationsByType(ChangeLog.class);
                for (ChangeLog annotation : annotations) {
                    collectAnnotation(elementName, annotation);
                }
            }
        }

        final ArrayList<String> versions = new ArrayList<>(changesByVersion.keySet());
        Collections.sort(versions);

        System.out.println("# Changelog for " + className);
        for (final String version : versions) {

            System.out.printf("%n## Version %s%n", version);

            final Map<ChangeType, List<String>> changeTypeListMap = changesByVersion.get(version);

            for (final ChangeType type : ChangeType.values()) {
                generateDetailsForType(type, changeTypeListMap);
            }
        }
    }

    private static void generateDetailsForType(final ChangeType type, final Map<ChangeType, List<String>> changeTypeListMap) {
        final List<String> strings = changeTypeListMap.get(type);
        if (strings == null || strings.isEmpty()) return;
        System.out.printf("%n### %s%n", type);
        for (final String description : strings)
            System.out.println(" - " + description);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        generateChangelog(Coordinate.class);
    }
}

