package VMSAPR.lab6;

public class Trapezoid extends FunctionAndPrecision{
    public double a;
    public double b;
    public Trapezoid(double a, double b) {
        this.a = a;
        this.b = b;
    }
    private final double p = precision(1);
    private double integral(int n) {
        double h = (b - a) / n;
        double in = (y(a) + y(b))/2 * h;
        for (int i = 1; i <= n - 1; i++) {
            in += y(a + i * h) * h;
        }
        return in;
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
