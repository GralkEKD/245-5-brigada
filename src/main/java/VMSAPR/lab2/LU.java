package VMSAPR.lab2;

public class LU {
    private double[] result;
    private double[][] initializeU(int n, double[][] initialMatrix){
        double[][] U = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = i; j <= n; j++) {
                if (i == j) {
                    U[i][j] = 1;
                } else {
                    U[i][j] = initialMatrix[i][j] / initialMatrix[i][i];
                }
            }
        }
        return U;
    }
    private double[][] initializeL(int n, double[][] initializedU, double[][] initialMatrix) {
        double[][] L = new double[n][n + 1];
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
    public void eval(int n, double[][] matrix) {
        double[][] U = initializeU(n, matrix);
        double[][] L = initializeL(n, U, matrix);
        double[] Y = new double[n];
        int i = 0;
        Y[i] = matrix[i][n];
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
                someSum += U[i][j]*result[j];
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
