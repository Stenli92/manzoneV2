package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.LoginUserServiceModel;
import menzonev2.example.demo.domain.services.models.SessionUserModel;
import menzonev2.example.demo.errors.CheckForUsernameNameException;
import menzonev2.example.demo.errors.UpdatePasswordsNotMatching;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.UserService;
import menzonev2.example.demo.services.ValidationService;
import menzonev2.example.demo.web.models.ForgottenPassModel;
import menzonev2.example.demo.web.models.UpdatePassModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/passwords")
@Scope("session")
public class PassController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpServletRequest request;
    private final ValidationService validationService;

    public PassController(UserService userService, UserRepository userRepository, HttpServletRequest request, ValidationService validationService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.request = request;
        this.validationService = validationService;
    }


    @GetMapping("/update-password")
    public String updatePass(){

        return "/user/update-password.html";
    }

    @PostMapping("/update-password")
    public String updatePass(@ModelAttribute UpdatePassModel model) {

        HttpSession session = request.getSession(true);

        SessionUserModel user = (SessionUserModel) session.getAttribute("user");


        this.validationService.passValidation(model.getOldPassword() , model.getNewPass() , model.getConfirmPass());

        System.out.println();

        this.userService.updatePassword(user , model);

        return "redirect:/users/home";
    }



    @ExceptionHandler(CheckForUsernameNameException.class)
    public ModelAndView exceptionHandler(CheckForUsernameNameException ex){

        ModelAndView mav = new ModelAndView("/errors/check-for-user-error.html");

        mav.addObject("message" , ex.getMessage());

        return mav;


    }

    @ExceptionHandler(UpdatePasswordsNotMatching.class)
    public ModelAndView exceptionHandler(UpdatePasswordsNotMatching ex){

        ModelAndView mav = new ModelAndView("/errors/update-pass-error.html");

        mav.addObject("message" , ex.getMessage());

        return mav;


    }
}
