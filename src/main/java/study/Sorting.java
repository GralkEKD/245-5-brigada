package study;

import java.util.Arrays;

public class Sorting {
    public static void bubbleSort(double[] array) {
        int length = array.length;
        int sortCount = 0;
        while ((length != 1) || (sortCount != 0)) {
            for (int i = 0; i < length - 1; i++) {
                sortCount = 0;
                double aux;
                if (array[i] > array[i + 1]) {
                    sortCount++;
                    aux = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = aux;
                }
            }
            length--;
        }
    }
    public static void mergeSort(double[] array) {
        mergeSort(array, 0, array.length - 1);
    }
    public static void mergeSort(double[] array, int start, int end) {
        double[] auxiliaryArray = new double[end - start + 1];
        if (end - start == 1) {

        }
    }

    public static void main(String[] args) {
        double[] initialArray = {3.0, 8.3, 2.2, 0.5, -1.7, -6.6, -2.1, 0.0};
        double[] array = new double[initialArray.length];
        System.arraycopy(initialArray, 0, array, 0, initialArray.length);
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
}
