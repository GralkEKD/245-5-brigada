package SAODSAPR.lab2;

import java.util.Arrays;

public class Variant6 {

    private final Number[][] matrix;

    public Variant6(Number[] AL, int[] I, int[] J) {
        this.matrix = initializeMatrix(I, J);
        fillMatrix(AL, I, J);
    }

    private Number[][] initializeMatrix(int[] I, int[] J) {
        int max1 = Arrays.stream(I).max().orElseThrow();
        int max2 = Arrays.stream(J).max().orElseThrow();
        return max1 > max2 ? new Number[max1][max1] : new Number[max2][max2];
    }

    private void fillMatrix(Number[] AL, int[] I, int[] J) {
        for (int i = 0; i < AL.length; i++) {
            matrix[I[i] - 1][J[i] - 1] = AL[i];
        }
    }

    public Number[][] getMatrix() {
        return matrix;
    }
}
