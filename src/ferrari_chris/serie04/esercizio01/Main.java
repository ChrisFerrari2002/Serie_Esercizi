package ferrari_chris.serie04.esercizio01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
            System.out.println(Utility.getPathToObject("ferrari_chris.serie04.esercizio01.F1Car"));
            System.out.println(Utility.getPathToObject("ferrari_chris.serie04.esercizio01.Bike"));
            System.out.println(Utility.getPathToObject("ferrari_chris.serie04.esercizio01.NascarCar"));
            System.out.println(Utility.getPathToClass("ferrari_chris.serie04.esercizio01.F1Car", "ferrari_chris.serie04.esercizio01.Bike"));
            System.out.println(Utility.getPathToClass("ferrari_chris.serie04.esercizio01.F1Car", "ferrari_chris.serie04.esercizio01.Vehicle"));
            System.out.println(Utility.getCommonAncestor("ferrari_chris.serie04.esercizio01.F1Car", "ferrari_chris.serie04.esercizio01.NascarCar"));
            System.out.println(Utility.getCommonAncestor("ferrari_chris.serie04.esercizio01.F1Car", "ferrari_chris.serie04.esercizio01.Bike"));
    }


}
