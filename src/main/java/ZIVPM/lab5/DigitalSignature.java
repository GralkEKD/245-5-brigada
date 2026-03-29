package ZIVPM.lab5;

import java.math.BigInteger;
import java.util.Random;

public class DigitalSignature {

    public static Key generateKey() {
        long seed = System.currentTimeMillis();
        seed = Long.rotateLeft(seed, 48) ^ seed;
        Random rnd = new Random(seed);

        BigInteger q = BigInteger.probablePrime(rnd.nextInt(254, 256), rnd);
        BigInteger p;
        while (true) {
            BigInteger k = new BigInteger(256, rnd);
            if ((p = k.multiply(q).add(BigInteger.ONE)).isProbablePrime(100)) break;
        }
        BigInteger a;
        while (true) {
            BigInteger g = new BigInteger(p.bitLength(), rnd);
            if (g.compareTo(BigInteger.ONE) <= 0 || g.compareTo(p) >= 0) continue;

            a = g.modPow(p.subtract(BigInteger.ONE).divide(q), p);
            if (!a.equals(BigInteger.ONE)) break;
        }
        BigInteger x;
        do {
            x = new BigInteger(q.bitLength(), rnd);
        } while (x.compareTo(BigInteger.ZERO) <= 0 || x.compareTo(q) >= 0);
        BigInteger y = a.modPow(x, p);
        return new Key(p, q, a, x, y);
    }

    private static int CRC(String message) {
        char[] messageChars = message.toCharArray();
        int crcReg = 0xAAAA_AAAA;
        for (char c : messageChars) {
            crcReg = Integer.rotateLeft(crcReg ^ c, 1);
        }
        return crcReg != 0 ? crcReg : 1;
    }

    public static Signature sign(String message, Key key) {
        long seed = System.currentTimeMillis();
        seed = Long.rotateLeft(seed, 48) ^ seed;
        Random rnd = new Random(seed);
        int crc = CRC(message);
        BigInteger bigCRC = BigInteger.valueOf(crc);
        BigInteger s = BigInteger.valueOf(0);
        BigInteger k = BigInteger.valueOf(0);
        BigInteger r1 = BigInteger.valueOf(0);
        while (s.equals(BigInteger.ZERO)) {
            r1 = BigInteger.valueOf(0);
            while (r1.equals(BigInteger.ZERO)) {
                do {
                    k = new BigInteger(key.q().bitLength(), rnd);
                } while (k.compareTo(BigInteger.ZERO) <= 0 || k.compareTo(key.q()) >= 0);
                BigInteger r = key.a().modPow(k, key.p());
                r1 = r.mod(key.q());
            }
            s = ((key.x().multiply(r1)).add(k.multiply(bigCRC))).mod(key.q());
        }
        return new Signature(r1, s);
    }

    public static boolean checkSignature(String message, String strSignature, Key key) {
        Signature signature;
        try {
            signature = Signature.fromString(strSignature);
        } catch (IllegalArgumentException e) {
            return false;
        }
        if (
                (signature.r1().signum() <= 0 ||
                key.q().subtract(signature.r1()).signum() <= 0 ||
                signature.s().signum() <= 0 ||
                key.q().subtract(signature.s()).signum() <= 0)
        ) return false;
        int crc = CRC(message);
        BigInteger MINUS_ONE = BigInteger.ZERO.subtract(BigInteger.ONE);
        BigInteger bigCRC = BigInteger.valueOf(crc);
        BigInteger v = bigCRC.modPow(MINUS_ONE, key.q());
        BigInteger z1 = signature.s().multiply(v).mod(key.q()),
                z2 = signature.r1().multiply(v).multiply(MINUS_ONE).mod(key.q());
        BigInteger u = (key.y().modPow(z2, key.p()).multiply(key.a().modPow(z1, key.p()))).mod(key.p()).mod(key.q());
        return u.equals(signature.r1());
    }
}
