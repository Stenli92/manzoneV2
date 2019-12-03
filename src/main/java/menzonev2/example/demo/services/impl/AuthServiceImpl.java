package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.LoginUserServiceModel;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.AuthService;
import menzonev2.example.demo.services.ValidationService;
import menzonev2.example.demo.services.HashingService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ValidationService authValidationService;
    private final HashingService hashingService;

    public AuthServiceImpl(UserRepository userRepository, ModelMapper mapper, ValidationService authValidationService, HashingService hashingService) {
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

        List<User> userList = this.userRepository.findAll();


        User regUser = this.mapper.map(user , User.class);
        regUser.setPassword(this.hashingService.hash(regUser.getPassword()));
        if (userList.isEmpty()){
            regUser.setRole("Admin");

        }else {

            regUser.setRole("User");

        }


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

        String insertedPass = DigestUtils.sha256Hex(user.getUsername());

        if(!insertedPass.equals(userToLog.getPassword())){
            return "/auth/login";


        }
        return "/home/home";
    }

    @Override
    public UserServiceModel getUser(String username) {

         User user = this.userRepository.findByUsername(username).orElse(null);

        return this.mapper.map(user , UserServiceModel.class);
    }


    @Override
    public boolean checkIfUserExists(String username) {

        return this.userRepository.existsByUsername(username);

    }

    @Override
    public void updateUserBalance(String username, Integer balance) {
        User user = this.userRepository.findByUsername(username).orElse(null);

        user.setBalance(balance);

        this.userRepository.save(user);
    }

    @Override
    public void removeUser(UserServiceModel user) {

        User userToRemove = this.userRepository.findByUsername(user.getUsername()).orElse(null);

        this.userRepository.delete(userToRemove);


    }

    @Override
    public void updateToAdmin(UserServiceModel user) {

        User userToUpdate = this.userRepository.findByUsername(user.getUsername()).orElse(null);

        userToUpdate.setRole("Admin");

        this.userRepository.save(userToUpdate);

    }


    @Override
    public List<UserServiceModel> getAllUsers() {

        List<User> allUsers = this.userRepository.findAllByRole("User");

        return allUsers.stream().map(e-> this.mapper.map(e , UserServiceModel.class)).collect(Collectors.toList());

    }


}
