package SAODSAPR.lab2;

import java.util.Arrays;

public class Variant2 {

    private final Number[][] matrix;

    public Variant2(Number[] AL, int[] J) {
        this.matrix = initializeMatrix(J);
        fillMatrix(AL, J);
    }

    private Number[][] initializeMatrix(int[] J) {
        int size = Arrays.stream(J).max().orElseThrow();
        return new Number[size][size];
    }

    private void fillMatrix(Number[] AL, int[] J) {
        int j = 0;
        for (int i = 0; i < AL.length; i++) {
            if (AL[i].equals(0)) j = J[i] - 1;
            else matrix[j][J[i] - 1] = AL[i];
        }
    }

    public Number[][] getMatrix() {
        return matrix;
    }
}
