package menzonev2.example.demo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/myprofile")
    public String  myprofile(){
        return new String("/user/myprofile.html");
    }

    @GetMapping("/update-password")
    public String  updatePass(){
        return new String("/user/update-password.html");
    }
}
