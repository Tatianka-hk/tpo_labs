package task2;

public class Result {
    public int [][] C;
    public void see() {
        if (C != null) {
            System.out.println("Matrix");
            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < C[0].length; j++) {
                    System.out.print(C[i][j]+ " ");
                }
                System.out.println();
            }
        }
    }


}
