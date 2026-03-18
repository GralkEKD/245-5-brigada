package SAODSAPR.upr1;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        final Integer[] array1 = new Integer[10];//{-88, -66, -79, -66, -19, -33, -45, 24, 60, 50};
        final Integer[] array2 = new Integer[20];//{-71, -65, -60, -65, -56, -47, -48, -27, -33, -32, -23, -11, 53, 37, 55, 34, 59, 68, 86, 67};
        final Integer[] array3 = new Integer[30];//{-97, -90, -97, -90, -77, -42, -87, -82, -38, -23, -32, 2, -13, -12, 14, 6, 48, 22, 29, 45, 54, 56, 63, 62, 84, 64, 72, 87, 94, 90};

        long seed;
        try (BufferedReader reader = Files.newBufferedReader(
                Path.of("src", "main", "java", "SAODSAPR", "upr1", "seed.txt")
        )) {
            BigInteger integer = new BigInteger(reader.readLine(), 16);
            seed = integer.longValue();
        }

        Random random = new Random(seed);
        for (int i = 0; i < 60; i++) {
            if (i < 10) array1[i] = random.nextInt(7, 9);
            else if (i < 30) array2[i - 10] = random.nextInt(4, 8);
            else array3[i - 30] = random.nextInt(1, 7);
        }

        System.out.println("Неотсортированные массивы:");
        System.out.println(Arrays.toString(array1) + '\n' + Arrays.toString(array2) + '\n' + Arrays.toString(array3));
        System.out.println("Отсортированные массивы:");
//        Sorting.insertionSort(array1, Comparator.reverseOrder());
//        Sorting.insertionSort(array2, Comparator.reverseOrder());
//        Sorting.insertionSort(array3, Comparator.reverseOrder());

        Integer[] k1 = {7, 8};
        Integer[] k2 = {4, 5, 6, 7};
        Integer[] k3 = {1, 2, 3, 4, 5, 6};
        Sorting.dispersionCountSort(array1, k1);
        System.out.println(Arrays.toString(array1) + '\n' +
                "Количество сравнений: " + Sorting.compCount +
                ", Количество перестановок: " + Sorting.swapCount);
        Sorting.dispersionCountSort(array2, k2);
        System.out.println(Arrays.toString(array2) + '\n' +
                "Количество сравнений: " + Sorting.compCount +
                ", Количество перестановок: " + Sorting.swapCount);
        Sorting.dispersionCountSort(array3, k3);
        System.out.println(Arrays.toString(array3) + '\n' +
                "Количество сравнений: " + Sorting.compCount +
                ", Количество перестановок: " + Sorting.swapCount);
    }
}
