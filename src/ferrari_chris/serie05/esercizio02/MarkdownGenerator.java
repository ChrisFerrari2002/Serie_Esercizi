package ferrari_chris.serie05.esercizio02;

import java.lang.reflect.*;
import java.util.Arrays;

public class MarkdownGenerator {

    private static void toMarkdown(final Class<?> theClass) {
        // TODO verify that class has expected annotation

        // Set title to class name
        System.out.printf("# Class `%s` info:%n", theClass.getName());

        // TODO Document parent class
        System.out.printf("Parent class : '%s'%n", theClass.getSuperclass());

        // TODO Document interfaces
        System.out.println("## Interfaces(s)");
        Class<?>[] interfaces = theClass.getInterfaces();
        for (Object s : interfaces) {
            System.out.printf(" - '%s'%n", s);
        }

        // TODO Document non ignored fields
        System.out.println("## Fields(s)");
        Field[] fields = theClass.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (!f.isAnnotationPresent(MarkdownDocIgnore.class)){
                System.out.printf(" - '%s %s'%n", f.getType().getSimpleName(), f.getName());
            }
        }

        // TOOD Document non ignoreed costructurs
        System.out.println("## Constructors(s)");
        Constructor<?>[] constructors = theClass.getDeclaredConstructors();
        for (Constructor<?> c : constructors) {
            if (!c.isAnnotationPresent(MarkdownDocIgnore.class)){
                Parameter[] parametersValue = c.getParameters();
                System.out.printf(" - '%s(", c.getName());
                for (int i = 0; i < parametersValue.length; i++) {
                    if (i == parametersValue.length - 1){
                        System.out.printf("%s)'%n", parametersValue[i]);
                        continue;
                    }
                    System.out.printf("%s, ",parametersValue[i]);
                }
            }
        }
        // TOOD Document non ignoreed methods
        System.out.println("## Methods(s)");
        Method[] methods = theClass.getDeclaredMethods();

        for (Method m : methods) {
            if (!m.isAnnotationPresent(MarkdownDocIgnore.class)) {
                Class<?>[] typeParameter = m.getParameterTypes();
                System.out.printf(" - '%s %s(", m.getReturnType().getSimpleName(), m.getName());
                for (int i = 0; i < typeParameter.length; i++) {
                    if (i == typeParameter.length - 1){
                        System.out.print(typeParameter[i]);
                        continue;
                    }
                    System.out.printf("%s, ", typeParameter[i].getSimpleName());
                }
                System.out.println(")");
            }
        }
    }

    public static void main(String[] args) {
        toMarkdown(Coordinate.class);
    }
}
