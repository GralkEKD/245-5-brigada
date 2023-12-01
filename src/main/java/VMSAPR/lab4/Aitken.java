package VMSAPR.lab4;

public class Aitken {
    private final int n; // N
    private final double[] xArray; // X
    private final double[] yArray; // Y
    private double[][] matrixOfPolynomials;
    public Aitken (int n, double[] x, double[] y) {
        this.n = n;
        xArray = x;
        yArray = y;
    }
    private double PX(int i, int j, double Z) {
        double p;
        if (matrixOfPolynomials[j][i] == 1) {
            return matrixOfPolynomials[i][j];
        } else if (j - i == 1) {
            p =  (yArray[i] * (xArray[j] - Z) - yArray[j] * (xArray[i] - Z)) / (xArray[j] - xArray[i]);
            matrixOfPolynomials[i][j] = p;
            matrixOfPolynomials[j][i] = 1;
            return p;
        } else {
            p = (PX(i,j-1,Z) * (xArray[j] - Z) - PX(i+1,j,Z) * (xArray[i] - Z)) / (xArray[j] - xArray[i]);
            matrixOfPolynomials[i][j] = p;
            matrixOfPolynomials[j][i] = 1;
            return p;
        }
    }
    public double eval(double Z) {
        this.matrixOfPolynomials = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                PX(i, j, Z);
            }
        }
        return matrixOfPolynomials[0][n - 1];
    }
}
