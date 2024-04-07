package task4;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.io.File;
public class Main {
    private static final String SEARCH_DIRECTORY = "C:\\Users\\Tatiana\\IdeaProjects\\lab_4\\src\\task4";
    private static final String[] KEYWORDS = {"Java", "programming", "technology"};

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        SearchTask task = new SearchTask(new File(SEARCH_DIRECTORY), KEYWORDS);
        List<File> foundFiles = pool.invoke(task);
        System.out.println("Файли: ");
        for (File file : foundFiles) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
