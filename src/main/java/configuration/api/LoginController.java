package configuration.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class LoginController {

    @RequestMapping(value = Routes.LOGIN_ROUTE, method = GET)
    public ResponseEntity<Object> login() throws URISyntaxException {
        URI frontEnd = new URI("http://localhost:8080/home");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(frontEnd);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

    }
}
