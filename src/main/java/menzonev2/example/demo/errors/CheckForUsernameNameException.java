package menzonev2.example.demo.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND , reason = "No such user registered")
public class CheckForUsernameNameException  extends RuntimeException{

    public CheckForUsernameNameException(String message) {
        super(message);
    }
}
