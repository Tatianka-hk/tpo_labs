package task1;

public class MatrixMultiplicatorSimple {
    static final int NRA = 1500;    // number of rows in matrix A
    static final int NCA =  1500;    // number of columns in matrix A
    static final int NCB = 1500;     // number of columns in matrix B

    public static void task() {
        System.out.println("size of matrix -  500");
        long startTime1 = System.currentTimeMillis();
        double[][] a = new double[NRA][NCA]; // matrix A to be multiplied
        double[][] b = new double[NCA][NCB]; // matrix B to be multiplied
        double[][] c = new double[NRA][NCB]; // result matrix C
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

        for (int i = 0; i < NRA; i++) {
            for (int j = 0; j < NCB; j++) {
                for (int k = 0; k < NCA; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        //            System.out.println("****");
//            System.out.println("Result Matrix:");
//            for (int i = 0; i < NRA; i++) {
//                System.out.println();
//                for (int j = 0; j < NCB; j++) {
//                    System.out.printf("%6.2f ", c[i][j]);
//                }
//            }
        long endTime1 = System.currentTimeMillis();
        long executionTime1 = endTime1 - startTime1;
        System.out.println("Execution time of simple: " + executionTime1 + " ms");
    }
}
