package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.UserService;
import menzonev2.example.demo.web.models.UpdateBalanceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class ProfileController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final HttpServletRequest request;

    @Autowired
    public ProfileController(UserService userService, UserRepository userRepository, BCryptPasswordEncoder encoder, HttpServletRequest request) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.request = request;
    }

    @GetMapping("/myprofile")
    public String  myprofile(){
        return new String("/user/myprofile.html");
    }

    @GetMapping("/update-password")
    public String  updatePass(){
        return new String("/user/update-password.html");
    }

    @GetMapping("/update-balance")
    public String updateBalance(){

        return "/user/update-balance.html";
    }

    @PostMapping("/update-balance")
    public String updateBalanceCommit(@ModelAttribute UpdateBalanceModel updateBalanceModel){

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        String pass = encoder.encode(updateBalanceModel.getPassword());


        if (!pass.equals(user.getPassword())){

            return "/user/update-balance.html";
        }

        if (!updateBalanceModel.getPassword().equals(updateBalanceModel.getConfirmPass())){

            return "/user/update-balance.html";

        }

        Integer newBalace = user.getBalance() + updateBalanceModel.getMoneyToInsert();

        user.setBalance(newBalace);

        this.userRepository.save(user);


        return "redirect:/users/myprofile";
    }
}