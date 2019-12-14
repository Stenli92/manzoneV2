package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.SessionUserModel;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final HttpServletRequest request;


    public HomeController(UserRepository userRepository, ModelMapper mapper, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.request = request;
    }

    @GetMapping("/")
    public String  index(){
        return new String("/home/index.html");
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/home")
    public String  home(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        User user = this.userRepository.findByUsername(username).orElse(null);

        SessionUserModel sessionUserModel = this.mapper.map(user , SessionUserModel.class);

        HttpSession session = request.getSession(true);

        session.setAttribute("user" , sessionUserModel);

        System.out.println();

        return new String("/home/home.html");
    }
}
