package VMSAPR.lab6;

public class FunctionAndPrecision {
    protected double y(double x) { return Math.pow(x, 2) - 4; }
    protected static final double epsilon = 1E-3;
    protected double precision(int p) { return 1/(Math.pow(2, p) - 1); }
}
