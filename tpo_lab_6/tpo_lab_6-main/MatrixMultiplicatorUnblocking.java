package task1;

import mpi.MPI;
import mpi.*;

public class MatrixMultiplicatorUnblocking {
    static final int NRA = 1500;    // number of rows in matrix A
    static final int NCA = 1500;    // number of columns in matrix A
    static final int NCB = 1500;     // number of columns in matrix B
    static final int MASTER = 0;  // taskid of first task
    static final int FROM_MASTER = 1;  // setting a message type
    static final int FROM_WORKER = 2;  // setting a message type

    public static void task(String[] args) {

        MPI.Init(args);

        int numtasks, taskid, numworkers,source, mtype; // message type
        int  averow, extra,  i, j, k, rc;
        double[][] a = new double[NRA][NCA]; // matrix A to be multiplied
        double[][] b = new double[NCA][NCB]; // matrix B to be multiplied
        double[][] c = new double[NRA][NCB]; // result matrix C
        int[] rows = {0}, offset = {0};

        // Initialize matrices a and b

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

        rc = 1;
        Status status;

        taskid = MPI.COMM_WORLD.Rank();
        numtasks = MPI.COMM_WORLD.Size();
        numtasks = 20;
        if (numtasks < 2) {
            System.out.println("Need at least two MPI tasks. Quitting...");
            MPI.COMM_WORLD.Abort(rc);
            System.exit(1);
        }

        numworkers = numtasks - 1;

        if (taskid == MASTER) {
            long startTime2 = System.currentTimeMillis();
//            System.out.println("mpi_mm has started with " + numtasks + " tasks.");

            averow = NRA / numworkers;
            extra = NRA % numworkers;

            for (int dest = 1; dest <= numworkers; dest++) {
                rows[0]  = (dest <= extra) ? averow + 1 : averow;
//                System.out.println("Sending " + rows[0] + " rows to task " + dest + " offset=" + offset[0]);
                MPI.COMM_WORLD.Isend(offset, 0, 1, MPI.INT, dest, FROM_MASTER);
                MPI.COMM_WORLD.Isend(rows, 0, 1, MPI.INT, dest, FROM_MASTER);
                MPI.COMM_WORLD.Isend(a, offset[0], rows[0], MPI.OBJECT, dest, FROM_MASTER);
                MPI.COMM_WORLD.Isend(b, 0,  NCA, MPI.OBJECT, dest, FROM_MASTER);
                offset[0] += rows[0];

            }

            // Receive results from worker tasks




            for (source = 1; source <= numworkers; source++) {
                Request offsetRequest  = MPI.COMM_WORLD.Irecv(offset, 0, 1, MPI.INT, source, FROM_WORKER);
                offsetRequest.Wait();
                Request rowsRequest  = MPI.COMM_WORLD.Irecv(rows, 0, 1, MPI.INT, source, FROM_WORKER);
                rowsRequest.Wait();
                Request cRequest = MPI.COMM_WORLD.Irecv(c, offset[0], rows[0], MPI.OBJECT, source, FROM_WORKER);
                cRequest.Wait();

            }

            for (source = 1; source <= numworkers; source++) {
//                System.out.println("Received results from task " + source);
            }



            // Print results
//            System.out.println("****");
//            System.out.println("Result Matrix:");
//            for (i = 0; i < NRA; i++) {
//                System.out.println();
//                for (j = 0; j < NCB; j++) {
//                    System.out.printf("%6.2f ", c[i][j]);
//                }
//            }
//            System.out.println("\n********");
//            System.out.println("Done.");
            long endTime2 = System.currentTimeMillis();
            long executionTime2 = endTime2 - startTime2;
            System.out.println("Execution time of non-blocking: " + executionTime2 + " ms");
        }
/******** worker task *****************/
        else { // if (taskid > MASTER)

            Request offsetRequest = MPI.COMM_WORLD.Irecv(offset, 0, 1, MPI.INT, MASTER, FROM_MASTER);
            Request rowsRequest =MPI.COMM_WORLD.Irecv(rows, 0, 1, MPI.INT, MASTER, FROM_MASTER);
            offsetRequest.Wait();
            rowsRequest.Wait();
            Request aRequest =MPI.COMM_WORLD.Irecv(a, 0, rows[0], MPI.OBJECT, MASTER, FROM_MASTER);
            aRequest.Wait();
            Request bRequest = MPI.COMM_WORLD.Irecv(b, 0, NCA, MPI.OBJECT, MASTER, FROM_MASTER);
            bRequest.Wait();


            for (k = 0; k < NCB; k++) {
                for (i = 0; i < rows[0]; i++) {
                    c[i][k] = 0.0;
                    for (j = 0; j < NCA; j++) {
                        c[i][k] += a[i][j] * b[j][k];
                    }
                }
            }
            MPI.COMM_WORLD.Isend(offset, 0, 1, MPI.INT, MASTER, FROM_WORKER);
            MPI.COMM_WORLD.Isend(rows, 0, 1, MPI.INT, MASTER, FROM_WORKER);
            MPI.COMM_WORLD.Isend(c, 0, rows[0], MPI.OBJECT, MASTER, FROM_WORKER);
        }

        MPI.Finalize();

    }
}
