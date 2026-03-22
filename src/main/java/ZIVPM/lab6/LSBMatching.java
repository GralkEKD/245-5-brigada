package ZIVPM.lab6;

import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class LSBMatching {

    private static final int SIGNUM_POSITIVE = 1;

    public static BufferedImage encrypt(BufferedImage image, String message) {
        BigInteger messageBytes = new BigInteger(SIGNUM_POSITIVE, message.getBytes(StandardCharsets.UTF_8));
        BufferedImage stegoImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int messageBitLength = message.getBytes(StandardCharsets.UTF_8).length * 8;
        stegoImage.setRGB(0, 0, messageBitLength & 0x00FFFFFF);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (x == 0 && y == 0) continue;
                int rgb = image.getRGB(x, y);
                if (messageBitLength-- > 0) {
                    if ((rgb & 0x01) != messageBytes.and(BigInteger.ONE).intValue()) {
                        rgb += (rgb & 255) == 0 ? 1 : -1;
                    }
                }
                messageBytes = messageBytes.shiftRight(1);
                stegoImage.setRGB(x, y, rgb & 0x00FFFFFF);
            }
        }
        return stegoImage;
    }

    public static String decrypt(BufferedImage stego) {
        BigInteger messageBits = BigInteger.valueOf(0L);
        int messageBitLength = stego.getRGB(0, 0) & 0xFFFFFF;
        int bitIndex = 0;
        for (int y = 0; y < stego.getHeight(); y++) {
            if (messageBitLength <= 0) break;
            for (int x = 0; x < stego.getWidth(); x++) {
                if (x == 0 && y == 0) continue;
                if (messageBitLength-- < 0) break;
                BigInteger bit = BigInteger.valueOf(stego.getRGB(x, y) & 0x01);
                messageBits = messageBits.add(bit.shiftLeft(bitIndex++));
            }
        }
        return new String(messageBits.toByteArray(), StandardCharsets.UTF_8);
    }
}
