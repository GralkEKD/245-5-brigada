package VMSAPR.lab3;

public class FunctionAndPrecision {
    protected double y(double x) { return 1 / (x + 2) - x; }
    protected static final double epsilon = 1E-2; // экспоненциальная форма записи 1 * 10^-2
    protected double derivative(double x) { return (y(x + epsilon) - y(x)) / epsilon; }
}
