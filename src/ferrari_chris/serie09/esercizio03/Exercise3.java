package ferrari_chris.serie09.esercizio03;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise3 {

    public static void main(String[] args) {
        final String input = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore 
        magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo 
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.""";

        final Stream<Character> sampleCharacterStream = input.chars().mapToObj(c -> Character.valueOf((char) c));
        System.out.println(sampleCharacterStream
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .distinct()
                .collect(Collectors.toList())
        );
        Stream<Character> sampleCharacterStream2 = input.chars().mapToObj(c -> Character.valueOf((char) c));
        System.out.println(sampleCharacterStream2.filter(s -> s.getClass().equals(Character.class)).count());
    }
}
