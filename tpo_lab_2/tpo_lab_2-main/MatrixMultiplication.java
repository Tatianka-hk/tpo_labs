package lab_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixMultiplication {
    public static void string_multiply(int[][] A, int[][] B, Result res, int numThreads) {

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int m = A.length;
        executor.execute(() -> {
        for (int i = 0; i < m; i++) {
            final int finalI = i;
//            System.out.println(i);
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
                    });

            try{
                executor.shutdown();
                executor.awaitTermination(1, TimeUnit.DAYS);

            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
    }





//    }
    public static void fox_multiply(int[][] A, int[][] B, Result res, int blockSize) {
        int n = A.length;
        int m = B[0].length;

        // Розбиваємо матриці  на  блоки
        int[][][][] blocksA = splitMatrix(A, blockSize);
        int[][][][] blocksB = splitMatrix(B, blockSize);

        // Створюємо масив потоків, які відповідають за множення кожної пари блоків
        Thread[][] threads = new Thread[blockSize][blockSize];
        Runnable run = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < blockSize; i++) {
                    for (int j = 0; j < blockSize; j++) {
                        for (int k = 0; k < blockSize; k++) {
                            res.C[i * blockSize + i][j * blockSize + j] += blocksA[i][j][i][k] * blocksB[j][i][k][j];
                        }
                    }
                }
            }
        };
        // Створюємо цикли для обробки кожної пари блоків матриць A та B
        try {
            for (int i = 0; i < blockSize; i++) {
                for (int j = 0; j < blockSize; j++) {
                    threads[i][j] = new Thread(new BlockMultiplier(blocksA[i][j], blocksB[j][i], res.C,
                            i * blockSize, j * blockSize, blockSize));
                    threads[i][j].start();
                    threads[i][j].join();
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }


    }

    private static int[][][][] splitMatrix(int[][] matrix, int blockSize) {
        int n = matrix.length;
        // Обчислюємо кількість блоків в рядку та стовпці
        int numBlocks = n / blockSize;
        int[][][][] blocks = new int[numBlocks][numBlocks][blockSize][blockSize];
        // Розбиваємо матрицю на блоки
        for (int i = 0; i < numBlocks; i++) {
            for (int j = 0; j < numBlocks; j++) {
                for (int k = 0; k < blockSize; k++) {
                    for (int l = 0; l < blockSize; l++) {
                        blocks[i][j][k][l] = matrix[i * blockSize + k][j * blockSize + l];
                    }
                }
            }
        }
        return blocks;
}

    private static class BlockMultiplier implements Runnable {

        private final int[][] blockA;
        private final int[][] blockB;
        private final int[][] matrixC;
        private final int rowOffset;
        private final int colOffset;
        private final int blockSize;

        public BlockMultiplier(int[][] blockA, int[][] blockB, int[][] matrixC,
                               int rowOffset, int colOffset, int blockSize) {
            this.blockA = blockA;
            this.blockB = blockB;
            this.matrixC = matrixC;
            this.rowOffset = rowOffset;
            this.colOffset = colOffset;
            this.blockSize = blockSize;
        }

        @Override
        public void run() {
            // Множимо два блоки матриць A та B
            for (int i = 0; i < blockSize; i++) {
                for (int j = 0; j < blockSize; j++) {
                    for (int k = 0; k < blockSize; k++) {
                        matrixC[rowOffset + i][colOffset + j] += blockA[i][k] * blockB[k][j];
                    }
                }
            }
        }
    }

}





