package VMSAPR.lab3;

public class Newtons extends FunctionAndPrecision{
    public double a;
    public double b;

    public Newtons(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double tangents() {
        double x;
        double xPrev;
        if (y(a) > y(b)) {
            xPrev = a;
        } else { xPrev = b; }
        do {
            x = xPrev - (y(xPrev) / derivative(xPrev));
            xPrev = x;
        } while (y(x) > epsilon);
        if (!(x >= a) || !(x <= b)) {
            xPrev = b;
            do {
                x = xPrev - (y(xPrev) / derivative(xPrev));
                xPrev = x;
            } while (y(x) > epsilon);
        }
        return x;
    }
}
