package task3;
import java.util.concurrent.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;
public class Main {

    public static void main(String[] args) {
        String file1 = "C:\\Users\\Tatiana\\IdeaProjects\\lab_4\\src\\task3\\file1.txt";
        String file2 = "C:\\Users\\Tatiana\\IdeaProjects\\lab_4\\src\\task3\\file2.txt";

        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
             BufferedReader br2 = new BufferedReader(new FileReader(file2))) {

            String text1 = br1.lines().collect(Collectors.joining(" "));
            String text2 = br2.lines().collect(Collectors.joining(" "));

            String[] words1 = text1.split("\\s+");
            String[] words2 = text2.split("\\s+");

            ForkJoinPool pool = ForkJoinPool.commonPool();
            ConcurrentHashMap<String, Integer> sharedWords = pool.invoke(new SharedWordsFinder(words1, words2));

            System.out.println("Shared words:");
            sharedWords.forEach((word, count) -> System.out.println(word + ": " + count));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
