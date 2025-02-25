package SAODSAPR.upr1;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        final Integer[] array1 = new Integer[10];
        final Integer[] array2 = new Integer[20];
        final Integer[] array3 = new Integer[30];

        long seed;
        try (BufferedReader reader = Files.newBufferedReader(
                Path.of("src", "main", "java", "SAODSAPR", "upr1", "seed.txt")
        )) {
            BigInteger integer = new BigInteger(reader.readLine(), 16);
            seed = integer.longValue();
        }

        Random random = new Random(seed);
        for (int i = 0; i < 60; i++) {
            if (i < 10) array1[i] = random.nextInt(-100, 100);
            else if (i < 30) array2[i - 10] = random.nextInt(-100, 100);
            else array3[i - 30] = random.nextInt(-100, 100);
        }

        System.out.println("Неотсортированные массивы:");
        System.out.println(Arrays.toString(array1) + '\n' + Arrays.toString(array2) + '\n' + Arrays.toString(array3));
        System.out.println("Отсортированные массивы:");
        Sorting.bubbleSort(array1);
        System.out.println(Arrays.toString(array1) + '\n' +
                "Количество сравнений: " + Sorting.compCount +
                ", Количество перестановок: " + Sorting.swapCount);
        Sorting.bubbleSort(array2);
        System.out.println(Arrays.toString(array2) + '\n' +
                "Количество сравнений: " + Sorting.compCount +
                ", Количество перестановок: " + Sorting.swapCount);
        Sorting.bubbleSort(array3);
        System.out.println(Arrays.toString(array3) + '\n' +
                "Количество сравнений: " + Sorting.compCount +
                ", Количество перестановок: " + Sorting.swapCount);
    }
}
