package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

public class RunSortingFiltered {
    public static void main(String[] args) {
        final long seed;
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get("/home/gralkekd/IdeaProjects/245-5-brigada/src/main/java/study/seed.txt")
        )) {
            BigInteger bigInteger = new BigInteger(reader.readLine(), 16);
            seed = bigInteger.longValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Random random = new Random(seed);
        Integer[] array = new Integer[20];
        random.ints()
                .boxed()
                .filter(x -> Math.abs(x) <= 20 && x != Integer.MIN_VALUE)
                .limit(20)
                .toList()
                .toArray(array);
        System.out.println(Arrays.toString(array));
        long timeNano = Timer.countTimeNanos(() -> Sorting.insertionSort(array));
        System.out.println(Arrays.toString(array));
        System.out.println("Time to sort: " + timeNano);
    }
}
