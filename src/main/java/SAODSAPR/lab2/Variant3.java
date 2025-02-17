package SAODSAPR.lab2;

import java.util.Arrays;

public class Variant3 {
    private final Number[][] matrix;

    public Variant3(Number[] AL, int[] I) {
        this.matrix = initializeMatrix(I);
        fillMatrix(AL, I);
    }

    private Number[][] initializeMatrix(int[] I) {
        int size = Arrays.stream(I).max().orElseThrow();
        return new Number[size][size];
    }

    private void fillMatrix(Number[] AL, int[] I) {
        int j = -1;
        for (int i = 0; i < AL.length; i++) {
            if (AL[i].equals(0)) j++;
            else matrix[I[i] - 1][j] = AL[i];
        }
    }

    public Number[][] getMatrix() {
        return matrix;
    }
}
