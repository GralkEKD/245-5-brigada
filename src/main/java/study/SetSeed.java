package study;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SetSeed {
    public static void main(String[] args) {
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get("/home/gralkekd/IdeaProjects/245-5-brigada/src/main/java/study/seed.txt")
        )) {
            long seed = System.currentTimeMillis();
            seed = Long.rotateLeft(seed, 48) ^ seed;
            writer.write(Long.toHexString(seed));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
