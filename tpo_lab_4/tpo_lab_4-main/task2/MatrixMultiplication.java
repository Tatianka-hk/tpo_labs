package task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixMultiplication {
    public static void string_multiply(int[][] A, int[][] B, Result res) {


        int m = A.length;

        for (int i = 0; i < m; i++) {
            final int finalI = i;
            int n = A[0].length;
            int p = B[0].length;

            for (int j = 0; j < p; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += A[finalI][k] * B[k][j];
                }
                res.C[finalI][j] = sum;
            }
        }

    }

}





