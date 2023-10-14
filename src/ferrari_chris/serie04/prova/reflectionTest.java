package ferrari_chris.serie04.prova;
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.*;

public class reflectionTest {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Cat cat = new Cat(10, "Stella");
        Field[] fieldsPublic = cat.getClass().getFields(); //Prende solo i campi public
        System.out.println("Campi public:");
        for (Field field : fieldsPublic) {
            System.out.print(field.getType().getSimpleName() + " ");
            System.out.print(field.getName() + " = ");
            if (field.getType().getSimpleName().equals("String")) {
                System.out.println("\"" + field.get(cat).toString() + "\"");
                continue;
            }
            System.out.println(field.get(cat).toString());
        }
        System.out.println("-------------------");
        Field[] fieldsAlsoPrivate = cat.getClass().getDeclaredFields(); //Prende sia i campi public che private
        System.out.println("Campi public e private:");
        for (Field field : fieldsAlsoPrivate) {
            field.setAccessible(true);
            System.out.print(field.getType().getSimpleName() + " ");
            System.out.print(field.getName() + " = ");
            if (field.getType().getSimpleName().equals("String")) {
                System.out.println("\"" + field.get(cat).toString() + "\"");
                continue;
            }
            System.out.println(field.get(cat).toString());
        }
        System.out.println("-------------------");
        Method[] methods = cat.getClass().getDeclaredMethods(); //Prende i metodi dichiarati
        System.out.println("Metodi:");
        for (Method method : methods) {
            System.out.print(method.getReturnType().getSimpleName());
            System.out.print(" " + method.getName());
            System.out.print("(");
            if (method.getParameterCount() > 0){
                Parameter[] parameters = method.getParameters();
                int cnt = 0;
                for (Parameter parameter : parameters) {
                    if (method.getParameterCount() > 1 && cnt != parameters.length-1){
                        System.out.print(parameter.getType().getSimpleName());
                        System.out.print(" " + parameter.getName() + ", ");
                        cnt++;
                    } else {
                        System.out.print(parameter.getType().getSimpleName());
                        System.out.print(" " + parameter.getName());
                    }
                }
            }
            System.out.println(")");
            if (method.getName().equals("meow")){
                method.setAccessible(true);
                System.out.print("Invoking method meow: ");
                method.invoke(cat);
            }
        }
        System.out.println("-------------------");
        Constructor<?>[] constructors = cat.getClass().getConstructors(); //Prende i costruttori dichiarati
        for (Constructor<?> constructor : constructors) {
            Parameter[] parameters = constructor.getParameters();
            System.out.println("Costruttore:");
            System.out.print("(");
            int cnt = 0;
            for (Parameter parameter : parameters) {
                if(parameters.length > 1 && cnt != parameters.length-1){
                    System.out.print(parameter.getType().getSimpleName() + " ");
                    System.out.print(parameter.getName() + ", ");
                    cnt++;
                    continue;
                }
                System.out.print(parameter.getType().getSimpleName() + " ");
                System.out.print(parameter.getName());
            }
            System.out.println(")");
        }
    }
}
