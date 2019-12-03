package menzonev2.example.demo.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED , reason = "The Passwords are not matching")
public class UpdatePasswordsNotMatching extends RuntimeException{

    public UpdatePasswordsNotMatching(String message) {

        super(message);
    }
}
