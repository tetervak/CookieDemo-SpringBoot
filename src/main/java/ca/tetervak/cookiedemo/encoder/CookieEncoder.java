// Alex Tetervak, Sheridan College, Ontario
package ca.tetervak.cookiedemo.encoder;

public interface CookieEncoder {
    String decode(String value);
    String encode(String value);
}
