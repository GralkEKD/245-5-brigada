package SAODSAPR.lab1;

public class Variant7 {
    private final Number[] AL;

    private final Integer[] K;

    public Variant7(Number[][] matrix) {
        int n = countSize(matrix);
        AL = new Number[n];
        K = new Integer[n];
        packMatrix(matrix);
    }

    private void packMatrix(Number[][] matrix) {
        int pointer = 0;
        int rank = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!matrix[i][j].equals(0)) {
                    AL[pointer] = matrix[i][j];
                    K[pointer++] = i + 1 + j * rank;
                }
            }
        }
    }

    private int countSize(Number[][] matrix) {
        int size = 0;
        for (Number[] numbers : matrix) {
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

    public Integer[] getK() {
        return K;
    }
}
