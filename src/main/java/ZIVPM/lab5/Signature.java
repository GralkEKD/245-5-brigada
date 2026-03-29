package ZIVPM.lab5;

import java.math.BigInteger;
import java.util.Base64;

public record Signature(
        BigInteger r1,
        BigInteger s
) {
    @Override
    public String toString() {
        Base64.Encoder encoder = Base64.getEncoder();

        String r1 = new String(encoder.encode(r1().toByteArray()));
        String s = new String(encoder.encode(s().toByteArray()));
        return r1 + '|' + s;
    }

    public static Signature fromString(String strSignature) {
        String[] r1_s = strSignature.split("\\|");
        if (r1_s.length != 2) throw new IllegalArgumentException("Неверный формат подписи");
        Base64.Decoder decoder = Base64.getDecoder();
        BigInteger r1 = new BigInteger(decoder.decode(r1_s[0].getBytes()));
        BigInteger s = new BigInteger(decoder.decode(r1_s[1].getBytes()));
        return new Signature(r1, s);
    }
}
