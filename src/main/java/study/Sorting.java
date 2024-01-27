package study;

/**
 * So, I decided to write some classes with interesting algorithms I come across, and as a first step this class was
 * created. It simply contains every sorting algorithm I could've found. At this point, there are selection sort,
 * insertion sort, bubble sort and merge sort. More algorithms are to be presented as I perfect my coding and
 * algorithms understanding skills, hopefully.
 */
public class Sorting {
    /**
     * Each caster method casts one of provided primitive arrays into an array of double
     * @param array an initial array of a given primitive
     * @return the array of double
     */

    private static double[] caster(int[] array) {
        double[] newArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {newArray[i] = array[i];}
        return newArray;
    }

    private static double[] caster(long[] array) {
        double[] newArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {newArray[i] = array[i];}
        return newArray;
    }

    private static double[] caster(float[] array) {
        double[] newArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {newArray[i] = array[i];}
        return newArray;
    }

    /**
     * Selection sort is a simple sorting algorithm that cycles through the array with two loops. An element with the
     * index of i (the outer loop's parameter) is replaced with the lowest of all elements in the range of [i+1; n],
     * where n = array.length. The iteration is repeated until i becomes an index of the second from the end array's
     * element. The method is overloaded, so it can accept arrays of integers, longs and floats. Its time complexity
     * is O(n^2) in average where n - array's length.
     * @param array an array to be sorted
     */

    public static void selectionSort(int[] array) {
        selectionSort(caster(array));
    }

    public static void selectionSort(long[] array) {
        selectionSort(caster(array));
    }

    public static void selectionSort(float[] array) {
        selectionSort(caster(array));
    }

    public static void selectionSort(double[] array) {
        double aux;
        for (int i = 0; i < array.length - 1; i++) {
            double min = array[i];
            int minIndex = i;
            aux = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            array[i] = min;
            array[minIndex] = aux;
        }
    }

    /**
     * Insertion sort
     * @param array
     */
    public static void insertionSort(double[] array) {
    }

    /**
     * Bubble sort is a simple sorting algorithm that cycles through the array with two loops. The inner loop is going
     * through each element from the beginning to a certain point (which is decrements each outer loop's round)
     * switching two neighboring elements if the next element is bigger than the current one. The algorithm stops when
     * at a certain step no elements switching was made. This algorithm is stable which means that it preserves
     * the initial order of equal elements. Its time complexity is O(n^2) in average where n - array's length.
     * @param array an array to be sorted
     */

    public static void bubbleSort(int[] array) {
        bubbleSort(caster(array));
    }

    public static void bubbleSort(long[] array) {
        bubbleSort(caster(array));
    }

    public static void bubbleSort(float[] array) {
        bubbleSort(caster(array));
    }

    public static void bubbleSort(double[] array) {
        int length = array.length;
        int sortCount = 0;
        while ((length != 1) || (sortCount != 0)) {
            sortCount = 0;
            for (int i = 0; i < length - 1; i++) {
                double aux;
                if (array[i] > array[i + 1]) {
                    sortCount++;
                    aux = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = aux;
                }
            }
            if (length != 1) length--;
        }
    }

    public static void mergeSort(int[] array) {
        mergeSort(caster(array));
    }

    public static void mergeSort(float[] array) {
        mergeSort(caster(array));
    }

    public static void mergeSort(long[] array) {
        mergeSort(caster(array));
    }

    public static void mergeSort(double[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public static void mergeSort(double[] array, int start, int end) {
        if (end - start == 1) {
            if (array[start] > array[end]) {
                double aux = array[start];
                array[start] = array[end];
                array[end] = aux;
            }
        } else if (start != end) {
            int endOfFirst = Math.floorDivExact(start + end, 2);
            int startOfSecond = Math.ceilDivExact(start + end, 2);
            if (startOfSecond == endOfFirst) startOfSecond++;
            mergeSort(array, start, endOfFirst);
            mergeSort(array, startOfSecond, end);
            double[] auxiliaryArray = new double[end - start + 1];
            int pointer1 = start;
            int pointer2 = startOfSecond;
            int auxPointer = 0;
            while (pointer1 <= endOfFirst || pointer2 <= end) {
                if (pointer1 <= endOfFirst && (pointer2 > end || array[pointer1] < array[pointer2])) {
                    auxiliaryArray[auxPointer++] = array[pointer1++];
                } else {
                    auxiliaryArray[auxPointer++] = array[pointer2++];
                }
            }
            System.arraycopy(auxiliaryArray, 0, array, start, auxiliaryArray.length);
        }
    }

}
