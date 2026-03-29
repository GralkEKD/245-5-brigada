package ZIVPM.lab5;

import java.math.BigInteger;

public record Key (
        BigInteger p,
        BigInteger q,
        BigInteger a,
        BigInteger x,
        BigInteger y
) {
}
