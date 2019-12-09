package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.LoginUserServiceModel;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.UserService;
import menzonev2.example.demo.services.ValidationService;
import menzonev2.example.demo.web.models.CartModel;
import menzonev2.example.demo.web.models.RegisterUserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@Scope("session")
public class UserController {

    private final ModelMapper mapper;
    private final UserService userService;
    private final ValidationService authValidationService;
    private final UserRepository userRepository;
    private final HttpServletRequest request;

    @Autowired
    public UserController(ModelMapper mapper, UserService userService, ValidationService authValidationService, UserRepository userRepository, HttpServletRequest request) {
        this.mapper = mapper;
        this.userService = userService;
        this.authValidationService = authValidationService;
        this.userRepository = userRepository;
        this.request = request;
    }



    @ModelAttribute("registerModel")
    public RegisterUserServiceModel registerModel(){

        return new RegisterUserServiceModel();
    }

    @GetMapping("/home")
    public String home(){


        return "home/home.html";
    }

    @GetMapping("/register")
    public String register(){

        return "auth/register.html";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerModel") RegisterUserServiceModel model , BindingResult result) {

        if (result.hasErrors()){


            return "/auth/register";
        }


        UserServiceModel serviceModel = mapper.map(model, UserServiceModel.class);

        this.userService.register(serviceModel);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(){

        return "auth/login.html";
    }



    @GetMapping("/all-users")
    public String allUsers(Model model){

        List<UserServiceModel> allUsers = this.userService.getAllUsers();

        model.addAttribute("users" , allUsers);

        return "user/all-users.html";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username){

        UserServiceModel user = this.userService.getUser(username);

        this.userService.removeUser(user);

        return "redirect:/users/all-users";
    }

//    @GetMapping("/admin/{username}")
//    public String makeAdmin(@PathVariable("username") String username){
//
//        UserServiceModel user = this.userService.getUser(username);
//
//        this.userService.updateToAdmin(user);
//
//        return "redirect:/users/all-users";
//    }

    @PostMapping("/logout")
    public String logout(SessionStatus status) {


        HttpSession session = request.getSession(true);

        status.setComplete();

        ((List<CartModel>) session.getAttribute("cart")).clear();


        session.invalidate();

        return "redirect:/";
    }






}
