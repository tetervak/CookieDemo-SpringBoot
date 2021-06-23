// Alex Tetervak, Sheridan College, Ontario
package ca.javateacher.cookiedemo.encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUrlEncoder implements CookieEncoder {

    @Override
    public String decode(String value){
        try {
            return URLDecoder.decode(value,java.nio.charset.StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException: " + e.getMessage());
        }
    }

    @Override
    public String encode(String value){
        try {
            return URLEncoder.encode(value,java.nio.charset.StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException: " + e.getMessage());
        }
    }
}
