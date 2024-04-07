package lab7;
import mpi.*;
public class Task {
    static final int NRA = 1500;
    static final int NCA = 1500;
    static final int NCB = 1500;

    void run(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
//        size = 20;

        double[][] a = new double[NRA][NCA]; // matrix A to be multiplied
        double[][] b = new double[NCA][NCB]; // matrix B to be multiplied
        double[][] c = new double[NRA][NCB]; // result matrix C

        if (rank == 0){
            for (int l = 0; l < NRA; l++) {
                for (int ll = 0; ll < NCA; ll++) {
                    a[l][ll] = 10;
                }
            }

            for (int l = 0; l < NCA; l++) {
                for (int ll = 0; ll < NCB; ll++) {
                    b[l][ll] = 10;
                }
            }
            System.out.println("size of matrix: 1500");
            System.out.println("amount of threads: 20");
            
        }
        long  startTime1 = System.currentTimeMillis();;

        MPI.COMM_WORLD.Bcast(b, 0, NCA , MPI.OBJECT, 0);

        int rowsPerProc = NRA / size;
        int extraRows = NRA % size;

        var rowsCounts = new int[size];
        var rowsOffsets = new int[size];
        for (int i = 0; i < size; i++) {
            rowsCounts[i] = i < extraRows ? rowsPerProc + 1 : rowsPerProc;
            rowsOffsets[i] = i == 0 ? 0 : rowsOffsets[i - 1] + rowsCounts[i - 1];
        }
        var rowsInProc = rowsCounts[rank];

        var localA = new double[rowsInProc][NCA];
        MPI.COMM_WORLD.Scatterv(a,0,rowsCounts,rowsOffsets,MPI.OBJECT,localA,0,rowsInProc,MPI.OBJECT, 0);

        double[][] localс =  new double[NRA/size][NCB];
        for (int k = 0; k < NCB; k++) {
            for (int i = 0; i < rowsInProc; i++) {
                for (int j = 0; j < NCA; j++) {
                    localс[i][k] += localA[i][j] * b[j][k];
                }
            }
        }

//        MPI.COMM_WORLD.Allgatherv( localс,0, rowsInProc, MPI.OBJECT, c, 0, rowsCounts, rowsOffsets, MPI.OBJECT);

        MPI.COMM_WORLD.Gatherv( localс,0, rowsInProc, MPI.OBJECT, c, 0, rowsCounts, rowsOffsets, MPI.OBJECT, 0);
        if (rank == 0){
//            System.out.println("****");
//            System.out.println("Result Matrix:");
//            for (int i = 0; i < NRA; i++) {
//                System.out.println();
//                for (int j = 0; j < NCB; j++) {
//                    System.out.printf("%6.2f ", c[i][j]);
//                }
//            }
//            System.out.println("\n********");
//            System.out.println("Done.");
            long endTime1 = System.currentTimeMillis();
            long executionTime1 = endTime1 - startTime1;
            System.out.println("Execution time of blocking: " + executionTime1 + " ms");
        }

        MPI.Finalize();
    }
}
