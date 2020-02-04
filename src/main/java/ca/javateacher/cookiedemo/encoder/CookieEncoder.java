// Alex Tetervak, Sheridan College, Ontario
package ca.javateacher.cookiedemo.encoder;

public interface CookieEncoder {
    String decode(String value);
    String encode(String value);
}
