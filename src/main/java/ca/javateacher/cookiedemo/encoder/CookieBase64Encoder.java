// Alex Tetervak, Sheridan College, Ontario
package ca.javateacher.cookiedemo.encoder;

import java.util.Base64;

public class CookieBase64Encoder implements CookieEncoder {
    @Override
    public String decode(String value) {
        Base64.Decoder decoder  = Base64.getDecoder();
        return new String(decoder.decode(value));
    }

    @Override
    public String encode(String value) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(value.getBytes());
    }
}
