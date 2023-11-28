package ferrari_chris.solSerie9.esercizio03;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Exercise3 {

    public static void main(String[] args) {
        final String input = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore 
        magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo 
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.""";

        // Unique characters
        final long uniqueCharactersCount = input.chars()
                .distinct()
                .count();

        System.out.println("Unique characters: " + uniqueCharactersCount);

        // Letters vs non-letters count
        final Map<Boolean, Long> collect = input.chars()
                .mapToObj(s -> (Character.valueOf((char) s)))
                .collect(Collectors.partitioningBy(Character::isLetter, Collectors.counting()));
        System.out.println("Letters vs non-letters: " + collect);

        // Digits
        System.out.println("Digits present: " + input.chars()
                .mapToObj(s -> (Character.valueOf((char) s)))
                .anyMatch(Character::isDigit));

        // Digits (alternative)
        System.out.println("Digits present: " + input.chars()
                .mapToObj(s -> (Character.valueOf((char) s)))
                .noneMatch(Predicate.not(Character::isDigit)));

        // Character count
        System.out.println("Character count sorted by Character");
        input.chars()
                .mapToObj(s -> (Character.valueOf((char) s)))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(System.out::println);

        System.out.println("10 most common letters");
        input.chars()
                .mapToObj(s -> (Character.valueOf((char) s)))
                .filter(Character::isLetter)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .forEach(System.out::println);

        // Most common vocal
        System.out.println("Most common vocal");

        final Predicate<Character> isVocal = (c) -> "aeiou".indexOf(Character.toLowerCase(c)) > 0;
        input.chars().mapToObj(c -> Character.valueOf((char) c))
                .filter(isVocal)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresentOrElse(
                        e -> System.out.printf("%c appearing %d times %n", e.getKey(), e.getValue()),
                        () -> System.out.println("No vocals found"));

    }
}

