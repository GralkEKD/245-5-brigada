package DMSAPR.lab1;

/**
 * Функциональный интерфейс, имеющий метод {@code test}, возвращающий {@code boolean}. Позволяет провести проверку
 * элемента матрицы {@code matrix} целых чисел с индексами {@code rowIndex, colIndex} на соответствие некоторому
 * условию.
 */
@FunctionalInterface
public interface IntMatrixPredicate {

    /**
     * Вычисляет предикат для данной матрицы и соответствующих индексов
     * @param matrix матрица целых чисел
     * @param rowIndex индекс строки элементы
     * @param colIndex индекс столбца элемента
     * @return {@code true} или {@code false}
     */
    boolean test(int[][] matrix, int rowIndex, int colIndex);
}
