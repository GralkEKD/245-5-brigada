package VMSAPR.lab6;

public class Rectangles extends FunctionAndPrecision{
    public double a;
    public double b;
    public Rectangles(double a, double b) {
        this.a = a;
        this.b = b;
    }
    private double p = precision(1);
    private double integral(int n, int d) {
        double in = 0;
        double h = (b - a) / n;
        for (int i = d; i <= n - (1 - d); i++) {
            in += y(a + i*h) * h;
        } return in;
    }
    public double evalLeft() {
        int n = 10;
        double res = integral(n, 0);
        double res2 = integral(2*n, 0);
        while (p * Math.abs(res - res2) > epsilon) {
            n *= 2;
            res = integral(n, 0);
            res2 = integral(2*n, 0);
        }
        return res2;
    }
    public double evalRight() {
        int n = 10;
        double res = integral(n, 1);
        double res2 = integral(2*n, 1);
        while (p * Math.abs(res - res2) > epsilon) {
            n *= 2;
            res = integral(n, 1);
            res2 = integral(2*n, 1);
        }
        return res2;
    }
    public double evalMiddle() {
        int n = 10;
        p = precision(1);
        double res = integral(n, 1);
        double res2 = integral(2*n, 1);
        while (p * Math.abs(res - res2) > epsilon) {
            n *= 2;
            res = integral(n, 1);
            res2 = integral(2*n, 1);
        }
        return res2;
    }
}
