package ferrari_chris.serie05.soluzioniEs2;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class MarkdownGenerator {

    private static void toMarkdown(final Class<?> theClass) {

        // Only generate for annotated classes
        if (!theClass.isAnnotationPresent(MarkdownDoc.class))
            return;

        final MarkdownDoc markdownDoc = theClass.getAnnotation(MarkdownDoc.class);

        // Set title to class name
        System.out.printf("Class %s info:%n", theClass.getName());

        // Document parent class
        if (markdownDoc.parentClass()) {
            final Class<?> superclass = theClass.getSuperclass();
            System.out.printf("Parent class: %s%n", superclass.getName());
        }

        // Document interfaces
        if (markdownDoc.interfaces()) {
            final Class<?>[] interfaces = theClass.getInterfaces();
            if (interfaces.length > 0) {
                System.out.printf("Interface(s)%n");
                for (Class<?> currentInterface : interfaces) {
                    System.out.printf(" - %s %n", currentInterface.getName());
                }
            }
        }

        if (markdownDoc.fields()) {
            // Filter constructors annotated with MarkdownDocIgnore
            final List<Field> fields = filterAnnotated(theClass.getDeclaredFields());
            if (!fields.isEmpty()) {
                System.out.printf("Fields(s)%n");
                for (Field field : fields) {
                    final Class<?> type = field.getType();
                    System.out.printf(" - %s %s %n", getTypeName(type), field.getName());
                }
            }
        }

        // Document constructors
        if (markdownDoc.constructors()) {
            // Filter constructors annotated with MarkdownDocIgnore
            final List<Constructor<?>> constructors = filterAnnotated(theClass.getConstructors());
            if (!constructors.isEmpty()) {
                System.out.printf("Constructor(s)%n");
                for (Constructor constructor : constructors) {
                    final Parameter[] parameters = constructor.getParameters();
                    System.out.printf(" - %s(%s) %n", constructor.getName(), getParameterList(parameters));
                }
            }
        }

        // Document methods
        if (markdownDoc.methods()) {
            // Filter methods annotated with MarkdownDocIgnore
            final List<Method> methods = filterAnnotated(theClass.getDeclaredMethods());
            if (!methods.isEmpty()) {
                System.out.printf("Methods(s)%n");
                for (Method method : methods) {
                    final Parameter[] parameters = method.getParameters();
                    System.out.printf(" - %s %s(%s) %n", method.getReturnType().getName(), method.getName(), getParameterList(parameters));
                }
            }
        }
    }

    private static String getTypeName(final Class<?> type) {
        if (type.isArray())
            return getTypeName(type.getComponentType()) + "[]";
        return type.getTypeName();
    }

    private static <T extends AccessibleObject> List<T> filterAnnotated(T[] elements) {
        final List<T> annotated = new ArrayList<>();
        for (T element: elements) {
            if (!element.isAnnotationPresent(MarkdownDocIgnore.class))
                annotated.add(element);
        }
        return annotated;
    }

    private static String getParameterList(final Parameter[] parameters) {
        if (parameters.length < 1)
            return "";

        String paramsString = parameters[0].getType().getName();
        for (int i = 1; i < parameters.length; i++) {
            paramsString += ", "+parameters[i].getType().getName();
        }
        return paramsString;
    }

    public static void main(String[] args) {
        toMarkdown(Coordinate.class);
    }
}
