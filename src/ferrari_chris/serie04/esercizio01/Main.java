package ferrari_chris.serie04.esercizio01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println(Main.getPathToObject("F1Car"));
            System.out.println(Main.getPathToObject("Bike"));
            System.out.println(Main.getPathToObject("NascarCar"));
            System.out.println(Main.getPathToClass("F1Car", "Bike"));
            System.out.println(Main.getPathToClass("F1Car", "Vehicle"));
            System.out.println(Main.getCommonAncestor("F1Car", "NascarCar"));
            System.out.println(Main.getCommonAncestor("F1Car", "Bike"));

        }
        catch (ClassNotFoundException c){
            System.out.println("Classe non trovata");
        }
    }

    private static List<String> getPathToClass(String startClassName, String endClassName) throws ClassNotFoundException {
        List<String> listClasses = new ArrayList<>();
        Class<?> currentClass = Class.forName("ferrari_chris.serie04.esercizio01." + startClassName);
        Class<?> endClass = Class.forName("ferrari_chris.serie04.esercizio01." + endClassName);
        boolean endClassExist = false;
        listClasses.add(currentClass.getSimpleName());
        while (currentClass.getSuperclass() != null){
            currentClass = currentClass.getSuperclass();
            listClasses.add(currentClass.getSimpleName());
            if (currentClass == endClass) {
                endClassExist = true;
                break;
            }
        }
        if (!endClassExist) {
            return Collections.emptyList();
        }
        return listClasses;
    }

    private static List<String> getPathToObject(String startClassName) throws ClassNotFoundException {
        List<String> listClasses = new ArrayList<>();
        Class<?> currentClass = Class.forName("ferrari_chris.serie04.esercizio01." + startClassName);
        listClasses.add(currentClass.getSimpleName());
        while (currentClass.getSuperclass() != null){
            currentClass = currentClass.getSuperclass();
            listClasses.add(currentClass.getSimpleName());
        }
        return listClasses;
    }

    private static String getCommonAncestor(String className0, String className1) throws ClassNotFoundException {
        Class<?> class0 = Class.forName("ferrari_chris.serie04.esercizio01." + className0);
        Class<?> class1 = Class.forName("ferrari_chris.serie04.esercizio01." + className1);
        List<String> list0 = getPathToObject(className0);
        List<String> list1 = getPathToObject(className1);
        int size0 = list0.size();
        int size1 = list1.size();
        if (size0 >= size1){
            for (String s : list0) {
                for (int j = 0; j < size1; j++) {
                    if (Objects.equals(list1.get(j), s))
                        return list1.get(j);
                }
            }
        } else {
            for (String s : list1) {
                for (int j = 0; j < size0; j++) {
                    if (Objects.equals(s, list0.get(j)))
                        return s;
                }
            }
        }
        return "No super class in common";
    }
}
