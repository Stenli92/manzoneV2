package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.AuthValidationService;
import menzonev2.example.demo.services.HashingService;
import org.springframework.stereotype.Service;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {

    private final UserRepository userRepository;
    private final HashingService hashingService;

    public AuthValidationServiceImpl(UserRepository userRepository, HashingService hashingService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
    }


    @Override
    public boolean validate(UserServiceModel user) {

        return this.passWordsAreMatching(user.getPassword() , user.getConfirmPassword()) &&
                this.emailIsValid(user.getEmail())
                && this.usernameIsFree(user.getUsername());

    }



    private boolean usernameIsFree(String username) {

        return !this.userRepository.existsByUsername(username);
    }

    private boolean emailIsValid(String email) {

        return email.contains("@");
    }

    private boolean passWordsAreMatching(String password, String confirmPassword) {

        return password.equals(confirmPassword);
    }


    @Override
    public boolean loginValidation(UserServiceModel user) {

        if (!this.userRepository.existsByUsername(user.getUsername())){

            return false;
        }

        User userToLog = this.userRepository.findByUsername(user.getUsername());

        String passwordHash = hashingService.hash(user.getPassword());

        return passwordHash.equals(userToLog.getPassword());
    }
}
