package ca.javateacher.cookiedemo.config;

import ca.javateacher.cookiedemo.encoder.CookieBase64Encoder;
import ca.javateacher.cookiedemo.encoder.CookieUrlEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CookieBase64Encoder cookieBase64Encoder(){
        return new CookieBase64Encoder();
    }

    @Bean
    public CookieUrlEncoder cookieUrlEncoder(){
        return new CookieUrlEncoder();
    }

}
