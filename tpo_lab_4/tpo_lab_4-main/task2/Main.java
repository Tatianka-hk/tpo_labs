package task2;
import java.util.Random;
import java.util.concurrent.*;
public class Main {
    public static void main(String[] args) {
//        //створення матриць
        int[][] A = new int [500][500];
        int [][] B = new int [500][500];

        //генерація матриць
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                A[i][j] = random.nextInt(201) - 100; // генеруємо число від -100 до 100
                B[i][j] = random.nextInt(201) - 100;
            }
        }

        Result res0 =new Result();
        Result res1 =new Result();
        res1.C = new int[A.length][B[0].length];
        res0.C= new int[A.length][B[0].length];

        MatrixMultiplication mul = new MatrixMultiplication();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MatrixMultiplicationTask myTask = new MatrixMultiplicationTask(A, B, res1, mul);


        long startTime = System.nanoTime();
        forkJoinPool.invoke(myTask);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000.0; // мс
        System.out.println("Час - "+duration+"(mc)");


        long startTime1 = System.nanoTime();
        mul.string_multiply(A, B, res0);
        long endTime1 = System.nanoTime();
        double duration1 = (endTime1 - startTime1) / 1000000.0; // мс
        System.out.println("Час - "+duration1+"(mc)");

}}