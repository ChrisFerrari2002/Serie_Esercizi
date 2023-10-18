package ferrari_chris.serie05.esercizio01.codegen;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashSet;

public class Main {
    private final static HashSet<String> fieldNames = new HashSet<>();

    // class name, implementation
    private static final String classTemplate = "public class %s {%n%s}";

    // type, field name, field value
    private static final String fieldTemplate = "\tprivate %s %s = %s;%n";

    // type, field name, field value
    private static final String fieldStringTemplate = "\tprivate %s %s = \"%s\";%n";

    // return type, method name, field name
    private static final String getterTemplate = "%n\tpublic %s get%s(){%n\t\treturn %s;%n\t}%n";

    // method name, parameter type, parameter, field name, parameter
    private static final String setterTemplate = "%n\tpublic void set%s(%s %s){%n\t\tthis.%s = %s;%n\t}%n";

    public static String generate(String className) throws Exception {
        Class<?> targetClass = null;
        String generatedCode = "";
        try {
            // get class
            targetClass = Class.forName(className);
            generatedCode = String.format(classTemplate, targetClass.getSimpleName(),
                    generateImplementation(targetClass));
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        return generatedCode;
    }
    private static int countRepetition(HashSet<String> set, String name){
        int cnt = 0;
        for (String s : set) {
            if (name.equals(s))
                cnt++;
        }
        return cnt;
    }
    private static void setNames(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();

        for (Field f : fields) {
            if (f.isAnnotationPresent(Extract.class) && fieldNames.contains(f.getName())){
                int cnt = countRepetition(fieldNames, f.getName());
                fieldNames.add(f.getName() + cnt);
                continue;
            } else if (f.isAnnotationPresent(Extract.class)) {
                fieldNames.add(f.getName());
            }
        }
    }
    private static String stringFields(Class<?> clazz){
        try {
            setNames(clazz);
            String type;
            Object value;
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                type = f.getType().getSimpleName();
                value = f.get(clazz).toString();
            }
            for (String s : fieldNames) {

            }
            return "";
        } catch (IllegalAccessException c){
            System.out.println("Error");
        }
        Class<?> newClass = clazz;
        setNames(clazz);
        String type;
        String value;
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            type = f.getType().getSimpleName();
            value = f.get(newClass);
        }
        for (String s : fieldNames) {

        }
        return "";
    }
    private static String stringGetters(Class<?> clazz){
        setNames(clazz);
        return "";
    }
    private static String stringSetters(Class<?> clazz){
        return "";
    }

    private static String generateImplementation(Class<?> clazz) {
        StringBuilder form = new StringBuilder();
        Field[] fields = clazz.getDeclaredFields();
        return "";
    }


    public static void main(String[] args) {
        try {
            // REMARK: if using packages, make sure to use the fully qualified name! (my.package.Example)
            System.out.println(generate("ferrari_chris.serie05.esercizio01.codegen.Example"));
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }
}
