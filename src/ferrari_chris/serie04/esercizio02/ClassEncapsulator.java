package ferrari_chris.serie04.esercizio02;

import java.lang.reflect.Field;


public class ClassEncapsulator {
    public static void encapsulate(Class<?> targetClass) throws IllegalAccessException {
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
                Object value = f.get(target);
                int val = getModifierValue(f);
                String modifier;
                switch (val) {
                    default -> {
                        modifier = "private ";
                        print(modifier, type, name, value);
                    }
                    //public static = 9
                    //private static = 10
                    case (9), (10) -> {
                        modifier = "private static ";
                        print(modifier, type, name, value);
                    }
                    //public + static + final = 25
                    //private + static + final = 26
                    case (25), (26) -> {
                        modifier = "private static final ";
                        print(modifier, type, name, value);
                    }
                }
            }
        } catch (IllegalAccessException e){
            System.out.println("Error");
        }

    }
    private static int getModifierValue(Field field){
        return field.getModifiers();
    }
    private static void print(String modifier, String type, String name, Object value){
        if (value == null){
            System.out.printf("\t%s%s %s;\n",modifier, type, name);
        } else if (value instanceof String){
            System.out.printf("\t%s%s %s = \"%s\";\n",modifier, type, name, value);
        } else {
            System.out.printf("\t%s%s %s = %s;\n",modifier, type, name, value);
        }
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
