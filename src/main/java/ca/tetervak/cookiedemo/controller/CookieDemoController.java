// Alex Tetervak, Sheridan College, Ontario
package ca.tetervak.cookiedemo.controller;

import ca.tetervak.cookiedemo.encoder.CookieEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieDemoController {

    private final Logger logger = LoggerFactory.getLogger(CookieDemoController.class);

    private final CookieEncoder cookieEncoder;

    public CookieDemoController(
            @Qualifier("cookieUrlEncoder") CookieEncoder cookieEncoder) {
        this.cookieEncoder = cookieEncoder;
    }

    @GetMapping(value={"/", "/index"})
    public ModelAndView index(@CookieValue(value = "encName", defaultValue = "") String encName,
                                  HttpServletResponse response){
        logger.trace("index() is called");
        if(encName.isEmpty()){
            logger.trace("userName is not found in cookies");
            logger.trace("trying to add a test cookie, to know if cookies are allowed");
            Cookie cookie = new Cookie("test", "whatever");
            cookie.setMaxAge(-1); // this cookie expires with the session, or when the browser is closed
            response.addCookie(cookie);
            return new ModelAndView("Input");
        }else{
            logger.trace("userName is found in cookies");
            String userName = cookieEncoder.decode(encName);
            return new ModelAndView("Welcome", "userName", userName);
        }

    }

    @GetMapping("/process")
    public ModelAndView process(
            @RequestParam(defaultValue = "") String userName,
            @CookieValue(value = "test", defaultValue = "") String test,
            HttpServletResponse response){
        logger.trace("process() is called");
        if (userName.trim().isEmpty()){
            ModelAndView mv = new ModelAndView("Output", "userName", "Stranger");
            mv.addObject("message", "The name input is left empty.");
            return mv;
        }else{
            ModelAndView mv = new ModelAndView("Output", "userName", userName);
            if(test.isEmpty()){
                logger.trace("test cookie is not found");
                mv.addObject("message", "Your browser doesn't like cookies");
            }else{
                mv.addObject("message", "Your name is saved in cookies.");
                Cookie cookie = new Cookie("encName", cookieEncoder.encode(userName));
                cookie.setMaxAge(24*60*60);
                response.addCookie(cookie);
                logger.trace("the user name is saved in the cookies");
            }
            return mv;
        }
    }

    @GetMapping("/forget")
    public String forget(HttpServletResponse response){
        logger.trace("forget() is called");
        Cookie cookie = new Cookie("encName","whatever");
        cookie.setMaxAge(0); // is set to expire immediately
        response.addCookie(cookie);
        return "redirect:index";
    }
}
