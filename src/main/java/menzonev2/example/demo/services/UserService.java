package menzonev2.example.demo.services;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.LoginUserServiceModel;
import menzonev2.example.demo.domain.services.models.SessionUserModel;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.web.models.CartModel;
import menzonev2.example.demo.web.models.RegisterUserServiceModel;
import menzonev2.example.demo.web.models.UpdateBalanceModel;
import menzonev2.example.demo.web.models.UpdatePassModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService extends UserDetailsService {

    String register(RegisterUserServiceModel user);

    boolean confirmPassValidation(SessionUserModel userModel , UpdateBalanceModel model, Model errorModel);

    void setNewBalance(SessionUserModel userModel , UpdateBalanceModel model);

    UserServiceModel getUser(String username);

    void updatePassword(SessionUserModel user , UpdatePassModel model);

    boolean checkIfUserExists(String username);

    void removeUser(UserServiceModel user);

    void updateToAdmin(UserServiceModel user);

    void setToUser(UserServiceModel user);

    List<UserServiceModel>  getAllUsers();

    void updateSellerBalance(List<CartModel> cart );

    void updateBuyerBalance(int amount , User user);

    void deleteUser(Long id);

    int cartAmount (List<CartModel> cart );

    User getUserFromContext();

}
