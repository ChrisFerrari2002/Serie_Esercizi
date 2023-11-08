package ferrari_chris.serie07.esercizio02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextAnalyzerMain {
    private static List<String> extractText(String path){
        try {
            return Arrays.asList(new String(Files.readAllBytes(Paths.get(path))).split("\\s+"));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }
    public static void main(String[] args) {
        List<String> list = extractText("src/ferrari_chris/serie07/esercizio02/File.txt");
        System.out.println(list);
        List<String> newList = list.stream().map(w -> w.replaceAll("[^a-zA-Z0-9]", "")).filter(w -> !w.isEmpty()) .toList();
        System.out.println("-------------Complete list-----------------");
        System.out.println(newList);

        System.out.println("-------------Correct list------------------");
        System.out.println(newList.stream().filter(w -> w.length() % 2 == 0).toList());

        System.out.println("-------------First letter vowel--------------");
        System.out.println(newList.stream().filter(w -> w.substring(0, 1).matches("[aeiouAEIOU]")).toList());

        System.out.println("-------------First letter T-----------------");
        System.out.println(newList.stream().filter(w -> w.charAt(0) == 'T').distinct().toList());

        Map<Integer, List<String>> numberedList = newList.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("-------------Counting letters--------------");
        numberedList.entrySet().stream().forEach(numList -> System.out.printf("%s : %s\n", numList.getKey(), numList.getValue()));

        System.out.println("-------------Counting letters--------------");
        long num = newList.stream().filter(w -> w.substring(0,1).matches("[aeiouAEIOU]")).count();
        System.out.println("Words that start with a vowel: " + num);
        System.out.println("Words that don't start with a vowel: " + (newList.size() - num));

        System.out.println("-------------Counting vowels total--------------");
        Map<Character, Long> numbCharacter = newList.stream().flatMap(w -> w.chars().mapToObj(c -> (char) c)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        numbCharacter.entrySet().stream().forEach(numList -> System.out.printf("%s : %s\n", numList.getKey(), numList.getValue()));
        System.out.println("Sum of lengths: " + list.stream().mapToInt(String::length).sum());
        System.out.println("Shortest word: " + list.stream().sorted(Comparator.comparing(String::length)).toList().get(0));
        System.out.println("Average length: " + (float) (list.stream().mapToInt(String::length).sum() / list.size()));
        System.out.println("Shortest word: " + list.stream().sorted(Comparator.comparing(String::length)).toList().get(list.size()-1));

    }
}
