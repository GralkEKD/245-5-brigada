package SAODSAPR.lab2;

import java.util.Arrays;

public class Variant7 {

    private final Number[][] matrix;
    private final int size;

    public Variant7(Number[] AL, int[] K) {
        this.matrix = initializeMatrix(K);
        this.size = this.matrix.length;
        fillMatrix(AL, K);
    }

    private Number[][] initializeMatrix(int[] K) {
        int size = (int) Math.ceil(Math.sqrt(Arrays.stream(K).max().orElseThrow()));
        return new Number[size][size];
    }

    private void fillMatrix(Number[] AL, int[] K) {
        for (int k = 0; k < AL.length; k++) {
            int j = Math.floorDiv(K[k], size);
            int i = K[k] - 1 - j * size;
            matrix[i][j] = AL[k];
        }
    }

    public Number[][] getMatrix() {
        return matrix;
    }
}
