package task3;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
public class SharedWordsFinder extends RecursiveTask<ConcurrentHashMap<String, Integer>> {
    private final String[] words1;
    private final String[] words2;

    private static final int THRESHOLD = 10;

    public SharedWordsFinder(String[] words1, String[] words2) {
        this.words1 = words1;
        this.words2 = words2;
    }

    @Override
    protected ConcurrentHashMap<String, Integer> compute() {
        if (words1.length <= THRESHOLD || words2.length <= THRESHOLD) {
            return findSharedWords(words1, words2);
        } else {
            int mid1 = words1.length / 2;
            int mid2 = words2.length / 2;
            SharedWordsFinder task1 = new SharedWordsFinder(Arrays.copyOfRange(words1, 0, mid1), Arrays.copyOfRange(words2, 0, mid2));
            SharedWordsFinder task2 = new SharedWordsFinder(Arrays.copyOfRange(words1, mid1, words1.length), Arrays.copyOfRange(words2, 0, mid2));
            SharedWordsFinder task3 = new SharedWordsFinder(Arrays.copyOfRange(words1, 0, mid1), Arrays.copyOfRange(words2, mid2, words2.length));
            SharedWordsFinder task4 = new SharedWordsFinder(Arrays.copyOfRange(words1, mid1, words1.length), Arrays.copyOfRange(words2, mid2, words2.length));

            invokeAll(task1, task2, task3, task4);

            ConcurrentHashMap<String, Integer> result = new ConcurrentHashMap<>();
            result.putAll(task1.join());
            result.putAll(task2.join());
            result.putAll(task3.join());
            result.putAll(task4.join());

            return result;
        }
    }

    private ConcurrentHashMap<String, Integer> findSharedWords(String[] words1, String[] words2) {
        List<String> list1 = Arrays.asList(words1);
        List<String> list2 = Arrays.asList(words2);
        ConcurrentHashMap<String, Integer> wordCounts = new ConcurrentHashMap<>();

        list1.parallelStream()
                .filter(list2::contains)
                .forEach(word -> wordCounts.merge(word, 1, Integer::sum));

        return wordCounts;
    }
}
