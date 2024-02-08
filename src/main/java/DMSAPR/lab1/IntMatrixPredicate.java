package DMSAPR.lab1;

@FunctionalInterface
public interface IntMatrixPredicate {
    boolean test(int[][] array, int rowIndex, int colIndex);
}
