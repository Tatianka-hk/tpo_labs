package task1;

import java.util.concurrent.*;

public class TextAnalysis extends RecursiveTask<int[]> {
    private String[] words;
    private int start;
    private int end;

    public TextAnalysis(String[] words) {
        this.words = words;
        this.start = 0;
        this.end = words.length;
    }

    private TextAnalysis(String[] words, int start, int end) {
        this.words = words;
        this.start = start;
        this.end = end;
    }

    @Override
    protected int[] compute() {
        if (end - start < 100) {
            int[] lengths = new int[end - start];
            for (int i = start; i < end; i++) {
                lengths[i - start] = words[i].length();
            }
            return lengths;
        } else {
            int mid = (start + end) / 2;
            TextAnalysis leftTask = new TextAnalysis(words, start, mid);
            TextAnalysis rightTask = new TextAnalysis(words, mid, end);
            leftTask.fork();
            int[] rightResult = rightTask.compute();
            int[] leftResult = leftTask.join();
            return joinResults(leftResult, rightResult);
        }
    }
    private int[] joinResults(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        System.arraycopy(left, 0, result, 0, left.length);
        System.arraycopy(right, 0, result, left.length, right.length);
        return result;
    }


}