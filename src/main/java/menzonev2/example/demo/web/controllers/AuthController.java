package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.LoginUserServiceModel;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.AuthService;
import menzonev2.example.demo.services.AuthValidationService;
import menzonev2.example.demo.web.models.ForgottenPassModel;
import menzonev2.example.demo.web.models.RegisterUserServiceModel;
import menzonev2.example.demo.web.models.UpdatePassModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@Scope("session")
public class AuthController {

    private final ModelMapper mapper;
    private final AuthService authService;
    private final AuthValidationService authValidationService;
    private final UserRepository userRepository;
    private final HttpServletRequest request;

    @Autowired
    public AuthController(ModelMapper mapper, AuthService authService, AuthValidationService authValidationService, UserRepository userRepository, HttpServletRequest request) {
        this.mapper = mapper;
        this.authService = authService;
        this.authValidationService = authValidationService;
        this.userRepository = userRepository;
        this.request = request;
    }

    @ModelAttribute("createUsername")
    public RegisterUserServiceModel registerModel(){

        return new RegisterUserServiceModel();
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("createUsername") RegisterUserServiceModel model){

        return "auth/register.html";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterUserServiceModel model , BindingResult result) {

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

    @GetMapping("/forgottenPass")
    public String forgottenPass(){

        return "auth/forgottenPass.html";
    }

    @PostMapping("/forgottenPass")
    public String forgottenPassConfirm(@ModelAttribute LoginUserServiceModel model , HttpSession session){

        if (!this.authService.checkIfUserExists(model.getUsername())){

            return "redirect:/users/login";
        }

        User user = this.userRepository.findByUsername(model.getUsername());

        session.setAttribute("user" , user);



        return "redirect:/users/forgottenPassQuest";
    }

    @GetMapping("/forgottenPassQuest")
    public String forgottenPassQuest(Model model){

        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute("user");

        String question = user.getSecretQuestion();

        model.addAttribute("question" , question);

        return "auth/forgottenPassQuest.html";
    }

    @PostMapping("/forgottenPassQuest")
    public String forgottenPassQuestCommit(@ModelAttribute ForgottenPassModel model){

        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute("user");

        String answer = user.getSecretAnswer();
        System.out.println();

        if (!user.getSecretAnswer().equals(model.getSecretAnswer())){


            return "redirect:/users/forgottenPassQuest";
        }


        return "redirect:/users/forgottenPassUpdate";

    }

    @GetMapping("/forgottenPassUpdate")
    public String forgottenPassUpdate(){


        return "/user/update-pass-aq.html";
    }

    @PostMapping("/forgottenPassUpdate")
    public String forgottenPassUpdate(@ModelAttribute UpdatePassModel model){

        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute("user");

        if (!model.getNewPass().equals(model.getConfirmPass())){

            session.invalidate();
            return "redirect:/users/";
        }



        User userToUpdatePass = this.userRepository.getOne(1);

        System.out.println();

        userToUpdatePass.setPassword(model.getNewPass());

        this.userRepository.save(this.mapper.map(userToUpdatePass , User.class));

        return "auth/forgottenPassQuest.html";
    }



    @PostMapping("/login")
    public String login(@ModelAttribute LoginUserServiceModel model , HttpSession session) {


        LoginUserServiceModel serviceModel = mapper.map(model, LoginUserServiceModel.class);

        User user = this.userRepository.findByUsername(model.getUsername());
        authService.login(serviceModel);
        session.setAttribute("user" ,user);
        return "redirect:/users/home";
    }

    @GetMapping("/home")
    public String home(){

        return "home/home.html";
    }

    @PostMapping("/logout")
    public String login( HttpSession session) {


        session.invalidate();
        return "redirect:/";
    }

//    @GetMapping("/update-password")
//    public String updatePass(){
//
//        return "/users/update-password.html";
//    }

    @PostMapping("/update-password")
    public String updatePass(@ModelAttribute UpdatePassModel model) {


       HttpSession session = request.getSession(true);

       User user = (User) session.getAttribute("user");

       String pass = DigestUtils.sha256Hex(model.getOldPassword());

       if (!user.getPassword().equals(pass)){

           return "redirect:/users/update-password";

       }

       if (!model.getNewPass().equals(model.getConfirmPass())){

           return "redirect:/users/update-password";

       }


       user.setPassword(DigestUtils.sha256Hex(model.getNewPass()));
       this.userRepository.save(user);

        return "redirect:/users/home";
    }


}
