package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.SessionUserModel;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.ValidationService;
import menzonev2.example.demo.web.models.RegisterUserServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public boolean passValidation(String oldPass, String newPass, String confirmPassword) {


        HttpSession session = request.getSession(true);

        SessionUserModel sessionUserModel = (SessionUserModel) session.getAttribute("user");

        User user = this.userRepository.findByUsername(sessionUserModel.getUsername()).orElse(null);

        String oldPassInsert = passwordEncoder.encode(oldPass);

        if (!user.getPassword().equals(oldPassInsert)){

            return false;

        }

        if (!newPass.equals(confirmPassword)){

            return false;

        }
        return true;
    }

    @Override
    public boolean registerValidation(RegisterUserServiceModel userModel, Model model) {
        List<User> allUsers = this.userRepository.findAll();

        boolean isTrue = true;

        for (User allUser : allUsers) {

            if (allUser.getUsername().equals(userModel.getUsername())){

                model.addAttribute("usernameTaken" , "The username is already taken");

                isTrue = false;
            }

            if (allUser.getEmail().equals(userModel.getEmail())){

                model.addAttribute("emailTaken" , "The email is already taken");
                isTrue = false;

            }

        }

        return isTrue;
    }
}
