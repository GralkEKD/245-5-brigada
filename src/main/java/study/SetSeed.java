package study;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SetSeed {
    public static void main(String[] args) throws IOException{
        try (BufferedWriter writer = Files.newBufferedWriter(
                Path.of("src", "main", "java", "study", "seed.txt")
        )) {
            long seed = System.currentTimeMillis();
            seed = Long.rotateLeft(seed, 48) ^ seed;
            writer.write(Long.toHexString(seed));
        }
    }
}
