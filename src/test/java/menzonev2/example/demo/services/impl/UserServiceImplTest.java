package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Mock
    UserRepository mockedUserRepository;

    @Test(expected = Exception.class)
    public void registerUserWithInvalidValue_ShouldThrowException() {

        userService.register(null);
        verify(mockedUserRepository.save(any()));

    }

    @Test(expected = Exception.class)
    public void getUserWithInvalidValue_ShouldThrowException() {

        userService.getUser(null);
        verify(mockedUserRepository.save(any()));

    }

    @Test(expected = Exception.class)
    public void removeUserWithInvalidUserValue_shouldThrowException() {

        userService.removeUser(null);
        verify(mockedUserRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void updateToAdminWithInvalidUserValue_shouldThrowException() {

        userService.updateToAdmin(null);
        verify(mockedUserRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void setToUserWithInvalidUserValue_shouldThrowException() {
        userService.setToUser(null);
        verify(mockedUserRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void getAllUsersWithInvalidValue_shouldThrowException() {

        userService.getAllUsers();
        verify(mockedUserRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void deleteUserWithInvalidValue_ShouldThrowException() {

        userService.deleteUser(null);
        verify(mockedUserRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void loadUserByUsernameWithInvalidValues_shouldThrowException() {

        userService.loadUserByUsername(null);
        verify(mockedUserRepository.save(any()));
    }
}