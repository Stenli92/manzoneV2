package menzonev2.example.demo.web.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUserServiceModel {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @NotEmpty(message = "Username field cannot be empty")
    @Size(min = 3 , max = 15 , message = "Username should have between 3 and 15 digits")
    private String username;
    @NotEmpty(message = "Password field cannot be empty")
    @Size(min = 3 , max = 15 , message = "Password field should have between 3 and 15 digits")
    private String password;
    private String confirmPassword;
    @NotEmpty(message = "Email field cannot be empty")
    @Pattern(regexp= EMAIL_PATTERN , message = "The email you inserted is not valid")
    private String email;
    @NotEmpty(message = "Field cannot be empty")
    @Size(min = 3 , max = 15 , message = "Field have between 3 and 15 digits")
    private String secretQuestion;
    @NotEmpty(message = "Field cannot be empty")
    @Size(min = 3 , max = 15 , message = "Field should have between 3 and 15 digits")
    private String secretAnswer;

    public RegisterUserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }
}
