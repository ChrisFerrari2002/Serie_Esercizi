package ferrari_chris.serie04.esercizio02;

import java.lang.reflect.Field;


public class ClassEncapsulator {
    public static void encapsulate(Class<?> targetClass) {
        System.out.println("public class " + targetClass.getSimpleName() + " {");
        printFields(targetClass);
        printGetters(targetClass);
        printSetters(targetClass);
        System.out.println("}");
    }
    private static void printFields(Class<?> targetClass) {
        try {
            Field[] fields = targetClass.getDeclaredFields();
            Target target = new Target();
            for (Field f : fields) {
                f.setAccessible(true);
                String type = f.getType().getSimpleName();
                String name = f.getName();
                Object fieldValue = f.get(target);
                if (type.equals("String")){
                    System.out.printf("\tprivate %s %s = \"%s\";\n", type, name, fieldValue);
                    continue;
                } else if (fieldValue == null) {
                    System.out.printf("\tprivate %s %s;\n", type, name);
                    continue;
                } else {
                    System.out.printf("\tprivate %s %s = %s;\n", type, name, fieldValue);
                }

            }
        } catch (IllegalAccessException exception){
            System.out.println("Error");
        }


    }
    private static int getModifierValue(Field field){
        return field.getModifiers();
    }

    private static void printGetters(Class<?> targetClass){
        Field[] fields = targetClass.getDeclaredFields();
        for (Field f : fields) {
            String name = f.getName();
            String getterName = f.getName().substring(0,1).toUpperCase() + f.getName().substring(1);
            String type = f.getType().getSimpleName();
            System.out.printf("\tpublic %s get%s() {\n", type, getterName);
            System.out.printf("\t\treturn %s;\n\t}\n", name);
        }
    }
    private static void printSetters(Class<?> targetClass){
        Field[] fields = targetClass.getDeclaredFields();
        for (Field f : fields) {
            String name = f.getName();
            String getterName = f.getName().substring(0,1).toUpperCase() + f.getName().substring(1);
            String type = f.getType().getSimpleName();
            System.out.printf("\tpublic %s set%s(%s %s) {\n", type, getterName, type, name);
            System.out.printf("\t\tthis.%s = %s;\n\t}\n", name, name);
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        encapsulate(Target.class);
    }
}
