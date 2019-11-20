package menzonev2.example.demo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public String  login(){
        return new String("/home/index.html");
    }

    @GetMapping("/home")
    public String  register(){
        return new String("/home/home.html");
    }
}
