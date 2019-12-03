package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.LoginUserServiceModel;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.AuthService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@Scope("session")
public class AuthController {

    private final ModelMapper mapper;
    private final AuthService authService;
    private final ValidationService authValidationService;
    private final UserRepository userRepository;
    private final HttpServletRequest request;

    @Autowired
    public AuthController(ModelMapper mapper, AuthService authService, ValidationService authValidationService, UserRepository userRepository, HttpServletRequest request) {
        this.mapper = mapper;
        this.authService = authService;
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
    public String register(@ModelAttribute("createUsername") RegisterUserServiceModel model){

        return "auth/register.html";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerModel") RegisterUserServiceModel model , BindingResult result) {

        if (result.hasErrors()){


            return "/auth/register";
        }


        UserServiceModel serviceModel = mapper.map(model, UserServiceModel.class);

        this.authService.register(serviceModel);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(){

        return "auth/login.html";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute LoginUserServiceModel model , HttpSession session , Model wrongLoginModel) {


        LoginUserServiceModel serviceModel = mapper.map(model, LoginUserServiceModel.class);

        User user = this.userRepository.findByUsername(model.getUsername()).orElse(null);

        if (!authValidationService.loginValidation(this.mapper.map(model , UserServiceModel.class))){


            String error = "Invalid username or password";
            wrongLoginModel.addAttribute( "error", error);
            return "auth/login.html";
        }

        session.setAttribute("user" ,user);
        return "redirect:/users/home";


    }

    @GetMapping("/all-users")
    public String allUsers(Model model){

        List<UserServiceModel> allUsers = this.authService.getAllUsers();

        model.addAttribute("users" , allUsers);

        return "user/all-users.html";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username){

        UserServiceModel user = this.authService.getUser(username);

        this.authService.removeUser(user);

        return "redirect:/users/all-users";
    }

    @GetMapping("/admin/{username}")
    public String makeAdmin(@PathVariable("username") String username){

        UserServiceModel user = this.authService.getUser(username);

        this.authService.updateToAdmin(user);

        return "redirect:/users/all-users";
    }

    @PostMapping("/logout")
    public String logout(SessionStatus status) {


        HttpSession session = request.getSession(true);

        status.setComplete();

        ((List<CartModel>) session.getAttribute("cart")).clear();


        session.invalidate();

        return "redirect:/";
    }






}
