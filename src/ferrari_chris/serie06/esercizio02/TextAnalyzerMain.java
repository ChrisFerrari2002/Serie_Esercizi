package ferrari_chris.serie06.esercizio02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class TextAnalyzerMain {
    private static String find(List<String> words, BiPredicate<String, String> operation){
        return "";
    }
    private static List<String> findAll(List<String> words, Predicate<String> operation){
        List<String> prova = new ArrayList<>();
        return prova;
    }
    private static List<String> extractText(String path){
        List<String> words = new ArrayList<>();
        try {
            words = Arrays.asList(new String(Files.readAllBytes(Paths.get(path))).split("\\s+"));
        } catch (IOException e){
            System.out.println("File sbagliato");
        }
        return words;
    }
    public static void main(String[] args) {

    }
}
