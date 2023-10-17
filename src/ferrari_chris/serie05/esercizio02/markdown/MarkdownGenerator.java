

public class MarkdownGenerator {

    private static void toMarkdown(final Class<?> theClass) {
        // TODO verify that class has expected annotation

        // Set title to class name
        System.out.printf("# Class `%s` info:%n", theClass.getName());

        // TODO Document parent class

        // TODO Document interfaces

        // TODO Document non ignored fields

        // TOOD Document non ignoreed costructurs

        // TOOD Document non ignoreed methods
    }

    public static void main(String[] args) {
        toMarkdown(Coordinate.class);
    }
}
