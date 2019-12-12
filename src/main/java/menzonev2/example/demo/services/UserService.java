package menzonev2.example.demo.services;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.LoginUserServiceModel;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.web.models.CartModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    String register(UserServiceModel user);


    UserServiceModel getUser(String username);

    boolean checkIfUserExists(String username);

    void updateUserBalance(String username , Integer balance);

    void removeUser(UserServiceModel user);

    void updateToAdmin(UserServiceModel user);

    List<UserServiceModel>  getAllUsers();

    void updateSellerBalance(List<CartModel> cart );

    void updateBuyerBalance(int amount , User user);

    int cartAmount (List<CartModel> cart );

    User getUserFromContext();

}
