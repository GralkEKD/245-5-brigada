package SAODSAPR.lab2;

import java.util.Arrays;

public class Variant1 {

    private final Number[][] matrix;

    public Variant1(int[][] TA, Number[][] TC) {
        this.matrix = initializeMatrix(TC.length);
        fillMatrix(TA, TC);
    }

    private Number[][] initializeMatrix(int size) {
        return new Number[size][size];
    }

    private void fillMatrix(int[][] TA, Number[][] TC) {
        for (int i = 0; i < TA.length; i++) {
            for (int j = 0; j < TA[i].length; j++) {
                int index = TA[i][j] - 1;
                if (index == -1) break;
                matrix[i][index] = TC[i][j];
            }
        }
    }

    public Number[][] getMatrix() { return matrix; }
}
