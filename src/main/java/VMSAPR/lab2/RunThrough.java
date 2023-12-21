package VMSAPR.lab2;

public class RunThrough {
    private final int n;
    private final double[][] matrix;
    public double[] result;
    public RunThrough(int n, double[][] matrix) {
        this.n = n;
        this.matrix = new double[n][n + 1];
        System.arraycopy(matrix, 0, this.matrix, 0, matrix.length);
    }
    private double[][] forward() {
        double[] alpha = new double[n - 1];
        double[] beta = new double[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                alpha[i] = -matrix[i][i + 1] / matrix[i][i];
                beta[i] = matrix[i][n] / matrix[i][i];
            } else if (i == n - 1) {
                beta[i] = (matrix[i][n] - matrix[i][i - 1] * beta[i - 1]) / (matrix[i][i] + matrix[i][i - 1] * alpha[i - 1]);
            } else {
                alpha[i] = -matrix[i][i + 1] / (matrix[i][i] + matrix[i][i - 1] * alpha[i - 1]);
                beta[i] = (matrix[i][n] - matrix[i][i - 1] * beta[i - 1]) / (matrix[i][i] + matrix[i][i - 1] * alpha[i - 1]);
            }
        }
        return new double[][]{alpha, beta};
    }
    private double[] backward(double[][] alphaBetaArray) {
        double[] result = new double[n];
        int i = n - 1;
        result[i] = alphaBetaArray[1][i];
        while (i > 0) {
            result[--i] = alphaBetaArray[0][i]*result[i + 1] + alphaBetaArray[1][i];
        }
        return result;
    }
    public void eval() {
        double[][] result = forward();
        this.result = backward(result);
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (double v : result) {
            sb.append(v).append("\n");
        }
        return(sb.toString());
    }
}
