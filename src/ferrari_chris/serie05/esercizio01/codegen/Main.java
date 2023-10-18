package ferrari_chris.serie05.esercizio01.codegen;

import java.lang.reflect.Field;
import java.util.*;

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

    public static String generate(String className) {
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

    private static String checkString(Field f){
        Extract extract;
        String name = "";
            extract = f.getAnnotation(Extract.class);
            name = extract.name();
            if (name.isBlank()) {
                fieldNames.add(f.getName());
                name = f.getName();
            } else if (fieldNames.contains(name)) {
                int cnt = 1;
                String tempName = name;
                while (fieldNames.contains(tempName)){
                    tempName = name;
                    tempName += cnt;
                    cnt++;
                }
                name = tempName;
                fieldNames.add(name);
            } else {
                fieldNames.add(name);
            }
        return name;
    }

    private static String generateFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String name = "";
        String type;
        Object value;
        String str = "";
        Example ex = new Example();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                if (f.isAnnotationPresent(Extract.class)) {
                    name = checkString(f);
                    type = f.getType().getSimpleName();
                    value = f.get(ex);
                    if (type.equals("String")) {
                        str += String.format(fieldStringTemplate, type, name, value);
                    } else {
                        str += String.format(fieldTemplate, type, name, value);
                    }
                }
            }
        } catch (IllegalAccessException e){
            System.out.println("Error");
            return "";
        }

        return str;
    }
    private static String generateGetters(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        String name = "";
        String type;
        String str = "";
        Object[] names = fieldNames.toArray();
        int cnt = names.length - 1;
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.isAnnotationPresent(Extract.class)) {
                name = names[cnt].toString().substring(0,1).toUpperCase() + names[cnt].toString().substring(1);
                type = f.getType().getSimpleName();
                str += String.format(getterTemplate, type, name, name);
                cnt--;
            }
        }
        return str;
    }
    private static String generateSetters(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        String name = "";
        String type;
        String str = "";
        Object[] names = fieldNames.toArray();
        int cnt = names.length - 1;
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.isAnnotationPresent(Extract.class)) {
                name = names[cnt].toString().substring(0,1).toUpperCase() + names[cnt].toString().substring(1);
                type = f.getType().getSimpleName();
                str += String.format(setterTemplate, name, type, name, names[cnt], names[cnt]);
                cnt--;
            }
        }
        return str;
    }
    private static String generateImplementation(Class<?> clazz) {
        return generateFields(clazz) + generateGetters(clazz) + generateSetters(clazz);
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
