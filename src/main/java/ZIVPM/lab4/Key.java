package ZIVPM.lab4;

import java.math.BigInteger;

public record Key (
    BigInteger e,
    BigInteger d,
    BigInteger n
) {

}
