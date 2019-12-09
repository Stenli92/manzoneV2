package menzonev2.example.demo.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String  login(){
        return new String("/home/index.html");
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/home")
    public String  register(){
        return new String("/home/home.html");
    }
}
