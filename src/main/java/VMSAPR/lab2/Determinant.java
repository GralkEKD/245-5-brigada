package VMSAPR.lab2;

public class Determinant extends LU{
    public Determinant(int n, double[][] matrix) {
        super(n, matrix);
    }
    public double determinant(double[][] matrix) {
        forward(super.matrix, n);
        double[][] L = initializeL(n, super.matrix, matrix);
        double determinant = 1;
        for (int i = 0; i < n; i++) {
            determinant *= L[i][i];
        }
        return determinant;
    }
}
