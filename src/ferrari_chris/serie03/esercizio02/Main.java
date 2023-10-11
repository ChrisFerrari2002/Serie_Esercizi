package ferrari_chris.serie03.esercizio02;

public class Main {
    public static void main(String[] args) {
        class EvaluateEvenNumer implements AdvancedStack.Evaluate{

            @Override
            public boolean verify(Object objectToTest) {
                if (objectToTest instanceof Integer){
                    return (Integer) objectToTest % 2 != 0;
                }
                return false;
            }
        }
        AdvancedStack.Evaluate evaluateString = new AdvancedStack.Evaluate() {
            @Override
            public boolean verify(Object objectToTest) {
                if (objectToTest instanceof String){
                    return ((String) objectToTest).contains("test");
                }
                return false;
            }
        };
        AdvancedStack.Evaluate evaluateInteger = new AdvancedStack.Evaluate() {
            @Override
            public boolean verify(Object objectToTest) {
                if (objectToTest instanceof Integer)
                    return true;
                return false;
            }
        };
        AdvancedStack.Evaluate evaluateFloatAndInteger = new AdvancedStack.Evaluate() {
            @Override
            public boolean verify(Object objectToTest) {
                if (objectToTest instanceof Float){
                    return (float)objectToTest > 0.5f;
                }
                if (objectToTest instanceof Double){
                    return (Double)objectToTest > 0.5;
                }
                return false;
            }
        };

        CustomStack stack = new CustomStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(4.5);
        stack.push(0.3f);
        stack.push(1f);
        stack.push("test 1");
        stack.push("Pretesto");
        stack.push("ABC");
        stack.push(10L);

        System.out.println("Integer elements EVEN: " + stack.count(new EvaluateEvenNumer()));
        System.out.println("Integer elements: " + stack.count(evaluateInteger));
        System.out.println("String elements with 'test': " + stack.count(evaluateString));
        System.out.println("Double or Float elements that are greater than 0.5: " + stack.count(evaluateFloatAndInteger));

    }
}
