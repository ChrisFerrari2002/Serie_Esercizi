package ferrari_chris.serie05.soluzioniEs1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

public class Main {
    private final static HashSet<String> fieldNames = new HashSet<>();

    // class name, implementation
    private static final String classTemplate = "public class %s {%n%s}";

    // class implementation template
    private static final String implementationTemplate = "\t// Fields%n%s%n\t// Getters%n%s%n\t// Setters%n%s";

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

            // generate class using 'classTemplate'
            generatedCode = String.format(classTemplate, targetClass.getSimpleName(), generateImplementation(targetClass));
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        return generatedCode;
    }

    private static String generateImplementation(Class<?> clazz) throws Exception {
        final Field[] fields = extractFields(clazz);
        String codeFields = "";
        String codeGetters = "";
        String codeSetters = "";

        try {
            Object targetObj = clazz.getConstructor().newInstance();
            for (Field field : fields) {
                String fieldName = field.getName();
                String capFieldName;
                String fieldType = field.getType().getSimpleName();
                if (field.isAnnotationPresent(Extract.class)) {
                    Extract annotation = field.getAnnotation(Extract.class);
                    String theName = annotation.name();
                    if (!theName.equals("")) {
                        fieldName = theName;
                    }
                    int oldSize = fieldNames.size();
                    fieldNames.add(fieldName);
                    int size = fieldNames.size();
                    if (size == oldSize) {
                        throw new Exception("You are trying to generate two fields with the same name");
                    }
                    // Generate fieldName
                    capFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    codeGetters += String.format(getterTemplate, fieldType, capFieldName, fieldName);
                    codeSetters += String.format(setterTemplate, capFieldName, fieldType, fieldName, fieldName, fieldName);
                }
                try {
                    field.setAccessible(true);
                    codeFields += String.format(fieldType.equals("String") ? fieldStringTemplate : fieldTemplate,
                            fieldType, fieldName, field.get(targetObj));

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            e.printStackTrace();
        }

        return String.format(implementationTemplate, codeFields, codeGetters, codeSetters);
    }


    static private Field[] extractFields(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }

    public static void main(String[] args) {
        try {
            // REMARK: if using packages, make sure to use the fully qualified name! (my.package.Example)
            System.out.println(generate("assignment05.solutions.codegen.Example"));
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }
}
