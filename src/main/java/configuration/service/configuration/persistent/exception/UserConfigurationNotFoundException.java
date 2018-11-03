package configuration.service.configuration.persistent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserConfigurationNotFoundException extends RuntimeException {
    public UserConfigurationNotFoundException(String cip) { super(format("Configuration for user %s was not found", cip)); }
}
