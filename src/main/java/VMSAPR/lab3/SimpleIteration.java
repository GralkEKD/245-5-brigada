package VMSAPR.lab3;

public class SimpleIteration extends FunctionAndPrecision {
    public double a;
    public double b;

    public SimpleIteration(double a, double b) {
        this.a = a;
        this.b = b;
    }
    private double phi(double x) {
        return 1 / (x + 2);
    }
    public double iteration() {
        double xPrePrev;
        if (y(a) > y(b)){
            xPrePrev = phi(a);
        } else { xPrePrev = phi(b); }
        double xPrev = phi(xPrePrev);
        double x = phi(xPrev);
        double q = Math.abs(x - xPrev) / Math.abs(xPrev - xPrePrev);
        while (Math.abs(x - xPrev) > Math.abs((1-q) / q) * epsilon) {
            xPrePrev = xPrev;
            xPrev = x;
            x = phi(x);
            q = Math.abs(x - xPrev) / Math.abs(xPrev - xPrePrev);
            if ((x > b) || (x < a)) {
                x = Double.POSITIVE_INFINITY;
                break;
            }
        }
        return x;
    }
}
