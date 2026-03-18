package ZIVPM.upr4;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

public class RSAMath {

    private RSAMath() {}

    public static Key generateKey(long p, long q) {
        long seed = System.currentTimeMillis();
        seed = Long.rotateLeft(seed, 48) ^ seed;
        Random rnd = new Random(seed);
        long n = p * q;
        long phi = (p - 1) * (q - 1);
        long e = 10;
        while (!isCoprime(e, phi)){
            e++;
        }
        long d = 2;
        while (!((e * d) % phi == 1)) {
            d++;
        }
        return new Key(
                new BigInteger(String.valueOf(e)),
                new BigInteger(String.valueOf(d)),
                new BigInteger(String.valueOf(n))
        );
    }

    private static boolean isCoprime(long a, long b) {
        for (long i = 2; i <= (Math.min(a, b)); i++) {
            if (a % i == 0 && b % i == 0) return false;
        }
        return true;
    }

    public static Key generateKey(long p, long q, long e, long d) {
        return new Key(new BigInteger(String.valueOf(e)),
                new BigInteger(String.valueOf(d)),
                new BigInteger(String.valueOf(p * q))
        );
    }

    public static String encrypt(Key key, String message) {
        // Представление байт исходного сообщения в виде целого числа
        BigInteger messageInt = new BigInteger(1, message.getBytes(StandardCharsets.UTF_8));
        // Вычисление числа бит в блоке
        final int k = key.n().bitLength() - 1;
        BigInteger cipher = new BigInteger(1, new byte[0]);
        BigInteger mask = BigInteger.ONE.shiftLeft(k).subtract(BigInteger.ONE); // k младших бит равны 1
        // Вычисление числа блоков в исходном сообщении
        final int blocks = Math.ceilDiv(messageInt.bitLength(), k);
        // Применение алгоритма RSA к блокам
        for (int i = 0; i < blocks; i++) {
            cipher = cipher.shiftLeft(k + 1)
                    .add(                               // cipher += (конкатенация)
                            messageInt.and(mask)        // k младших бит
                            .modPow(key.e(), key.n())   // в степени e по модулю n
                            );
            messageInt = messageInt.shiftRight(k);
        } // Перевод числа в формат Base64
        byte[] base64ByteArray = Base64.getEncoder().encode(
                cipher.toString().getBytes(StandardCharsets.ISO_8859_1));
        return new String(base64ByteArray);
    }

    public static String decrypt(Key key, String cipherBase64) {
        // Перевод числа в формате Base64 в целое число
        BigInteger cipherInt = new BigInteger(new String(
                Base64.getDecoder().decode(cipherBase64.getBytes(StandardCharsets.ISO_8859_1))));
        // Вычисление числа бит в блоке
        final int k = key.n().bitLength();
        BigInteger message = new BigInteger(1, new byte[0]);
        final BigInteger mask = BigInteger.ONE.shiftLeft(k).subtract(BigInteger.ONE); // k младших бит равны 1
        // Вычисление числа блоков в шифре
        final int blocks = Math.ceilDiv(cipherInt.bitLength(), k);
        // Применение алгоритма RSA к блокам
        for (int i = 0; i < blocks; i++) {
            message = message.shiftLeft(k - 1)
                    .add(                               // message += (конкатенация)
                            cipherInt.and(mask)         // k младших бит
                            .modPow(key.d(), key.n())   // в степени d по модулю n
                            );
            cipherInt = cipherInt.shiftRight(k);
        }
        // Перевод массива байт целого числа в строку
        return new String(message.toByteArray(), StandardCharsets.UTF_8);
    }
}

