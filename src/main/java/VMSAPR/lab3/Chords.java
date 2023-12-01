package VMSAPR.lab3;

public class Chords extends FunctionAndPrecision{
    public double a;
    public double b;
    public Chords (double a, double b) {
        this.a = a;
        this.b = b;
    }
    public double chordsMethod() {
        if (y(a) == 0) {
            return a;
        } else if (y(b) == 0) {
            return b;
        }
        double x = a + b;
        if (y(a) < y(b)) {
            double xprev = a;
            while (Math.abs(y(x)) > epsilon) {
                x = xprev - (((b - xprev)) / (y(b) - y(xprev))) * y(xprev);
                xprev = x;
            }
        } else {
            double xprev = b;
            while (Math.abs(y(x)) > epsilon) {
                x = xprev - (((xprev - a)) / (y(xprev) - y(a))) * y(xprev);
                xprev = x;
            }
        }
        return x;
    }
}
