package study;

import SAODSAPR.upr1.Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class RunSorting {
    public static void main(String[] args) throws IOException{
        long seed;
        try (BufferedReader reader = Files.newBufferedReader(
                Path.of("src", "main", "java", "study", "seed.txt")
        )) {
            BigInteger integer = new BigInteger(reader.readLine(), 16);
            seed = integer.longValue();
        }
        long mean = 0;
        int num = 100;
        for (int i = 0; i < num; i++) {
            long time;
            Integer[] array = new Integer[1_000_000];
            Random random = new Random(seed);
            for (int j = 0; j < array.length; j++) {
                array[j] = random.nextInt(5, 20);
            }
//            random.ints(1_000_000)
//                    .boxed()
//                    .toList()
//                    .toArray(array);
            Integer[] k = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
            time = Timer.countTimeNanos(() -> Sorting.dispersionCountSort(array, k));
            System.out.printf("Time %d: %d ns\n", i+1, time);
            mean += time;
        }
        System.out.println("Mean: " + (mean / num) + " ns");
    }
}
