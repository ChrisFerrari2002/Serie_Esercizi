package ferrari_chris.serie05.esercizio03;

public class MathOperations {

    @Validate(
            @ValidationItem(params = {1, 2}, result = 3)
    )
    public static int add(int a, int b) {
        // Intentionally wrong to test validation!
        return a - b;
    }

    @Validate({
            @ValidationItem(params = {1, 2}, result = -1),
            @ValidationItem(params = {-1, 1}, result = -2),
            @ValidationItem(params = {-10, -10}, result = 0)
    })
    public static int sub(int a, int b) {
        return a - b;
    }

    @Validate({
        @ValidationItem(params = {1, 2}, result = 1),
        @ValidationItem(params = {10, 20}, result = 10)
    })
    public static int min(int a, int b) {
        return Math.min(a, b);
    }
}
