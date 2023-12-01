package VMSAPR.lab6;

public class Newtons extends FunctionAndPrecision{
    public double a;
    public double b;
    public Newtons(double a, double b) {
        this.a = a;
        this.b = b;
    }
    private final double p = precision(4);
    private double integral(int n) {
        double h = (b - a) / (3 * n);
        double in = (y(a) - y(b)) * 3*h/8;
        for (int i = 1; i <= n; i++) {
            in += 3*h/8 * (3 * (y(a + (i * 3 - 2) * h) + y(a + (i * 3 - 1) * h)) + 2 * y(a + (i * 3) * h));
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
