package ZIVPM.lab3;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FeistelNetwork {

    private static int Vi(long key, int i) {
        int k1 = ((int) key), k2 = ((int) (key >> 32));
        return Integer.rotateLeft(k1, i) ^ Integer.rotateRight(k2, i);
    }

    private static int roundFunction(int Xim1, int Vi) {
        return Xim1 + Vi;
    }

    private static void encryptSegment(int[] blocks, long key, int rounds) {
        if (blocks.length != 4) throw new RuntimeException("Неверное число блоков: " + blocks.length);
        for (int i = 0; i < rounds; i++) {
            int temp = blocks[0];
            blocks[0] = blocks[1] ^ roundFunction(blocks[0], Vi(key, i));
            blocks[1] = blocks[2];
            blocks[2] = blocks[3];
            blocks[3] = temp;
        }
    }

    private static void decryptSegment(int[] blocks, long key, int rounds) {
        if (blocks.length != 4) throw new RuntimeException("Неверное число блоков: " + blocks.length);
        for (int i = rounds - 1; i >= 0; i--) {
            int temp = blocks[3];
            blocks[3] = blocks[2];
            blocks[2] = blocks[1];
            blocks[1] = blocks[0] ^ roundFunction(temp, Vi(key, i));
            blocks[0] = temp;
        }
    }

    public static String encrypt(String message, long key, int rounds) {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        // Число блоков кратно 4-м
        int[] segments = new int[Math.ceilDiv(messageBytes.length, 16) * 4];
        for (int i = 0; i < messageBytes.length; i++) {
            segments[i / 4] |= (((int) messageBytes[i]) & 0xFF) << ((i % 4) * 8);
        }
        for (int i = 0; i < segments.length; i += 4) {
            int[] blocks = new int[4];
            System.arraycopy(segments, i, blocks, 0, 4);
            encryptSegment(blocks, key, rounds);
            System.arraycopy(blocks, 0, segments, i, 4);
        }
        byte[] cipherBytes = new byte[segments.length * 4];
        for (int i = 0; i < cipherBytes.length; i++) {
            cipherBytes[i] = (byte) (segments[i / 4] >> ((i % 4) * 8));
        }

        return new String(Base64.getEncoder().encode(cipherBytes));
    }

    public static String decrypt(String cipherBase64, long key, int rounds) {
        byte[] cipherBytes = Base64.getDecoder().decode(cipherBase64);
        int[] segments = new int[Math.ceilDiv(cipherBytes.length, 16) * 4];
        for (int i = 0; i < cipherBytes.length; i++) {
            segments[i / 4] |= (((int) cipherBytes[i]) & 0xFF) << ((i % 4) * 8);
        }
        for (int i = 0; i < segments.length; i += 4) {
            int[] blocks = new int[4];
            System.arraycopy(segments, i, blocks, 0, 4);
            decryptSegment(blocks, key, rounds);
            System.arraycopy(blocks, 0, segments, i, 4);
        }
        byte[] messageBytesWithNil = new byte[segments.length * 4];
        for (int i = 0; i < messageBytesWithNil.length; i++) {
            messageBytesWithNil[i] = (byte) (segments[i / 4] >> ((i % 4) * 8));
        }
        int nilCount = 0;
        for (int i = messageBytesWithNil.length - 1; i > 0; i--) {
            if (messageBytesWithNil[i] != 0) break;
            nilCount++;
        }
        byte[] messageBytes = new byte[messageBytesWithNil.length - nilCount];
        System.arraycopy(messageBytesWithNil, 0, messageBytes, 0, messageBytes.length);

        return new String(messageBytes, StandardCharsets.UTF_8);
    }
}
