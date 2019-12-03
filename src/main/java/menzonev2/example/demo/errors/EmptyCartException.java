package menzonev2.example.demo.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND , reason = "Cart is Empty")
public class EmptyCartException extends RuntimeException{

    public EmptyCartException(String message) {
        super(message);
    }
}
