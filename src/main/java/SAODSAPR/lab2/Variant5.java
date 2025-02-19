package SAODSAPR.lab2;

public class Variant5 {

    private final Number[][] matrix;

    public Variant5(Number[] AL, int[] I, int[] JC) {
        this.matrix = initializeMatrix(JC);
        fillMatrix(AL, I, JC);
    }

    private Number[][] initializeMatrix(int[] JC) {
        int size = JC.length - 1;
        return new Number[size][size];
    }

    private void fillMatrix(Number[] AL, int[] I, int[] JC) {
        int column = 0;
        for (int i = 0; i < JC.length - 1; i++) {
            for (int j = JC[i] - 1; j < JC[i + 1] - 1; j++) {
                matrix[I[j] - 1][column] = AL[j];
            }
            column++;
        }
    }

    public Number[][] getMatrix() {
        return matrix;
    }
}

