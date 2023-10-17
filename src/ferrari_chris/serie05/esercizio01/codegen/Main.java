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

    private static String generateImplementation(Class<?> clazz) throws Exception {
        StringBuilder form = new StringBuilder();
        Field[] fields = clazz.getDeclaredFields();
        String type;
        String fieldName;
        String fieldValue;
        Object annotation;
        for (Field f : fields) {
            if (f.isAnnotationPresent(Extract.class)){
                f.setAccessible(true);
                annotation = f.get(clazz);
                type = f.getType().getSimpleName();

                fieldName = annotation.toString();
                fieldValue = f.get(clazz).toString();
                if (type.equals("String")){
                    form.append(String.format(fieldStringTemplate, type, fieldName, fieldValue));
                    continue;
                }
                form.append(String.format(fieldTemplate, type, fieldName, fieldValue));
            }
        }
        for (Field f : fields) {
            f.setAccessible(true);
        }


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
