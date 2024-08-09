package study;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * So, I decided to write some classes with interesting algorithms I come across, and as a first step this class was
 * created. It simply contains every sorting algorithm I could've found. At this point, there are selection sort,
 * bubble sort and merge sort. More algorithms are to be presented as I perfect my coding and algorithms understanding
 * skills, hopefully.
 */
public class Sorting {

    /**
     * DEPRECATED: this {@code swap(...)} method was being used on arrays of primitive doubles. As for now, any array
     * consists of {@code Comparable} type objects
     * <p>
     * Swaps two array elements' places. Just a simple element of a program logic which is sometimes repeatedly called.
     * @param array array where the elements need to be swapped
     * @param index1 an index of the first element
     * @param index2 an index of the second element
     */
    @Deprecated(forRemoval = true)
    private static void swap(double[] array, int index1, int index2) {
        double aux = array[index1];
        array[index1] = array[index2];
        array[index2] = aux;
    }

    /**
     * Swaps two array elements' places. Just a simple element of a program logic which is sometimes repeatedly called.
     * @param array array where the elements need to be swapped
     * @param index1 an index of the first element
     * @param index2 an index of the second element
     * @param <T> object type which is stored on the array
     */
    private static <T extends Comparable<T>> void swap(T[] array, final int index1, final int index2) {
        T aux = array[index1];
        array[index1] = array[index2];
        array[index2] = aux;
    }

    private static <T extends Comparable<T>> void insert(T[] array, final int elementIndex, final int insertionIndex) {
        T temp = array[elementIndex];
        System.arraycopy(array, insertionIndex, array, insertionIndex + 1, elementIndex - insertionIndex);
        array[insertionIndex] = temp;
    }

    private static void heapify(double[] array) {

    }

    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        selectionSort(array, Comparator.naturalOrder());
    }

    /**
     * Selection sort is a simple sorting algorithm that cycles through the array with two loops. An element with the
     * index of {@code i} (the outer loop's parameter) is replaced with the lowest of all elements in the range of
     * {@code [i+1; n]}, where {@code n == array.length - 1}. The iteration is repeated until {@code i} becomes
     * the index of the second from the end array's element, hence, the outer cycle reaches the end of the array.
     * <p>
     * This algorithm's time complexity is O(n^2) in any case where n - array's length.
     * @param array an array to be sorted
     */

    public static <T extends Comparable<T>> void selectionSort(T[] array, Comparator<T> order) {
        for (int i = 0; i < array.length - 1; i++) {
            T min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (order.compare(array[j], min) < 0) {
                    min = array[j];
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    public static <T extends Comparable<T>> void doubleSelectionSort(T[] array) {
        doubleSelectionSort(array, Comparator.naturalOrder());
    }

    /**
     * Double selection sort is a modified selection sort algorithm. The main difference is that in the same time
     * the greatest and the smallest elements are being sorted in the beginning and the end respectfully.
     * Given that, the outer cycle's number of iterations can be effectively reduced by half. However, the overall
     * time-complexity is still estimated as O(n^2).
     * @param array an array to be sorted
     */

    public static <T extends Comparable<T>> void doubleSelectionSort(T[] array, Comparator<T> order) {
        int lim = Math.ceilDivExact(array.length, 2) - 1;
        for (int i = 0; i <= lim; i++) {
            int endingPosition = array.length - 1 - i, minIndex = i, maxIndex = endingPosition;
            T min = array[i], max = array[endingPosition];
            for (int j = i; j <= endingPosition; j++) {
                if (order.compare(array[j], max) > 0) {
                    max = array[j];
                    maxIndex = j;
                }
                if (order.compare(array[j], min) < 0) {
                    min = array[j];
                    minIndex = j;
                }
            }
            swap(array, minIndex, i);
            /* In this case the biggest element in the sequence is swapped with minimal, as the smallest one is always
             * placed in the beginning of the sequence (i-th index)
             */
            if (maxIndex == i) swap(array, minIndex, endingPosition);
            else swap(array, maxIndex, endingPosition);
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        insertionSort(array, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> void insertionSort(T[] array, Comparator<T> order) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (order.compare(array[i], array[j]) < 0) {
                    insert(array, i, j);
                    break;
                }
            }
        }
    }

    /**
     * The overdriven {@code bubbleSort(...)} method call that takes no {@code Comparator} argument and sets the
     * natural order of the sorting as the default order.
     * @param array an array to be sorted
     */

    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        bubbleSort(array, Comparator.naturalOrder());
    }

    /**
     * <p>
     * Bubble sort is a simple sorting algorithm that cycles through the array with two loops. The inner loop is going
     * through each element from the beginning to a certain point (which is decremented each outer loop's cycle)
     * swapping two neighboring elements if the next element is bigger than the current one. The algorithm stops when
     * at a certain step no elements swaps was made. This algorithm is stable which means that it preserves
     * the initial order of equal elements. Its time complexity is O(n^2) in average where n - array's length.
     * </p>
     * <p>
     * This bubble sort implementation takes an additional {@code Comparator} parameter to specify the order of sorted
     * elements
     * </p>
     * @param array an array to be sorted
     * @param order an order in which the array is to be sorted (ascending, descending or any other user's specific)
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] array, Comparator<T> order) {
        int length = array.length;
        int sortCount = 0;
        while (length != 1 || sortCount != 0) {
            sortCount = 0;
            for (int i = 0; i < length - 1; i++) {
                if (order.compare(array[i], array[i + 1]) > 0) {
                    sortCount++;
                    swap(array, i, i + 1);
                }
            }
            if (length != 1) length--;
        }
    }

    public static <T extends Comparable<T>> void shakerSort(T[] array) {
        shakerSort(array, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> void shakerSort(T[] array, Comparator<T> order) {
        int start = 0, end = array.length - 1, sortCountForward = 0, sortCountBackward = 0;
        while (start != end || sortCountForward != 0 && sortCountBackward != 0) {
            sortCountForward = 0;
            sortCountBackward = 0;
            for (int i = start; i <= end - 1; i++) {
                if (order.compare(array[i], array[i + 1]) > 0) {
                    sortCountForward++;
                    swap(array, i, i + 1);
                }
            }
            if (start != end) end--;
            for (int i = end; i >= start + 1; i--) {
                if (order.compare(array[i], array[i - 1]) <= 0) {
                    sortCountBackward++;
                    swap(array, i, i - 1);
                }
            }
            if (start != end) start++;
        }
    }

    /**
     * Merge sort is the "Divide-and-Conquer" sorting algorithm. It's performed as follows:
     * <p>
     *     1. The array is divided on two equal halves
     *     2. Each of these parts are sorted separately
     *     3. The auxiliary array with the same length is allocated
     *     4.
     * </p>
     * @param array
     * @param <T>
     */

    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        mergeSort(array, Comparator.naturalOrder(), 0, array.length - 1);
    }

    public static <T extends Comparable<T>> void mergeSort(T[] array, Comparator<T> order) {
        mergeSort(array, order, 0, array.length - 1);
    }

    public static <T extends Comparable<T>> void mergeSort(T[] array, Comparator<T> order, int start, int end) {
        if (start == end) return;
        if (end - start == 1) {
            if (order.compare(array[start], array[end]) > 0) {
                swap(array, start, end);
            }
        } else {
            int endOfFirst = Math.floorDivExact(start + end, 2);
            int startOfSecond = Math.ceilDivExact(start + end, 2);
            if (startOfSecond == endOfFirst) startOfSecond++;
            if (start != endOfFirst) mergeSort(array, order, start, endOfFirst);
            if (startOfSecond != end) mergeSort(array, order, startOfSecond, end);
            var auxiliaryArrayList = new ArrayList<T>();
            for (int i = 0; i < end - start + 1; i++) {
                auxiliaryArrayList.add(null);
            }
            var auxiliaryArray = auxiliaryArrayList.toArray();
            int pointer1 = start;
            int pointer2 = startOfSecond;
            int auxPointer = 0;
            while (pointer1 <= endOfFirst || pointer2 <= end) {
                if (pointer1 <= endOfFirst && (pointer2 > end || order.compare(array[pointer1], array[pointer2]) < 0)) {
                    auxiliaryArray[auxPointer++] = array[pointer1++];
                } else {
                    auxiliaryArray[auxPointer++] = array[pointer2++];
                }
            }
            System.arraycopy(auxiliaryArray, 0, array, start, auxiliaryArray.length);
        }
    }

    public static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSort(array, Comparator.naturalOrder(), 0, array.length - 1);
    }

    public static <T extends Comparable<T>> void quickSort(T[] array, Comparator<T> order) {
        quickSort(array, order, 0, array.length - 1);
    }

    public static <T extends Comparable<T>> void quickSort(T[] array, Comparator<T> order, int start, int end) {
        if (start == end) return;
        if (end - start == 1) {
            if (order.compare(array[start], array[end]) > 0) {
                swap(array, start, end);
            }
        } else {
            T pivot = array[start];
            int pointerA = start + 1, pointerB = end;
            while (pointerA != pointerB) {
                while (order.compare(array[pointerA], pivot) <= 0 && pointerA < pointerB) {
                    pointerA++;
                }
                while (order.compare(array[pointerB], pivot) > 0 && pointerA < pointerB) {
                    pointerB--;
                }
                if (pointerA != pointerB) swap(array, pointerA, pointerB);
            }
            if (order.compare(array[pointerA], pivot) <= 0) {
                System.arraycopy(array, start + 1, array, start, pointerA - start);
                array[pointerA] = pivot;
            }
            else {
                System.arraycopy(array, start + 1, array, start, pointerA - start);
                array[pointerA - 1] = pivot;
            }
            if (pointerA > start + 1) quickSort(array, order, start, pointerA - 1);
            if (pointerB < end) quickSort(array, order, pointerB, end);
            }
        }
    }

