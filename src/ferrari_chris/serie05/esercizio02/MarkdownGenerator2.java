package ferrari_chris.serie05.esercizio02;

import java.lang.reflect.*;
import java.net.Proxy;

public class MarkdownGenerator2 {

    private static void toMarkdown(final Class<?> theClass) {
        String className = theClass.getName();
        System.out.printf("# Class '%s'\n", className);
        String parentClass = theClass.getSuperclass().getName();
        System.out.printf("# Parent class: '%s'\n", parentClass);

        System.out.println("## Interfaces(s)");
        for (Class<?> anInterface : theClass.getInterfaces()) {
            if (!anInterface.isAnnotationPresent(MarkdownDocIgnore.class)) {
                System.out.printf("- '%s'\n", anInterface.getName());
            }
        }
        System.out.println("## Fields(s)");
        for (Field field : theClass.getDeclaredFields()){
            if (!field.isAnnotationPresent(MarkdownDocIgnore.class)){
                System.out.printf("- '%s %s'\n", field.getType(), field.getName());
            }
        }
        System.out.println("## Constructors");
        for (Constructor<?> constructor : theClass.getDeclaredConstructors()){
            if (!constructor.isAnnotationPresent(MarkdownDocIgnore.class)) {
                Type[] types = constructor.getParameterTypes();
                StringBuilder params;
                if (types.length > 1) {
                    params = new StringBuilder();
                    for (int i = 0; i < types.length; i++) {
                        if (i != types.length - 1) {
                            params.append(types[i]).append(", ");
                        } else {
                            params.append(types[i]);
                        }
                    }
                    System.out.printf("- '%s(%s)\n'", constructor.getName(), params);
                } else {
                    System.out.printf("- '%s(%s)\n'", constructor.getName(), types[0]);
                }
            }
        }
        System.out.println("## Methods");
        for (Method method : theClass.getDeclaredMethods()){
            if (!method.isAnnotationPresent(MarkdownDocIgnore.class)){
                String name = method.getName();
                String returnType = method.getReturnType().getSimpleName();
                Type[] types = method.getParameterTypes();
                StringBuilder params = null;
                if (types.length > 1) {
                    params = new StringBuilder();
                    for (int i = 0; i < types.length; i++) {
                        if (i != types.length - 1) {
                            params.append(types[i]).append(", ");
                        } else {
                            params.append(types[i]);
                        }
                    }
                } else if (types.length == 1) {
                    System.out.printf("- '%s %s (%s)'\n", returnType, name, types[0]);
                } else {
                    System.out.printf("- '%s %s()'\n", returnType, name);
                }
                System.out.printf("- '%s %s (%s)'\n", returnType, name, params);
            }
        }

    }

    public static void main(String[] args) {
        toMarkdown(Coordinate.class);
    }
}
