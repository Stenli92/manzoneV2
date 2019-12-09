package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.LoginUserServiceModel;
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

        User user = (User) session.getAttribute("user");


        this.validationService.passValidation(model.getOldPassword() , model.getNewPass() , model.getConfirmPass());


        user.setPassword(DigestUtils.sha256Hex(model.getNewPass()));
        this.userRepository.save(user);

        return "redirect:/users/home";
    }

    @GetMapping("/forgottenPass")
    public String forgottenPass(){

        return "auth/forgottenPass.html";
    }

    @PostMapping("/forgottenPass")
    public String forgottenPassConfirm(@ModelAttribute LoginUserServiceModel model , HttpSession session){


        User user = this.userRepository.findByUsername(model.getUsername())
                .orElseThrow(()-> new CheckForUsernameNameException("No such user registered"));

        session.setAttribute("user" , user);



        return "redirect:/passwords/forgottenPassQuest";
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


            return "redirect:/passwords/forgottenPassQuest";
        }


        return "redirect:/passwords/forgottenPassUpdate";

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
//            throw new UpdatePasswordsNotMatching("The passwords are not matching");

            ModelAndView mav = new ModelAndView();

            String error = "Passwords are not matching";

            mav.addObject("unmatching" , error);

            return "redirect:/passwords/forgottenPassUpdate";
        }



        user.setPassword(DigestUtils.sha256Hex(model.getNewPass()));

        this.userRepository.save(user);

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
