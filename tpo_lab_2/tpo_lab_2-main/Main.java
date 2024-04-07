package lab_2;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        int[][]A = {{6,7,5,9},{3, 8, 4,3}, {1,0,2,3}, {9,5,2,4}};
//        int [][] B = {{6,7,5,0}, {3, 8, 4,4}, {1,8,2,7}, {9,5,6,1}};
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
        int numProcessors = Runtime.getRuntime().availableProcessors();
        int  block_size = (int) Math.sqrt(numProcessors);


        Result res1 =new Result();
        res1.C = new int[A.length][B[0].length];
        Result res2 =new Result();
        res2.C = new int[A.length][B[0].length];
        MatrixMultiplication mul = new MatrixMultiplication();
        System.out.println("Часу");
        // для 3-ої частини завдання
        long currTime = System.nanoTime();
        mul.string_multiply(A,B,res1,block_size);
        currTime = System.nanoTime() - currTime;
        System.out.println("Часу для стрічкового алгоритму - "+currTime);

        long currTime1 = System.nanoTime();
        mul.fox_multiply(A,B,res2,block_size);
        currTime1 = System.nanoTime() - currTime;
        System.out.println("Часу для  алгоритму фоксу - "+currTime1);

        // для 4-ої частини завдання
        long currTime3 = System.nanoTime();
        mul.string_multiply(A,B,res1,50);
        currTime3 = System.nanoTime() - currTime;
        System.out.println("Часу для стрічкового алгоритму - "+currTime);

//        long currTime4 = System.nanoTime();
//        mul.fox_multiply(A,B,res2,50);
//        currTime4 = System.nanoTime() - currTime;
//        System.out.println("Часу для  алгоритму фоксу - "+currTime1);



//

}}