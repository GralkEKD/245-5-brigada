package VMSAPR.lab4;

import VMSAPR.lab2.RunThrough;

public class Splines {
    /*
    [Verse 1]
    I do my math all day and in my slumber
    Body count's higher than Graham's number
    Call me X-Ray, 'cause I'm high on the spectrum
    I filled her up in her Latus Rectum

    I spin her full, you can call me a basis
    Try fitting me in a 3x3 matrix
    I'm past 3D and got edges or faces
    That girl be lettin' me run all the bases

    ...

    [Verse 2]
    She can transform me like Fourier
    She got better curves than Bezier
    I start with velocity, end with viscosity
    I'm a monstrosity every day

    I make her yodel, she calls me Godel
    'Cause without me, she feels so incomplete
    And no one can ever say "no" to this beat
    And unlike my lyrics, my math is discrete

    ...

    [Chorus]
    College math has been getting on my nerves
    They call me the derivative 'cause I like tangent to her curves
    I'm the Kama Sutra of numbers, a mathematical thesaurus
    No, I'm not a fan of star signs, but I treat her like a Torus (Aha, get it? Like Taurus, but torus, which is a donut basically)

    [Verse 3]
    My sections are conic, depression is chronic
    My series harmonic, but I'm idiotic
    My lines asymptotic, my rhymes are hypnotic
    My tricks hyperbolic, my arcs parabolic

    I'm alcoholic, but I can't stand the taste of beer
    'Cause I'm an emotional wreck from Georgia Tech, and I can't even engineer

    [Verse 4]
    You cannot do me on a God Rhythm
    All natural, like logarithm
    I'm under her curves, like a Riemann's sum
    I paint her white with my se-

    No, we can't say that! What if kids who are just interested in maths would listen to that? Let's redo the entire verse.

    She be goin' down and up like a sin wave
    Making my body go convex to concave
    I've got so many mathematical theories
    The only Taylor I like is the series

    I don't know anything about topology
    I hope I taught you some math and biology
    The birds, the bees and some binary trees
    I don't care how bad this is, no apologies

    A product's the only time I like the cross
    She can transform me again like Laplas
    I got women pouirn', endless like MacLaurin
    When it comes to scoring and math, I'm the boss
     */
    private final double[][] matrix;
    private final int n;
    private final double[] x;
    private final double[] y;
    private double[] derivatives;
    public Splines (int n, double[] x, double[] y) {
        this.n = n;
        this.x = x;
        this.y = y;
        matrix = new double[n][n + 1];
    }
    private void matrixFilling() {
        for (int i = 0; i < n; i++) {
            double h, hi;
            double[] currentRow = new double[n + 1];
            if (i == 0) {
                h = x[1] - x[0];
                currentRow[0] = - 4 / h;
                currentRow[1] = - 2 / h;
                currentRow[n] = - 6 * (y[1] - y[0]) / Math.pow(h, 2);
            } else if (i == n - 1) {
                h = x[n - 1] - x[n - 2];
                currentRow[n - 2] = 2 / h;
                currentRow[n - 1] = 4 / h;
                currentRow[n] = 6 * (y[n - 1] - y[n - 2]) / Math.pow(h, 2);
            } else {
                h = x[i] - x[i - 1]; // 0.5
                hi = x[i + 1] - x[i]; // 6
                currentRow[i - 1] = 1/h; // 2
                currentRow[i] = 2 * (1/h + 1/hi); //
                currentRow[i + 1] = 1/hi;
                currentRow[n] = 3 * ((y[i] - y[i - 1]) / Math.pow(h, 2) + (y[i + 1] - y[i]) / Math.pow(hi, 2));
            }
            System.arraycopy(currentRow, 0, matrix[i], 0, matrix[i].length);
        }
        RunThrough runThrough = new RunThrough(n, matrix);
        runThrough.eval();
        derivatives = new double[runThrough.result.length];
        System.arraycopy(runThrough.result, 0, derivatives, 0, derivatives.length);
    }
    public double eval(double x) {
        matrixFilling();
        double first, second, third, fourth;
        int im1 = -1, i1 = -1;
        for (int i = 0; i < n; i++) {
            if (this.x[i] == x) {
                return y[i];
            } else if (this.x[i] > x && i > 0) {
                i1 = i;
                im1 = i - 1;
                break;
            }
        }
        if (im1 == -1) {
            return Double.NaN;
        } else {
            double h = this.x[i1] - this.x[im1];
            first = y[im1] * (Math.pow(x - this.x[i1], 2) * (2 * (x - this.x[im1]) + h)) / Math.pow(h, 3);
            second = derivatives[im1] * (Math.pow(x - this.x[i1], 2) * (x - this.x[im1])) / Math.pow(h, 2);
            third = y[i1] * (Math.pow(x - this.x[i1], 2) * (2 * (this.x[i1] - x) + h)) / Math.pow(h, 3);
            fourth = derivatives[i1] * (Math.pow(x - this.x[im1], 2) * (x - this.x[i1])) / Math.pow(h, 2);
        }
        return first + second + third + fourth;
    }
}
