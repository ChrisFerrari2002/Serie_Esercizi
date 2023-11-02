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
        String solution = words.get(0);
        for (String word : words) {
            if (operation.test(solution, word))
                solution = word;
        }
        return solution;
    }
    private static List<String> findAll(List<String> words, Predicate<String> operation){
        List<String> wordsList = new ArrayList<>();
        for (String word : words) {
            if (operation.test(word))
                wordsList.add(word);
        }
        return wordsList;
    }
    private static List<String> extractText(String path){
        List<String> words = new ArrayList<>();
        try {
            words = Arrays.asList(new String(Files.readAllBytes(Paths.get(path))).split("[\\s.,:';!?]+"));
        } catch (IOException e){
            System.out.println("File sbagliato");
        }
        return words;
    }
    private static int getCnt(char[] letters1) {
        int cnt = 0;
        for (char letter : letters1) {
            if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' ||
                    letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' || letter == 'U' )
                cnt++;
        }
        return cnt;
    }
    public static void main(String[] args) {
        List<String> wordsList = extractText("src/ferrari_chris/serie06/esercizio02/File.txt");
        System.out.println(wordsList);;
        System.out.println("Longest String: " + find(wordsList, (String w1, String w2) -> w1.length() < w2.length()));

        String shortestWord = find(wordsList, (String w1, String w2) -> w2.length() <= w1.length() && w2.length() >= 5);
        System.out.println("Shortest word: " + shortestWord);

        String wordWithMostVowels = find(wordsList, (String w1, String w2) -> {
            char[] letters1 = w1.toCharArray();
            char[] letters2 = w2.toCharArray();
            int cnt1 = getCnt(letters1);
            int cnt2 = getCnt(letters2);
            return cnt1 < cnt2;
        });
        System.out.println("Word with most vowels: " + wordWithMostVowels);

        List<String> wordsStartWithVowel = findAll(wordsList, (String w) -> w.substring(0,1).matches("[AEIOUaeiuo]"));
        System.out.println("Words that start with a vowel: " + wordsStartWithVowel);
        List<String> wordsStartWithT = findAll(wordsList, (String w) -> w.substring(0,1).matches("T"));
        System.out.println("Words that start with T: " + wordsStartWithT);

    }


}
