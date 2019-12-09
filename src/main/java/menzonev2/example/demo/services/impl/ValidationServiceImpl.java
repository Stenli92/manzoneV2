package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class ValidationServiceImpl implements ValidationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpServletRequest request;


    @Autowired
    public ValidationServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.request = request;
    }




    @Override
    public boolean loginValidation(UserServiceModel user) {

        if (!this.userRepository.existsByUsername(user.getUsername())){

            return false;
        }

        User userToLog = this.userRepository.findByUsername(user.getUsername()).orElse(null);

        String passwordHash = passwordEncoder.encode(userToLog.getPassword());

        return passwordHash.equals(userToLog.getPassword());
    }

    @Override
    public boolean passValidation(String oldPass, String newPass, String confirmPassword) {


        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute("user");

        String oldPassInsert = passwordEncoder.encode(oldPass);

        if (!user.getPassword().equals(oldPassInsert)){

            return false;

        }

        if (!newPass.equals(confirmPassword)){

            return false;

        }
        return true;
    }


}
