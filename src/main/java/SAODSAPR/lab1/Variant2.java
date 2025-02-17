package SAODSAPR.lab1;

public class Variant2 {

    private final Number[] AL;

    private final Integer[] J;

    public Variant2(Number[][] matrix) {
        int n = countSize(matrix);
        AL = new Number[n];
        J = new Integer[n];
        packMatrix(matrix);
    }

    private void packMatrix(Number[][] matrix) {
        int pointer = 0;
        for (int i = 0; i < matrix.length; i++) {
            AL[pointer] = 0;
            J[pointer++] = i + 1;
            for (int j = 0; j < matrix.length; j++) {
                if (!matrix[i][j].equals(0)) {
                    AL[pointer] = matrix[i][j];
                    J[pointer++] = j + 1;
                }
            }
        }
        AL[pointer] = 0;
        J[pointer] = 0;
    }

    private int countSize(Number[][] matrix) {
        int size = 1;
        for (Number[] numbers : matrix) {
            size++;
            for (Number number : numbers) {
                if (!number.equals(0)) {
                    size++;
                }
            }
        }
        return size;
    }

    public Number[] getAL() {
        return AL;
    }

    public Integer[] getJ() {
        return J;
    }
}
