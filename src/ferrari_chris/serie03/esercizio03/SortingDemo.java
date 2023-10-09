package es3;

import java.util.Arrays;
import java.util.List;

public class SortingDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1, 5, 6, 3, 8);
        System.out.println("Initial Order: " + numbers);

        // Using a nested class for ascending sorting
        // FIXME to implement
        // Collections.sort(numbers, ...

        System.out.println("Ascending Order: " + numbers);
        // REMARK: expected [1, 2, 3, 5, 5, 6, 8, 9]

        // Using a local class for descending sorting
        // FIXME to implement
        // Collections.sort(numbers, ...
        System.out.println("Descending Order: " + numbers);
        // REMARK: expected [9, 8, 6, 5, 5, 3, 2, 1]

        // Using a nested class for ascending sorting
        // FIXME to implement
        // Collections.sort(numbers, ...
        System.out.println("Ascending odd Order: " + numbers);
        // REMARK: expected [2, 6, 8, 1, 3, 5, 5, 9]
    }
}
