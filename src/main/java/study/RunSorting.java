package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class RunSorting {
    public static void main(String[] args) {
        long seed;
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get("/home/gralkekd/IdeaProjects/245-5-brigada/src/main/java/study/seed.txt")
        )) {
            BigInteger integer = new BigInteger(reader.readLine(), 16);
            seed = integer.longValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long mean = 0;
        int num = 100;
        for (int i = 0; i < num; i++) {
            long time;
            Integer[] array = new Integer[100_000];
            Random random = new Random(seed);
            random.ints(100_000)
                    .boxed()
                    .toList()
                    .toArray(array);
            time = Timer.countTimeNanos(() -> Sorting.quickSort(array));
            System.out.printf("Time %d: %d ns\n", i+1, time);
            mean += time;
        }
        System.out.println("Mean: " + (mean / num) + " ns");
    }
}
