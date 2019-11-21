package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.LoginUserServiceModel;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.AuthService;
import menzonev2.example.demo.services.AuthValidationService;
import menzonev2.example.demo.services.HashingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final AuthValidationService authValidationService;
    private final HashingService hashingService;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper mapper, AuthValidationService authValidationService, HashingService hashingService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.authValidationService = authValidationService;
        this.hashingService = hashingService;
    }


    @Override
    public String register(UserServiceModel user) {

//        if (!this.authValidationService.validate(user)){
//
//            //do something
//            return "redirect: /register";
//        }


        User regUser = this.mapper.map(user , User.class);
        regUser.setPassword(this.hashingService.hash(regUser.getPassword()));
        this.userRepository.save(regUser);
        return "redirect: /login";
    }

    @Override
    public String login(LoginUserServiceModel user) {
        String passwordHash = hashingService.hash(user.getPassword());

        User userToLog = this.userRepository.findByUsernameAndPassword(user.getUsername() , passwordHash).orElse(null);

        if (userToLog == null){

            return "/auth/login";
        }
        return "/home/home";
    }

    @Override
    public UserServiceModel getUser(String username) {

        return this.mapper.map(this.userRepository.findByUsername(username) , UserServiceModel.class);
    }


    @Override
    public boolean checkIfUserExists(String username) {

        return this.userRepository.existsByUsername(username);

    }
}
