package VMSAPR.lab2;

public class Seidel extends Jacobi{
    public Seidel(int n, double[][] matrix) {
        super(n, matrix);
    }
    @Override
    public void eval() {
        initializeX();
        double EPSILON = 1E-3;
        boolean isPreciseEnough = false;
        while (!isPreciseEnough) {
            isPreciseEnough = true;
            for (int i = 0; i < X.length; i++) {
                double sum1 = 0, sum2 = 0;
                double[] prev = new double[X.length];
                System.arraycopy(X, 0, prev, 0, prev.length);
                for (int j = 0; j < i; j++) {
                    sum1 += matrix[i][j] * X[j];
                }
                for (int j = i + 1; j < matrix.length; j++) {
                    sum2 += matrix[i][j] * prev[j];
                }
                X[i] = (matrix[i][matrix.length] - sum1 - sum2) / matrix[i][i];
                if (Math.abs(X[i] - prev[i]) > EPSILON) {
                    isPreciseEnough = false;
                }
            }
        }
    }
}
