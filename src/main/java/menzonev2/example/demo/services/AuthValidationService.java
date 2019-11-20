package menzonev2.example.demo.services;

import menzonev2.example.demo.domain.services.models.UserServiceModel;

public interface AuthValidationService {

    boolean validate (UserServiceModel user);
}
