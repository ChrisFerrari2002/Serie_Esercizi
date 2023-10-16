package ferrari_chris.serie04.esercizio01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Utility {
    public static List<String> getPathToClass(String startClassName, String endClassName) {
        try {
            List<String> listClasses = new ArrayList<>();
            Class<?> currentClass = Class.forName(startClassName);
            Class<?> endClass = Class.forName(endClassName);
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
        }catch (ClassNotFoundException c) {
            System.out.print("Class not found ");
            return Collections.emptyList();
        }
    }

    public static List<String> getPathToObject(String startClassName) {
        try{
            List<String> listClasses = new ArrayList<>();
            Class<?> currentClass = Class.forName(startClassName);
            listClasses.add(currentClass.getSimpleName());
            while (currentClass.getSuperclass() != null){
                currentClass = currentClass.getSuperclass();
                listClasses.add(currentClass.getSimpleName());
            }
            return listClasses;
        } catch (ClassNotFoundException c){
            System.out.print("Class not found ");
            return Collections.emptyList();
        }

    }

    public static String getCommonAncestor(String className0, String className1) {
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
        return "No super class in common\n";
    }
}
