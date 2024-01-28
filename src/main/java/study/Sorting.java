package study;

/**
 * So, I decided to write some classes with interesting algorithms I come across, and as a first step this class was
 * created. It simply contains every sorting algorithm I could've found. At this point, there are selection sort,
 * bubble sort and merge sort. More algorithms are to be presented as I perfect my coding and algorithms understanding
 * skills, hopefully.
 */
public class Sorting {

    /**
     * Swaps two array elements' places. Just a simple element of a program logic which is sometimes repeatedly called.
     * @param array array where the elements need to be swapped
     * @param index1 an index of the first element
     * @param index2 an index of the second element
     */
    private static void swap(double[] array, int index1, int index2) {
        double aux = array[index1];
        array[index1] = array[index2];
        array[index2] = aux;
    }

    private static void arrayToMaxHeap(double[] array) {

    }

    /**
     * Selection sort is a simple sorting algorithm that cycles through the array with two loops. An element with the
     * index of i (the outer loop's parameter) is replaced with the lowest of all elements in the range of [i+1; n],
     * where n = array.length. The iteration is repeated until i becomes an index of the second from the end array's
     * element. The method is overloaded, so it can accept arrays of integers, longs and floats. Its time complexity
     * is O(n^2) in average where n - array's length.
     * @param array an array to be sorted
     */

    public static void selectionSort(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            double min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    /**
     * DEPRECATED: In cases when minimal and/or maximal element's indexes coincide with the outer cycle's iterator
     * an element loss may occur. Totally fixable, but until then one should refrain from using this method.
     * Double selection sort is a modified selection sort algorithm. The main difference is that in the same time
     * the greatest and the smallest elements are being sorted in. Given that, the outer cycle's number of iterations
     * can be effectively reduced by half.
     * @param array an array to be sorted
     */

    @Deprecated
    public static void doubleSelectionSort(double[] array) {
        int lim = Math.ceilDivExact(array.length, 2) - 1;
        for (int i = 0; i <= lim; i++) {
            int endingPosition = array.length - 1 - i, minIndex = i, maxIndex = endingPosition;
            double min = array[i], max = array[endingPosition];
            for (int j = i; j <= endingPosition; j++) {
                if (array[j] > max) {
                    max = array[j];
                    maxIndex = j;
                } else if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            array[minIndex] = array[i];
            array[i] = min;
            array[maxIndex] = array[endingPosition];
            array[endingPosition] = max;
        }
    }

    /**
     * Bubble sort is a simple sorting algorithm that cycles through the array with two loops. The inner loop is going
     * through each element from the beginning to a certain point (which is decrements each outer loop's round)
     * switching two neighboring elements if the next element is bigger than the current one. The algorithm stops when
     * at a certain step no elements switching was made. This algorithm is stable which means that it preserves
     * the initial order of equal elements. Its time complexity is O(n^2) in average where n - array's length.
     * @param array an array to be sorted
     */

    public static void bubbleSort(double[] array) {
        int length = array.length;
        int sortCount = 0;
        while ((length != 1) || (sortCount != 0)) {
            sortCount = 0;
            for (int i = 0; i < length - 1; i++) {
                double aux;
                if (array[i] > array[i + 1]) {
                    sortCount++;
                    swap(array, i, i + 1);
                }
            }
            if (length != 1) length--;
        }
    }

    public static void mergeSort(double[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public static void mergeSort(double[] array, int start, int end) {
        if (end - start == 1) {
            if (array[start] > array[end]) {
                swap(array, start, end);
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

    public static void quickSort(double[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(double[] array, int start, int end) {
        if (end - start == 1) {
            if (array[start] > array[end]) {
                swap(array, start, end);
            }
        } else if (start != end) {
            double pivot = array[start];
            int pointerA = start + 1, pointerB = end;
            while (pointerA != pointerB) {
                while (array[pointerA] <= pivot && pointerA < pointerB) {
                    pointerA++;
                }
                while (array[pointerB] > pivot && pointerA < pointerB) {
                    pointerB--;
                }
                swap(array, pointerA, pointerB);
            }
            swap(array, start, pointerA - 1);
            quickSort(array, start, pointerA - 1);
            quickSort(array, pointerA, end);
        }
    }
}
