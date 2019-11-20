package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.LoginUserServiceModel;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.AuthService;
import menzonev2.example.demo.services.AuthValidationService;
import menzonev2.example.demo.web.models.RegisterUserServiceModel;
import menzonev2.example.demo.web.models.UpdatePassModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
@Scope("session")
public class AuthController {

    private final ModelMapper mapper;
    private final AuthService authService;
    private final AuthValidationService authValidationService;
    private final UserRepository userRepository;
    @Autowired(required=true)
    private HttpServletRequest request;

    public AuthController(ModelMapper mapper, AuthService authService, AuthValidationService authValidationService, UserRepository userRepository) {
        this.mapper = mapper;
        this.authService = authService;
        this.authValidationService = authValidationService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String register(){

        return "auth/register.html";
    }

    @GetMapping("/login")
    public String login(){

        return "auth/login.html";
    }

    @GetMapping("/forgottenPass")
    public String forgottenPass(){

        return "auth/forgottenPass.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterUserServiceModel model) {


        UserServiceModel serviceModel = mapper.map(model, UserServiceModel.class);

//        if (!authValidationService.validate(serviceModel)) {
//            mav.addObject("emailError", "Email is not valid");
//            mav.setViewName("/auth/register");
//        }

        this.authService.register(serviceModel);

        return "redirect:/users/login";
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
