package by.rabtsevich.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping(value = "/home.html")
    public ModelAndView homePage(ModelAndView modelAndView) {
        log.info("Call homePage");
        modelAndView.setViewName("home"); // -> /WEB-INF/jsp/ + home + .jsp
        return modelAndView;
    }
}
