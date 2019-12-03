package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.ValidationService;
import menzonev2.example.demo.services.HashingService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class ValidationServiceImpl implements ValidationService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final HttpServletRequest request;


    public ValidationServiceImpl(UserRepository userRepository, HashingService hashingService, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.request = request;
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

        User userToLog = this.userRepository.findByUsername(user.getUsername()).orElse(null);

        String passwordHash = hashingService.hash(user.getPassword());

        return passwordHash.equals(userToLog.getPassword());
    }

    @Override
    public boolean passValidation(String oldPass, String newPass, String confirmPassword) {


        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute("user");

        String oldPassInsert = DigestUtils.sha256Hex(oldPass);

        if (!user.getPassword().equals(oldPassInsert)){

            return false;

        }

        if (!newPass.equals(confirmPassword)){

            return false;

        }
        return true;
    }


}
