package ferrari_chris.serie04.esercizio02;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class ClassEncapsulator {
    public void encapsulate(String target) throws ClassNotFoundException, IllegalAccessException {
        Class<?> targetClass = Class.forName("ferrari_chris.serie04.esercizio02." + target);
        Field[] fields = targetClass.getDeclaredFields();
        List<String> type = getType(fields);
        List<String> name = getName(fields);
        List<String> value = getValue(fields);
        printClass(target, type, name, value);

    }
    private List<String> getType(Field[] fields) throws ClassNotFoundException {
        List<String> listOfTypes = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            listOfTypes.add(fields[i].getType().getSimpleName());
        }
        return listOfTypes;
    }
    private List<String> getName(Field[] fields) throws ClassNotFoundException {
        List<String> listOfNames = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            listOfNames.add(fields[i].getName());
        }
        return listOfNames;
    }
    private List<String> getValue(Field[] fields) throws IllegalAccessException, ClassNotFoundException {
        List<String> listOfValue = new ArrayList<>();
        Target test = new Target();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if (fields[i].get(test) instanceof String){
                listOfValue.add("\"" + fields[i].get(test) + "\"");
                continue;
            }
            listOfValue.add(fields[i].get(test).toString());
        }
        return listOfValue;
    }
    private void printFields(List<String> typeList, List<String> nameList, List<String> valueList) throws ClassNotFoundException {

        for (int i = 0; i < typeList.size(); i++) {
            System.out.printf("\tprivate %s %s = %s;\n", typeList.get(i), nameList.get(i), valueList.get(i));
        }
        System.out.println();
    }
    private void printGetters(List<String> typeList, List<String> nameList){
        String fieldName;
        String rightFieldName;
        for (int i = 0; i < nameList.size(); i++) {
            fieldName = nameList.get(i);
            rightFieldName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            System.out.printf("\tpublic %s %s() {\n\t\treturn %s;\n\t}\n\n",
                    typeList.get(i), rightFieldName, nameList.get(i));
        }
    }
    private void printSetters(List<String> typeList, List<String> nameList){
        String fieldName;
        String rightFieldName;
        for (int i = 0; i < nameList.size(); i++) {
            fieldName = nameList.get(i);
            rightFieldName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            System.out.printf("\tpublic void %s(%s %s) {\n\t\tthis.%s = %s;\n\t}\n\n",
                    rightFieldName, typeList.get(i), fieldName, fieldName, fieldName);
        }
    }
    private void printClass(String target, List<String> typeList, List<String> nameList, List<String> valueList) throws ClassNotFoundException {
        String rightClassName = target.substring(0, 1).toUpperCase() + target.substring(1);
        System.out.printf("public class %s Target {\n", rightClassName);
        printFields(typeList, nameList, valueList);
        printGetters(typeList, nameList);
        printSetters(typeList, nameList);
        System.out.println("}");
    }



    public static void main(String[] args) {
        ClassEncapsulator classEncapsulator = new ClassEncapsulator();
        try {
            classEncapsulator.encapsulate("Target");

        } catch (ClassNotFoundException c){
            System.out.println("Classe non trovata.");
        } catch (IllegalAccessException e){
            System.out.println("Illegal access");
        }
    }
}
