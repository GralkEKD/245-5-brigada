package SAODSAPR.upr1;

import java.util.*;

public class Sorting {

    private static final double DECREASE_FACTOR = 1.247;

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
        int sortCount = 0;
        compCount = 0; swapCount = 0;
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
    }

    private static <T extends Comparable<T>> int insert(T[] array, final int elementIndex, final int insertionIndex) {
        T temp = array[elementIndex];
        int assignCount = 1;
        for (int i = elementIndex; i > insertionIndex; i--) {
            array[i] = array[i - 1];
            assignCount++;
        }
        array[insertionIndex] = temp;
        assignCount++;
        return assignCount;
    }

    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        insertionSort(array, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> void insertionSort(T[] array, Comparator<T> order) {
        compCount = 0; swapCount = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                compCount++;
                if (order.compare(array[i], array[j]) < 0) {
                    swapCount += (insert(array, i, j)) / 3;
                    break;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        selectionSort(array, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> void selectionSort(T[] array, Comparator<T> order) {
        compCount = 0; swapCount = 0;
        for (int i = 0; i < array.length - 1; i++) {
            T min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                compCount++;
                if (order.compare(array[j], min) < 0) {
                    min = array[j];
                    minIndex = j;
                }
            }
            swapCount++;
            swap(array, i, minIndex);
        }
    }

    public static <T extends Comparable<T>> void shakerSort(T[] array) {
        shakerSort(array, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> void shakerSort(T[] array, Comparator<T> order) {
        compCount = 0; swapCount = 0;
        int start = 0, end = array.length - 1, sortCountForward = 0, sortCountBackward = 0;
        while (start != end || sortCountForward != 0 && sortCountBackward != 0) {
            sortCountForward = 0;
            sortCountBackward = 0;
            for (int i = start; i <= end - 1; i++) {
                compCount++;
                if (order.compare(array[i], array[i + 1]) > 0) {
                    swapCount++;
                    sortCountForward++;
                    swap(array, i, i + 1);
                }
            }
            if (start != end) end--;
            for (int i = end; i >= start + 1; i--) {
                compCount++;
                if (order.compare(array[i], array[i - 1]) <= 0) {
                    swapCount++;
                    sortCountBackward++;
                    swap(array, i, i - 1);
                }
            }
            if (start != end) start++;
        }
    }

    public static <T extends Comparable<T>> void combSort(T[] array) {
        combSort(array, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> void combSort(T[] array, Comparator<T> order) {
        int length = array.length;
        compCount = 0; swapCount = 0;
        int step = (int) Math.floor(array.length / DECREASE_FACTOR);
        while (step != 0) {
            for (int i = 0; i < length - step; i++) {
                compCount++;
                if (order.compare(array[i], array[i + step]) > 0) {
                    swapCount++;
                    swap(array, i, i + step);
                }
            }
            step = (int) Math.floor(step / DECREASE_FACTOR);
        }
    }

    public static <T extends Comparable<T>> void gnomeSort(T[] array) {
        gnomeSort(array, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> void gnomeSort(T[] array, Comparator<T> order) {
        int i = 0;
        int k = 0;
        swapCount = 0; compCount = 0;
        while (i < array.length - 1) {
            compCount++;
            if (order.compare(array[i], array[i + 1]) > 0) {
                if (i >= k) k = i + 1;
                swapCount++;
                swap(array, i, i + 1);
                if (i != 0) i--;
            } else {
                i = (i < k ? k : i + 1);
            }
        }
    }

    public static <T extends Comparable<T>> void compCountSort(T[] array) {
        compCountSort(array, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> void compCountSort(T[] array, Comparator<T> order) {
        compCount = 0; swapCount = 0;

        int[] counterArray = new int[array.length];
        ArrayList<T> sorted = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            sorted.add(null);
        }
        var sortedArray = sorted.toArray();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                compCount++;
                if (i != j && order.compare(array[j], array[i]) < 0) {
                    counterArray[i]++;
                }
            }
        }

        for (int i = 0; i < array.length; i++) {
            while (!Objects.isNull(sortedArray[counterArray[i]])) counterArray[i]++;
            sortedArray[counterArray[i]] = array[i];
        }

        System.arraycopy(sortedArray, 0, array, 0, array.length);
    }

    public static <T> void dispersionCountSort(T[] array, T[] values) {
        compCount = 0; swapCount = 0;
        int[] F = new int[values.length];
        int[] D = new int[values.length];
        ArrayList<T> sorted = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            sorted.add(null);
        }
        var sortedArray = sorted.toArray();
        for (T t : array) {
            for (int j = 0; j < values.length; j++) {
                compCount++;
                if (t.equals(values[j])) {
                    F[j]++;
                    break;
                }
            }
        }
        D[0] = F[0];
        for (int i = 1; i < F.length; i++) {
            D[i] = D[i - 1] + F[i];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < D.length; j++) {
                compCount++;
                if (array[i].equals(values[j])) {
                    sortedArray[D[j] - 1] = values[j];
                    D[j]--;
                    break;
                }
            }
        }
        System.arraycopy(sortedArray, 0, array, 0, array.length);
    }
}
