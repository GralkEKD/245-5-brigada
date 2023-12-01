package VMSAPR.lab4;

public class Lagrange {
    private final int n;
    private final double[] xArray;
    private final double[] yArray;
    public Lagrange (int n, double[] x, double[] y) {
        this.n = n;
        xArray = x;
        yArray = y;
    }
    private double[][] differenceTable() {
        double[][] table = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    table[i][j] = xArray[i] - xArray[j];
                } else {table[i][j] = 1;}
            }
        }
        return table;
    }

    private double productOfMainDiagonal(double x) {
        double pr = 1;
        for (double v : xArray) {
            pr *= x - v;
        }
        return pr;
    }
    private double di(double[] row){
        double product = 1;
        for (double v : row) {
            product *= v;
        }
        return product;
    }
    public double eval(double x) {
        for (int i = 0; i < n; i++) {
            if (x == xArray[i]) {
                return yArray[i];
            }
        }
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += yArray[i] / (di(differenceTable()[i]) * (x - xArray[i]));
        }
        return productOfMainDiagonal(x) * sum;
    }
}
