package lab_5.task4;
import java.util.Random;
import java.util.concurrent.*;
public class Main {
    public static void main(String[] args) {

        int[][] A = new int [2000][2000];
        int [][] B = new int [2000][2000];


        Random random = new Random();
        for (int i = 0; i < 2000; i++) {
            for (int j = 0; j < 2000; j++) {
                A[i][j] = random.nextInt(201) - 100; // генеруємо число від -100 до 100
                B[i][j] = random.nextInt(201) - 100;
            }
        }

        Result res0 =new Result();
        res0.C= new int[A.length][B[0].length];

        MatrixMultiplication mul = new MatrixMultiplication();
        int numProcessors = Runtime.getRuntime().availableProcessors();
        int  block_size = (int) Math.sqrt(numProcessors);
//        long currTime = System.nanoTime();
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        mul.string_multiply(A,B,res0,block_size);
//        currTime = System.nanoTime() - currTime;
        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//        System.out.println("Часу для стрічкового алгоритму - "+currTime);
        System.out.println("Витрати пам'яті матриці розміру 2000 ="+ (afterMemory - beforeMemory) + " байт");


    }}
