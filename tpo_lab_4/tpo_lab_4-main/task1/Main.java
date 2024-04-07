package task1;

import java.util.*;
import java.util.concurrent.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Введення тексту для аналізу
//        String text = "Це є приклад тексту для аналізу. Він містить кілька речень з різними довжинами.";
        String e1 = "C:\\Users\\Tatiana\\IdeaProjects\\lab_4\\src\\task1\\text1.txt"; //100 слів
        String e2 = "C:\\Users\\Tatiana\\IdeaProjects\\lab_4\\src\\task1\\text2.txt";//500 слів
        String e3 = "C:\\Users\\Tatiana\\IdeaProjects\\lab_4\\src\\task1\\text3.txt";//1000 слів
        String e4 = "C:\\Users\\Tatiana\\IdeaProjects\\lab_4\\src\\task1\\text4.txt";//5000 слів
        String e5 = "C:\\Users\\Tatiana\\IdeaProjects\\lab_4\\src\\task1\\text5.txt";//10000 слів
        String text = "";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(e3))) {
            while ((line = br.readLine()) != null) {
                text=text+line;
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        // Розбиття тексту на слова
        String[] words = text.split("\\s+");

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        TextAnalysis task = new TextAnalysis(words);
        long startTime = System.nanoTime();
        int[] lengths = forkJoinPool.invoke(task);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000.0; // мс


        double average = Arrays.stream(lengths).average().orElse(Double.NaN);

        System.out.println("Середня довжина слова: " + average);
        System.out.println("Кількість слів: " + words.length);
        System.out.println("Час: " + duration +"(мс)");

    }
}