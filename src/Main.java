import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int row1, row2, column1, column2;
        Scanner scn = new Scanner(System.in);
        //System.out.println("How many Threads?");
        int n = scn.nextInt();

        //System.out.println("Enter The Dimension of the first Matrix(Row Column)");
        row1 = scn.nextInt();
        column1 = scn.nextInt();

        int[][] MatrixA, MatrixB, Multiplicated_Matrix, TransposedMatB;

        MatrixA = new int[row1][column1];

        //  System.out.println("Enter the firsr Matrix");
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < column1; j++) {
                MatrixA[i][j] = scn.nextInt();
            }
        }

        //System.out.println("Enter The Dimension of the second Matrix(Row Column)");
        row2 = scn.nextInt();
        column2 = scn.nextInt();

        MatrixB = new int[row2][column2];
        TransposedMatB = new int[column2][row2];
        Multiplicated_Matrix = new int[row1][column2];
        int[][] resultant_Matrix = new int[row1][column2];

        //System.out.println("Enter the second Matrix");
        for (int i = 0; i < row2; i++) {
            for (int j = 0; j < column2; j++) {
                MatrixB[i][j] = scn.nextInt();
            }
        }

        for (int i = 0; i < row2; i++) {
            for (int j = 0; j < column2; j++) {
                TransposedMatB[j][i] = MatrixB[i][j];
            }
        }
        PC pc = new PC(row1 * column2);

        worker[] wk = new worker[n];
        
        for (int j = 0; j < (row1 * column2) / n; j++) {
            for (int i = 0; i < n; i++) {
                wk[i] = new worker(resultant_Matrix, pc);
            }
        }
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < column2; j++) {
                WorkItem workitem = new WorkItem(i, j, MatrixA[i], TransposedMatB[j]);
                pc.put(workitem);
            }
        }
        long beginT, endT;
        beginT = System.nanoTime();
        try {
            for (int i = 0; i < n; i++) {
                wk[i].t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Worker Thread Interrupted");
        }
        endT = System.nanoTime();
        System.out.println("Multiplication Using " + n + " Threads\n");
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < column2; j++) {
                System.out.printf("%d ", resultant_Matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("\nTime Required in Multi Threaded Fashion: " + (endT - beginT) + " nanoseconds\n");
    }
}

