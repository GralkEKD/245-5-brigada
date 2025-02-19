package SAODSAPR.lab2;

public class Variant4 {

    private final Number[][] matrix;

    public Variant4(Number[] AL, int[] J, int[] IC) {
        this.matrix = initializeMatrix(IC);
        fillMatrix(AL, J, IC);
    }

    private Number[][] initializeMatrix(int[] IC) {
        int size = IC.length - 1;
        return new Number[size][size];
    }

    private void fillMatrix(Number[] AL, int[] J, int[] IC) {
        int row = 0;
        for (int i = 0; i < IC.length - 1; i++) {
            for (int j = IC[i] - 1; j < IC[i + 1] - 1; j++) {
                matrix[row][J[j] - 1] = AL[j];
            }
            row++;
        }
    }

    public Number[][] getMatrix() {
        return matrix;
    }
}
