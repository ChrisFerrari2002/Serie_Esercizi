package ferrari_chris.serie05.esercizio03;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class EvaluateMethods {
    public static void main(String[] args) {
        Class<MathOperations> mathClass = MathOperations.class;
        int val1, val2, result, methodResult;
        try {
            for (Method m : mathClass.getDeclaredMethods()) {
                if (m.isAnnotationPresent(Validate.class)){
                    System.out.println("Method name: " + m.getName());
                    ValidationItem[] annotation =  m.getAnnotation(Validate.class).value();
                    for (ValidationItem v : annotation) {
                        val1 = v.params()[0];
                        val2 = v.params()[1];
                        result = v.result();
                        System.out.printf("Val1: %d | Val2: %d | Result Expected: %d%n", val1, val2, result);
                        methodResult = (int) m.invoke(mathClass, val1, val2);
                        if (result == methodResult) {
                            System.out.printf("METHOD OK! RESULT = %d%n", methodResult);
                            continue;
                        } else {
                            System.out.printf("METHOD ERROR! RESULT = %d%n", methodResult);
                        }
                    }
                }

            }
        } catch (InvocationTargetException | IllegalAccessException e){
            System.out.println("Error");
        }

    }
}
