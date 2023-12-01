package VMSAPR.lab6;

public class Simpsons extends FunctionAndPrecision {
    public double a;
    public double b;
    public Simpsons(double a, double b) {
        this.a = a;
        this.b = b;
    }
    private final double p = precision(4);
    private double integral(int n) {
        double h = (b - a) / (2 * n);
        double in = h * (y(a) - y(b));
        for (int i = 1; i <= n; i++) {
            in += 4 * h * y(a + (2 * i - 1) * h) + 2 * h * y(a + (2 * i) * h);
        }
        return in / 3;
    }
    public double eval() {
        int n = 10;
        double res = integral(n);
        double res2 = integral(2*n);
        while (p * Math.abs(res2 - res) > epsilon) {
            n *= 2;
            res = integral(n);
            res2 = integral(2*n);
        }
        return res2;
    }
}
