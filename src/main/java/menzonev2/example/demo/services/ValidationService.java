package menzonev2.example.demo.services;

import menzonev2.example.demo.domain.services.models.UserServiceModel;

public interface ValidationService {

    boolean validate (UserServiceModel user);

    boolean loginValidation(UserServiceModel user);

    boolean passValidation(String oldPass , String newPass ,String confirmPassword);
}
