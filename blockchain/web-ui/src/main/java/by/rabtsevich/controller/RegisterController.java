package by.rabtsevich.controller;

import by.rabtsevich.pojo.AppUser;
import by.rabtsevich.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    AppUserService appUserService;

    @GetMapping
    public ModelAndView showRegisterPage() {
        return new ModelAndView("register");
    }

    @PostMapping
    public String registerNewUser(AppUser appUser) {
        appUserService.createNewUser(appUser);
        return "redirect:home.html";
    }
}
