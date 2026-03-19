package ZIVPM.upr4real.ui;

import java.util.Random;
import java.util.function.IntPredicate;

public class OTPGenerator {

    private int a = Integer.MAX_VALUE, l = Integer.MAX_VALUE;

    private static final int LOWERCASE_LATIN = 26;
    private static final int LOWERCASE_LATIN_AND_DIGITS = 36;
    private static final int LOWERCASE_UPPERCASE_LATIN = 52;
    private static final int LOWERCASE_UPPERCASE_LATIN_AND_DIGITS = 62;
    private static final int LOWERCASE_UPPERCASE_LATIN_DIGITS_AND_SPECIAL = 69;

    private static final int[] A_VALS = {
            LOWERCASE_LATIN,
            LOWERCASE_LATIN_AND_DIGITS,
            LOWERCASE_UPPERCASE_LATIN,
            LOWERCASE_UPPERCASE_LATIN_AND_DIGITS,
            LOWERCASE_UPPERCASE_LATIN_DIGITS_AND_SPECIAL
    };

    public OTPGenerator(double time, double speed, int probExponent) {
        long sAsterisk = (long) Math.ceil(speed * time / (Math.pow(10.0, -probExponent)));
        long s = Long.MAX_VALUE;
        for (int a : A_VALS) {
            for (int l = 0; l < Integer.MAX_VALUE; l++) {
                long aToL = (long) Math.pow(a, l);
                if (aToL >= sAsterisk) {
                    if (aToL < s) {
                        s = aToL;
                        this.a = a;
                        this.l = l;
                    }
                    break;
                }
            }
        }
    }

    public long getA() {
        return a;
    }

    public long getL() {
        return l;
    }

    public String generateOTP() {
        long seed = System.currentTimeMillis();
        seed = Long.rotateLeft(seed, 48) ^ seed;
        Random rnd = new Random(seed);
        IntPredicate range;
        switch (a) {
            case LOWERCASE_LATIN -> range = (i ->
                    i >= 97 && i <= 122);
            case LOWERCASE_LATIN_AND_DIGITS -> range = (i ->
                    i >= 97 && i <= 122 ||
                    i >= 48 && i <= 57);
            case LOWERCASE_UPPERCASE_LATIN -> range = (i ->
                    i >= 97 && i <= 122 ||
                    i >= 65 && i <= 90);
            case LOWERCASE_UPPERCASE_LATIN_AND_DIGITS -> range = (i ->
                    i >= 97 && i <= 122 ||
                    i >= 65 && i <= 90 ||
                    i >= 48 && i <= 57);
            case LOWERCASE_UPPERCASE_LATIN_DIGITS_AND_SPECIAL -> range = (i ->
                    i >= 97 && i <= 122 ||
                    i >= 65 && i <= 90 ||
                    i >= 48 && i <= 57 ||
                    i >= 33 && i <= 39);
            default -> throw new RuntimeException("Неверная длина строки");
        }
        int[] result = java.util.stream.IntStream
                .generate(() -> rnd.nextInt(128))
                .filter(range)
                .limit(l)
                .toArray();
        char[] otp = new char[result.length];
        for (int i = 0; i < result.length; i++) {
            otp[i] = (char) result[i];
        }
        return new String(otp);
    }
}
