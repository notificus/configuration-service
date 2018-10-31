package configuration.service.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CipMismatchException extends RuntimeException {
    public CipMismatchException() {
        super("Cip provided in path does not match with cip of the user");
    }
}
