package DMSAPR.function;

@FunctionalInterface
public interface IntMatrixConsumer {
    void apply(int[][] matrix, int rowIndex, int colIndex);
}
