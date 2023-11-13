package ferrari_chris.serie08.esercizio02;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class TextAnalyzerMain {
    private static List<String> extractText(String path){
        try {
            return Arrays.asList(new String(Files.readAllBytes(Paths.get(path))).split("\\s+"));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) throws Exception {
        List<String> list = extractText("src/ferrari_chris/serie08/esercizio02/File.txt");
        System.out.println(list);
        System.out.println("Longest word:");
        list.stream().max(Comparator.comparingInt(String::length)).ifPresent(System.out::println);
        System.out.println("Shortest word with at least 5 char:");
        System.out.println(list.stream().filter(w -> w.length() >= 5).min(Comparator.comparingInt(String::length)).orElse(("NOT FOUND")));
        System.out.println("Word with most vowels:");
        list.stream().max((word1, word2) ->
                        (int) (word1.chars().filter(c -> "aeiouAEIOU".indexOf(c) != -1).count() -
                                word2.chars().filter(c -> "aeiouAEIOU".indexOf(c) != -1).count()))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Could not find shortest word."));
        System.out.println("First letter with at least 10 characters:");
        System.out.println(list.stream().filter(w -> w.length() >= 10).findFirst().orElseThrow(() -> new Exception("Could not find shortest word.")));
    }
}
