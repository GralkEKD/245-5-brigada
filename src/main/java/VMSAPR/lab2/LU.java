package VMSAPR.lab2;

public class LU extends Gauss {
    private double[] result;
    public LU(int n, double[][] matrix) {
        super(n, matrix);
    }
    protected double[][] initializeL(int n, double[][] initializedU, double[][] initialMatrix) {
        double[][] L = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    L[i][j] = initialMatrix[i][j];
                } else {
                    double someSum = 0;
                    for (int k = 0; k <= j - 1; k++) {
                        someSum += L[i][k] * initializedU[k][j];
                    }
                    L[i][j] = initialMatrix[i][j] - someSum;
                }
            }
        }
        return L;
    }
    public void eval(double[][] matrix) {
        forward(super.matrix, n);
        double[][] L = initializeL(n, super.matrix, matrix);
        double[] Y = new double[n];
        int i = 0;
        Y[i] = matrix[i][n] / L[i][i];
        for (; i < n; i++) {
            double someSum = 0;
            for (int j = 0; j < i; j++) {
                someSum += Y[j]*L[i][j];
            }
            Y[i] = matrix[i][n] - someSum;
        }
        result = new double[n];
        i = n - 1;
        result[i] = Y[i];
        for (--i; i >= 0; i--) {
            double someSum = 0;
            for (int j = i; j >= 0; j--) {
                someSum += super.matrix[i][j]*result[j];
            }
            result[i] = Y[i] - someSum;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double v : result) {
            sb.append(v).append("\n");
        }
        return sb.toString();
    }
}
