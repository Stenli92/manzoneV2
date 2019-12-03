package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.AuthService;
import menzonev2.example.demo.services.HashingService;
import menzonev2.example.demo.web.models.UpdateBalanceModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final HttpServletRequest request;
    private final HashingService hashingService;

    public UserController(AuthService authService, UserRepository userRepository, HttpServletRequest request, HashingService hashingService) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.request = request;
        this.hashingService = hashingService;
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

        String pass = this.hashingService.hash(updateBalanceModel.getPassword());


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
