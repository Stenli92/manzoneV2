package menzonev2.example.demo.services;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.LoginUserServiceModel;
import menzonev2.example.demo.domain.services.models.UserServiceModel;

import java.util.List;

public interface AuthService {

    String register(UserServiceModel user);

    String login(LoginUserServiceModel user);

    UserServiceModel getUser(String username);

    boolean checkIfUserExists(String username);

    void updateUserBalance(String username , Integer balance);

    void removeUser(UserServiceModel user);

    void updateToAdmin(UserServiceModel user);

    List<UserServiceModel>  getAllUsers();
}
