package VMSAPR.lab2;

public class ReciprocalMatrix extends Gauss{
    public ReciprocalMatrix(int n, double[][] matrix) {
        super(n, matrix);
    }
    private final double[][] results = new double[n][n];
    private void ABtoAE(int iter) {
        for (int i = 0; i < n; i++) {
            if (i == iter) {
                matrix[i][n] = 1;
            } else {
                matrix[i][n] = 0;
            }
        }
    }

    @Override
    public void eval() {
        for (int i = 0; i < n; i++) {
            ABtoAE(i);
            super.eval();
            for (int j = 0; j < result.length; j++) {
                results[j][i] = result[j];
            }
        }
    }
    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();
        for (double[] v : results) {
            for (double m : v) {
                sb.append(m).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
