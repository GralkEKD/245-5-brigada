package VMSAPR.lab3;

public class HalfDivision extends FunctionAndPrecision{
    public double a;
    public double b;

    public HalfDivision(double a, double b) {
        this.a = a;
        this.b = b;
    }
    public double division(){
        double c = (a + b) / 2;
        if (y(c) == 0){
            return (c);
        } else if (y(b) == 0) {
            return (b);
        } else if (y(a) == 0) {
            return (a);
        }
        if (Math.signum(y(c)) == Math.signum(y(b))){
            b = c;
        } else {
            a = c;
        }
        if (Math.abs(b - a) < 2 * epsilon){
            return ((a + b) / 2);
        } else { return (this.division()); }
    }
}
