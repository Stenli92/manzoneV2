package menzonev2.example.demo.services;

import menzonev2.example.demo.web.models.RegisterUserServiceModel;
import org.springframework.ui.Model;

public interface ValidationService {



    boolean registerValidation(RegisterUserServiceModel userModel , Model model) ;

    boolean passValidation(String oldPass , String newPass ,String confirmPassword);
}
