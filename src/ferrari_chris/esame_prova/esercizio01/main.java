package ferrari_chris.esame_prova.esercizio01;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.sql.Types;
import java.util.Arrays;

class AutoTest {
    public AutoTest() {
        System.out.println("===AutoTest()");
    }

    public String autorunString() {
        System.out.println("===autorunString()");
        return "defaultString";
    }

    private String autorunString(final String input) {
        System.out.println("===autorunString(" + input + ")");
        return input;
    }

    public Integer autorunInteger(final Integer i) {
        System.out.println("===autorunInteger(" + i + ")");
        return Integer.sum(i, 1);
    }

    public void noRun() {
        System.out.println("===noRun()");
    }

    public void autorun(String data, Integer first, Integer second) {
        System.out.println("===autorun(" + data + ", " + first + ", " + second + ")");
    }

    public String toString() {
        return "AutoTest";
    }
}

class PerformTest {

    public static void main(String[] args) throws Exception {
        Class<AutoTest> testClass = AutoTest.class;
        Method[] mets = testClass.getDeclaredMethods();

        for (Method met : mets) {
            String name = met.getName();
            if (name.startsWith("autorun")){
                Object[] params = new Object[met.getParameterCount()];
                Class<?>[] types = met.getParameterTypes();
                int paramIndex = 0;
                for (Class<?> type : types) {
                    if (type.equals(String.class))
                        params[paramIndex++] = name;
                    else if (type.equals(Integer.class)) {
                        params[paramIndex++] = paramIndex;
                    }
                }
                if (Modifier.isPrivate(met.getModifiers())){
                    met.setAccessible(true);
                }
                AutoTest instance = testClass.getDeclaredConstructor().newInstance();
                Object result = met.invoke(instance, params);
            }
        }
    }
}
