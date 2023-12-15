package VMSAPR.lab2;

public class Jacobi {
    protected final double[] X;
    protected final double[][] matrix;
    public Jacobi(int n, double[][] matrix) {
        X = new double[n];
        this.matrix = new double[n][n + 1];
        System.arraycopy(matrix, 0, this.matrix, 0, this.matrix.length);
    }
    protected void initializeX() {
        for (int i = 0; i < matrix.length; i++) {
            X[i] = matrix[i][matrix.length] / matrix[i][i];
        }
    }
    public void eval() {
        initializeX();
        double EPSILON = 1E-3;
        boolean isPreciseEnough = false;
        while (!isPreciseEnough) {
            isPreciseEnough = true;
            for (int i = 0; i < X.length; i++) {
                double sum1 = 0;
                double prev = X[i];
                for (int j = 0; j < X.length; j++) {
                    if (i != j) {
                        sum1 += matrix[i][j] * X[j];
                    }
                }
                X[i] = (matrix[i][matrix.length] - sum1) / matrix[i][i];
                if (Math.abs(X[i] - prev) > EPSILON) {
                    isPreciseEnough = false;
                }
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double v : X) {
            sb.append(v).append("\n");
        }
        return sb.toString();
    }
}
