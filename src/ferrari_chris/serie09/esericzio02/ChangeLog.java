package ferrari_chris.serie09.esericzio02;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ChangeLog {
    static void addClasses(Class<Coordinate> myClass, Map<String, String> target) {
        if (myClass.isAnnotationPresent(Added.class)){
            String version = myClass.getAnnotation(Added.class).version();
            String text = myClass.getAnnotation(Added.class).text();
            target.put(version, text);
        }
        if (myClass.isAnnotationPresent(Changed.class)){
            String version = myClass.getAnnotation(Changed.class).version();
            String text = myClass.getAnnotation(Changed.class).text();
            target.put(version, text);
        }
        if (myClass.isAnnotationPresent(Removed.class)){
            String version = myClass.getAnnotation(Removed.class).version();
            String text = myClass.getAnnotation(Removed.class).text();
            target.put(version, text);
        }
        if (myClass.isAnnotationPresent(Fixed.class)){
            String version = myClass.getAnnotation(Fixed.class).version();
            String text = myClass.getAnnotation(Fixed.class).text();
            target.put(version, text);
        }
    }
    static void addFields(Class<Coordinate> myClass, Map<String, String> target) {
        Field[] fields = myClass.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.isAnnotationPresent(Added.class)){
                String version = f.getAnnotation(Added.class).version();
                String text = f.getAnnotation(Added.class).text();
                target.put(version, text);
            }
            if (f.isAnnotationPresent(Changed.class)){
                String version = f.getAnnotation(Changed.class).version();
                String text = f.getAnnotation(Changed.class).text();
                target.put(version, text);
            }
            if (f.isAnnotationPresent(Removed.class)){
                String version = f.getAnnotation(Removed.class).version();
                String text = f.getAnnotation(Removed.class).text();
                target.put(version, text);
            }
            if (f.isAnnotationPresent(Fixed.class)){
                String version = f.getAnnotation(Fixed.class).version();
                String text = f.getAnnotation(Fixed.class).text();
            }
        }

    }
    public static <T> void main(String[] args) throws NoSuchMethodException {
        Class<Coordinate> myClass = Coordinate.class;
        Map<String, List<String>> allAnnotations = new HashMap<>();
        System.out.println("# Changelog for " + myClass.getSimpleName());





    }
}
