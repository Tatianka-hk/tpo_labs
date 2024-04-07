package task2;
import java.util.concurrent.*;
public class MatrixMultiplicationTask extends RecursiveAction {
    private final int[][] A;
    private final int[][] B;
    private final Result res;
    MatrixMultiplication mul;

    public MatrixMultiplicationTask(int[][] A, int[][] B, Result res, MatrixMultiplication  mul) {
        this.A = A;
        this.B = B;
        this.res = res;
        this.mul = mul;
    }

    @Override
    protected void compute() {
        mul.string_multiply(A, B, res);
    }
}