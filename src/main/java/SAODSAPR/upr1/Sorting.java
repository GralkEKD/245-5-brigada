package SAODSAPR.upr1;

import java.util.Comparator;

public class Sorting {

    public static int compCount;

    public static int swapCount;

    private Sorting() {}

    private static <T extends Comparable<T>> void swap(T[] array, final int index1, final int index2) {
        T aux = array[index1];
        array[index1] = array[index2];
        array[index2] = aux;
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        bubbleSort(array, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] array, Comparator<T> order) {
        int length = array.length;
        int sortCount = 0, compCount = 0, swapCount = 0;
        while (length != 1 || sortCount != 0) {
            sortCount = 0;
            for (int i = 0; i < length - 1; i++) {
                compCount++;
                if (order.compare(array[i], array[i + 1]) > 0) {
                    swapCount++;
                    sortCount++;
                    swap(array, i, i + 1);
                }
            }
            if (length != 1) length--;
        }
        Sorting.compCount = compCount;
        Sorting.swapCount = swapCount;
    }
}
