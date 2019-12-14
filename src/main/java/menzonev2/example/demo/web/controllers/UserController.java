package menzonev2.example.demo.web.controllers;


import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.UserService;
import menzonev2.example.demo.services.ValidationService;
import menzonev2.example.demo.web.models.RegisterUserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private final ValidationService validationService;
    private final HttpServletRequest request;

    @Autowired
    public UserController(ModelMapper mapper, UserService userService, ValidationService authValidationService, UserRepository userRepository, ValidationService validationService, HttpServletRequest request) {
        this.mapper = mapper;
        this.userService = userService;
        this.authValidationService = authValidationService;
        this.userRepository = userRepository;
        this.validationService = validationService;
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
    public String register(@Valid @ModelAttribute("registerModel") RegisterUserServiceModel userModel , BindingResult result , Model model) {

        if (result.hasErrors()){


            return "/auth/register";
        }

        if (!this.validationService.registerValidation(userModel , model)){

            return "/auth/register";

        }

        this.userService.register(userModel);

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



    @GetMapping("/details/{username}")
    public String userDetails(@PathVariable("username") String username , Model model){


        UserServiceModel user = this.userService.getUser(username);



        model.addAttribute("username" , user.getUsername());
        model.addAttribute("email" , user.getEmail());




        return "/user/user-details.html";
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

    @PostMapping("/admin/{username}")
    public String setAdminRole(@PathVariable("username") String username) {

        UserServiceModel user = this.userService.getUser(username);

        this.userService.updateToAdmin(user);



        return "redirect:/users/all-users";
    }

    @PostMapping("/set-user/{username}")
    public String setUserRole(@PathVariable("username") String username) {

        UserServiceModel user = this.userService.getUser(username);

        System.out.println();

        this.userService.setToUser(user);


        return "redirect:/users/all-users";
    }

    @PostMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable("id") Long id) {

        this.userService.deleteUser(id);

        return "redirect:/users/all-users";
    }







}
