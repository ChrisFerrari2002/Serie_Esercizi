package ferrari_chris.serie03.esercizio03;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingDemo {
    static class AscendingSorting implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1, 5, 6, 3, 8);
        System.out.println("Initial Order: " + numbers);

        // Using a nested class for ascending sorting
        Collections.sort(numbers, new AscendingSorting());
        System.out.println("Ascending Order: " + numbers);

        // Using a local class for descending sorting
        class DescendingSorting implements Comparator<Integer>{

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        }
        Collections.sort(numbers, new DescendingSorting());
        System.out.println("Descending Order: " + numbers);

        // Using a nested class for ascending sorting
        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 % 2 != 0 && o2 % 2 != 0)
                    return o1-o2;
                if (o1 % 2 == 0 && o2 % 2 == 0)
                    return o1-o2;
                if (o1 % 2 == 0)
                    return -1;
                return 0;
            }
        });
        System.out.println("Ascending odd Order: " + numbers);
        // REMARK: expected [2, 6, 8, 1, 3, 5, 5, 9]
    }
}
