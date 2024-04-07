package task4;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.concurrent.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class SearchTask extends RecursiveTask<List<File>> {
    private final File directory;
    private final String[] keywords;
    public SearchTask(File directory, String[] keywords) {
        this.directory = directory;
        this.keywords = keywords;
    }
    @Override
    protected List<File> compute() {
        List<File> foundFiles = new ArrayList<>();
        List<SearchTask> subTasks = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                //якщо файл є директорією
                if (file.isDirectory()) {
                    SearchTask subTask = new SearchTask(file, keywords);
                    subTasks.add(subTask);
                    subTask.fork();
                } else if (file.getName().endsWith(".txt")) {
                    if (containsKeywords(file, keywords)) {
                        foundFiles.add(file);
                    }
                }
            }
        }
        for (SearchTask subTask : subTasks) {
            foundFiles.addAll(subTask.join());
        }
        return foundFiles;
    }
    private boolean containsKeywords(File file, String[] keywords) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String keyword : keywords) {
                    if (line.contains(keyword)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

