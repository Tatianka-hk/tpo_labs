package task1;
import mpi.*;
public class Main {
    public static void main(String[] args) {
        MatrixMultiplicatorBlocking task1 = new MatrixMultiplicatorBlocking();
        MatrixMultiplicatorUnblocking task2 = new MatrixMultiplicatorUnblocking();


        task1.task(args);

//
//        System.out.println("unblocking 2");

        task2.task(args);



    }
}
