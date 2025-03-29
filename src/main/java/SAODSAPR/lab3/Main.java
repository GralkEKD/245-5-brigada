package SAODSAPR.lab3;

import study.Timer;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
        final Integer[] array10 = {-45, 24, -79, 19, -66, -33, 60, -88, 50, -66};
        final Integer[] array20 = {-65, -33, -23, 68, 86, -56, 53, -32, -48, -60, 67, -47, 37, -11, 34, 59, -71, -67, 54, -27};
        final Integer[] array30 = {64, 63, 29, -97, 56, 90, 14, 48, 62, -12, -87, -82, 22, -77, -42, 87, 2, -97, 94, 84, -90, -13, -32, 6, 54, -38, -23, 72, 45, -90};

        long seed;
        try (BufferedReader reader = Files.newBufferedReader(
                Path.of("src", "main", "java", "SAODSAPR", "upr1", "seed.txt")
        )) {
            BigInteger readSeed = new BigInteger(reader.readLine(), 16);
            seed = readSeed.longValue();
        }

        final Long[] array1_000_000 = new Long[1_000_000];

        Random random = new Random(seed);

        random.longs(1_000_000, -50_000, 50_000)
                .boxed()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new))
                .toArray(array1_000_000);

        long timeLin = 0;
        long timeBinIter = 0;
        long timeBinRec = 0;
        long timeInterp = 0;
        for (int i = 0; i < array1_000_000.length; i += 1_000) {
            int finalI = i;
            timeLin += Timer.countTimeMillis(
                    () -> Search.linearSearch(array1_000_000, array1_000_000[finalI])
            );
            timeInterp += Timer.countTimeNanos(
                    () -> Search.interpolationSearch(array1_000_000, array1_000_000[finalI])
            );
            timeBinRec += Timer.countTimeNanos(
                    () -> Search.binarySearchRec(array1_000_000, array1_000_000[finalI])
            );
            timeBinIter += Timer.countTimeNanos(
                    () -> Search.binarySearchIter(array1_000_000, array1_000_000[finalI])
            );
        }

        System.out.printf("Среднее время поиска в массиве длиной %d: \n", array1_000_000.length);
        System.out.printf("Линейный поиск: %.3f мс, " +
                        "Бинарный итерационный поиск: %.3f нс, " +
                        "Бинарный рекурсивный поиск: %.3f нс, " +
                        "Интерполяционный поиск: %.3f нс\n",
                (double) timeLin / 1000,
                (double) timeBinIter / 1000,
                (double) timeBinRec / 1000,
                (double) timeInterp / 1000);

//        final Integer[][] arrays = {array10, array20, array30};
//        int compCountLin, compCountBinI, compCountBinR, compCountInt;
        Arrays.sort(array10);
        Arrays.sort(array20);
        Arrays.sort(array30);

//        for (int array = 1; array < 4; array++) {
//            System.out.println("---------------------------------------------------------------------");
//            compCountLin = 0; compCountBinI = 0; compCountBinR = 0; compCountInt = 0;
//            for (int i = 0; i < 10 * array; i += array) {
//                Search.linearSearch(arrays[array - 1], arrays[array - 1][i]);
//                System.out.printf("Лин. поиск %d, количество сравнений: %d\n", i / array, Search.compCount);
//                compCountLin += Search.compCount;
//
//                Search.binarySearchIter(arrays[array - 1], arrays[array - 1][i]);
//                System.out.printf("Бин. итер. поиск %d, количество сравнений: %d\n", i / array, Search.compCount);
//                compCountBinI += Search.compCount;
//
//                Search.binarySearchRec(arrays[array - 1], arrays[array - 1][i]);
//                System.out.printf("Бин. рек. поиск %d, количество сравнений: %d\n", i / array, Search.compCount);
//                compCountBinR += Search.compCount;
//
//                Search.interpolationSearch(arrays[array - 1], arrays[array - 1][i]);
//                System.out.printf("Интерп. поиск %d, количество сравнений: %d\n", i / array, Search.compCount);
//                compCountInt += Search.compCount;
//
//                System.out.println();
//            }
//            System.out.printf("Линейный поиск для массива из %d элементов: \n", array * 10);
//            System.out.println("Среднее число сравнений: " + (double) compCountLin / 10);
//            System.out.printf("Бинарный итерационный поиск для массива из %d элементов: \n", array * 10);
//            System.out.println("Среднее число сравнений: " + (double) compCountBinI / 10);
//            System.out.printf("Бинарный рекурсивный поиск для массива из %d элементов: \n", array * 10);
//            System.out.println("Среднее число сравнений: " + (double) compCountBinR / 10);
//            System.out.printf("Интерполяционный поиск для массива из %d элементов: \n", array * 10);
//            System.out.println("Среднее число сравнений: " + (double) compCountInt / 10);
//        }
    }

}
