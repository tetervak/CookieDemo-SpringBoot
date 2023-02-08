// Alex Tetervak, Sheridan College, Ontario
package ca.tetervak.cookiedemo.encoder;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class CookieUrlEncoder implements CookieEncoder {

    @Override
    public String decode(String value){
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    @Override
    public String encode(String value){
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
