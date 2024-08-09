package study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class SortingTest {

    private final Double[] sortedDoublesArray1 = {-1.4, -0.3, 0.0, 0.1, 0.2, 0.7, 1.9, 3.1, 5.5, 5.5, 6.8, 10.3};
    private final Double[] sortedDoublesArray2 = {-1.2, -0.8, 0.0, 1.2, 2.4};
    private final Double[] descendingSortedDoublesArray2 = {2.4, 1.2, 0.0, -0.8, -1.2};
    private final Double[] sortedDoublesArray3 = {-10_000_000_000_000.2, 20_000_000_000_000.9};
    private final Integer[] sortedIntegersArray1 = {-8, -4, -1, 0, 0, 0, 4, 9, 15, 29};
    private final Integer[] sortedIntegersArray2 = {-10, -7, 0, 3, 5};
    private final Integer[] sortedIntegersArray3 = {-2_100_000_000, 2_100_000_000};
    private final String[] sortedStringsArray1 = {
            "And you'll get what you deserve, with the witches that you serve",
            "Beg me for mercy, admit you were toxic",
            "Clap along if you feel, that the room without a roof",
            "Don't get too close, it's dark inside",
            "Everybody's looking for something",
            "I walk the lonely road, The only one that I have ever known",
            "The end is where we begin"
    };
    private final String[] sortedStringsArray2 = {"AAAA", "AAAa", "Aaaa", "BBBB", "BBBb"};

    @Test
    public void selectionSortForDoubles1() {
        Double[] array = {0.0, 0.7, 0.2, 0.1, 10.3, 6.8, 5.5, -1.4, -0.3, 3.1, 1.9, 5.5};
        Sorting.selectionSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray1, array);
    }

    @Test
    public void selectionSortForDoubles2() {
        Double[] array = new Double[]{2.4, 1.2, 0.0, -0.8, -1.2};
        Sorting.selectionSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray2, array);
    }

    @Test
    public void selectionSortForDoubles3() {
        Double[] array = new Double[]{20_000_000_000_000.9, -10_000_000_000_000.2};
        Sorting.selectionSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray3, array);
    }

    @Test
    public void selectionSortForIntegers1() {
        Integer[] array = {0, 9, 15, -1, 29, 0, -4, 0, -8, 4};
        Sorting.selectionSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray1, array);
    }

    @Test
    public void selectionSortForIntegers2() {
        Integer[] array = {5, 3, 0, -7, -10};
        Sorting.selectionSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray2, array);
    }

    @Test
    public void selectionSortForIntegers3() {
        Integer[] array = {2_100_000_000, -2_100_000_000};
        Sorting.selectionSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray3, array);
    }

    @Test
    public void bubbleSortForDoubles1() {
        Double[] array = {0.0, 0.7, 0.2, 0.1, 10.3, 6.8, 5.5, -1.4, -0.3, 3.1, 1.9, 5.5};
        Sorting.bubbleSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray1, array);
    }

    @Test
    public void bubbleSortForDoubles2() {
        Double[] array = new Double[]{2.4, 1.2, 0.0, -0.8, -1.2};
        Sorting.bubbleSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray2, array);
    }

    @Test
    public void bubbleSortForDoubles2Descending() {
        Double[] array = new Double[]{-1.2, -0.8, 0.0, 1.2, 2.4};
        Sorting.bubbleSort(array, Comparator.reverseOrder());
        Assertions.assertArrayEquals(descendingSortedDoublesArray2, array);
    }

    @Test
    public void bubbleSortForDoubles3() {
        Double[] array = new Double[]{20_000_000_000_000.9, -10_000_000_000_000.2};
        Sorting.bubbleSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray3, array);
    }

    @Test
    public void bubbleSortForIntegers1() {
        Integer[] array = {0, 9, 15, -1, 29, 0, -4, 0, -8, 4};
        Sorting.bubbleSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray1, array);
    }

    @Test
    public void bubbleSortForIntegers2() {
        Integer[] array = {5, 3, 0, -7, -10};
        Sorting.bubbleSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray2, array);
    }

    @Test
    public void bubbleSortForIntegers3() {
        Integer[] array = {2_100_000_000, -2_100_000_000};
        Sorting.bubbleSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray3, array);
    }

    @Test
    public void doubleSelectionSortForDoubles1() {
        Double[] array = {0.0, 0.7, 0.2, 0.1, 10.3, 6.8, 5.5, -1.4, -0.3, 3.1, 1.9, 5.5};
        Sorting.doubleSelectionSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray1, array);
    }

    @Test
    public void doubleSelectionSortForDoubles2() {
        Double[] array = new Double[]{2.4, 1.2, 0.0, -0.8, -1.2};
        Sorting.doubleSelectionSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray2, array);
    }

    @Test
    public void doubleSelectionSortForDoubles2_edgeCase() {
        Double[] array = {2.4, 1.2, 0.0, -1.2, -0.8};
        Sorting.doubleSelectionSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray2, array);
    }

    @Test
    public void doubleSelectionSortForDoubles3() {
        Double[] array = new Double[]{20_000_000_000_000.9, -10_000_000_000_000.2};
        Sorting.doubleSelectionSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray3, array);
    }

    @Test
    public void doubleSelectionSortForIntegers1() {
        Integer[] array = {0, 9, 15, -1, 29, 0, -4, 0, -8, 4};
        Sorting.doubleSelectionSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray1, array);
    }

    @Test
    public void doubleSelectionSortForIntegers2() {
        Integer[] array = {5, 3, 0, -7, -10};
        Sorting.doubleSelectionSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray2, array);
    }

    @Test
    public void doubleSelectionSortForIntegers3() {
        Integer[] array = {2_100_000_000, -2_100_000_000};
        Sorting.doubleSelectionSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray3, array);
    }

    @Test
    public void insertionSortForDoubles1() {
        Double[] array = {0.0, 0.7, 0.2, 0.1, 10.3, 6.8, 5.5, -1.4, -0.3, 3.1, 1.9, 5.5};
        Sorting.shakerSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray1, array);
    }

    @Test
    public void insertionSortForDoubles2() {
        Double[] array = new Double[]{2.4, 1.2, 0.0, -0.8, -1.2};
        Sorting.insertionSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray2, array);
    }

    @Test
    public void insertionSortForDoubles2Descending() {
        Double[] array = new Double[]{-1.2, -0.8, 0.0, 1.2, 2.4};
        Sorting.insertionSort(array, Comparator.reverseOrder());
        Assertions.assertArrayEquals(descendingSortedDoublesArray2, array);
    }

    @Test
    public void insertionSortForDoubles3() {
        Double[] array = new Double[]{20_000_000_000_000.9, -10_000_000_000_000.2};
        Sorting.insertionSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray3, array);
    }

    @Test
    public void insertionSortForIntegers1() {
        Integer[] array = {0, 9, 15, -1, 29, 0, -4, 0, -8, 4};
        Sorting.insertionSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray1, array);
    }

    @Test
    public void insertionSortForIntegers2() {
        Integer[] array = {5, 3, 0, -7, -10};
        Sorting.insertionSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray2, array);
    }

    @Test
    public void insertionSortForIntegers3() {
        Integer[] array = {2_100_000_000, -2_100_000_000};
        Sorting.insertionSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray3, array);
    }

    @Test
    public void mergeSortForDoubles1() {
        Double[] array = {0.0, 0.7, 0.2, 0.1, 10.3, 6.8, 5.5, -1.4, -0.3, 3.1, 1.9, 5.5};
        Sorting.mergeSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray1, array);
    }

    @Test
    public void mergeSortForDoubles2() {
        Double[] array = new Double[]{2.4, 1.2, 0.0, -0.8, -1.2};
        Sorting.mergeSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray2, array);
    }

    @Test
    public void mergeSortForDoubles2Descending() {
        Double[] array = new Double[]{-1.2, -0.8, 0.0, 1.2, 2.4};
        Sorting.mergeSort(array, Comparator.reverseOrder());
        Assertions.assertArrayEquals(descendingSortedDoublesArray2, array);
    }

    @Test
    public void mergeSortForDoubles3() {
        Double[] array = new Double[]{20_000_000_000_000.9, -10_000_000_000_000.2};
        Sorting.mergeSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray3, array);
    }

    @Test
    public void mergeSortForIntegers1() {
        Integer[] array = {0, 9, 15, -1, 29, 0, -4, 0, -8, 4};
        Sorting.mergeSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray1, array);
    }

    @Test
    public void mergeSortForIntegers2() {
        Integer[] array = {5, 3, 0, -7, -10};
        Sorting.mergeSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray2, array);
    }

    @Test
    public void mergeSortForIntegers3() {
        Integer[] array = {2_100_000_000, -2_100_000_000};
        Sorting.mergeSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray3, array);
    }

    @Test
    public void quickSortForDoubles1() {
        Double[] array = {0.0, 0.7, 0.2, 0.1, 10.3, 6.8, 5.5, -1.4, -0.3, 3.1, 1.9, 5.5};
        Sorting.quickSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray1, array);
    }

    @Test
    public void quickSortForDoubles2() {
        Double[] array = new Double[]{2.4, 1.2, 0.0, -0.8, -1.2};
        Sorting.quickSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray2, array);
    }

    @Test
    public void quickSortForDoubles3() {
        Double[] array = new Double[]{20_000_000_000_000.9, -10_000_000_000_000.2};
        Sorting.quickSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray3, array);
    }

    @Test
    public void quickSortForIntegers1() {
        Integer[] array = {0, 9, 15, -1, 29, 0, -4, 0, -8, 4};
        Sorting.quickSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray1, array);
    }

    @Test
    public void quickSortForIntegers2() {
        Integer[] array = {5, 3, 0, -7, -10};
        Sorting.quickSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray2, array);
    }

    @Test
    public void quickSortForIntegers3() {
        Integer[] array = {2_100_000_000, -2_100_000_000};
        Sorting.quickSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray3, array);
    }

    @Test
    public void shakerSortForDoubles1() {
        Double[] array = {0.0, 0.7, 0.2, 0.1, 10.3, 6.8, 5.5, -1.4, -0.3, 3.1, 1.9, 5.5};
        Sorting.shakerSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray1, array);
    }

    @Test
    public void shakerSortForDoubles2() {
        Double[] array = new Double[]{2.4, 1.2, 0.0, -0.8, -1.2};
        Sorting.shakerSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray2, array);
    }

    @Test
    public void quickSortForDoubles2Descending() {
        Double[] array = new Double[]{-1.2, -0.8, 0.0, 1.2, 2.4};
        Sorting.quickSort(array, Comparator.reverseOrder());
        Assertions.assertArrayEquals(descendingSortedDoublesArray2, array);
    }

    @Test
    public void shakerSortForDoubles3() {
        Double[] array = new Double[]{20_000_000_000_000.9, -10_000_000_000_000.2};
        Sorting.shakerSort(array);
        Assertions.assertArrayEquals(sortedDoublesArray3, array);
    }

    @Test
    public void shakerSortForIntegers1() {
        Integer[] array = {0, 9, 15, -1, 29, 0, -4, 0, -8, 4};
        Sorting.shakerSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray1, array);
    }

    @Test
    public void shakerSortForIntegers2() {
        Integer[] array = {5, 3, 0, -7, -10};
        Sorting.shakerSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray2, array);
    }

    @Test
    public void shakerSortForIntegers3() {
        Integer[] array = {2_100_000_000, -2_100_000_000};
        Sorting.shakerSort(array);
        Assertions.assertArrayEquals(sortedIntegersArray3, array);
    }

    @Test
    public void randomArrayInsertionSort() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            Integer[] sortedArray = new Integer[100]; // Array to be sorted with stdlib algorithm
            Integer[] unsortedArray; // Array to be sorted with my algorithm
            random.ints(100)
                    .boxed()
                    .toList()
                    .toArray(sortedArray);
            unsortedArray = sortedArray.clone();
            Arrays.stream(sortedArray)
                    .sorted()
                    .toList()
                    .toArray(sortedArray);
            Sorting.insertionSort(unsortedArray);
            Assertions.assertArrayEquals(sortedArray, unsortedArray);
        }
    }

    @Test
    public void randomArraySelectionSort() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            Integer[] sortedArray = new Integer[100]; // Array to be sorted with stdlib algorithm
            Integer[] unsortedArray; // Array to be sorted with my algorithm
            random.ints(100)
                    .boxed()
                    .toList()
                    .toArray(sortedArray);
            unsortedArray = sortedArray.clone();
            Arrays.stream(sortedArray)
                    .sorted()
                    .toList()
                    .toArray(sortedArray);
            Sorting.selectionSort(unsortedArray);
            Assertions.assertArrayEquals(sortedArray, unsortedArray);
        }
    }

    @Test
    public void randomArrayDoubleSelectionSort() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            Integer[] sortedArray = new Integer[100]; // Array to be sorted with stdlib algorithm
            Integer[] unsortedArray; // Array to be sorted with my algorithm
            random.ints(100)
                    .boxed()
                    .toList()
                    .toArray(sortedArray);
            unsortedArray = sortedArray.clone();
            Arrays.stream(sortedArray)
                    .sorted()
                    .toList()
                    .toArray(sortedArray);
            Sorting.doubleSelectionSort(unsortedArray);
            Assertions.assertArrayEquals(sortedArray, unsortedArray);
        }
    }

    @Test
    public void randomArrayBubbleSort() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            Integer[] sortedArray = new Integer[100]; // Array to be sorted with stdlib algorithm
            Integer[] unsortedArray; // Array to be sorted with my algorithm
            random.ints(100)
                    .boxed()
                    .toList()
                    .toArray(sortedArray);
            unsortedArray = sortedArray.clone();
            Arrays.stream(sortedArray)
                    .sorted()
                    .toList()
                    .toArray(sortedArray);
            Sorting.bubbleSort(unsortedArray);
            Assertions.assertArrayEquals(sortedArray, unsortedArray);
        }
    }

    @Test
    public void randomArrayShakerSort() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            Integer[] sortedArray = new Integer[100]; // Array to be sorted with stdlib algorithm
            Integer[] unsortedArray; // Array to be sorted with my algorithm
            random.ints(100)
                    .boxed()
                    .toList()
                    .toArray(sortedArray);
            unsortedArray = sortedArray.clone();
            Arrays.stream(sortedArray)
                    .sorted()
                    .toList()
                    .toArray(sortedArray);
            Sorting.shakerSort(unsortedArray);
            Assertions.assertArrayEquals(sortedArray, unsortedArray);
        }
    }

    @Test
    public void randomArrayMergeSort() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            Integer[] sortedArray = new Integer[100]; // Array to be sorted with stdlib algorithm
            Integer[] unsortedArray; // Array to be sorted with my algorithm
            random.ints(100)
                    .boxed()
                    .toList()
                    .toArray(sortedArray);
            unsortedArray = sortedArray.clone();
            Arrays.stream(sortedArray)
                    .sorted()
                    .toList()
                    .toArray(sortedArray);
            Sorting.mergeSort(unsortedArray);
            Assertions.assertArrayEquals(sortedArray, unsortedArray);
        }
    }

    @Test
    public void randomArrayQuickSort() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            Integer[] sortedArray = new Integer[100]; // Array to be sorted with stdlib algorithm
            Integer[] unsortedArray; // Array to be sorted with my algorithm
            random.ints(100)
                    .boxed()
                    .toList()
                    .toArray(sortedArray);
            unsortedArray = sortedArray.clone();
            Arrays.stream(sortedArray)
                    .sorted()
                    .toList()
                    .toArray(sortedArray);
            Sorting.quickSort(unsortedArray);
            Assertions.assertArrayEquals(sortedArray, unsortedArray);
        }
    }
}
